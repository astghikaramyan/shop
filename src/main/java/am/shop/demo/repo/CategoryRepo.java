package am.shop.demo.repo;

import am.shop.demo.entity.CategoryEntity;
import com.arangodb.springframework.repository.ArangoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CategoryRepo extends ArangoRepository<CategoryEntity, String> {
  Optional<CategoryEntity> getCategoryEntityByName(String name);
}
