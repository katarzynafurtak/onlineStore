package com.capgemini.onlineStore.persistence.repo;

import com.capgemini.onlineStore.persistence.entity.TransactionEntity;

import java.util.List;

public interface SearchTransactionRepo {

    public List<TransactionEntity > findTransactionByCriteria(TransactionSearchCriteria searchCriteria);
}
