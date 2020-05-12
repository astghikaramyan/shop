package am.shop.demo.mapper;


import am.shop.demo.dto.UserDto;
import am.shop.demo.entity.UserEntity;

@org.mapstruct.Mapper(componentModel = "spring")
public interface UserMapper extends Mapper<UserEntity, UserDto> {
}
