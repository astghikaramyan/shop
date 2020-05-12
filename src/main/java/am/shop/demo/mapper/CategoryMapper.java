package am.shop.demo.mapper;


import am.shop.demo.dto.CategoryDto;
import am.shop.demo.entity.CategoryEntity;

@org.mapstruct.Mapper(componentModel = "spring")
public interface CategoryMapper extends Mapper<CategoryEntity, CategoryDto> {
}
