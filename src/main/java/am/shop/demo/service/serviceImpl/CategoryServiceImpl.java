package am.shop.demo.service.serviceImpl;

import am.shop.demo.entity.CategoryEntity;
import am.shop.demo.exceptions.DatabaseException;
import am.shop.demo.repo.CategoryRepo;
import am.shop.demo.service.CategoryService;
import com.arangodb.ArangoDBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService {
  private CategoryRepo categoryRepo;

  @Autowired
  public CategoryServiceImpl(CategoryRepo categoryRepo){
    this.categoryRepo = categoryRepo;
  }

  @Override
  public CategoryEntity addCat(CategoryEntity categoryEntity) throws DatabaseException {
    try{
      return this.categoryRepo.save(categoryEntity);
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public Optional<CategoryEntity> getCategory(String id) throws DatabaseException{
    try{
      return this.categoryRepo.findById(id);
    }catch (ArangoDBException e){
      throw  new DatabaseException(e.getMessage());
    }
  }

  @Override
  public CategoryEntity updateCategory(CategoryEntity categoryEntity) throws DatabaseException {
    try{
      return this.categoryRepo.save(categoryEntity);
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public Iterable<CategoryEntity> getAll() throws DatabaseException{
    try{
      return this.categoryRepo.findAll();
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public void delete(String id) throws DatabaseException{
    try{
      this.categoryRepo.deleteById(id);
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }

  @Override
  public Optional<CategoryEntity> searchCategory(CategoryEntity categoryEntity) throws DatabaseException{
    try{
      return this.categoryRepo.getCategoryEntityByName(categoryEntity.getName());
    }catch (ArangoDBException e){
      throw new DatabaseException(e.getMessage());
    }
  }
}
