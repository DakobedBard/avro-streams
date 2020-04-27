package kafka.streams.interactive.query.services;

import kafka.streams.interactive.query.bean.ProductDTO;


import kafka.streams.interactive.query.dao.InventoryRepository;
import org.mddarr.products.ProductAvro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private InventoryRepository postgresRepository;

    @Autowired
    AvroProductProducer avroProductProducer;

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    public void addProduct(ProductDTO productDTO){
        UUID uuid =  UUID.randomUUID();
        ProductAvro product = new ProductAvro(uuid.toString(),productDTO.getName(),productDTO.getBrand(),productDTO.getPrice());
//        ProductEntity product = new ProductEntity(uuid.toString(),productDTO.getName(),productDTO.getBrand(),productDTO.getPrice());
        postgresRepository.save(product);
        avroProductProducer.sendProduct(product);
    }

    public Optional<ProductAvro> getProduct(String id){
        return postgresRepository.findById(id);
    }
    public void deleteProduct(String id){postgresRepository.deleteById(id);}


}
