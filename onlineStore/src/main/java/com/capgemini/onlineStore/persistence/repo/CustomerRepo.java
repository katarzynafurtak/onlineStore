package com.capgemini.onlineStore.persistence.repo;

import com.capgemini.onlineStore.persistence.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<CustomerEntity, Long>, CustomerRepoCustom {
}
