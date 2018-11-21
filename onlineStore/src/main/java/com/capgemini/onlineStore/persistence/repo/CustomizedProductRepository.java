package com.capgemini.onlineStore.persistence.repo;

import com.capgemini.onlineStore.persistence.entity.ProductEntity;

import java.util.List;

public interface CustomizedProductRepository {

    List<ProductEntity> findByName(String name);

    List<ProductEntity> findAll();

    List<ProductEntity> tenTopSelling();
}
