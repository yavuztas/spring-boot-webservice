package dev.yavuztas.boilerplate.springbootwebservice.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

@Entity
public class Item {

    @Id
    @Column(name = "iditem")
    private Long id;

    private String name;

    private String game;

    @Column(name = "expirationdate")
    private LocalDate expirationDate;

    private Long quantity;

    @JoinColumn(name = "iduser")
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "item")
    private Set<Property> properties = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Property> getProperties() {
        return properties;
    }

    public void setProperties(Set<Property> properties) {
        this.properties = properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) &&
                Objects.equals(game, item.game) &&
                Objects.equals(expirationDate, item.expirationDate) &&
                Objects.equals(quantity, item.quantity) &&
                Objects.equals(user, item.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, game, expirationDate, quantity, user);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Item.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("game='" + game + "'")
                .add("expirationDate=" + expirationDate)
                .add("quantity=" + quantity)
                .toString();
    }
}
