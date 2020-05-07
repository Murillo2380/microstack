package microstack.services;

import org.apache.tapestry5.ioc.annotations.ImportModule;

import uno.services.UnoModule;
import util.services.UtilModule;

@ImportModule({
    UnoModule.class,
    UtilModule.class,
})
public class AppModule {

}
