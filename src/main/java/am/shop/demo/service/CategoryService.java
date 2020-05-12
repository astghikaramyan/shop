package am.shop.demo.service;


import am.shop.demo.entity.CategoryEntity;
import am.shop.demo.exceptions.DatabaseException;

import java.util.Optional;

public interface CategoryService {
  CategoryEntity addCat(CategoryEntity categoryEntity) throws DatabaseException;
  Optional<CategoryEntity> getCategory(String id) throws DatabaseException;
  CategoryEntity updateCategory(CategoryEntity categoryEntity) throws DatabaseException;
  Iterable<CategoryEntity> getAll() throws  DatabaseException;
  void delete(String id) throws DatabaseException;
  Optional<CategoryEntity> searchCategory(CategoryEntity categoryEntity) throws DatabaseException;
}
