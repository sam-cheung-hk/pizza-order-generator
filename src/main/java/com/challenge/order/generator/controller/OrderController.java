package com.challenge.order.generator.controller;

import com.challenge.order.generator.bean.common.PizzaOrder;
import com.challenge.order.generator.bean.incoming.request.PlaceOrderRequest;
import com.challenge.order.generator.bean.incoming.response.PlaceOrderResponse;
import com.challenge.order.generator.converter.PizzaOrderDataConverter;
import com.challenge.order.generator.service.PizzaOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private PizzaOrderDataConverter pizzaOrderDataConverter;

    @Autowired
    private PizzaOrderService pizzaOrderService;

    @Operation(summary = "Place pizza order",
            description = "Place pizza order",
            tags = { "Pizza Order" }
    )
    @ApiResponse(responseCode = "200",
            description = "Successful Operation",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlaceOrderResponse.class)))
    @PostMapping("/place")
    public PlaceOrderResponse placePizzaOrder(@Valid @RequestBody PlaceOrderRequest request) throws Exception {
        LOGGER.debug("Place pizza order request. Request: {}", request);

        // Transform to Pizza Order
        PizzaOrder pizzaOrder = pizzaOrderDataConverter.transformToPizzaOrder(request);

        // Place order
        pizzaOrderService.placeOrder(pizzaOrder);

        return pizzaOrderDataConverter.transformToPlaceOrderResponse(pizzaOrder);
    }

}
