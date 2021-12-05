package com.challenge.order.generator.converter;

import com.challenge.order.generator.bean.common.PizzaOrder;
import com.challenge.order.generator.bean.incoming.request.PlaceOrderRequest;
import com.challenge.order.generator.bean.incoming.response.PlaceOrderResponse;
import com.challenge.order.generator.bean.outgoing.request.CreateOrderRequest;
import com.challenge.order.generator.bean.outgoing.response.CreateOrderResponse;
import org.springframework.stereotype.Component;

@Component
public class PizzaOrderDataConverter {

    /**
     * Transform from {@link PlaceOrderRequest} to {@link PizzaOrder}
     *
     * @param request {@link PlaceOrderRequest} to be transformed
     * @return transformed {@link PizzaOrder}
     */
    public PizzaOrder transformToPizzaOrder(PlaceOrderRequest request) {
        PizzaOrder pizzaOrder = new PizzaOrder();
        if (request.getPizzas() != null) {
            for (PlaceOrderRequest.Pizza requestPizza : request.getPizzas()) {
                PizzaOrder.OrderDetail orderDetail = new PizzaOrder.OrderDetail();
                orderDetail.setName(requestPizza.getName());
                orderDetail.setQuantity(requestPizza.getQuantity());

                pizzaOrder.addOrderDetail(orderDetail);
            }
        }

        return pizzaOrder;
    }

    /**
     * Transform from {@link PizzaOrder} to {@link CreateOrderRequest}
     *
     * @param pizzaOrder {@link PizzaOrder} to be transformed
     * @return transformed {@link CreateOrderRequest}
     */
    public CreateOrderRequest transformToCreateOrderRequest(PizzaOrder pizzaOrder) {
        CreateOrderRequest request = new CreateOrderRequest();
        request.setOrderReferenceNo(pizzaOrder.getOrderReferenceNo());
        request.setOrderTime(pizzaOrder.getOrderTime());

        if (pizzaOrder.getOrderDetails() != null) {
            for (PizzaOrder.OrderDetail orderDetail : pizzaOrder.getOrderDetails()) {
                CreateOrderRequest.OrderDetail requestOrderDetail = new CreateOrderRequest.OrderDetail();
                requestOrderDetail.setName(orderDetail.getName());
                requestOrderDetail.setQuantity(orderDetail.getQuantity());
                requestOrderDetail.setPrice(orderDetail.getSubtotal());

                request.addOrderDetail(requestOrderDetail);
            }
        }

        return request;
    }

    /**
     * Transform from {@link PizzaOrder} to {@link PlaceOrderResponse}
     *
     * @param pizzaOrder {@link PizzaOrder} to be transformed
     * @return transformed {@link PlaceOrderResponse}
     */
    public PlaceOrderResponse transformToPlaceOrderResponse(PizzaOrder pizzaOrder) {
        PlaceOrderResponse response = new PlaceOrderResponse();
        response.setOrderTransNo(pizzaOrder.getOrderTransactionNo());
        response.setTotalPrice(pizzaOrder.getTotalPrice());

        return response;
    }

    /**
     * Update {@link PizzaOrder} from {@link CreateOrderResponse}
     *
     * @param pizzaOrder {@link PizzaOrder} to be updated
     * @param response {@link CreateOrderResponse}
     */
    public void updatePizzaOrder(PizzaOrder pizzaOrder, CreateOrderResponse response) {
        pizzaOrder.setOrderTransactionNo(response.getOrderTransNo());
        pizzaOrder.setTotalPrice(response.getTotalPrice());
    }

}
