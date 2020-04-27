package org.mddarr.orders.beans;

import org.springframework.context.annotation.Bean;

import java.util.List;

public class OrderDTO {
    List<String> products;
    List<Long> quantities;
    Double price;
    String customerID;

    public List<String> getProducts() { return products; }
    public void setProducts(List<String> products) { this.products = products; }
    public List<Long> getQuantities() {  return quantities;}
    public void setQuantities(List<Long> quantities) {this.quantities = quantities; }
    public Double getPrice() {return price;}
    public void setPrice(Double price) {this.price = price; }
    public String getCustomerID() {return customerID; }
    public void setCustomerID(String customerID) {this.customerID = customerID;}

    public OrderDTO(){

    }

    public OrderDTO(List<String> products, List<Long> quantities, Double price, String customerID) {
        this.products = products;
        this.quantities = quantities;
        this.price = price;
        this.customerID = customerID;
    }
}
