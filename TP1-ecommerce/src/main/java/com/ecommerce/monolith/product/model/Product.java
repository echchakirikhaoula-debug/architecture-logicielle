package com.ecommerce.monolith.product.model;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import com.ecommerce.monolith.product.model.Category;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private int stock;

    // ✅ AJOUTE ICI
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // getters & setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    // ✅ AJOUTE ICI
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
