package com.capgemini.onlineStore.persistence.mapper;

import com.capgemini.onlineStore.persistence.entity.PurchaseEntity;
import com.capgemini.onlineStore.persistence.mapper.common.CycleAvoidingMappingContext;
import com.capgemini.onlineStore.to.PurchaseTO;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface PurchaseMapper {

    PurchaseTO mapToPurchaseTO(PurchaseEntity purchaseEntity, @Context CycleAvoidingMappingContext context);

    List<PurchaseTO> mapToListPurchaseTO(List<PurchaseEntity> purchaseEntities, @Context CycleAvoidingMappingContext context);

    PurchaseEntity mapToPurchaseEntity(PurchaseTO purchaseTO, @Context CycleAvoidingMappingContext context);
}

