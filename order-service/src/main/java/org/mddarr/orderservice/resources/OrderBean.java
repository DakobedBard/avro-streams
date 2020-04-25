package org.mddarr.orderservice.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderBean {
    private Long orderID;
    private Long customerID;
    private List<Long> productIDs;
    private List<Long> quantites;
    private String state;
    private Long price;

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderBean)) return false;
        OrderBean orderBean = (OrderBean) o;
        return Objects.equals(orderID, orderBean.orderID) &&
                Objects.equals(customerID, orderBean.customerID) &&
                Objects.equals(productIDs, orderBean.productIDs) &&
                Objects.equals(quantites, orderBean.quantites) &&
                Objects.equals(state, orderBean.state) &&
                Objects.equals(price, orderBean.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, customerID, productIDs, quantites, state, price);
    }

    public OrderBean(Long customerID, List<Long> productIDs, List<Long> quantites, String state, Long price) {
        this.customerID = customerID;
        this.productIDs = productIDs;
        this.quantites = quantites;
        this.state = state;
        this.price = price;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}


