package dev.yavuztas.boilerplate.springbootwebservice.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.yavuztas.boilerplate.springbootwebservice.domain.Item;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * View model class for domain {@link Item}
 *
 * @author Yavuz Tas
 */
public class ItemView {

    private final String name;
    private final String game;
    private final LocalDate expirationDate;
    private final Long quantity;

    @JsonProperty("property")
    private final List<PropertyView> properties = new ArrayList<>();

    public ItemView(Item item) {
        this.name = item.getName();
        this.game = item.getGame();
        this.expirationDate = item.getExpirationDate();
        this.quantity = item.getQuantity();

        item.getProperties().forEach(property -> this.properties.add(new PropertyView(property)));
    }

    public String getName() {
        return name;
    }

    public String getGame() {
        return game;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public Long getQuantity() {
        return quantity;
    }

    public List<PropertyView> getProperties() {
        return properties;
    }
}
