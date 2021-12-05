package com.challenge.order.generator.bean.common;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PizzaOrder {

    /**
     * Reference number assigned to the order
     */
    private String orderReferenceNo;

    /**
     * Time when the order is placed
     */
    private ZonedDateTime orderTime;

    /**
     * Transaction number created for the order
     */
    private String orderTransactionNo;

    /**
     * Total price of the order
     */
    private BigDecimal totalPrice;

    /**
     * Detail of the order
     */
    private List<OrderDetail> orderDetails;

    public String getOrderReferenceNo() {
        return orderReferenceNo;
    }

    public void setOrderReferenceNo(String orderReferenceNo) {
        this.orderReferenceNo = orderReferenceNo;
    }

    public ZonedDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(ZonedDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderTransactionNo() {
        return orderTransactionNo;
    }

    public void setOrderTransactionNo(String orderTransactionNo) {
        this.orderTransactionNo = orderTransactionNo;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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
        return "PizzaOrder{" +
                "orderReferenceNo='" + orderReferenceNo + '\'' +
                ", orderTime=" + orderTime +
                ", orderTransactionNo='" + orderTransactionNo + '\'' +
                ", totalPrice=" + totalPrice +
                ", orderDetails=" + (orderDetails == null? null: Arrays.toString(orderDetails.toArray())) +
                '}';
    }

    public static class OrderDetail {

        /**
         * Pizza name
         */
        private String name;

        /**
         * Quantity of the pizza
         */
        private Integer quantity;

        /**
         * Sub-total pizza for the pizza
         */
        private BigDecimal subtotal;

        // Getters & Setters

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

        public BigDecimal getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(BigDecimal subtotal) {
            this.subtotal = subtotal;
        }

        @Override
        public String toString() {
            return "OrderDetail{" +
                    "name='" + name + '\'' +
                    ", quantity=" + quantity +
                    ", subtotal=" + subtotal +
                    '}';
        }
    }
}
