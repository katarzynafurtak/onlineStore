package com.capgemini.onlineStore;

import com.capgemini.onlineStore.persistence.entity.ProductEntity;
import com.capgemini.onlineStore.persistence.repo.ProductRepo;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductTest {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ProductRepo productRepo;

//    @Test
//    public void optimisticLockingTest() {
//
//        //given
//        ProductEntity product = new ProductEntity();
//        product.setName("Pralka");
//        productRepo.save(product);
//
//        ProductEntity copy1 = productRepo.findAll().get(0);
//        ProductEntity copy2 = productRepo.findAll().get(0);
//
//        copy1.setName("Suszarka");
//        productRepo.update(copy1);
//
//        copy2.setName("Lokówka");
//        try{
//            productRepo.update(copy2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        List<ProductEntity> foundProducts = productRepo.findAll();
//        Assertions.assertThat(foundProducts).hasSize(1);
//        Assertions.assertThat(foundProducts.get(0).getName()).isEqualTo("Suszarka");
//
//
//    }
}