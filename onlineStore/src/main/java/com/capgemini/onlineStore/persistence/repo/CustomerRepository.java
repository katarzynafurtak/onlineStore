package com.capgemini.onlineStore.persistence.repo;

import com.capgemini.onlineStore.persistence.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long>, CustomizedCustomerRepository {
}
