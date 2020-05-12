package microstack.util.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.services.ComponentClassResolver;
import org.apache.tapestry5.services.LibraryMapping;

public class UtilModule {
    @Contribute(ComponentClassResolver.class)
    public void doContributeComponentClassResolver(Configuration<LibraryMapping> configuration) {
        String name = getClass().getPackage().getName();
        configuration.add(new LibraryMapping("util", name.substring(0, name.lastIndexOf('.'))));
    }
}
