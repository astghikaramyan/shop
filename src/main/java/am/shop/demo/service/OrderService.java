package am.shop.demo.service;


import am.shop.demo.entity.OrderEntity;
import am.shop.demo.exceptions.DatabaseException;

import java.util.Optional;

public interface OrderService {
  OrderEntity addOrder(OrderEntity orderEntity) throws DatabaseException;
  Optional<OrderEntity> getOrder(String id) throws DatabaseException;
  OrderEntity updateOrder(OrderEntity orderEntity) throws  DatabaseException;
  Iterable<OrderEntity> getOrders() throws DatabaseException;
  void delete(String id) throws DatabaseException;
  Iterable<OrderEntity> getUserOrders(String userId) throws DatabaseException;
}
