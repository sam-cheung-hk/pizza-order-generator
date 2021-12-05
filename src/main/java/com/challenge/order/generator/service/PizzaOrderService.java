package com.challenge.order.generator.service;

import com.challenge.order.generator.bean.common.PizzaOrder;
import com.challenge.order.generator.bean.db.Pizza;
import com.challenge.order.generator.bean.outgoing.response.CreateOrderResponse;
import com.challenge.order.generator.converter.PizzaOrderDataConverter;
import com.challenge.order.generator.exception.ResourceNotFoundException;
import com.challenge.order.generator.repository.PizzaRepository;
import com.challenge.order.generator.util.UniqueIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Service
public class PizzaOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PizzaOrderService.class);

    @Autowired
    private UniqueIdGenerator uniqueIdGenerator;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private PizzaOrderReceiverService pizzaOrderReceiverService;

    @Autowired
    private PizzaOrderDataConverter pizzaOrderDataConverter;

    /**
     * Place pizza order
     *
     * @param pizzaOrder pizza order to be placed
     * @throws Exception error occurred during placing order
     */
    public void placeOrder(PizzaOrder pizzaOrder) throws Exception {
        // Find out all pizza pricing
        if (pizzaOrder.getOrderDetails() != null) {
            for (PizzaOrder.OrderDetail orderDetail : pizzaOrder.getOrderDetails()) {
                Pizza pizza = pizzaRepository.findByName(orderDetail.getName());
                if (pizza == null) {
                    LOGGER.error("Pizza cannot be found. Pizza name: {}", orderDetail.getName());
                    throw new ResourceNotFoundException("Pizza cannot be found. Pizza name: " + orderDetail.getName());
                }

                // Calculate pricing
                orderDetail.setSubtotal(pizza.getPrice().multiply(BigDecimal.valueOf(orderDetail.getQuantity())));
            }
        }

        // Set reference info
        pizzaOrder.setOrderReferenceNo(uniqueIdGenerator.getNextIdInString());
        pizzaOrder.setOrderTime(ZonedDateTime.now());

        // Create order
        CreateOrderResponse result = pizzaOrderReceiverService.createOrder(pizzaOrder);

        // Update order
        pizzaOrderDataConverter.updatePizzaOrder(pizzaOrder, result);
    }

}
