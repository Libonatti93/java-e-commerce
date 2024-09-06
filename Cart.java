package com.example.ecommerce.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Product> products = new ArrayList<>();
    private BigDecimal total;

    public Cart() {
        this.total = BigDecimal.ZERO;
    }

    public List<Product> getProducts() {
        return products;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void addProduct(Product product) {
        this.products.add(product);
        this.total = this.total.add(product.getPrice());
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        this.total = this.total.subtract(product.getPrice());
    }
}
