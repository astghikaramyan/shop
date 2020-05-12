package am.shop.demo.mapper;


import am.shop.demo.dto.OrderDto;
import am.shop.demo.entity.OrderEntity;

@org.mapstruct.Mapper(componentModel = "spring")
public interface OrderMapper extends Mapper<OrderEntity, OrderDto> {
}
