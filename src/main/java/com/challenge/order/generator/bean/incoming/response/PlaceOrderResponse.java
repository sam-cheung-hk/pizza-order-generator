package com.challenge.order.generator.bean.incoming.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PlaceOrderResponse {

    @Schema(description = "Transaction number of the order", example = "784690292656873473")
    @JsonProperty("orderTransNo")
    private String orderTransNo;

    @Schema(description = "Total price of the order", example = "300.00")
    @JsonProperty("totalPrice")
    private BigDecimal totalPrice;

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
        // Force to 2 d.p.
        this.totalPrice = (totalPrice == null? null: totalPrice.setScale(2, RoundingMode.HALF_EVEN));
    }

    @Override
    public String toString() {
        return "PlaceOrderResponse{" +
                "orderTransNo='" + orderTransNo + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
