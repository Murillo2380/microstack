package microstack.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.ImportModule;
import org.apache.tapestry5.ioc.services.ApplicationDefaults;
import org.apache.tapestry5.ioc.services.SymbolProvider;
import org.apache.tapestry5.services.transform.ComponentClassTransformWorker2;
import org.springframework.transaction.PlatformTransactionManager;

import uno.services.UnoModule;
import util.services.UtilModule;
import util.tx.TransactionMethodAdvice;
import util.tx.TransactionWorker;

@ImportModule({
    PersonalizacionSpring.class,
    UnoModule.class,
    UtilModule.class,
})
public class AppModule {
    @Contribute(SymbolProvider.class)
    @ApplicationDefaults
    public void doContributeApplicationDefaults(MappedConfiguration<String, Object> configuration) {
        configuration.add(SymbolConstants.HMAC_PASSPHRASE, "uLrS7aeYGCP6TtDu2zeVS5zdbfgYSwS3");
        // El bootstrap no separa los botones si se eliminan los espacios
        configuration.add(SymbolConstants.COMPRESS_WHITESPACE, false);
        configuration.add(SymbolConstants.OMIT_GENERATOR_META, true);
        configuration.add(SymbolConstants.MINIFICATION_ENABLED, false);
        configuration.add(SymbolConstants.ENABLE_PAGELOADING_MASK, false);
        configuration.add(SymbolConstants.JAVASCRIPT_INFRASTRUCTURE_PROVIDER, "jquery");
        configuration.add(SymbolConstants.EXCEPTION_REPORTS_DIR, "target/exceptions");
        configuration.add(SymbolConstants.SESSION_LOCKING_ENABLED, false);
        configuration.add(SymbolConstants.CLUSTERED_SESSIONS, false);
    }

    /**
     * Para usar anotaciones propias como @Tx
     * @param workers
     * @param txm
     */
    @Contribute(ComponentClassTransformWorker2.class)
    public static void provideWorkers(
            OrderedConfiguration<ComponentClassTransformWorker2> workers,
            PlatformTransactionManager txm) {
        TransactionMethodAdvice advisor = new TransactionMethodAdvice(txm);
        workers.add(TransactionWorker.class.getSimpleName(), new TransactionWorker(advisor));
    }


}
