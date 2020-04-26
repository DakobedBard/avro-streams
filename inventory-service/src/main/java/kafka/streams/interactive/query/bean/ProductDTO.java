package kafka.streams.interactive.query.bean;

import org.springframework.kafka.annotation.KafkaListener;

public class ProductDTO {
    String name;
    String brand;
    Long price;

    public ProductDTO(String name, String brand, Long price) {
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
