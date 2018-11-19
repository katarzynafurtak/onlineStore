package com.capgemini.onlineStore.persistence.mapper;

import com.capgemini.onlineStore.persistence.entity.OrderProductEntity;
import com.capgemini.onlineStore.persistence.mapper.common.CycleAvoidingMappingContext;
import com.capgemini.onlineStore.to.OrderProductTO;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface OrderProductMapper {

    OrderProductTO mapToOrderProductTO(OrderProductEntity orderProductEntity, @Context CycleAvoidingMappingContext context);

    List<OrderProductTO> mapToListOrderProductTO(List<OrderProductEntity> orderProductEntities, @Context CycleAvoidingMappingContext context);

    OrderProductEntity mapToOrderProductEntity(OrderProductTO orderProductTO, @Context CycleAvoidingMappingContext context);
}
