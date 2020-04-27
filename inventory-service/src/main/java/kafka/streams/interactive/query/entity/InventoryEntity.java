package kafka.streams.interactive.query.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InventoryEntity {
    @Id
    private String id;
    private Long quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public InventoryEntity(String id, Long quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
