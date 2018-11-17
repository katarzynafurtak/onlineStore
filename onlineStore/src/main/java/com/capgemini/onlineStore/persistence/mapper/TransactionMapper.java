package com.capgemini.onlineStore.persistence.mapper;

import com.capgemini.onlineStore.persistence.entity.TransactionEntity;
import com.capgemini.onlineStore.persistence.mapper.common.CycleAvoidingMappingContext;
import com.capgemini.onlineStore.persistence.to.TransactionTO;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface TransactionMapper {

    TransactionTO mapToTransactionTO(TransactionEntity transactionEntity, @Context CycleAvoidingMappingContext context);

    List<TransactionTO> mapToListTransactionTO(List<TransactionEntity> transactionEntities, @Context CycleAvoidingMappingContext context);

    TransactionEntity mapToTransactionEntity(TransactionTO transactionTO, @Context CycleAvoidingMappingContext context);
}

