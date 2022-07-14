package com.kofteciyusuf.app.businness.services;

import com.kofteciyusuf.app.core.Result;
import com.kofteciyusuf.app.entities.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Result addProduct(Product product);

    Result<Product> getProduct(String productId);

    Result<List<Product>> getAllProducts();

    Result<Product> changeProductPrice(String productId, int productPrice);

    Result deleteProduct(String productId);

    Result<Page<Product>> pageableProductList(int pageNumber, int pageSize);


}
