package com.capgemini.onlineStore.persistence.repo.impl;

import com.capgemini.onlineStore.persistence.datatype.Status;
import com.capgemini.onlineStore.persistence.entity.QCustomerEntity;
import com.capgemini.onlineStore.persistence.entity.QOrderProductEntity;
import com.capgemini.onlineStore.persistence.entity.QProductEntity;
import com.capgemini.onlineStore.persistence.entity.QPurchaseEntity;
import com.capgemini.onlineStore.persistence.repo.CustomizedPurchaseRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public class PurchaseRepositoryImpl implements CustomizedPurchaseRepository {

    @PersistenceContext
    private EntityManager em;

    public BigDecimal totalCostPurchases(long customerId) {

        QCustomerEntity customer = QCustomerEntity.customerEntity;
        QPurchaseEntity purchase = QPurchaseEntity.purchaseEntity;
        QOrderProductEntity order = QOrderProductEntity.orderProductEntity;
        QProductEntity product = QProductEntity.productEntity;

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory
                .selectFrom(customer)
                .select(order.sellPrice.multiply(order.amount).sum())
                .join(customer.purchases, purchase)
                .join(purchase.orders, order)
                .join(order.product, product)
                .where(customer.id.eq(customerId))
                .fetchOne();
    }

    @Override
    public BigDecimal totalCostPurchasesWithStatus(long customerId, Status status) {

        QCustomerEntity customer = QCustomerEntity.customerEntity;
        QPurchaseEntity purchase = QPurchaseEntity.purchaseEntity;
        QOrderProductEntity order = QOrderProductEntity.orderProductEntity;
        QProductEntity product = QProductEntity.productEntity;

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory
                .selectFrom(customer)
                .select(order.sellPrice.multiply(order.amount).sum())
                .join(customer.purchases, purchase)
                .join(purchase.orders, order)
                .join(order.product, product)
                .where(customer.id.eq(customerId))
                .where(purchase.status.eq(status))
                .fetchOne();
    }

    @Override
    public BigDecimal totalCostPurchasesWithStatusForAllCustomers(Status status) {

        QCustomerEntity customer = QCustomerEntity.customerEntity;
        QPurchaseEntity purchase = QPurchaseEntity.purchaseEntity;
        QOrderProductEntity order = QOrderProductEntity.orderProductEntity;
        QProductEntity product = QProductEntity.productEntity;

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory
                .selectFrom(customer)
                .select(order.sellPrice.multiply(order.amount).sum())
                .join(customer.purchases, purchase)
                .join(purchase.orders, order)
                .join(order.product, product)
                .where(purchase.status.eq(status))
                .fetchOne();
    }

    @Override
    public BigDecimal gainForTimePeriod(LocalDate from, LocalDate to) {

        QCustomerEntity customer = QCustomerEntity.customerEntity;
        QPurchaseEntity purchase = QPurchaseEntity.purchaseEntity;
        QOrderProductEntity order = QOrderProductEntity.orderProductEntity;
        QProductEntity product = QProductEntity.productEntity;

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory
                .selectFrom(customer)
                .select(order.sellPrice.multiply(order.amount).subtract(order.product.price.multiply(order.amount)).sum())
                .join(customer.purchases, purchase)
                .join(purchase.orders, order)
                .join(order.product, product)
                .fetchOne();
    }


}
