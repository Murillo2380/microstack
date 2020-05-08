package uno.pages;

import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

public class CanvasUno {
    @Inject
    JavaScriptSupport javaScriptSupport;

    @AfterRender
    void doAfterRender() {
        javaScriptSupport
            .require("uno/Canvas")
            .invoke("default");
        javaScriptSupport
            .require("uno/Canvas")
            .invoke("otro");
    }
}
