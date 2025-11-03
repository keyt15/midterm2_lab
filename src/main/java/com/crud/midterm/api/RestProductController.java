package com.crud.midterm.api;

import com.crud.midterm.DTO.ProductDTO;
import com.crud.midterm.Model.Product;
import com.crud.midterm.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class RestProductController {

    private final ProductService service;

    public RestProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@Valid @RequestBody ProductDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable int id, @Valid @RequestBody ProductDTO dto) {
        Product existing = service.findById(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return service.update(existing, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        if (service.findById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        service.delete(id);
    }
}
