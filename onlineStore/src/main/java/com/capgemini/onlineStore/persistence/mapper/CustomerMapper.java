package com.capgemini.onlineStore.persistence.mapper;

import com.capgemini.onlineStore.persistence.entity.CustomerEntity;
import com.capgemini.onlineStore.persistence.mapper.common.CycleAvoidingMappingContext;
import com.capgemini.onlineStore.persistence.to.CustomerTO;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface CustomerMapper {

    CustomerTO mapToCustomerTO(CustomerEntity customerEntity, @Context CycleAvoidingMappingContext context);

    List<CustomerTO> mapToListCustomerTO(List<CustomerEntity> customerEntities, @Context CycleAvoidingMappingContext context);

    CustomerEntity mapToCustomerEntity(CustomerTO customerTO, @Context CycleAvoidingMappingContext context);
}