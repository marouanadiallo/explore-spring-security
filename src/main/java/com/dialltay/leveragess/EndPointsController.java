package com.dialltay.leveragess;

import com.dialltay.leveragess.product.Product;
import com.dialltay.leveragess.product.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EndPointsController {

    private final ProductService productService;

    public EndPointsController(ProductService productService) {
        this.productService = productService;
    }


    // curl -u alphamar:mar123 http://localhost:8080/hello
    @GetMapping("/hello")
    String sayHello() {
        return "Hello, World!";
    }

    // curl -u alphamar:mar123 http://localhost:8080/sell
    // curl -u betamar:mar321 http://localhost:8080/sell
    @GetMapping("/sell")
    List<Product> sellProducts() {

        List<Product> products = new ArrayList<>();
        products.add(new Product("Product 1", "alphamar"));
        products.add(new Product("Product 2", "betamar"));
        products.add(new Product("Product 3", "alphamar"));

        return productService.sellProducts(products);
    }

    // curl -u alphamar:mar123 http://localhost:8080/find
    // curl -u betamar:mar321 http://localhost:8080/find
    @GetMapping("/find")
    List<Product> findProducts() {
        return productService.findAllProducts();
    }

    // curl -u alphamar:mar123 http://localhost:8080/products/c
    // curl -u betamar:mar321 http://localhost:8080/products/c
    @GetMapping("/products/{term}")
    List<Product> findProductsByName(@PathVariable String term) {
        return productService.findAllByNameContains(term);
    }

    // curl -u alphamar:mar123 http://localhost:8080/products/search/c
    // curl -u betamar:mar321 http://localhost:8080/products/search/c
    @GetMapping("/products/search/{term}")
    List<Product> findProductsByName2(@PathVariable String term) {
        return productService.findProducts(term);
    }
}
