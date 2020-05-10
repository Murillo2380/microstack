package util.tx;

import org.apache.tapestry5.plastic.MethodAdvice;
import org.apache.tapestry5.plastic.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.support.TransactionTemplate;

public class TransactionMethodAdvice implements MethodAdvice {
    private static final Logger log = LoggerFactory.getLogger(TransactionMethodAdvice.class);
    private final TransactionTemplate transactionTemplate;

    public TransactionMethodAdvice(PlatformTransactionManager txm) {
        this.transactionTemplate = new TransactionTemplate(txm);
    }

    @Override
    public void advise(MethodInvocation invocation) {
        try {
            transactionTemplate.execute(status -> invocation.proceed());
        } catch (UnexpectedRollbackException e) {
            // Se produce cuando hay una subtraccion marcada como rollback pero que no hace throw
            log.trace("", e);
        }
    }
}