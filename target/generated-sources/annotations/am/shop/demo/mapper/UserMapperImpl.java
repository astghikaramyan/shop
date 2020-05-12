package am.shop.demo.mapper;

import am.shop.demo.dto.UserDto;
import am.shop.demo.entity.UserEntity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-12T15:12:34+0400",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_252 (Private Build)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( dto.getId() );
        userEntity.setName( dto.getName() );
        userEntity.setEmail( dto.getEmail() );
        userEntity.setPassword( dto.getPassword() );
        Set<String> set = dto.getRoles();
        if ( set != null ) {
            userEntity.setRoles( new HashSet<String>( set ) );
        }
        else {
            userEntity.setRoles( null );
        }

        return userEntity;
    }

    @Override
    public UserDto toDto(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( entity.getId() );
        userDto.setName( entity.getName() );
        userDto.setEmail( entity.getEmail() );
        userDto.setPassword( entity.getPassword() );
        Set<String> set = entity.getRoles();
        if ( set != null ) {
            userDto.setRoles( new HashSet<String>( set ) );
        }
        else {
            userDto.setRoles( null );
        }

        return userDto;
    }

    @Override
    public List<UserEntity> toEntityList(Iterable<UserDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>();
        for ( UserDto userDto : dtos ) {
            list.add( toEntity( userDto ) );
        }

        return list;
    }

    @Override
    public List<UserDto> toDtoList(Iterable<UserEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>();
        for ( UserEntity userEntity : entities ) {
            list.add( toDto( userEntity ) );
        }

        return list;
    }
}
