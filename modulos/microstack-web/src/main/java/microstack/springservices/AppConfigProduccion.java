package microstack.springservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import microstack.services.PersonalizacionSpring;

@Configuration
@Import({AppConfig.class})
@Profile(PersonalizacionSpring.PERFIL_SPRING_PRODUCCION)
@PropertySource("classpath:/configuration.properties")
public class AppConfigProduccion {
    private static final Logger log = LoggerFactory.getLogger(AppConfigProduccion.class);

    AppConfigProduccion() {
        log.info("************************************** SPRING PERFIL PRODUCCION  ***************");
    }

}