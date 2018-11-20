package com.capgemini.onlineStore;

import com.capgemini.onlineStore.databuilder.CommonTestDataBuilder;
import com.capgemini.onlineStore.persistence.datatype.Status;
import com.capgemini.onlineStore.persistence.entity.CustomerEntity;
import com.capgemini.onlineStore.persistence.entity.PurchaseEntity;
import com.capgemini.onlineStore.persistence.repo.CustomerRepo;
import com.capgemini.onlineStore.persistence.repo.PurchaseRepo;
import com.capgemini.onlineStore.validator.Validator;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PurchaseQueryTest {

    private CustomerEntity customer;
    private PurchaseEntity purchase1;
    private PurchaseEntity purchase2;

    @Autowired
    private PurchaseRepo purchaseRepo;

    @Autowired
    private CustomerRepo customerRepo;


    @Test
    public void shouldGetAmountOfCompletedPurchasesByCustomer() {

        //given
        purchase1 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withCustomer(customer)
                .withStatus(Status.COMPLETED)
                .build();
        purchaseRepo.save(purchase1);

        purchase2 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withCustomer(customer)
                .withStatus(Status.IN_DELIVERY)
                .build();
        purchaseRepo.save(purchase2);

        Set<PurchaseEntity> purchases = new HashSet<>();
        purchases.add(purchase1);
        purchases.add(purchase2);

        customer = CommonTestDataBuilder.commonCustomerBuilder()
                .withPurchases(purchases)
                .build();
        customerRepo.save(customer);

        //when
        long result = purchaseRepo.getAmountOfCompletedPurchasesByCustomer(customer);

        //then
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    public void shouldSaveCustomer() {

        //given

        CustomerEntity customer = CommonTestDataBuilder.commonCustomerBuilder()
                .withLastName("Filipiak")
                .build();

        CustomerEntity customerEntity = customerRepo.save(customer);
        CustomerEntity found = customerRepo.findOne(customerEntity.getId());
        //then
        Assertions.assertThat(found).isNotNull();
    }
}
