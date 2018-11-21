package com.capgemini.onlineStore.service;

import com.capgemini.onlineStore.to.OrderProductTO;

import java.util.List;

public interface OrderProductService {

    List<OrderProductTO> findAllOrders();

    OrderProductTO saveOrder(OrderProductTO orderProductTO);
}
