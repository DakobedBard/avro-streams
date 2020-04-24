package kafka.streams.interactive.query;

import java.util.Objects;

public class ProductPurchaseCountBean {
    private String brand;
    private String name;
    private Long count;

    public ProductPurchaseCountBean() {}

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getCount() {
        return count;
    }
    public void setCount(Long count) {
        this.count = count;
    }
    public ProductPurchaseCountBean(String brand, String name, Long count) {
        this.brand = brand;
        this.name = name;
        this.count = count;
    }
    @Override
    public String toString() {
        return "ProductPurchaseCountBean{" +
                "brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", purchases=" + count +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProductPurchaseCountBean that = (ProductPurchaseCountBean) o;
        return Objects.equals(brand, that.brand) &&
                Objects.equals(name, that.name) &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, name, count);
    }
}
