package com.capgemini.onlineStore;

import com.capgemini.onlineStore.databuilder.CommonTestDataBuilder;
import com.capgemini.onlineStore.persistence.datatype.Status;
import com.capgemini.onlineStore.persistence.entity.CustomerEntity;
import com.capgemini.onlineStore.persistence.entity.OrderProductEntity;
import com.capgemini.onlineStore.persistence.entity.ProductEntity;
import com.capgemini.onlineStore.persistence.entity.PurchaseEntity;
import com.capgemini.onlineStore.persistence.repo.CustomerRepository;
import com.capgemini.onlineStore.persistence.repo.OrderProductRepository;
import com.capgemini.onlineStore.persistence.repo.ProductRepository;
import com.capgemini.onlineStore.persistence.repo.PurchaseRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PurchaseQueryTest {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;


    @Test
    public void shouldCountAmountOfCompletedPurchasesByCustomer() {

        //given
        PurchaseEntity purchase1 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.COMPLETED).build();
        PurchaseEntity savedPurchase1 = purchaseRepository.save(purchase1);

        PurchaseEntity purchase2 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.IN_PROGRESS).build();
        PurchaseEntity savedPurchase2 = purchaseRepository.save(purchase2);

        Set<PurchaseEntity> purchases = new HashSet<>();
        purchases.add(savedPurchase1);
        purchases.add(savedPurchase2);

        CustomerEntity customer = CommonTestDataBuilder.commonCustomerBuilder()
                .withPurchases(purchases).build();
        CustomerEntity savedCustomer = customerRepository.save(customer);

        //when
        long result = customerRepository.cntAmountOfCompletedPurchases(savedCustomer.getId());

        //then
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    public void shouldNotCountAmountOfCompletedPurchasesByCustomer() {

        //given
        PurchaseEntity purchase = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.AWAITING_PAYMENT).build();
        PurchaseEntity savedPurchase = purchaseRepository.save(purchase);

        Set<PurchaseEntity> purchases = new HashSet<>();
        purchases.add(savedPurchase);

        CustomerEntity customer = CommonTestDataBuilder.commonCustomerBuilder()
                .withPurchases(purchases).build();
        CustomerEntity savedCustomer = customerRepository.save(customer);

        //when
        long result = customerRepository.cntAmountOfCompletedPurchases(savedCustomer.getId());

        //then
        Assertions.assertThat(result).isEqualTo(0);
    }

    @Test
    public void shouldSaveCustomer() {

        //given
        CustomerEntity customer = CommonTestDataBuilder.commonCustomerBuilder()
                .withLastName("Filipiak").build();
        CustomerEntity savedCustomer = customerRepository.save(customer);

        //when
        CustomerEntity found = customerRepository.findOne(savedCustomer.getId());

        //then
        Assertions.assertThat(found).isEqualTo(savedCustomer);
    }

    @Test
    public void shouldFindListOfNamesAndAmountOfProductsWithStatusInProgressPurchase() {

        //given
        ProductEntity product1 = CommonTestDataBuilder.commonProductBuilder()
                .withName("Telewizor").build();
        ProductEntity savedProduct1 = productRepository.save(product1);

        ProductEntity product2 = CommonTestDataBuilder.commonProductBuilder()
                .withName("Pralka").build();
        ProductEntity savedProduct2 = productRepository.save(product2);

        ProductEntity product3 = CommonTestDataBuilder.commonProductBuilder()
                .withName("Lodówka").build();
        ProductEntity savedProduct3 = productRepository.save(product3);

        OrderProductEntity order1 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(5)
                .withProduct(product1).build();
        OrderProductEntity savedOrder1 = orderProductRepository.save(order1);

        OrderProductEntity order2 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(2)
                .withProduct(product2).build();
        OrderProductEntity savedOrder2 = orderProductRepository.save(order2);

        OrderProductEntity order3 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(1)
                .withProduct(product3).build();
        OrderProductEntity savedOrder3 = orderProductRepository.save(order3);

        PurchaseEntity purchase1 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.IN_PROGRESS)
                .withOrder(savedOrder1)
                .withOrder(savedOrder2).build();
        PurchaseEntity savedPurchase1 = purchaseRepository.save(purchase1);

        PurchaseEntity purchase2 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.IN_PROGRESS)
                .withOrder(savedOrder3).build();
        PurchaseEntity savedPurchase2 = purchaseRepository.save(purchase2);

        PurchaseEntity purchase3 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.CANCELLED)
                .withOrder(savedOrder1)
                .withOrder(savedOrder2).build();
        PurchaseEntity savedPurchase3 = purchaseRepository.save(purchase3);

        //when

        //then


    }

    @Test
    public void shouldCalculateTotalCostOfPurchaseByCustomerAndStatus() {

        //given
        ProductEntity product1 = CommonTestDataBuilder.commonProductBuilder()
                .withName("Telewizor").build();
        ProductEntity savedProduct1 = productRepository.save(product1);

        ProductEntity product2 = CommonTestDataBuilder.commonProductBuilder()
                .withName("Pralka").build();
        ProductEntity savedProduct2 = productRepository.save(product2);

        OrderProductEntity order1 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(5)
                .withProduct(savedProduct1).build();
        OrderProductEntity savedOrder1 = orderProductRepository.save(order1);

        OrderProductEntity order2 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(3)
                .withProduct(savedProduct2).build();
        OrderProductEntity savedOrder2 = orderProductRepository.save(order2);

        OrderProductEntity order3 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(10)
                .withProduct(savedProduct1).build();
        OrderProductEntity savedOrder3 = orderProductRepository.save(order3);

        PurchaseEntity purchase1 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.IN_PROGRESS)
                .withOrder(order1)
                .withOrder(order2).build();
        PurchaseEntity savedPurchase1 = purchaseRepository.save(purchase1);

        PurchaseEntity purchase2 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.AWAITING_PAYMENT)
                .withOrder(savedOrder3)
                .build();
        PurchaseEntity savedPurchase2 = purchaseRepository.save(purchase2);

        CustomerEntity customer = CommonTestDataBuilder.commonCustomerBuilder()
                .withPurchase(savedPurchase1)
                .withPurchase(savedPurchase2).build();
        CustomerEntity savedCustomer = customerRepository.save(customer);

        //when

        BigDecimal totalCostPurchasesWithStatus = purchaseRepository.
                totalCostPurchasesWithStatus(savedCustomer.getId(), Status.IN_PROGRESS);

        //then
        Assertions.assertThat(totalCostPurchasesWithStatus)
                .isNotNull()
                .isEqualByComparingTo(BigDecimal.valueOf(80000.00));
    }

    @Test
    public void shouldCalculateTotalCostOfAllPurchasesByCustomer() {

        //given
        ProductEntity product1 = CommonTestDataBuilder.commonProductBuilder()
                .withName("Telewizor").build();
        ProductEntity savedProduct1 = productRepository.save(product1);

        ProductEntity product2 = CommonTestDataBuilder.commonProductBuilder()
                .withName("Pralka").build();
        ProductEntity savedProduct2 = productRepository.save(product2);

        OrderProductEntity order1 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(5)
                .withProduct(savedProduct1).build();
        OrderProductEntity savedOrder1 = orderProductRepository.save(order1);

        OrderProductEntity order2 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(3)
                .withProduct(savedProduct2).build();
        OrderProductEntity savedOrder2 = orderProductRepository.save(order2);

        OrderProductEntity order3 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(10)
                .withProduct(savedProduct1).build();
        OrderProductEntity savedOrder3 = orderProductRepository.save(order3);

        PurchaseEntity purchase1 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.IN_PROGRESS)
                .withOrder(savedOrder1)
                .withOrder(savedOrder2).build();
        PurchaseEntity savedPurchase1 = purchaseRepository.save(purchase1);

        PurchaseEntity purchase2 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.AWAITING_PAYMENT)
                .withOrder(savedOrder3)
                .build();
        PurchaseEntity savedPurchase2 = purchaseRepository.save(purchase2);

        CustomerEntity customer = CommonTestDataBuilder.commonCustomerBuilder()
                .withPurchase(savedPurchase1)
                .withPurchase(savedPurchase2).build();
        CustomerEntity savedCustomer = customerRepository.save(customer);

        //when

        BigDecimal totalCostPurchases = purchaseRepository.totalCostPurchases(savedCustomer.getId());
        //then
        Assertions.assertThat(totalCostPurchases)
                .isNotNull()
                .isEqualByComparingTo(BigDecimal.valueOf(180000.00));
    }

    @Test
    public void shouldCalculateTotalCostOfAllPurchasesWithStatus() {

        //given
        ProductEntity product1 = CommonTestDataBuilder.commonProductBuilder()
                .withName("Telewizor").build();
        ProductEntity savedProduct1 = productRepository.save(product1);

        ProductEntity product2 = CommonTestDataBuilder.commonProductBuilder()
                .withName("Pralka").build();
        ProductEntity savedProduct2 = productRepository.save(product2);

        OrderProductEntity order1 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(5)
                .withProduct(savedProduct1).build();
        OrderProductEntity savedOrder1 = orderProductRepository.save(order1);

        OrderProductEntity order2 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(3)
                .withProduct(savedProduct2).build();
        OrderProductEntity savedOrder2 = orderProductRepository.save(order2);

        OrderProductEntity order3 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(10)
                .withProduct(savedProduct1).build();
        OrderProductEntity savedOrder3 = orderProductRepository.save(order3);

        PurchaseEntity purchase1 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.IN_PROGRESS)
                .withOrder(savedOrder1)
                .withOrder(savedOrder2)
                .build();
        PurchaseEntity savedPurchase1 = purchaseRepository.save(purchase1);

        PurchaseEntity purchase2 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.AWAITING_PAYMENT)
                .withOrder(savedOrder3).build();
        PurchaseEntity savedPurchase2 = purchaseRepository.save(purchase2);

        PurchaseEntity purchase3 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.IN_PROGRESS)
                .withOrder(savedOrder1)
                .withOrder(savedOrder2)
                .build();
        PurchaseEntity savedPurchase3 = purchaseRepository.save(purchase3);

        CustomerEntity customer1 = CommonTestDataBuilder.commonCustomerBuilder()
                .withPurchase(savedPurchase1)
                .withPurchase(savedPurchase2)
                .build();
        CustomerEntity savedCustomer1 = customerRepository.save(customer1);

        CustomerEntity customer2 = CommonTestDataBuilder.commonCustomerBuilder()
                .withPurchase(savedPurchase3).build();
        CustomerEntity savedCustomer = customerRepository.save(customer2);

        //when
        BigDecimal totalCost = purchaseRepository.totalCostPurchasesWithStatusForAllCustomers(Status.IN_PROGRESS);
        //then
        Assertions.assertThat(totalCost)
                .isNotNull()
                .isEqualByComparingTo(BigDecimal.valueOf(80000.00));
    }

    @Test
    public void shouldFindSpecificAmountOfBestSellers() {

        //given
        ProductEntity product1 = CommonTestDataBuilder.commonProductBuilder()
                .withName("TV").build();
        ProductEntity savedProduct1 = productRepository.save(product1);

        ProductEntity product2 = CommonTestDataBuilder.commonProductBuilder()
                .withName("Computer").build();
        ProductEntity savedProduct2 = productRepository.save(product2);

        ProductEntity product3 = CommonTestDataBuilder.commonProductBuilder()
                .withName("DVD").build();
        ProductEntity savedProduct3 = productRepository.save(product3);

        OrderProductEntity order1 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(2)
                .withProduct(savedProduct1).build();
        OrderProductEntity savedOrder1 = orderProductRepository.save(order1);

        OrderProductEntity order2 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(1)
                .withProduct(savedProduct3).build();
        OrderProductEntity savedOrder2 = orderProductRepository.save(order2);

        OrderProductEntity order3 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(6)
                .withProduct(savedProduct2).build();
        OrderProductEntity savedOrder3 = orderProductRepository.save(order3);

        OrderProductEntity order4 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(4)
                .withProduct(savedProduct1).build();
        OrderProductEntity savedOrder4 = orderProductRepository.save(order4);

        PurchaseEntity purchase1 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.COMPLETED)
                .withOrder(savedOrder1)
                .withOrder(savedOrder2)
                .build();
        PurchaseEntity savedPurchase1 = purchaseRepository.save(purchase1);

        PurchaseEntity purchase2 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.CANCELLED)
                .withOrder(savedOrder2).build();
        PurchaseEntity savedPurchase2 = purchaseRepository.save(purchase2);

        PurchaseEntity purchase3 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.IN_PROGRESS)
                .withOrder(savedOrder2)
                .withOrder(savedOrder3)
                .withOrder(savedOrder4).build();
        PurchaseEntity savedPurchase3 = purchaseRepository.save(purchase3);

        //when
        List<ProductEntity> tenTopSelling = productRepository.tenTopSelling();

        //then
        Assertions.assertThat(tenTopSelling)
                .isNotNull()
                .isNotEmpty();
    }
}
