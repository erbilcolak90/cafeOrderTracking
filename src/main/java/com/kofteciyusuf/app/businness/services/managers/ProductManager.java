package com.kofteciyusuf.app.businness.services.managers;

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
    public Product addProduct(Product product) {

        try {
            product.setCreateDate(new Date());
            product.setUpdateDate(new Date());
            product.setDeleted(false);
            //save product to database
            this.productRepository.save(product);
            return product;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }

    @Override
    public Product getProduct(String productId) {
        try {
            Product product = this.productRepository.findById(productId).orElseThrow();
            return product;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {

        try {
            return this.productRepository.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<Product> pagebleProductList(int number, int size) {

        try {
            Pageable pageable = PageRequest.of(number, size, Sort.by(Sort.Direction.DESC, "name"));
            return this.productRepository.findAll(pageable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Product changeProductPrice(String id, int price) {
        try {
            Product product = this.productRepository.findById(id).orElseThrow();
            product.setPrice(price);
            product.setUpdateDate(new Date());
            //save product
            this.productRepository.save(product);
            return product;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Product deleteProduct(String id) {

        try {
            Product product = this.productRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            product.setDeleted(true);
            product.setUpdateDate(new Date());
            //save product
            this.productRepository.save(product);
            return product;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}


