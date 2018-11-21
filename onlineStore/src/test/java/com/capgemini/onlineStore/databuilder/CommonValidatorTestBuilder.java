package com.capgemini.onlineStore.databuilder;

import com.capgemini.onlineStore.persistence.datatype.Status;
import com.capgemini.onlineStore.to.OrderProductTO;
import com.capgemini.onlineStore.to.ProductTO;
import com.capgemini.onlineStore.to.PurchaseTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

public class CommonValidatorTestBuilder {

    public static PurchaseTO purchaseWithStatus(Status status) {
        return new PurchaseTO.PurchaseTOBuilder()
                .withOrder(new OrderProductTO.OrderProductTOBuilder()
                        .withAmount(1)
                        .withProduct(new ProductTO.ProductTOBuilder()
                                .withName("Monitor")
                                .build())
                        .build())
                .withDate(LocalDateTime.of(2017, Month.JANUARY, 15, 17, 00))
                .withStatus(status)
                .build();
    }

    public static PurchaseTO lowPricePurchase() {
        return new PurchaseTO.PurchaseTOBuilder()
                .withOrder(new OrderProductTO.OrderProductTOBuilder()
                        .withAmount(1)
                        .withProduct(new ProductTO.ProductTOBuilder()
                                .withPrice(BigDecimal.valueOf(100))
                                .withMarge(BigDecimal.valueOf(50))
                                .build())
                        .build())
                .build();
    }

    public static PurchaseTO highPricePurchase() {
        return new PurchaseTO.PurchaseTOBuilder()
                .withOrder(new OrderProductTO.OrderProductTOBuilder()
                        .withAmount(1)
                        .withProduct(new ProductTO.ProductTOBuilder()
                                .withPrice(BigDecimal.valueOf(1000))
                                .withMarge(BigDecimal.valueOf(50))
                                .build())
                        .build())
                .build();
    }

    public static PurchaseTO purchaseWithProductPrice(double price, double marge, int amount) {
        return new PurchaseTO.PurchaseTOBuilder()
                .withOrder(new OrderProductTO.OrderProductTOBuilder()
                        .withAmount(amount)
                        .withProduct(new ProductTO.ProductTOBuilder()
                                .withName("Pralka")
                                .withPrice(BigDecimal.valueOf(price))
                                .withMarge(BigDecimal.valueOf(marge))
                                .build())
                        .build())
                .build();
    }
}
