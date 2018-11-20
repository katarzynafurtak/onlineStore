package com.capgemini.onlineStore.service.impl;

import com.capgemini.onlineStore.persistence.entity.OrderProductEntity;
import com.capgemini.onlineStore.persistence.mapper.OrderProductMapper;
import com.capgemini.onlineStore.persistence.mapper.common.CycleAvoidingMappingContext;
import com.capgemini.onlineStore.persistence.repo.OrderProductRepository;
import com.capgemini.onlineStore.persistence.repo.PurchaseRepository;
import com.capgemini.onlineStore.service.OrderProductService;
import com.capgemini.onlineStore.to.OrderProductTO;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepo;

    @Autowired
    private PurchaseRepository purchaseRepo;

    @Autowired
    private OrderProductMapper orderProductMapper;

//    @Override
//    public double calculateTotalCost(Long transactionId) {
//        return orderProductRepo.calculateTotalCostOfTransaction(transactionId);
//    }

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
