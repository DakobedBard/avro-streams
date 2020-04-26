package org.mddarr.orders.beans;

import java.util.List;

public class OrderDTO {
    List<ProductDTO> products;
    List<Long> quantities;
    Long price;
    String customerID;

    public List<ProductDTO> getProducts() { return products; }
    public void setProducts(List<ProductDTO> products) { this.products = products; }
    public List<Long> getQuantities() {  return quantities;}
    public void setQuantities(List<Long> quantities) {this.quantities = quantities; }
    public Long getPrice() {return price;}
    public void setPrice(Long price) {this.price = price; }
    public String getCustomerID() {return customerID; }
    public void setCustomerID(String customerID) {this.customerID = customerID;}

    public OrderDTO(List<ProductDTO> products, List<Long> quantities, Long price, String customerID) {
        this.products = products;
        this.quantities = quantities;
        this.price = price;
        this.customerID = customerID;
    }
}
