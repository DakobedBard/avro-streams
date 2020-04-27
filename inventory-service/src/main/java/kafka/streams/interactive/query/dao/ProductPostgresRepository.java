package kafka.streams.interactive.query.dao;

import kafka.streams.interactive.query.entity.ProductEntity;
import org.mddarr.inventory.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductPostgresRepository extends CrudRepository<ProductEntity, String> {

}
