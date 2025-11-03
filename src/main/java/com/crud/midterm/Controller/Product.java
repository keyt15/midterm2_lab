package com.crud.midterm.Controller;

import com.crud.midterm.DTO.ProductDTO;
import com.crud.midterm.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class Product {

    private final ProductService service;

    public Product(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", service.findAll());
        return "products/list";
    }

    @GetMapping("/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new ProductDTO());
        return "products/new";
    }


    @PostMapping
    public String create(@Valid @ModelAttribute("product") ProductDTO dto,
                         RedirectAttributes ra) {

        service.save(dto);
        ra.addFlashAttribute("success", "Product created successfully!");
        return "redirect:/products";
    }


    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable int id, Model model) {
        com.crud.midterm.Model.Product product = service.findById(id);

        if (product == null) {
            return "redirect:/products";
        }

        model.addAttribute("product", product);
        return "products/edit";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable int id,
                         @Valid @ModelAttribute("product") ProductDTO dto,
                         RedirectAttributes ra) {

        com.crud.midterm.Model.Product existing = service.findById(id);

        if (existing == null) {
            return "redirect:/products";
        }

        service.update(existing, dto);
        ra.addFlashAttribute("success", "Product updated successfully!");
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes ra) {
        service.delete(id);
        ra.addFlashAttribute("success", "Product deleted successfully!");
        return "redirect:/products";
    }
}
