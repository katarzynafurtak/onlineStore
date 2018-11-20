package com.capgemini.onlineStore.persistence.repo;

import com.capgemini.onlineStore.persistence.entity.PurchaseEntity;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepo extends CrudRepository<PurchaseEntity, Long>, PurchaseRepoCustom {

}
