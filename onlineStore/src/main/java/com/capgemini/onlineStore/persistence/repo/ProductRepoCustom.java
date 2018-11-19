package com.capgemini.onlineStore.persistence.repo;

import com.capgemini.onlineStore.persistence.entity.ProductEntity;

import java.util.List;

public interface ProductRepoCustom {

    List<ProductEntity> findByName(String name);

    List<ProductEntity> findAll();

    //ProductEntity update(ProductEntity productEntity);
}
