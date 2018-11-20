package com.capgemini.onlineStore.persistence.repo;

import com.capgemini.onlineStore.persistence.entity.CustomerEntity;

public interface CustomizedCustomerRepository {

    Long cntAmountOfCompletedPurchases(long customerId);
}
