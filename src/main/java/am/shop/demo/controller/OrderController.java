package am.shop.demo.controller;

import am.shop.demo.dto.OrderDto;
import am.shop.demo.entity.OrderEntity;
import am.shop.demo.mapper.OrderMapper;
import am.shop.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class OrderController {
  private OrderService orderService;
  private OrderMapper orderMapper;

  @Autowired
  public OrderController(OrderService orderService, OrderMapper orderMapper){
    this.orderService = orderService;
    this.orderMapper = orderMapper;
  }

  @PostMapping("/api/orders")
  public ResponseEntity<OrderDto> addOrder(@RequestBody @Valid OrderEntity orderEntity){
    try{
      orderEntity = this.orderService.addOrder(orderEntity);
      if(orderEntity != null){
        return ResponseEntity.ok(this.orderMapper.toDto(orderEntity));
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @GetMapping("/api/orders/_search")
  public ResponseEntity<OrderDto> getOrder(@RequestParam(name="id") String id){
    try{
      Optional<OrderEntity> orderEntity = this.orderService.getOrder(id);
      if(orderEntity.isPresent()){
        return ResponseEntity.ok(this.orderMapper.toDto(orderEntity.get()));
      }
      return ResponseEntity.notFound().build();
    }catch (Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @GetMapping("/api/orders")
  public ResponseEntity<List<OrderDto>> getOrders(){
    try{
      Iterable<OrderEntity> orderEntities = this.orderService.getOrders();
      if(orderEntities != null){
        return ResponseEntity.ok(this.orderMapper.toDtoList(orderEntities));
      }
      return ResponseEntity.notFound().build();
    }catch (Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @PutMapping("/api/orders")
  public ResponseEntity<OrderDto> update(@RequestBody OrderEntity orderEntity){
    try{
      orderEntity = this.orderService.updateOrder(orderEntity);
      if(orderEntity != null){
        return ResponseEntity.ok(this.orderMapper.toDto(orderEntity));
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @DeleteMapping("/api/orders")
  public ResponseEntity delete(@RequestParam(name="id") String id){
    try{
      this.orderService.delete(id);
    }catch (Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @GetMapping("/api/orders/_searchUserOrders")
  public ResponseEntity<List<OrderDto>> getUserOrders(@RequestParam(name="userId") String userId){
    try {
      Iterable<OrderEntity> orderEntities = this.orderService.getUserOrders(userId);
      if(orderEntities != null){
        return ResponseEntity.ok(this.orderMapper.toDtoList(orderEntities));
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
