package microstack.pages;

import static microstack.jooq.Tables.CUSTOMERS;
import static microstack.jooq.Tables.EMPLOYEES;
import static microstack.jooq.Tables.ORDERS;

import java.sql.Timestamp;
import java.util.List;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.slf4j.Logger;

import util.tx.Tx;

public class Clientes {

    public static final class Cli {
        public Integer id;
        public String firstName;
        public String lastName;
    }

    public static final class Pedido {
        public Timestamp orderDate;
        public String empleado;
    }

    @Inject
    Logger log;
    @Inject
    DSLContext dsl;
    @Inject
    AjaxResponseRenderer ajaxResponseRenderer;
    @Inject
    ComponentResources resources;

    @Inject
    Block blkPedidos;

    @Property
    Cli cli;
    @Property
    Integer customerId;
    @Property
    Pedido pedido;

    @Tx
    public List<Cli> getClientes() {
        return dsl
            .select(
                CUSTOMERS.ID,
                CUSTOMERS.FIRST_NAME,
                CUSTOMERS.LAST_NAME)
            .from(CUSTOMERS)
            .fetchInto(Cli.class);
    }

    @OnEvent("pedidos")
    public void doPedidos(Integer customerId) {
        this.customerId = customerId;
        ajaxResponseRenderer.addRender("znPedidos", blkPedidos);
    }

    @Tx
    public List<Pedido> getPedidos() {
        return dsl
            .select(
                ORDERS.ID,
                ORDERS.ORDER_DATE,
                EMPLOYEES.FIRST_NAME.as("empleado"))
            .from(ORDERS)
            .join(EMPLOYEES)
                .on(EMPLOYEES.ID.eq(ORDERS.EMPLOYEE_ID))
            .where(ORDERS.CUSTOMER_ID.eq(customerId))
            .orderBy(ORDERS.ORDER_DATE.desc())
            .fetchInto(Pedido.class);
    }

    @Tx
    public String getNombreCliente() {
        return dsl
            .select(
                DSL.concat(
                    CUSTOMERS.FIRST_NAME,
                    DSL.val(" "),
                    CUSTOMERS.LAST_NAME
                ))
            .from(CUSTOMERS)
            .where(CUSTOMERS.ID.eq(customerId))
            .fetchOne(0, String.class);
    }

}
