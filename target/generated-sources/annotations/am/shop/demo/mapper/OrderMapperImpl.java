package am.shop.demo.mapper;

import am.shop.demo.dto.OrderDto;
import am.shop.demo.entity.OrderEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-12T15:12:35+0400",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_252 (Private Build)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderEntity toEntity(OrderDto dto) {
        if ( dto == null ) {
            return null;
        }

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setId( dto.getId() );
        orderEntity.setProductId( dto.getProductId() );
        orderEntity.setUserId( dto.getUserId() );
        orderEntity.setDone( dto.getDone() );
        orderEntity.setQuantity( dto.getQuantity() );
        orderEntity.setCardNumber( dto.getCardNumber() );
        orderEntity.setCardId( dto.getCardId() );
        orderEntity.setOrderTime( dto.getOrderTime() );

        return orderEntity;
    }

    @Override
    public OrderDto toDto(OrderEntity entity) {
        if ( entity == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setId( entity.getId() );
        orderDto.setProductId( entity.getProductId() );
        orderDto.setUserId( entity.getUserId() );
        orderDto.setDone( entity.getDone() );
        orderDto.setOrderTime( entity.getOrderTime() );
        orderDto.setQuantity( entity.getQuantity() );
        orderDto.setCardNumber( entity.getCardNumber() );
        orderDto.setCardId( entity.getCardId() );

        return orderDto;
    }

    @Override
    public List<OrderEntity> toEntityList(Iterable<OrderDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<OrderEntity> list = new ArrayList<OrderEntity>();
        for ( OrderDto orderDto : dtos ) {
            list.add( toEntity( orderDto ) );
        }

        return list;
    }

    @Override
    public List<OrderDto> toDtoList(Iterable<OrderEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<OrderDto> list = new ArrayList<OrderDto>();
        for ( OrderEntity orderEntity : entities ) {
            list.add( toDto( orderEntity ) );
        }

        return list;
    }
}
