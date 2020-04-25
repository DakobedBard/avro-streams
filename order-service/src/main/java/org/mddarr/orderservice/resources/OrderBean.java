package org.mddarr.orderservice.resources;

import java.util.Objects;

public class OrderBean {
    private Long orderID;
    private Long customerID;
    private Long productID;
    private Long quantity;
    private String state;
    private Long price;

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "customerID=" + customerID +
                ", productID=" + productID +
                ", quantity=" + quantity +
                ", state='" + state + '\'' +
                ", price=" + price +
                '}';
    }

    public OrderBean(Long customerID, Long productID, Long quantity, String state, Long price) {
        this.customerID = customerID;
        this.productID = productID;
        this.quantity = quantity;
        this.state = state;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderBean)) return false;
        OrderBean orderBean = (OrderBean) o;
        return Objects.equals(customerID, orderBean.customerID) &&
                Objects.equals(productID, orderBean.productID) &&
                Objects.equals(quantity, orderBean.quantity) &&
                Objects.equals(state, orderBean.state) &&
                Objects.equals(price, orderBean.price);
    }


    @Override
    public int hashCode() {
        return Objects.hash(customerID, productID, quantity, state, price);
    }
    public Long getPrice() { return price; }
    public void setPrice(Long price) { this.price = price;}
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public Long getCustomerID() {return customerID;  }
    public void setCustomerID(Long customerID) {this.customerID = customerID; }
    public Long getProductID() {return productID; }
    public void setProductID(Long productID) { this.productID = productID;}
    public Long getQuantity() {return quantity;}
    public void setQuantity(Long quantity) { this.quantity = quantity; }

}


