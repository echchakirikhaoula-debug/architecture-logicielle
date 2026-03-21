
package com.ecommerce.monolith.product.service;

import com.ecommerce.monolith.product.model.Product;
import com.ecommerce.monolith.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    // Injection par constructeur
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // ✅ CREATE
    public Product save(Product product) {
        return productRepository.save(product);
    }

    // ✅ READ ALL
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    // ✅ READ BY ID
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // ✅ UPDATE
    public Product update(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());


        return productRepository.save(product);
    }

    // ✅ DELETE
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
