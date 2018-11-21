package com.capgemini.onlineStore.persistence.repo;

import com.capgemini.onlineStore.persistence.datatype.Status;
import com.capgemini.onlineStore.persistence.entity.CustomerEntity;
import com.capgemini.onlineStore.persistence.entity.PurchaseEntity;

import java.math.BigDecimal;

public interface CustomizedPurchaseRepository {
    BigDecimal totalCostPurchases(long customerId);

    BigDecimal totalCostPurchasesWithStatus(long customerId, Status status);

    BigDecimal totalCostPurchasesWithStatusForAllCustomers(Status status);
}
