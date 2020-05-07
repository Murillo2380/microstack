package uno.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.services.ComponentClassResolver;
import org.apache.tapestry5.services.LibraryMapping;

public class UnoModule {
    @Contribute(ComponentClassResolver.class)
    public void doContributeComponentClassResolver(Configuration<LibraryMapping> configuration) {
        configuration.add(new LibraryMapping("uno", "uno"));
    }
}
