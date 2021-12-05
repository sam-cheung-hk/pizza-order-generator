package com.challenge.order.generator.bean.incoming.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

public class PlaceOrderRequest {

    @Schema(description = "Detail of the order", required = true)
    @NotNull(message = "pizzas is mandatory")
    @NotEmpty(message = "pizzas cannot be empty")
    @Valid
    @JsonProperty("pizzas")
    private List<Pizza> pizzas;

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    @Override
    public String toString() {
        return "PlaceOrderRequest{" +
                "pizzas=" + (pizzas == null? null: Arrays.toString(pizzas.toArray())) +
                '}';
    }

    public static class Pizza {

        @Schema(description = "Pizza name", required = true, example = "PIZZA_TYPE_A")
        @NotBlank(message = "name in pizzas is mandatory")
        @JsonProperty("name")
        private String name;

        @Schema(description = "Quantity of the pizza", required = true, example = "1")
        @NotNull(message = "quantity in pizzas is mandatory")
        @Min(1)
        @JsonProperty("quantity")
        private Integer quantity;

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

        @Override
        public String toString() {
            return "Pizza{" +
                    "name='" + name + '\'' +
                    ", quantity=" + quantity +
                    '}';
        }
    }

}
