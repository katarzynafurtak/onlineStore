package com.capgemini.onlineStore.persistence.service;

import com.capgemini.onlineStore.persistence.entity.OrderProductEntity;
import com.capgemini.onlineStore.persistence.repo.OrderProductRepo;
import com.capgemini.onlineStore.persistence.to.OrderProductTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface OrderProductService {

    double calculateTotalCost(Long transactionId);

   // List<String> findSpecificAmountOfBestSellers(int amountOfBestSellers);

    List<OrderProductTO> findAllOrders();

    OrderProductTO saveOrder(OrderProductTO orderProductTO);
}
