package com.crud.midterm.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class ProductDTO {

    @NotBlank(message = "Name required")
    private String name;

    @NotBlank(message = "Brand required")
    private String brand;

    @PositiveOrZero(message = "Price must be ≥ 0")
    private double price;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
