package dev.yavuztas.boilerplate.springbootwebservice.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class Property {

    @Id
    @Column(name = "idproperty")
    private Long id;

    private String name;

    private String value;

    @JoinColumn(name = "iditem")
    @ManyToOne
    private Item item;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return Objects.equals(name, property.name) &&
                Objects.equals(value, property.value) &&
                Objects.equals(item, property.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, item);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Property.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("value='" + value + "'")
                .toString();
    }
}
