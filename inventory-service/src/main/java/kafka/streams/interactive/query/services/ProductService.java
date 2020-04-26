package kafka.streams.interactive.query.services;

import kafka.streams.interactive.query.bean.ProductDTO;
import kafka.streams.interactive.query.dao.ProductRepository;

import kafka.streams.interactive.query.entity.ProductEntity;
import org.mddarr.inventory.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.function.BiFunction;

@Service
public class ProductService {
    @Autowired
    ProductRepository productMongoRepository;

    public void addProduct(ProductDTO productDTO){
        UUID uuid =  UUID.randomUUID();
        ProductEntity product = new ProductEntity(uuid.toString(),productDTO.getName(),productDTO.getBrand(),productDTO.getPrice());
        productMongoRepository.save(product);
    }

    public Optional<ProductEntity> getProduct(String id){
        return productMongoRepository.findById(id);
    }
    public void deleteProduct(String id){productMongoRepository.deleteById(id);}


}
