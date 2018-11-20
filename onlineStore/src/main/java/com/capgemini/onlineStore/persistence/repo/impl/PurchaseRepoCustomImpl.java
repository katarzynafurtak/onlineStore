package com.capgemini.onlineStore.persistence.repo.impl;

import com.capgemini.onlineStore.persistence.datatype.Status;
import com.capgemini.onlineStore.persistence.entity.*;
import com.capgemini.onlineStore.persistence.repo.PurchaseRepoCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PurchaseRepoCustomImpl implements PurchaseRepoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long getAmountOfCompletedPurchasesByCustomer(CustomerEntity customerEntity) {

        QPurchaseEntity purchase = QPurchaseEntity.purchaseEntity;

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory
                .from(purchase)
                .where(purchase.status.in(Status.COMPLETED).and(purchase.customer.eq(customerEntity)))
                .fetchCount();
    }

}
