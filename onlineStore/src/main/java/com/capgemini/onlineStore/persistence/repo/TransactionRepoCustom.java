package com.capgemini.onlineStore.persistence.repo;

import com.capgemini.onlineStore.persistence.entity.CustomerEntity;

public interface TransactionRepoCustom {

    long getAmountOfCompletedTransactionsByCustomer(CustomerEntity customerEntity);
}
