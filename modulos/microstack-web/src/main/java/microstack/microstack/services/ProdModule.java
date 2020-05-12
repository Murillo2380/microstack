package microstack.microstack.services;

import org.apache.tapestry5.ioc.annotations.Startup;
import org.slf4j.Logger;

public class ProdModule {
    @Startup
    public void inicializacion(final Logger log) {
        log.info("************************************** init: TAPESTRY PERFIL PRODUCCIÓN  ***************");
    }
}
