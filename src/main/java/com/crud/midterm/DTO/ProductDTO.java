package com.crud.midterm.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class ProductDTO {

    @NotBlank(message = "Name required")
    private String name;

    private String description;

    @PositiveOrZero(message = "Price must be ≥ 0")
    private double price;

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}
}




