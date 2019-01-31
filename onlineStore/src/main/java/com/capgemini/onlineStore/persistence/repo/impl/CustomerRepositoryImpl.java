package com.capgemini.onlineStore.persistence.repo.impl;

import com.capgemini.onlineStore.persistence.datatype.Status;
import com.capgemini.onlineStore.persistence.entity.QCustomerEntity;
import com.capgemini.onlineStore.persistence.entity.QPurchaseEntity;
import com.capgemini.onlineStore.persistence.repo.CustomizedCustomerRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomerRepositoryImpl implements CustomizedCustomerRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long cntAmountOfCompletedPurchases(long customerId) {

        QCustomerEntity customer = QCustomerEntity.customerEntity;
        QPurchaseEntity purchase = QPurchaseEntity.purchaseEntity;

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory
                .selectFrom(customer)
                .join(customer.purchases, purchase)
                .where(purchase.status.eq(Status.COMPLETED)
                        .and(customer.id.eq(customerId)))
                .fetchCount();
    }
}
//dla jednego brancza
