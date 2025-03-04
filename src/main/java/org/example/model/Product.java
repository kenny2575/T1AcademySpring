package org.example.model;

import jakarta.persistence.*;
import org.example.enums.ProductType;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Product() {}

    public Product(ProductType productType, User user) {
        this.productType = productType;
        this.user = user;
    }
    
}


