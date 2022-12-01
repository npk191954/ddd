package com.smartrm.smartrmtradeservice.domain.repository;

import com.smartrm.smarttrade.domain.Order;

/**
 * @author: yoda
 * @description:
 */
public interface OrderRepository {

  Order getOrderById(long orderId);

  void addOrder(Order order);

  void updateOrder(Order order);

  void addOrUpdate(Order order);

}
