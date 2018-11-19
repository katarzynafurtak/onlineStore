package com.capgemini.onlineStore;


import com.capgemini.onlineStore.persistence.entity.CustomerEntity;
import com.capgemini.onlineStore.persistence.entity.ProductEntity;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductQueryTest {

    @PersistenceContext
    private EntityManager manager;

    @Test
    public void shouldFindProductByName() {

        //given
        ProductEntity productEntity = new ProductEntity();
        productEntity.setMarge(BigDecimal.valueOf(50));
        productEntity.setName("cytryna");
        productEntity.setPrice(BigDecimal.valueOf(1.34));
        productEntity.setWeight(BigDecimal.valueOf(0.30));
        manager.persist(productEntity);

        //when
        List<ProductEntity> result = manager.createNamedQuery("Product.findByName", ProductEntity.class)
                .setParameter("name", "cytryna")
                .getResultList();

        //then
        Assertions.assertThat(result).hasSize(1);
        Assertions.assertThat(result.stream().map(r -> r.getName()).findAny().get()).isEqualTo("cytryna");


    }
}
