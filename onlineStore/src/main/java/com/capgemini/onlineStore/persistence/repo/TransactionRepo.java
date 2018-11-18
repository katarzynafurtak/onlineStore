package com.capgemini.onlineStore.persistence.repo;

import com.capgemini.onlineStore.persistence.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepo extends CrudRepository<TransactionEntity, Long>, TransactionRepoCustom {

}
