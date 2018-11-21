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

        Set<OrderProductEntity> orders1 = new HashSet<>();
        orders1.add(order1);
        orders1.add(order2);

        PurchaseEntity purchase1 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.IN_PROGRESS)
                .withOrders(orders1).build();
        PurchaseEntity savedPurchase1 = purchaseRepository.save(purchase1);

        Set<OrderProductEntity> orders2 = new HashSet<>();
        orders2.add(order3);

        PurchaseEntity purchase2 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.IN_PROGRESS)
                .withOrders(orders2).build();
        PurchaseEntity savedPurchase2 = purchaseRepository.save(purchase2);

        PurchaseEntity purchase3 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.CANCELLED)
                .withOrders(orders1).build();
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
                .withProduct(product1).build();
        OrderProductEntity savedOrder1 = orderProductRepository.save(order1);

        OrderProductEntity order2 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(3)
                .withProduct(product2).build();
        OrderProductEntity savedOrder2 = orderProductRepository.save(order2);

        OrderProductEntity order3 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(10)
                .withProduct(product1).build();
        OrderProductEntity savedOrder3 = orderProductRepository.save(order3);

        Set<OrderProductEntity> orders1 = new HashSet<>();
        orders1.add(order1);
        orders1.add(order2);

        PurchaseEntity purchase1 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.IN_PROGRESS)
                .withOrders(orders1).build();
        PurchaseEntity savedPurchase1 = purchaseRepository.save(purchase1);

        Set<OrderProductEntity> orders2 = new HashSet<>();
        orders2.add(order3);

        PurchaseEntity purchase2 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.AWAITING_PAYMENT)
                .withOrders(orders2).build();
        PurchaseEntity savedPurchase2 = purchaseRepository.save(purchase2);

        Set<PurchaseEntity> purchases = new HashSet<>();
        purchases.add(purchase1);
        purchases.add(purchase2);

        CustomerEntity customer = CommonTestDataBuilder.commonCustomerBuilder()
                .withPurchases(purchases).build();
        CustomerEntity savedCustomer = customerRepository.save(customer);

        //when

        BigDecimal totalCostPurchasesWithStatus = purchaseRepository.
                totalCostPurchasesWithStatus(savedCustomer.getId(), Status.IN_PROGRESS);

        //then
        Assertions.assertThat(totalCostPurchasesWithStatus)
                .isNotNull();

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
                .withProduct(product1).build();
        OrderProductEntity savedOrder1 = orderProductRepository.save(order1);

        OrderProductEntity order2 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(3)
                .withProduct(product2).build();
        OrderProductEntity savedOrder2 = orderProductRepository.save(order2);

        OrderProductEntity order3 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(10)
                .withProduct(product1).build();
        OrderProductEntity savedOrder3 = orderProductRepository.save(order3);

        Set<OrderProductEntity> orders1 = new HashSet<>();
        orders1.add(order1);
        orders1.add(order2);

        PurchaseEntity purchase1 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.IN_PROGRESS)
                .withOrders(orders1).build();
        PurchaseEntity savedPurchase1 = purchaseRepository.save(purchase1);

        Set<OrderProductEntity> orders2 = new HashSet<>();
        orders2.add(order3);

        PurchaseEntity purchase2 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.AWAITING_PAYMENT)
                .withOrders(orders2).build();
        PurchaseEntity savedPurchase2 = purchaseRepository.save(purchase2);

        Set<PurchaseEntity> purchases = new HashSet<>();
        purchases.add(purchase1);
        purchases.add(purchase2);

        CustomerEntity customer = CommonTestDataBuilder.commonCustomerBuilder()
                .withPurchases(purchases).build();
        CustomerEntity savedCustomer = customerRepository.save(customer);

        //when

        BigDecimal totalCostPurchases = purchaseRepository.totalCostPurchases(savedCustomer.getId());
        //then
        Assertions.assertThat(totalCostPurchases)
                .isNotNull();


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
                .withProduct(product1).build();
        OrderProductEntity savedOrder1 = orderProductRepository.save(order1);

        OrderProductEntity order2 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(3)
                .withProduct(product2).build();
        OrderProductEntity savedOrder2 = orderProductRepository.save(order2);

        OrderProductEntity order3 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(10)
                .withProduct(product1).build();
        OrderProductEntity savedOrder3 = orderProductRepository.save(order3);

        Set<OrderProductEntity> orders1 = new HashSet<>();
        orders1.add(order1);
        orders1.add(order2);

        PurchaseEntity purchase1 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.IN_PROGRESS)
                .withOrders(orders1).build();
        PurchaseEntity savedPurchase1 = purchaseRepository.save(purchase1);

        Set<OrderProductEntity> orders2 = new HashSet<>();
        orders2.add(order3);

        PurchaseEntity purchase2 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.AWAITING_PAYMENT)
                .withOrders(orders2).build();
        PurchaseEntity savedPurchase2 = purchaseRepository.save(purchase2);

        Set<PurchaseEntity> purchases1 = new HashSet<>();
        purchases1.add(purchase1);
        purchases1.add(purchase2);

        PurchaseEntity purchase3 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.IN_PROGRESS)
                .withOrders(orders1).build();
        PurchaseEntity savedPurchase3 = purchaseRepository.save(purchase3);

        Set<PurchaseEntity> purchases2 = new HashSet<>();
        purchases2.add(purchase3);

        CustomerEntity customer1 = CommonTestDataBuilder.commonCustomerBuilder()
                .withPurchases(purchases1).build();
        CustomerEntity savedCustomer1 = customerRepository.save(customer1);

        CustomerEntity customer2 = CommonTestDataBuilder.commonCustomerBuilder()
                .withPurchases(purchases2).build();
        CustomerEntity savedCustomer = customerRepository.save(customer2);

        //when
        BigDecimal totalCost = purchaseRepository.totalCostPurchasesWithStatusForAllCustomers(Status.IN_PROGRESS);
        //then
        Assertions.assertThat(totalCost)
                .isNotNull();
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
                .withProduct(product1).build();
        OrderProductEntity savedOrder1 = orderProductRepository.save(order1);

        OrderProductEntity order2 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(1)
                .withProduct(product3).build();
        OrderProductEntity savedOrder2 = orderProductRepository.save(order2);

        OrderProductEntity order3 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(6)
                .withProduct(product2).build();
        OrderProductEntity savedOrder3 = orderProductRepository.save(order3);

        OrderProductEntity order4 = CommonTestDataBuilder.commonOrderProductBuilder()
                .withAmount(4)
                .withProduct(product1).build();
        OrderProductEntity savedOrder4 = orderProductRepository.save(order4);

        Set<OrderProductEntity> orders1 = new HashSet<>();
        orders1.add(order1);
        orders1.add(order2);

        PurchaseEntity purchase1 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.COMPLETED)
                .withOrders(orders1).build();
        PurchaseEntity savedPurchase1 = purchaseRepository.save(purchase1);

        Set<OrderProductEntity> orders2 = new HashSet<>();
        orders2.add(order2);

        PurchaseEntity purchase2 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.CANCELLED)
                .withOrders(orders2).build();
        PurchaseEntity savedPurchase2 = purchaseRepository.save(purchase2);

        Set<OrderProductEntity> orders3 = new HashSet<>();
        orders3.add(order2);
        orders3.add(order3);
        orders3.add(order4);

        PurchaseEntity purchase3 = CommonTestDataBuilder.commonPurchaseBuilder()
                .withStatus(Status.IN_PROGRESS)
                .withOrders(orders3).build();
        PurchaseEntity savedPurchase3 = purchaseRepository.save(purchase3);

        //when

        List<ProductEntity> tenTopSelling = productRepository.tenTopSelling();
        //then
        Assertions.assertThat(tenTopSelling)
                .isNotNull()
                .isNotEmpty();

    }

