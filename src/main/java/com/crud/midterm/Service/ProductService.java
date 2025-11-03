package com.crud.midterm.Service;

import com.crud.midterm.DTO.ProductDTO;
import com.crud.midterm.Model.Product;
import com.crud.midterm.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> findAll() { return repo.findAll(); }

    public Product findById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product save(ProductDTO dto) {
        Product p = new Product();
        updateFields(p, dto);
        return repo.save(p);
    }

    public Product update(Product existing, ProductDTO dto) {
        updateFields(existing, dto);
        return repo.save(existing);
    }

    private void updateFields(Product p, ProductDTO dto) {
        p.setName(dto.getName());
        p.setBrand(dto.getBrand());
        p.setPrice(dto.getPrice());
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
