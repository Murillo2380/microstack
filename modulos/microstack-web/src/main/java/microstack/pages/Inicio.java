package microstack.pages;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

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
}
