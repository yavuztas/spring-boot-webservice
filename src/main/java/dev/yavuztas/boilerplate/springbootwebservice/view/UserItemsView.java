package dev.yavuztas.boilerplate.springbootwebservice.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.yavuztas.boilerplate.springbootwebservice.domain.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * View model class for userItems web request.
 * This is a design choice to not expose the domain model outside and also to keep our domain models clean.
 * Certainly, some other solutions might be considered like @{@link com.fasterxml.jackson.annotation.JsonView}
 * However, we prefer not to spread conversion logic over on domain models.
 *
 * @author Yavuz Tas
 */
public class UserItemsView {

    private final String username;

    @JsonProperty("item")
    private final List<ItemView> items = new ArrayList<>();

    public UserItemsView(String username, List<Item> items) {
        this.username = username;
        items.forEach(item -> this.items.add(new ItemView(item)));
    }

    public String getUsername() {
        return username;
    }

    public List<ItemView> getItems() {
        return items;
    }
}
