package com.capgemini.onlineStore.persistence.mapper;

import com.capgemini.onlineStore.persistence.entity.ProductEntity;
import com.capgemini.onlineStore.persistence.mapper.common.CycleAvoidingMappingContext;
import com.capgemini.onlineStore.to.ProductTO;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface ProductMapper {

    ProductTO mapToProductTO(ProductEntity productEntity, @Context CycleAvoidingMappingContext context);

    List<ProductTO> mapToListProductTO(List<ProductEntity> productEntities, @Context CycleAvoidingMappingContext context);

    ProductEntity mapToProductEntity(ProductTO productTO, @Context CycleAvoidingMappingContext context);
}