//    @Test
//    public void shouldFindSpecificAmountOfBestCustomersInSpecificTime() {
//
//        //given
//        ProductEntity product1 = CommonTestDataBuilder.commonProductBuilder()
//                .withName("TV")
//                .withPrice(BigDecimal.valueOf(9000))
//                .build();
//        ProductEntity savedProduct1 = productRepository.save(product1);
//
//        ProductEntity product2 = CommonTestDataBuilder.commonProductBuilder()
//                .withName("Computer")
//                .withPrice(BigDecimal.valueOf(1000))
//                .build();
//        ProductEntity savedProduct2 = productRepository.save(product2);
//
//        OrderProductEntity order1 = CommonTestDataBuilder.commonOrderProductBuilder()
//                .withAmount(1)
//                .withProduct(product1).build();
//        OrderProductEntity savedOrder1 = orderProductRepository.save(order1);
//
//        OrderProductEntity order2 = CommonTestDataBuilder.commonOrderProductBuilder()
//                .withAmount(1)
//                .withProduct(product2).build();
//        OrderProductEntity savedOrder2 = orderProductRepository.save(order2);
//
//        OrderProductEntity order3 = CommonTestDataBuilder.commonOrderProductBuilder()
//                .withAmount(5)
//                .withProduct(product1).build();
//        OrderProductEntity savedOrder3 = orderProductRepository.save(order3);
//
//        Set<OrderProductEntity> orders1 = new HashSet<>();
//        orders1.add(order1);
//        orders1.add(order2);
//
//        PurchaseEntity purchase1 = CommonTestDataBuilder.commonPurchaseBuilder()
//                .withStatus(Status.COMPLETED)
//                .withOrders(orders1).build();
//        PurchaseEntity savedPurchase1 = purchaseRepository.save(purchase1);
//
//        Set<OrderProductEntity> orders2 = new HashSet<>();
//        orders2.add(order2);
//
//        PurchaseEntity purchase2 = CommonTestDataBuilder.commonPurchaseBuilder()
//                .withStatus(Status.COMPLETED)
//                .withOrders(orders2).build();
//        PurchaseEntity savedPurchase2 = purchaseRepository.save(purchase2);
//
//        Set<OrderProductEntity> orders3 = new HashSet<>();
//        orders3.add(order3);
//
//        PurchaseEntity purchase3 = CommonTestDataBuilder.commonPurchaseBuilder()
//                .withStatus(Status.COMPLETED)
//                .withOrders(orders3).build();
//        PurchaseEntity savedPurchase3 = purchaseRepository.save(purchase3);
//
//        Set<OrderProductEntity> orders4 = new HashSet<>();
//        orders4.add(order3);
//
//        PurchaseEntity purchase4 = CommonTestDataBuilder.commonPurchaseBuilder()
//                .withStatus(Status.COMPLETED)
//                .withOrders(orders4).build();
//        PurchaseEntity savedPurchase4 = purchaseRepository.save(purchase4);
//
//        Set<OrderProductEntity> orders5 = new HashSet<>();
//        orders5.add(order2);
//
//        PurchaseEntity purchase5 = CommonTestDataBuilder.commonPurchaseBuilder()
//                .withStatus(Status.COMPLETED)
//                .withOrders(orders5).build();
//        PurchaseEntity savedPurchase5 = purchaseRepository.save(purchase5);
//
//        Set<PurchaseEntity> purchases1 = new HashSet<>();
//        purchases1.add(purchase1);
//        purchases1.add(purchase2);
//
//        CustomerEntity customer1 = CommonTestDataBuilder.commonCustomerBuilder()
//                .withPurchases(purchases1).build();
//        CustomerEntity savedCustomer1 = customerRepository.save(customer1);
//
//        Set<PurchaseEntity> purchases2 = new HashSet<>();
//        purchases2.add(purchase3);
//
//        CustomerEntity customer2 = CommonTestDataBuilder.commonCustomerBuilder()
//                .withPurchases(purchases2).build();
//        CustomerEntity savedCustomer = customerRepository.save(customer2);
//
//        Set<PurchaseEntity> purchases3 = new HashSet<>();
//        purchases3.add(purchase3);
//
//        CustomerEntity customer2 = CommonTestDataBuilder.commonCustomerBuilder()
//                .withPurchases(purchases2).build();
//        CustomerEntity savedCustomer = customerRepository.save(customer2);
//
//        Set<PurchaseEntity> purchases2 = new HashSet<>();
//        purchases2.add(purchase3);
//
//        CustomerEntity customer2 = CommonTestDataBuilder.commonCustomerBuilder()
//                .withPurchases(purchases2).build();
//        CustomerEntity savedCustomer = customerRepository.save(customer2);
//
//        //when
//
//        //then
//
//
//    }




}
