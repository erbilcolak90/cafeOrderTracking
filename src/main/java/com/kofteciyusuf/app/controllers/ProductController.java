package com.kofteciyusuf.app.controllers;

import com.kofteciyusuf.app.businness.services.ProductService;
import com.kofteciyusuf.app.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public Product addProduct(@Valid @RequestBody Product product) {
        return this.productService.addProduct(product);
    }

    @GetMapping("/getProduct")
    public Product getProduct(@Valid @RequestParam String productId){
        return this.productService.getProduct(productId);
    }

    @GetMapping("/getAllProducts")
    public List<Product> getProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/pagebleProductList")
    public Page<Product> pagebleProductList(@RequestParam int number, @RequestParam int size) {
        return this.productService.pagebleProductList(number, size);
    }

    @PostMapping("/changeProductPrice")
    public Product changeProductPrice(@Valid @RequestParam String id, @RequestParam int price) {
        return this.productService.changeProductPrice(id, price);
    }

    @DeleteMapping("/deleteProduct")
    public Product deleteProduct(@Valid @RequestParam String id) {
        return this.productService.deleteProduct(id);
    }




}
