package com.capgemini.onlineStore.validator;

import com.capgemini.onlineStore.persistence.entity.TransactionEntity;

public interface Validator {


    void validate(TransactionEntity transactionEntity);
}
