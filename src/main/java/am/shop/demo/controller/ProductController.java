package am.shop.demo.controller;

import am.shop.demo.dto.ProductDto;
import am.shop.demo.entity.ProductEntity;
import am.shop.demo.mapper.ProductMapper;
import am.shop.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class ProductController {

  private ProductService productService;
  private ProductMapper productMapper;

  @Autowired
  public ProductController(ProductService productService, ProductMapper productMapper){
    this.productService = productService;
    this.productMapper = productMapper;
  }

  @PostMapping("/api/products")
  public ResponseEntity<ProductDto> addProduct(@RequestBody @Valid ProductEntity productEntity){
    try{
      productEntity = this.productService.addProduct(productEntity);
      if(productEntity != null){
        return ResponseEntity.ok(this.productMapper.toDto(productEntity));
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @GetMapping("/api/products/_search")
  public ResponseEntity<List<ProductDto>> getProduct(@RequestParam(name="catId") String catId){
    try{
      Iterable<ProductEntity> productEntities = this.productService.getProducsByCatId(catId);
      if(productEntities != null){
        return ResponseEntity.ok(this.productMapper.toDtoList(productEntities));
      }else {
        return ResponseEntity.notFound().build();
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }


  @GetMapping("/api/products/_searchByPrId")
  public ResponseEntity<ProductDto> getProductById(@RequestParam(name="prId") String prId){
    try{
      Optional<ProductEntity> productEntity = this.productService.getProduct(prId);
      if(productEntity.isPresent()){
        return ResponseEntity.ok(this.productMapper.toDto(productEntity.get()));
      }else {
        return ResponseEntity.notFound().build();
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
//  @GetMapping("/api/products")
//  public ResponseEntity<List<ProductDto>> getProducts(){
//    try{
//      Iterable<ProductEntity> productEntities = this.productService.getProducts();
//      if(productEntities != null){
//        return ResponseEntity.ok(this.productMapper.toDtoList(productEntities));
//      }
//      return ResponseEntity.notFound().build();
//    }catch (ArangoDBException e){
//      e.printStackTrace();
//    }
//    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//  }

  @PutMapping("/api/products")
  public ResponseEntity<ProductDto> update(@RequestBody ProductEntity productEntity, @RequestParam(name="id") String id){
    try{
      productEntity = this.productService.updateProduct(productEntity);
      if(productEntity != null){
        return ResponseEntity.ok(this.productMapper.toDto(productEntity));
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @DeleteMapping("/api/products")
  public ResponseEntity delete(@RequestParam(name="id") String id){
    try{
      this.productService.delete(id);
    }catch (Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
  }
  @GetMapping("/api/products")
  public ResponseEntity<List<ProductDto>> getProductsByCatId(@RequestParam(name="catId") String catId){
    try{
      Iterable<ProductEntity> productEntities = this.productService.getProducsByCatId(catId);
      if(productEntities != null){
        return ResponseEntity.ok(this.productMapper.toDtoList(productEntities));
      }else {
        return ResponseEntity.notFound().build();
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
