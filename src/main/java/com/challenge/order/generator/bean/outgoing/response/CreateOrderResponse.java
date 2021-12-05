package com.challenge.order.generator.bean.outgoing.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class CreateOrderResponse {

    /**
     * Unique reference number of the order
     */
    @JsonProperty("orderReferenceNo")
    private String orderReferenceNo;

    /**
     * Time when the order is placed
     */
    @JsonProperty("orderTime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime orderTime;

    /**
     * Transaction number of the order
     */
    @JsonProperty("orderTransNo")
    private String orderTransNo;

    /**
     * Total price of the order
     */
    @JsonProperty("totalPrice")
    private BigDecimal totalPrice;

    // Getters & Setters

    public String getOrderReferenceNo() {
        return orderReferenceNo;
    }

    public void setOrderReferenceNo(String orderReferenceNo) {
        this.orderReferenceNo = orderReferenceNo;
    }

    public ZonedDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = (orderTime == null? null: ZonedDateTime.parse(orderTime));
    }

    public String getOrderTransNo() {
        return orderTransNo;
    }

    public void setOrderTransNo(String orderTransNo) {
        this.orderTransNo = orderTransNo;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CreateOrderResponse{" +
                "orderReferenceNo='" + orderReferenceNo + '\'' +
                ", orderTime=" + orderTime +
                ", orderTransNo='" + orderTransNo + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
