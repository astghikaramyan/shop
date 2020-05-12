package am.shop.demo.repo;

import am.shop.demo.entity.OrderEntity;
import com.arangodb.springframework.repository.ArangoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends ArangoRepository<OrderEntity, String> {
  Iterable<OrderEntity> getOrderEntitiesByUserId(String userId);
}
