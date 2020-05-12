package am.shop.demo.mapper;


import am.shop.demo.dto.ProductDto;
import am.shop.demo.entity.ProductEntity;

@org.mapstruct.Mapper(componentModel = "spring")
public interface ProductMapper extends Mapper<ProductEntity, ProductDto> {
}
