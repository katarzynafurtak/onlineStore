package com.capgemini.onlineStore.persistence.repo;

import com.capgemini.onlineStore.persistence.entity.OrderProductEntity;
import com.capgemini.onlineStore.persistence.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderProductRepo extends CrudRepository<OrderProductEntity, Long>, OrderProductRepoCustom {


}
