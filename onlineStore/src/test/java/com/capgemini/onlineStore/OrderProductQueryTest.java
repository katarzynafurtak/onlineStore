package com.capgemini.onlineStore;

import com.capgemini.onlineStore.persistence.datatype.Status;
import com.capgemini.onlineStore.persistence.entity.OrderProductEntity;
import com.capgemini.onlineStore.persistence.entity.ProductEntity;
import com.capgemini.onlineStore.persistence.entity.TransactionEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderProductQueryTest {

    @PersistenceContext
    private EntityManager manager;

    @Test
    public void shouldCalculateTotalCostOfTransaction() {

        //given
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setStatus(Status.COMPLETED);
        manager.persist(transactionEntity);

        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setName("Apple MacBook");
        productEntity1.setPrice(BigDecimal.valueOf(9000));
        productEntity1.setMarge(BigDecimal.valueOf(80));
        manager.persist(productEntity1);

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setName("Telewizor LG");
        productEntity2.setPrice(BigDecimal.valueOf(3000));
        productEntity2.setMarge(BigDecimal.valueOf(50));
        manager.persist(productEntity2);

        OrderProductEntity orderProductEntity1 = new OrderProductEntity();
        orderProductEntity1.setTransaction(transactionEntity);
        orderProductEntity1.setProduct(productEntity1);
        orderProductEntity1.setAmount(1);
        manager.persist(orderProductEntity1);

        OrderProductEntity orderProductEntity2 = new OrderProductEntity();
        orderProductEntity2.setTransaction(transactionEntity);
        orderProductEntity2.setProduct(productEntity2);
        orderProductEntity2.setAmount(2);
        manager.persist(orderProductEntity2);

        double expectedResult = ((orderProductEntity1.getAmount()* orderProductEntity1.getProduct().getPrice().doubleValue())/(1-(0.01*orderProductEntity1.getProduct().getMarge().doubleValue()) +
                (orderProductEntity2.getAmount()* orderProductEntity2.getProduct().getPrice().doubleValue())/(1-(0.01*orderProductEntity2.getProduct().getMarge().doubleValue()))));

//        //when
//        double totalCost = manager.ca().calculateTotalCost(trans1.getId());
//
//        //then
//        Assertions.assertThat(expectedResult).isEqualTo(totalCost);
//    }
}
}
