package com.dialltay.leveragess.product;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    List<Product> sellProducts(List<Product> products);
    List<Product> findAllProducts();
    List<Product> findAllByNameContains(String term);
    List<Product> findProducts(String term);
}


@Service
class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Filter products by the owner of the authenticated user.
     * -- #filterObject refers to an object in the list as parameters. In this case, it refers to the Product object.
     * -- #authentication refers to the authentication object of the current user. It provides par Spring Security.
     * * * * The collection instance you provide has to be mutable.
     *
     * @param products List of products to be sold
     * @return List of products that the authenticated user is allowed to sell
     */
    @PreFilter("filterObject.owner == authentication.name")
    @Override
    public List<Product> sellProducts(List<Product> products) {
        return products; // for demonstration purposes, we just return the filtered list.
    }

    /**
     * Filter the results list by the owner of the authenticated user.
     *
     * @return List of products that the authenticated user is allowed to see
     */
    @PostFilter("filterObject.owner == authentication.name")
    @Override
    public List<Product> findAllProducts() {

        return List.of(
                new Product("Product 1", "alphamar"),
                new Product("Product 2", "betamar"),
                new Product("Product 3", "alphamar")
        );
    }

    @Override
    public List<Product> findAllByNameContains(String term) {
        return productRepository.findAllByNameContains(term)
                .stream()
                .map(productEntity -> new Product(productEntity.getName(), productEntity.getOwner()))
                .toList();
    }

    @Override
    public List<Product> findProducts(String term) {
        return productRepository.findProducts(term)
                .stream()
                .map(productEntity -> new Product(productEntity.getName(), productEntity.getOwner()))
                .toList();
    }
}