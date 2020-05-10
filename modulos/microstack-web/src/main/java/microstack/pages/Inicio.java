package microstack.pages;

import java.time.LocalDate;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import uno.pages.CanvasUno;
import uno.pages.FloresUno;
import uno.pages.IndexUno;

public class Inicio {
    @Inject
    PageRenderLinkSource renderLinkSource;

    /**
     * Hacemos los enlaces de esta manera para permitir refactorizar de forma
     * segura.
     * @return Un enlace una otra página
     */
    public Link getInicioUnoPage() {
        return renderLinkSource.createPageRenderLink(IndexUno.class);
    }

    public Link getCanvasUnoPage() {
        return renderLinkSource.createPageRenderLink(CanvasUno.class);
    }

    public Link getFloresUnoPage() {
        return renderLinkSource.createPageRenderLink(FloresUno.class);
    }

    public Link getClientesPage() {
        return renderLinkSource.createPageRenderLink(Clientes.class);
    }

    public LocalDate getHoy() {
        return LocalDate.now();
    }
}
