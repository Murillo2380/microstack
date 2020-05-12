package microstack.microstack.springservices;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import microstack.microstack.services.PersonalizacionSpring;

@Configuration
@Import({AppConfig.class})
@Profile(PersonalizacionSpring.PERFIL_SPRING_DESARROLLO)
@PropertySource("classpath:/configuration-devel.properties")
public class AppConfigDesarrollo {
    public static final String PRUEBAS_LOGGER = AppConfigDesarrollo.class.getName() + "-pruebas";
    private static final Logger log = LoggerFactory.getLogger(AppConfigDesarrollo.class);

    AppConfigDesarrollo() {
        log.info("************************************** SPRING PERFIL DESARROLLO  ***************");
    }

}