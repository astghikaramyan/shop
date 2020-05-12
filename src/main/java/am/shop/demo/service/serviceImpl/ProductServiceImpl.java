package am.shop.demo.service.serviceImpl;

import am.shop.demo.entity.ProductEntity;
import am.shop.demo.exceptions.DatabaseException;
import am.shop.demo.repo.ProductRepo;
import am.shop.demo.service.ProductService;
import com.arangodb.ArangoDBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

  private ProductRepo productRepo;

  @Autowired
  public ProductServiceImpl(ProductRepo productRepo){
    this.productRepo = productRepo;
  }
  @Override
  public ProductEntity addProduct(ProductEntity productEntity) throws DatabaseException {
    try{
      return this.productRepo.save(productEntity);
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public Optional<ProductEntity> getProduct(String id) throws DatabaseException {
    try{
      return this.productRepo.findById(id);
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public ProductEntity updateProduct(ProductEntity productEntity) throws DatabaseException {
    try{
      return this.productRepo.save(productEntity);
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public Iterable<ProductEntity> getProducts() throws DatabaseException {
    try{
      return this.productRepo.findAll();
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public void delete(String id) throws DatabaseException {
    try{
      this.productRepo.deleteById(id);
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public Iterable<ProductEntity> getProducsByCatId(String catId) throws DatabaseException {
    try{
      return this.productRepo.getProductEntitiesByCatId(catId);
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }
}
