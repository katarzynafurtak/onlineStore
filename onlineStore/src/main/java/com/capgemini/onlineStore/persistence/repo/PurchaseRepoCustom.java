package com.capgemini.onlineStore.persistence.repo;

import com.capgemini.onlineStore.persistence.entity.CustomerEntity;
import com.capgemini.onlineStore.persistence.entity.PurchaseEntity;

public interface PurchaseRepoCustom {

    Long getAmountOfCompletedPurchasesByCustomer(CustomerEntity customerEntity);

    //double calculateCostOfPurchase(PurchaseEntity purchaseEntity);
}
