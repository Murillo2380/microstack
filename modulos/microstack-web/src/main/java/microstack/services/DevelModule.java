package microstack.services;

import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Startup;
import org.apache.tapestry5.ioc.internal.services.ClasspathResourceSymbolProvider;
import org.apache.tapestry5.ioc.services.SymbolProvider;
import org.apache.tapestry5.ioc.services.SymbolSource;
import org.slf4j.Logger;

public class DevelModule {
    @Startup
    public void inicializacion(final Logger log) {
        log.info("************************************** init: TAPESTRY PERFIL DESARROLLO  ***************");
    }

    @Contribute(SymbolSource.class)
    public void doContributeSymbolSource(final OrderedConfiguration<SymbolProvider> configuration) {
     // Si queremos que spring use el mísmo hay que decírselo en su conf.
        configuration.add(
            "MicrostackApplicationSymbols",
            new ClasspathResourceSymbolProvider("/configuration-devel.properties"),
            "before:ApplicationDefaults");
    }
}
