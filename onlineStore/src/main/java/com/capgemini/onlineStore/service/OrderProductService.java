package com.capgemini.onlineStore.service;

import com.capgemini.onlineStore.to.OrderProductTO;
import com.capgemini.onlineStore.to.OrderProductTO;

import java.util.List;

public interface OrderProductService {

    //double calculateTotalCost(Long transactionId);

   // List<String> findSpecificAmountOfBestSellers(int amountOfBestSellers);

    List<OrderProductTO> findAllOrders();

    OrderProductTO saveOrder(OrderProductTO orderProductTO);
}
