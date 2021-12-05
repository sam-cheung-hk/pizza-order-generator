package com.challenge.order.generator.bean.outgoing.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateOrderRequest {

    /**
     * Unique reference number of the order
     */
    @JsonProperty("orderReferenceNo")
    private String orderReferenceNo;

    /**
     * Time when the order is placed (in ISO-8601 format)
     */
    @JsonProperty("orderTime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime orderTime;

    /**
     * Detail of the order
     */
    @JsonProperty("orderDetails")
    private List<OrderDetail> orderDetails;

    // Getters & setters

    public String getOrderReferenceNo() {
        return orderReferenceNo;
    }

    public void setOrderReferenceNo(String orderReferenceNo) {
        this.orderReferenceNo = orderReferenceNo;
    }

    public String getOrderTime() {
        return (orderTime == null? null: orderTime.toString());
    }

    public void setOrderTime(ZonedDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        if (this.orderDetails == null) {
            this.orderDetails = new ArrayList<>();
        }
        this.orderDetails.add(orderDetail);
    }

    @Override
    public String toString() {
        return "CreateOrderRequest{" +
                "orderReferenceNo='" + orderReferenceNo + '\'' +
                ", orderTime=" + orderTime +
                ", orderDetails=" + (orderDetails == null? null: Arrays.toString(orderDetails.toArray())) +
                '}';
    }

    public static class OrderDetail {

        /**
         * Pizza name
         */
        @JsonProperty("name")
        private String name;

        /**
         * Quantity of the pizza
         */
        @JsonProperty("quantity")
        private Integer quantity;

        /**
         * Sub-total price of the pizza
         */
        @JsonProperty("price")
        private BigDecimal price;

        // Getters & setters

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "OrderDetail{" +
                    "name='" + name + '\'' +
                    ", quantity=" + quantity +
                    ", price=" + price +
                    '}';
        }
    }

}
