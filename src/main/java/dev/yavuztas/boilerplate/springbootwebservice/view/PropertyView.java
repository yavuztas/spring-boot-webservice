package dev.yavuztas.boilerplate.springbootwebservice.view;

import dev.yavuztas.boilerplate.springbootwebservice.domain.Property;

/**
 * View model class for domain {@link Property}
 *
 * @author Yavuz Tas
 */
public class PropertyView {

    private final String name;
    private final String value;

    public PropertyView(Property property) {
        this.name = property.getName();
        this.value = property.getValue();
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

}
