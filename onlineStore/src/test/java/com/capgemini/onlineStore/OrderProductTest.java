package com.capgemini.onlineStore;

import com.capgemini.onlineStore.persistence.datatype.Status;
import com.capgemini.onlineStore.service.OrderProductService;
import com.capgemini.onlineStore.service.ProductService;
import com.capgemini.onlineStore.to.OrderProductTO;
import com.capgemini.onlineStore.to.ProductTO;
import com.capgemini.onlineStore.to.TransactionTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderProductTest {

    @Autowired
    private OrderProductService orderProductService;

    @Autowired
    private ProductService productService;

    @Before
    public void initialize() {

//        TransactionTO trans1 = new TransactionTO.TransactionTOBuilder()
//                .withCustomer()
//
//
//        ProductTO product1 = new ProductTO.ProductTOBuilder()
//                .withName("Apple MacBook Pro13")
//                .withPrice(BigDecimal.valueOf(9000))
//                .withMarge(BigDecimal.valueOf(50))
//                .withWeight(BigDecimal.valueOf(2))
//                .build();
//        apple = pro.sa
//
//
//        ProductTO product2= new ProductTO.ProductTOBuilder()
//                .withName("Telewizor LG")
//                .withPrice(BigDecimal.valueOf(3000))
//                .withMarge(BigDecimal.valueOf(50))
//                .withWeight(BigDecimal.valueOf(18))
//                .build();
//
//        ContactData contactData = new ContactData.ContactDataBuilder()
//                .withCity("Poznań")
//                .withStreet("Krakowska")
//                .withNumberOfHouse("5")
//                .withNumberOfFlat(7)
//                .withPostcode("60-467")
//                .withEmail("poznan@interia.pl")
//                .withPhoneNumber("809888111")
//                .build();
//
//        CustomerTO customCustomer = new CustomerTO.CustomerTOBuilder()
//                .withFirstName("Marian")
//                .withLastName("Wolański")
//                .withDateOfBirth(LocalDate.of(1967, Month.JANUARY, 5))
//                .withContactData(contactData)
//                .build();
//
//        TransactionTO customTransaction = new TransactionTO.TransactionTOBuilder()
//                .withStatus(Status.COMPLETED)
//                .withCustomer(customCustomer)
//                .build();
//
//        Set<TransactionTO> transactions = new HashSet<>();
//        transactions.add(customTransaction);
//



    }





    @Test
    public void shouldCalculateTotalCostOfTransaction() {

        //given
        TransactionTO trans1 = new TransactionTO.TransactionTOBuilder()
                .withStatus(Status.COMPLETED)
                .build();

        ProductTO product1 = new ProductTO.ProductTOBuilder()
                .withName("Apple MacBook Pro13")
                .withPrice(BigDecimal.valueOf(9000))
                .withMarge(BigDecimal.valueOf(50))
                .withWeight(BigDecimal.valueOf(2))
                .build();


        ProductTO product2= new ProductTO.ProductTOBuilder()
                .withName("Telewizor LG")
                .withPrice(BigDecimal.valueOf(3000))
                .withMarge(BigDecimal.valueOf(50))
                .withWeight(BigDecimal.valueOf(18))
                .build();


        OrderProductTO order1 = new OrderProductTO.OrderProductTOBuilder()
                .withAmount(1)
                .withProduct(product1)
                .withTransaction(trans1)
                .build();
        orderProductService.saveOrder(order1);

        OrderProductTO order2 = new OrderProductTO.OrderProductTOBuilder()
                .withAmount(2)
                .withProduct(product2)
                .withTransaction(trans1)
                .build();
        orderProductService.saveOrder(order2);


        List<OrderProductTO> found = orderProductService.findAllOrders();
        double expectedResult = 0.00;

        for (OrderProductTO order : orderProductService.findAllOrders()) {
            if (order.getTransaction().getId() == trans1.getId()) {
                expectedResult += (order.getAmount()* order.getProduct().getPrice().doubleValue())/(1-(0.01*order.getProduct().getMarge().doubleValue()));
            }
        }
//
//        //when
//        double totalCost = orderProductService.calculateTotalCost(trans1.getId());
//
//        //then
//        Assertions.assertThat(expectedResult).isEqualTo(totalCost);
    }
}
