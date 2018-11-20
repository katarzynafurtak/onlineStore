package com.capgemini.onlineStore;

import com.capgemini.onlineStore.databuilder.CommonTestDataBuilder;
import com.capgemini.onlineStore.persistence.datatype.Status;
import com.capgemini.onlineStore.persistence.entity.CustomerEntity;
import com.capgemini.onlineStore.persistence.entity.PurchaseEntity;
import com.capgemini.onlineStore.persistence.repo.CustomerRepository;
import com.capgemini.onlineStore.persistence.repo.PurchaseRepo;
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
    private PurchaseEntity savedPurchase1;
    private PurchaseEntity savedPurchase2;

    @Autowired
    private PurchaseRepo purchaseRepo;

    @Autowired
    private CustomerRepository customerRepository;


    @Test
    public void shouldGetAmountOfCompletedPurchasesByCustomer() {

        //given
        PurchaseEntity purchase1 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.COMPLETED)
                .build();
        savedPurchase1 = purchaseRepo.save(purchase1);

        PurchaseEntity purchase2 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.IN_DELIVERY)
                .build();
        savedPurchase2 = purchaseRepo.save(purchase2);

        Set<PurchaseEntity> purchases = new HashSet<>();
        purchases.add(savedPurchase1);
        purchases.add(savedPurchase2);

        CustomerEntity customer = CommonTestDataBuilder.commonCustomerBuilder()
                .withPurchases(purchases)
                .build();
        CustomerEntity customerEntity = customerRepository.save(customer);

        Iterable<CustomerEntity> foundCustomers = customerRepository.findAll();
        Iterable<PurchaseEntity> foundPuchases = purchaseRepo.findAll();


        //when
        long result = customerRepository.cntAmountOfCompletedPurchases(customerEntity.getId());

        //then
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    public void shouldSaveCustomer() {

        //given

        CustomerEntity customer = CommonTestDataBuilder.commonCustomerBuilder()
                .withLastName("Filipiak")
                .build();

        CustomerEntity customerEntity = customerRepository.save(customer);
        CustomerEntity found = customerRepository.findOne(customerEntity.getId());
        //then
        Assertions.assertThat(found).isEqualTo(customerEntity);
    }
}
