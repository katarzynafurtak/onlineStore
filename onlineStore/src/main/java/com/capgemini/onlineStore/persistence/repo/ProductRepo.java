package com.capgemini.onlineStore.persistence.repo;

import com.capgemini.onlineStore.persistence.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<ProductEntity, Long> {
}
