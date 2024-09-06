package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "new_product";
    }

    @PostMapping
    public String createProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id)
                                           .orElseThrow(() -> new IllegalArgumentException("Produto inválido: " + id));
        model.addAttribute("product", product);
        return "edit_product";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        Product existingProduct = productRepository.findById(id)
                                                   .orElseThrow(() -> new IllegalArgumentException("Produto inválido: " + id));
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        productRepository.save(existingProduct);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                                           .orElseThrow(() -> new IllegalArgumentException("Produto inválido: " + id));
        productRepository.delete(product);
        return "redirect:/products";
    }
}
