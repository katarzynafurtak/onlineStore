package com.capgemini.onlineStore.persistence.service.impl;

import com.capgemini.onlineStore.persistence.entity.OrderProductEntity;
import com.capgemini.onlineStore.persistence.mapper.OrderProductMapper;
import com.capgemini.onlineStore.persistence.mapper.common.CycleAvoidingMappingContext;
import com.capgemini.onlineStore.persistence.repo.OrderProductRepo;
import com.capgemini.onlineStore.persistence.repo.TransactionRepo;
import com.capgemini.onlineStore.persistence.service.OrderProductService;
import com.capgemini.onlineStore.persistence.to.OrderProductTO;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private OrderProductRepo orderProductRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Override
    public double calculateTotalCost(Long transactionId) {
        //TransactionEntity transactionEntity = transactionRepo.findOne(transactionId);
        return orderProductRepo.calculateTotalCostOfTransaction(transactionId);
    }

//    @Override
//    public List<String> findSpecificAmountOfBestSellers(int amountOfBestSellers) {
//        return orderProductRepo.findSpecificAmountOfBestSellers(amountOfBestSellers);
//    }

    @Override
    public List<OrderProductTO> findAllOrders() {
        return orderProductMapper.mapToListOrderProductTO(Lists.newArrayList(orderProductRepo.findAll()), CycleAvoidingMappingContext.create());
    }

    @Override
    public OrderProductTO saveOrder(OrderProductTO orderProductTO) {
        OrderProductEntity orderProductEntity = orderProductRepo.save(orderProductMapper.mapToOrderProductEntity(orderProductTO, CycleAvoidingMappingContext.create()));
        return orderProductMapper.mapToOrderProductTO(orderProductEntity, CycleAvoidingMappingContext.create());
    }

}
