package am.shop.demo.mapper;

import am.shop.demo.dto.ProductDto;
import am.shop.demo.entity.ProductEntity;
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
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductEntity toEntity(ProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setId( dto.getId() );
        productEntity.setName( dto.getName() );
        productEntity.setPrice( dto.getPrice() );
        productEntity.setDesc( dto.getDesc() );
        productEntity.setCatId( dto.getCatId() );
        productEntity.setImgUrl( dto.getImgUrl() );

        return productEntity;
    }

    @Override
    public ProductDto toDto(ProductEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( entity.getId() );
        productDto.setName( entity.getName() );
        productDto.setPrice( entity.getPrice() );
        productDto.setDesc( entity.getDesc() );
        productDto.setCatId( entity.getCatId() );
        productDto.setImgUrl( entity.getImgUrl() );

        return productDto;
    }

    @Override
    public List<ProductEntity> toEntityList(Iterable<ProductDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<ProductEntity> list = new ArrayList<ProductEntity>();
        for ( ProductDto productDto : dtos ) {
            list.add( toEntity( productDto ) );
        }

        return list;
    }

    @Override
    public List<ProductDto> toDtoList(Iterable<ProductEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>();
        for ( ProductEntity productEntity : entities ) {
            list.add( toDto( productEntity ) );
        }

        return list;
    }
}
