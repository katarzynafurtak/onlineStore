package com.capgemini.onlineStore.persistence.repo.impl;

import com.capgemini.onlineStore.persistence.entity.QOrderProductEntity;
import com.capgemini.onlineStore.persistence.entity.QProductEntity;
import com.capgemini.onlineStore.persistence.repo.OrderProductRepoCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderProductRepoCustomImpl implements OrderProductRepoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public double calculateTotalCostOfTransaction(Long id) {

        QProductEntity product = QProductEntity.productEntity;
        QOrderProductEntity orderProduct = QOrderProductEntity.orderProductEntity;

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory.from(orderProduct)
                .innerJoin(orderProduct.product, product)
                .where(orderProduct.transaction.id.eq(id))
                .select((orderProduct.amount.doubleValue().multiply(product.price).multiply(-100).divide(product.marge.subtract(100))).sum())
                .fetchOne();
    }

    @Override
    public List<String> findSpecificAmountOfBestSellers(int amountOfBestSellers) {

        QProductEntity product = QProductEntity.productEntity;
        QOrderProductEntity orderProduct = QOrderProductEntity.orderProductEntity;

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory.from(orderProduct)
                .innerJoin(orderProduct.product, product)
                .select(product.name)
                .groupBy(product.name)
                .orderBy(orderProduct.amount.sum().desc())
                .limit(amountOfBestSellers)
                .fetch();
    }
}
