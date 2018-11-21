package com.capgemini.onlineStore.persistence.repo.impl;

import com.capgemini.onlineStore.persistence.datatype.Status;
import com.capgemini.onlineStore.persistence.entity.*;
import com.capgemini.onlineStore.persistence.repo.CustomizedProductRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements CustomizedProductRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ProductEntity> findByName(String name) {

        QProductEntity product = QProductEntity.productEntity;
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory.selectFrom(product)
                .where(product.name.eq(name))
                .fetch();
    }

    @Override
    public List<ProductEntity> findAll() {

        QProductEntity product = QProductEntity.productEntity;
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory.selectFrom(product)
                   .fetch();
    }

    @Override
    public List<ProductEntity> tenTopSelling() {

        QProductEntity product = QProductEntity.productEntity;
        QOrderProductEntity order = QOrderProductEntity.orderProductEntity;
        QPurchaseEntity purchase = QPurchaseEntity.purchaseEntity;
        QCustomerEntity customer = QCustomerEntity.customerEntity;
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory
                .selectFrom(customer)
                .select(order.product)
                .join(customer.purchases, purchase)
                .join(purchase.orders, order)
                .join(order.product, product)
                .where(purchase.status.eq(Status.COMPLETED))
                .groupBy(order.product)
                .orderBy(order.amount.sum().desc())
                .fetch();
    }

    public List<ProductForDelivery> productsForDelivery(){

        QProductEntity product = QProductEntity.productEntity;
        QOrderProductEntity order = QOrderProductEntity.orderProductEntity;
        QPurchaseEntity purchase = QPurchaseEntity.purchaseEntity;
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory
                .query()
                .from(purchase)
                .select(Projections.constructor(ProductForDelivery.class,
                        product.name, order.amount))
                .join(purchase.orders, order)
                .join(order.product, product)
                .select(product.name, order.amount)
                .where(purchase.status.eq(Status.IN_DELIVERY))
                .fetch()
                 .stream()
                 .map(p -> new ProductForDelivery(p.get(product.name), p.get(order.amount)))
                 .collect(Collectors.toList());

    }
}
