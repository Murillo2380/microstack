package microstack.microstack.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;

public class Layout {
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    String title;

    @Inject
    @Symbol(SymbolConstants.PRODUCTION_MODE)
    @Property(write = false)
    boolean productionMode;
}
