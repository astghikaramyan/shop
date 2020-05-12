package am.shop.demo.repo;

import am.shop.demo.entity.ProductEntity;
import com.arangodb.springframework.repository.ArangoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends ArangoRepository<ProductEntity, String> {
  Iterable<ProductEntity> getProductEntitiesByCatId(String catId);
}
