package am.shop.demo.controller;

import am.shop.demo.dto.CategoryDto;
import am.shop.demo.entity.CategoryEntity;
import am.shop.demo.mapper.CategoryMapper;
import am.shop.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class CategoryController {
  private CategoryService categoryService;
  private CategoryMapper categoryMapper;

  @Autowired
  public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper){
    this.categoryService = categoryService;
    this.categoryMapper = categoryMapper;
  }

  @PostMapping("/api/categories")
  public ResponseEntity<CategoryDto> addCat(@RequestBody @Valid CategoryEntity categoryEntity){
    try{
      categoryEntity = this.categoryService.addCat(categoryEntity);
      if(categoryEntity != null){
        return ResponseEntity.ok(this.categoryMapper.toDto(categoryEntity));
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @GetMapping("/api/categories/_search")
  public ResponseEntity<CategoryDto> getCat(@RequestParam(name = "id") String id){
    try{
      Optional<CategoryEntity> categoryEntity = this.categoryService.getCategory(id);
      if(categoryEntity.isPresent()){
        return ResponseEntity.ok(this.categoryMapper.toDto(categoryEntity.get()));
      }
      return ResponseEntity.notFound().build();
    }catch (Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @GetMapping("/api/categories")
  public ResponseEntity<List<CategoryDto>> getCategories(){
    try{
      Iterable<CategoryEntity> categoryEntities = this.categoryService.getAll();
      if(categoryEntities != null){
        return ResponseEntity.ok(this.categoryMapper.toDtoList(categoryEntities));
      }
      return ResponseEntity.notFound().build();
    }catch (Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @PutMapping("/api/categories")
  public ResponseEntity<CategoryDto> update(@RequestBody CategoryEntity categoryEntity){
    try{
      categoryEntity = this.categoryService.updateCategory(categoryEntity);
      if(categoryEntity != null){
        return ResponseEntity.ok(this.categoryMapper.toDto(categoryEntity));
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @DeleteMapping("/api/categories")
  public ResponseEntity delete(@RequestParam(name = "id") String id){
    try{
      this.categoryService.delete(id);
    }catch (Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @PostMapping("/api/categories/_search")
  public ResponseEntity<CategoryDto> searchCat(@RequestBody CategoryEntity categoryEntity){
    try{
      Optional<CategoryEntity> categoryEntity1 = this.categoryService.searchCategory(categoryEntity);
      if(categoryEntity1.isPresent()){
        return ResponseEntity.ok(this.categoryMapper.toDto(categoryEntity1.get()));
      }
      return ResponseEntity.notFound().build();
    }catch (Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
