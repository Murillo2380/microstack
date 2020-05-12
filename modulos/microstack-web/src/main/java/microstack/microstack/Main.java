package microstack.microstack;

import java.nio.file.Paths;
import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.apache.tapestry5.spring.TapestrySpringFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import microstack.microstack.services.AppModule;
import microstack.microstack.services.DevelModule;
import microstack.microstack.services.ProdModule;

public class Main {
    private static final String CONTEXT_PATH = "/";
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        try {
            int httpPort = Integer.parseInt(System.getProperty("httpPort", "8080"));
            Server server = new Server(httpPort);
            WebAppContext webapp = crearWebapp(CONTEXT_PATH, server);
            // A WebAppContext is a ContextHandler as well so it needs to be set to
            // the server so it is aware of where to send the appropriate requests.
            server.setHandler(webapp);
            server.start();

            // El tiempo máximo de sesión tiene que ajustarse después de arrancar el servidor
            webapp
                .getSessionHandler()
                .setMaxInactiveInterval(2 * 60 * 60); // En segundos => 2 horas

            if (!webapp.isAvailable()) {
                log.error("La aplicación no arrancó correctamente", webapp.getUnavailableException());
                server.stop();
            }

            if (log.isTraceEnabled()) {
                server.dumpStdErr();
            }

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    log.info("Exiting application (shutdown hook)");
                }
            });

            // The use of server.join() the will make the current thread join and
            // wait until the server is done executing.
            // See http://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html#join()
            server.join();
        } catch (Exception e) {
            log.error("Ha ocurrido un problema, y se ha finalizado la aplicación", e);
            System.exit(-1);
        }
    }

    /**
     * Crear una webapp en Jetty
     * Refrerencias: https://www.eclipse.org/jetty/documentation/current/session-management.html
     *
     * @param contextPath El context path pe: /subtel
     * @param server      El jetty server
     * @return            Una contexto de webapp de jetty
     */
    private WebAppContext crearWebapp(String contextPath, Server server) {
        boolean esDesarrolloLocal = Paths.get("src", "main","webapp").toFile().exists();
        WebAppContext webapp = new WebAppContext();
        webapp.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
        webapp.setServer(server);
        webapp.setContextPath(contextPath);
        webapp.setBaseResource(
            Resource.newResource(esDesarrolloLocal
                ? Paths.get("src", "main","webapp").toFile()   // Desarrollo local
                : Paths.get("web").toFile()                    // Publicado en un servidor (el .zip)
            )
        );

        // Añadir el filtro de tapestry
        FilterHolder filterHolder = new FilterHolder(TapestrySpringFilter.class);
        filterHolder.setName(getFilterName(AppModule.class));
        webapp.addFilter(filterHolder, "/*", EnumSet.of(DispatcherType.REQUEST));

        // Para arracar con -Dtapestry.execution-mode=dev|production por defecto en T es production
        webapp.setInitParameter("tapestry.app-package", getAppPackage(AppModule.class));
        webapp.setInitParameter("tapestry.development-modules", DevelModule.class.getName());
        webapp.setInitParameter("tapestry.production-modules", ProdModule.class.getName());
        webapp.setServer(server);
        return webapp;
    }

    private String getFilterName(Class<?> claseModuloComun) {
        String simpleName = claseModuloComun.getSimpleName();
        return simpleName.substring(0, simpleName.indexOf("Module")).toLowerCase();
    }

    private String getAppPackage(Class<?> moduleClass) {
        String pkgname = moduleClass.getPackage().getName();
        return pkgname.substring(0, pkgname.lastIndexOf('.'));
    }

}
