package com.capgemini.onlineStore.persistence.repo;

import com.capgemini.onlineStore.persistence.entity.PurchaseEntity;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<PurchaseEntity, Long>, CustomizedPurchaseRepository {

}
