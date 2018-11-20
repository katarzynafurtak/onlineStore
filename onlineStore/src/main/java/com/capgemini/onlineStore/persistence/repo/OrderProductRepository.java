package com.capgemini.onlineStore.persistence.repo;

import com.capgemini.onlineStore.persistence.entity.OrderProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository<OrderProductEntity, Long>, CustomizedOrderProductRepository {


}
