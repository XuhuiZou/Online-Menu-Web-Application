package com.onlinemenu.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "menu_item")
public class MenuItem {

    public enum Category {
        Appetizers, Entrees, Desserts, Drinks;

        @Override
        public String toString() {
            return this == Entrees ? "Entrées" : name();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    public MenuItem() {
    }

    public MenuItem(String name, Category category, String description, BigDecimal price) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MenuItem{id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price + '}';
    }
}
