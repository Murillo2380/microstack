package microstack.uno.pages;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.Renderable;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

public class IndexUno {
    private static final String ZN_SALUDO = "znSaludo";
    @Inject
    AjaxResponseRenderer ajaxResponseRenderer;
    @Inject
    Block blkSaludo;

    @OnEvent("saludar-es")
    void doSaludarES() {
        ajaxResponseRenderer.addRender(ZN_SALUDO, blkSaludo);
    }

    @OnEvent("saludar-gl")
    void doSaludarGL() {
        ajaxResponseRenderer.addRender(
            ZN_SALUDO,
            (Renderable) writer -> {
                writer.element("div", "class", "alert alert-info");
                writer.write("Bos días");
                writer.end();
            }
        );
    }

    @OnEvent("limpiar")
    void doLimpiar() {
        ajaxResponseRenderer.addRender(ZN_SALUDO, (Renderable) writer -> {});
    }

}
