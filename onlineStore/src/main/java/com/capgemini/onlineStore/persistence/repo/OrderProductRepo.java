package com.capgemini.onlineStore.persistence.repo;

import com.capgemini.onlineStore.persistence.entity.OrderProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepo extends CrudRepository<OrderProductEntity, Long>, OrderProductRepoCustom {


}
