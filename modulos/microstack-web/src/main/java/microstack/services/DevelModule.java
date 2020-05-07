package microstack.services;

import org.apache.tapestry5.ioc.annotations.Startup;
import org.slf4j.Logger;

public class DevelModule {
    @Startup
    public void inicializacion(final Logger log) {
        log.info("************************************** init: TAPESTRY PERFIL DESARROLLO  ***************");
    }
}
