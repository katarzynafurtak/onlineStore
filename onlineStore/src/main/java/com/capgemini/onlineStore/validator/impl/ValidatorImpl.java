package com.capgemini.onlineStore.validator.impl;

import com.capgemini.onlineStore.persistence.entity.TransactionEntity;
import com.capgemini.onlineStore.persistence.repo.OrderProductRepo;
import com.capgemini.onlineStore.persistence.repo.TransactionRepo;
import com.capgemini.onlineStore.validator.Validator;
import com.capgemini.onlineStore.validator.exception.InvalidTransactionException;

public class ValidatorImpl implements Validator {

    private TransactionRepo transactionRepo;


    @Override
    public void validate(TransactionEntity transactionEntity) throws InvalidTransactionException {

        if(transactionRepo.getAmountOfCompletedTransactionsByCustomer(transactionEntity.getCustomer()) < 3 && ) {

        }

    }
}
