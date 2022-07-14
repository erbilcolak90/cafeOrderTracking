package com.kofteciyusuf.app.businness.services.managers;

import com.kofteciyusuf.app.businness.services.ProductService;
import com.kofteciyusuf.app.core.*;
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

    /*
    TODO:
     find metodlarında orelsethrow lar eklenecek..tüm managerlarda
     classlara validasyonlar eklenecek ve bu validasyonların çalışması için de controller request bölümüne de @valid eklemek.
     classlarda nelerin valide edilip edilmeyeceğini tespit edeceğim.her classta en az 1 validasyon olmak zorunda
     isimlendirmeleri kontrolden geçireceğim.
     Crud işlemlerin hepsi tamamlanacak.
     HER ZAMAN MOST VALUEBLE PRODUCT a odaklan
     Her sorgu postmanda ekli şekilde olacak

     FIXME:

     */

    @Override
    public Result addProduct(Product product) {

        try {
            product.setCreateDate(new Date());
            product.setUpdateDate(new Date());
            product.setDeleted(false);
            //save product to database
            this.productRepository.save(product);
            return new Result(true, "Product added",null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result<Product> getProduct(String productId) {
        try {
            Product product = this.productRepository.findById(productId).orElseThrow();
            return new Result<Product>(true,"Product listed", product);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result<List<Product>> getAllProducts() {

        try {
            return new Result<List<Product>>(true,"Products listed", this.productRepository.findAll());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result<Page<Product>> pageableProductList(int pageNumber, int pageSize) {

        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "name"));
            return new Result<Page<Product>>(true,"Page(s) listed", this.productRepository.findAll(pageable));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result<Product> changeProductPrice(String productId, int productPrice) {
        try {
            Product product = this.productRepository.findById(productId).orElseThrow();
            product.setPrice(productPrice);
            product.setUpdateDate(new Date());
            //save product
            this.productRepository.save(product);
            return new Result<Product>(true,"Product price changed", product);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result deleteProduct(String productId) {

        try {
            Product product = this.productRepository.findById(productId).orElseThrow(IllegalArgumentException::new);
            product.setDeleted(true);
            product.setUpdateDate(new Date());
            //save product
            this.productRepository.save(product);
            return new Result(true,"Product deleted",null);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return null;
    }
}


