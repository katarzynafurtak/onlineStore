package com.capgemini.onlineStore.persistence.repo.impl;

import com.capgemini.onlineStore.persistence.entity.ProductEntity;
import com.capgemini.onlineStore.persistence.entity.QProductEntity;
import com.capgemini.onlineStore.persistence.repo.ProductRepoCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ProductRepoCustomImpl implements ProductRepoCustom {

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
}
