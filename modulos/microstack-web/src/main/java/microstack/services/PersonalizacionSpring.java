package microstack.services;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.spring.ApplicationContextCustomizer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.ConfigurableWebApplicationContext;

public class PersonalizacionSpring {
    // Estas tres deberían ser excluyentes
    public static final String PERFIL_SPRING_PRODUCCION = "production";
    public static final String PERFIL_SPRING_PREPROD = "preprod";
    public static final String PERFIL_SPRING_DESARROLLO = "development";

    /**
     * Personalización del contexto de Spring
     * @param config
     * @param productionMode
     */
    public void contributeApplicationContextCustomizer(
        OrderedConfiguration<ApplicationContextCustomizer> config,
        @Symbol(SymbolConstants.PRODUCTION_MODE) boolean productionMode) {

        String executionMode = System.getProperty(SymbolConstants.EXECUTION_MODE, PERFIL_SPRING_PRODUCCION);
        List<String> perfilesTapestry = Arrays.asList(StringUtils.split(executionMode, ","));

        if (productionMode && !perfilesTapestry.contains(PERFIL_SPRING_PRODUCCION)) {
            // Si estamos en productionMode debemos activar el perfil de producción.
            perfilesTapestry.add(PERFIL_SPRING_PRODUCCION);
        }

        ApplicationContextCustomizer customizer = (ServletContext servletContext, ConfigurableWebApplicationContext appContext) -> {
            ConfigurableEnvironment environment = appContext.getEnvironment();
            List<String> perfilesSpring = Arrays.asList(environment.getActiveProfiles());
            perfilesTapestry
                .stream()
                .filter(perfilTapestry -> !perfilesSpring.contains(perfilTapestry))
                .forEach(appContext.getEnvironment()::addActiveProfile);
        };

        config.add("MicrostackContextCustomizer", customizer);
    }

}

