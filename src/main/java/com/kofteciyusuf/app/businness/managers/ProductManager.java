package com.kofteciyusuf.app.businness.managers;

import com.kofteciyusuf.app.businness.services.ProductService;
import com.kofteciyusuf.app.entities.Product;
import com.kofteciyusuf.app.repositories.ProductRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        try{
            product.setCreateDate(new Date());
            product.setUpdateDate(new Date());
            product.setDeleted(false);

            this.productRepository.save(product);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return product;

    }

    @Override
    public List<Product> getProducts() {

        try{
            List<Product> productList = this.productRepository.findAll();
        }catch (Exception ex){
                ex.printStackTrace();
        }
        return this.productRepository.findAll();
    }

    @Override
    public Product changeProductPrice(String id,int price) {
        Product product = this.productRepository.findById(id).orElseGet(Product::new);

        try{
            product.setPrice(price);
            product.setUpdateDate(new Date());

            this.productRepository.save(product);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return product;
    }

    @Override
    public Product deleteProduct(String id) {
        Product product = this.productRepository.findById(id).orElseGet(Product::new);

        try{
            product.setDeleted(true);
            product.setUpdateDate(new Date());

            this.productRepository.save(product);
        }catch (Exception ex){
            if(this.productRepository.findById(id) == null){
               ex.printStackTrace();
            }
        }
        return product;
    }

    @Override
    public Page<Product> getProductList(int number, int size) {

        Pageable pageable = PageRequest.of(number,size,Sort.by(Sort.Direction.DESC,"name"));
        Page<Product> productPage = this.productRepository.findAll(pageable);

        return  productPage;
    }
}


