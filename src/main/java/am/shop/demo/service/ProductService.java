package am.shop.demo.service;


import am.shop.demo.entity.ProductEntity;
import am.shop.demo.exceptions.DatabaseException;

import java.util.Optional;

public interface ProductService {
  ProductEntity addProduct(ProductEntity productEntity) throws DatabaseException;
  Optional<ProductEntity> getProduct(String id) throws DatabaseException;
  ProductEntity updateProduct(ProductEntity productEntity) throws DatabaseException;
  Iterable<ProductEntity> getProducts() throws DatabaseException;
  void delete(String id) throws  DatabaseException;
  Iterable<ProductEntity> getProducsByCatId(String catId) throws DatabaseException;

}
