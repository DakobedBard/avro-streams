package kafka.streams.interactive.query.dao;

import org.mddarr.products.ProductAvro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends CrudRepository<ProductAvro, String> {

}
