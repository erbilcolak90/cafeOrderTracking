package com.kofteciyusuf.app.businness.managers;

import com.kofteciyusuf.app.businness.services.ProductService;
import com.kofteciyusuf.app.entities.Product;
import com.kofteciyusuf.app.repositories.ProductRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Service
public class ProductManager implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {

        product.setCreateDate(new Date());
        product.setUpdateDate(new Date());
        product.setDeleted(false);

        this.productRepository.save(product);

        return product;
    }

    @Override
    public List<Product> getProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product changeProductPrice(String id,int price) {
        Product product = this.productRepository.findById(id).orElseGet(Product::new);
        product.setPrice(price);
        product.setUpdateDate(new Date());

        this.productRepository.save(product);
        return product;
    }

    @Override
    public Product deleteProduct(String id) {
        Product product = this.productRepository.findById(id).orElseGet(Product::new);
        product.setDeleted(true);
        product.setUpdateDate(new Date());

        this.productRepository.save(product);

        return product;
    }
}
