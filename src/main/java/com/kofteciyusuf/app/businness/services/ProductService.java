package com.kofteciyusuf.app.businness.services;

import com.kofteciyusuf.app.core.DataResult;
import com.kofteciyusuf.app.core.Result;
import com.kofteciyusuf.app.entities.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Result addProduct(Product product);
    DataResult<Product> getProduct(String productId);
    DataResult<List<Product>> getAllProducts();

    DataResult<Product> changeProductPrice(String productId,int productPrice);

    Result deleteProduct(String productId);

    DataResult<Page<Product>> pageableProductList(int pageNumber,int pageSize);




}
