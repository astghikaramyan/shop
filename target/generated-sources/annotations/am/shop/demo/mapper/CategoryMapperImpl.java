package am.shop.demo.mapper;

import am.shop.demo.dto.CategoryDto;
import am.shop.demo.entity.CategoryEntity;
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
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryEntity toEntity(CategoryDto dto) {
        if ( dto == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setId( dto.getId() );
        categoryEntity.setName( dto.getName() );

        return categoryEntity;
    }

    @Override
    public CategoryDto toDto(CategoryEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId( entity.getId() );
        categoryDto.setName( entity.getName() );

        return categoryDto;
    }

    @Override
    public List<CategoryEntity> toEntityList(Iterable<CategoryDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<CategoryEntity> list = new ArrayList<CategoryEntity>();
        for ( CategoryDto categoryDto : dtos ) {
            list.add( toEntity( categoryDto ) );
        }

        return list;
    }

    @Override
    public List<CategoryDto> toDtoList(Iterable<CategoryEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<CategoryDto> list = new ArrayList<CategoryDto>();
        for ( CategoryEntity categoryEntity : entities ) {
            list.add( toDto( categoryEntity ) );
        }

        return list;
    }
}
