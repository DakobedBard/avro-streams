package org.mddarr.orders.entity;

import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class OrderEntity {
    @Id
    String id;
    @ElementCollection
    List<String> products;
    @ElementCollection
    List<Long> quantities;
    Double price;
    String customerID;
    String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public OrderEntity(){

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public List<Long> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<Long> quantities) {
        this.quantities = quantities;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public OrderEntity(String id, List<String> products, List<Long> quantities, Double price, String customerID, String state) {
        this.id = id;
        this.products = products;
        this.quantities = quantities;
        this.price = price;
        this.customerID = customerID;
        this.state = state;
    }
}
