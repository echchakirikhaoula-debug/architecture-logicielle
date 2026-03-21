package com.ecommerce.monolith.product.dto;

import com.ecommerce.monolith.product.model.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Category category;
}