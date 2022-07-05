package com.kofteciyusuf.app.businness.services;

import com.kofteciyusuf.app.entities.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);

    List<Product> getProducts();

    Product changeProductPrice(String id,int price);

    Product deleteProduct(String id);
}
