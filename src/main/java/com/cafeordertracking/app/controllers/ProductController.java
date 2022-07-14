package com.cafeordertracking.app.controllers;

import com.cafeordertracking.app.businness.services.ProductService;
import com.cafeordertracking.app.core.Result;
import com.cafeordertracking.app.entities.Product;
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
    public Result addProduct(@Valid @RequestBody Product product) {
        return this.productService.addProduct(product);
    }

    @PostMapping("/changeProductPrice")
    public Result<Product> changeProductPrice(@Valid @RequestParam String productId, @RequestParam int productPrice) {
        return this.productService.changeProductPrice(productId, productPrice);
    }


    @GetMapping("/getProduct")
    public Result<Product> getProduct(@Valid @RequestParam String productId){
        return this.productService.getProduct(productId);
    }

    @GetMapping("/getAllProducts")
    public Result<List<Product>> getProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/pageableProductList")
    public Result<Page<Product>> pageableProductList(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return this.productService.pageableProductList(pageNumber, pageSize);
    }

    @DeleteMapping("/deleteProduct")
    public Result deleteProduct(@Valid @RequestParam String productId) {
        return this.productService.deleteProduct(productId);
    }




}
