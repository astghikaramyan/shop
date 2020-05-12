package am.shop.demo.mapper;

import java.util.List;


public interface Mapper<Entity, Dto>{
  Entity toEntity(Dto dto);
  Dto toDto(Entity entity);
  List<Entity> toEntityList(Iterable<Dto> dtos);
  List<Dto> toDtoList(Iterable<Entity> entities);
}
