package com.capgemini.onlineStore.persistence.repo.impl;

import com.capgemini.onlineStore.persistence.datatype.Status;
import com.capgemini.onlineStore.persistence.entity.CustomerEntity;
import com.capgemini.onlineStore.persistence.entity.QTransactionEntity;
import com.capgemini.onlineStore.persistence.repo.TransactionRepoCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TransactionRepoCustomImpl implements TransactionRepoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long getAmountOfCompletedTransactionsByCustomer(CustomerEntity customerEntity) {

        QTransactionEntity transaction = QTransactionEntity.transactionEntity;

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory
                .from(transaction)
                .where(transaction.status.in(Status.COMPLETED).and(transaction.customer.eq(customerEntity)))
                .fetchCount();
    }
}
