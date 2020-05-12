package am.shop.demo.service.serviceImpl;

import am.shop.demo.entity.OrderEntity;
import am.shop.demo.exceptions.DatabaseException;
import am.shop.demo.repo.OrderRepo;
import am.shop.demo.service.OrderService;
import com.arangodb.ArangoDBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {

  private OrderRepo orderRepo;

  @Autowired
  public OrderServiceImpl(OrderRepo orderRepo){
    this.orderRepo = orderRepo;
  }

  @Override
  public OrderEntity addOrder(OrderEntity orderEntity) throws DatabaseException {
    try{
      return this.orderRepo.save(orderEntity);
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public Optional<OrderEntity> getOrder(String id) throws DatabaseException{
    try{
      return this.orderRepo.findById(id);
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public OrderEntity updateOrder(OrderEntity orderEntity) throws DatabaseException{
    try{
      return this.orderRepo.save(orderEntity);
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public Iterable<OrderEntity> getOrders() throws DatabaseException{
    try{
      return this.orderRepo.findAll();
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public void delete(String id) throws DatabaseException{
    try{
      this.orderRepo.deleteById(id);
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public Iterable<OrderEntity> getUserOrders(String userId) throws DatabaseException {
    try{
      return this.orderRepo.getOrderEntitiesByUserId(userId);
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }
}
