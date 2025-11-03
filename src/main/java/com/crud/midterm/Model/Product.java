package com.crud.midterm.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name required")
    private String name;

    private String description;
    private double price;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}
}
