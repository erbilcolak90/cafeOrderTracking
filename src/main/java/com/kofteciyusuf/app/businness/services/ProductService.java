package com.kofteciyusuf.app.businness.services;

import com.kofteciyusuf.app.entities.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);

    List<Product> getProducts();

    Product changeProductPrice(String id,int price);

    Product deleteProduct(String id);

    Page<Product> getProductList(int number,int size);




}
