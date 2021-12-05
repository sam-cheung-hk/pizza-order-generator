package com.challenge.order.generator.converter;

import com.challenge.order.generator.bean.common.PizzaOrder;
import com.challenge.order.generator.bean.incoming.request.PlaceOrderRequest;
import com.challenge.order.generator.bean.incoming.response.PlaceOrderResponse;
import com.challenge.order.generator.bean.outgoing.request.CreateOrderRequest;
import com.challenge.order.generator.bean.outgoing.response.CreateOrderResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PizzaOrderDataConverterTests {

    @Autowired
    private PizzaOrderDataConverter pizzaOrderDataConverter;

    @Test
    public void test_TransformPlaceOrderRequestToPizzaOrder() {
        // Test 1
        PlaceOrderRequest.Pizza request1Pizza1 = new PlaceOrderRequest.Pizza();
        request1Pizza1.setName("PIZZA_TYPE_A");
        request1Pizza1.setQuantity(1);

        PlaceOrderRequest request1 = new PlaceOrderRequest();
        request1.setPizzas(new ArrayList<>());
        request1.getPizzas().add(request1Pizza1);

        PizzaOrder pizzaOrder1 = pizzaOrderDataConverter.transformToPizzaOrder(request1);

        Assert.assertNotNull(pizzaOrder1);
        Assert.assertEquals(pizzaOrder1.getOrderDetails().size(), 1);

        PizzaOrder.OrderDetail pizzaOrder1Detail1 = pizzaOrder1.getOrderDetails().get(0);
        Assert.assertEquals(pizzaOrder1Detail1.getName(), "PIZZA_TYPE_A");
        Assert.assertEquals(pizzaOrder1Detail1.getQuantity().intValue(), 1);

        // Test 2
        PlaceOrderRequest.Pizza request2Pizza1 = new PlaceOrderRequest.Pizza();
        request2Pizza1.setName("PIZZA_TYPE_A");
        request2Pizza1.setQuantity(1);

        PlaceOrderRequest.Pizza request2Pizza2 = new PlaceOrderRequest.Pizza();
        request2Pizza2.setName("PIZZA_TYPE_B");
        request2Pizza2.setQuantity(2);

        PlaceOrderRequest request2 = new PlaceOrderRequest();
        request2.setPizzas(new ArrayList<>());
        request2.getPizzas().add(request2Pizza1);
        request2.getPizzas().add(request2Pizza2);

        PizzaOrder pizzaOrder2 = pizzaOrderDataConverter.transformToPizzaOrder(request2);

        Assert.assertNotNull(pizzaOrder2);
        Assert.assertEquals(pizzaOrder2.getOrderDetails().size(), 2);

        for (PizzaOrder.OrderDetail orderDetail : pizzaOrder2.getOrderDetails()) {
            Assert.assertTrue("PIZZA_TYPE_A".equals(orderDetail.getName()) || "PIZZA_TYPE_B".equals(orderDetail.getName()));
            if ("PIZZA_TYPE_A".equals(orderDetail.getName())) {
                // PIZZA_TYPE_A
                Assert.assertEquals(orderDetail.getQuantity().intValue(), 1);
            } else {
                // PIZZA_TYPE_B
                Assert.assertEquals(orderDetail.getQuantity().intValue(), 2);
            }
        }
    }

    @Test
    public void test_TransformPizzaOrderToCreateOrderRequest() {
        // Test 1
        ZonedDateTime zonedDateTime1 = ZonedDateTime.now();

        PizzaOrder pizzaOrder1 = new PizzaOrder();
        pizzaOrder1.setOrderReferenceNo("1");
        pizzaOrder1.setOrderTime(zonedDateTime1);
        pizzaOrder1.setOrderDetails(new ArrayList<>());

        PizzaOrder.OrderDetail pizzaOrder1Detail1 = new PizzaOrder.OrderDetail();
        pizzaOrder1Detail1.setName("PIZZA_TYPE_A");
        pizzaOrder1Detail1.setQuantity(1);
        pizzaOrder1Detail1.setSubtotal(BigDecimal.valueOf(100L));
        pizzaOrder1.getOrderDetails().add(pizzaOrder1Detail1);

        CreateOrderRequest request1 = pizzaOrderDataConverter.transformToCreateOrderRequest(pizzaOrder1);

        Assert.assertNotNull(request1);
        Assert.assertEquals(request1.getOrderReferenceNo(), "1");
        Assert.assertEquals(ZonedDateTime.parse(request1.getOrderTime()).toEpochSecond(), zonedDateTime1.toEpochSecond());
        Assert.assertEquals(request1.getOrderDetails().size(), 1);

        CreateOrderRequest.OrderDetail request1Detail1 = request1.getOrderDetails().get(0);
        Assert.assertEquals(request1Detail1.getName(), "PIZZA_TYPE_A");
        Assert.assertEquals(request1Detail1.getQuantity().intValue(), 1);
        Assert.assertEquals(request1Detail1.getPrice().intValue(), 100);

        // Test 2
        ZonedDateTime zonedDateTime2 = ZonedDateTime.now();

        PizzaOrder pizzaOrder2 = new PizzaOrder();
        pizzaOrder2.setOrderReferenceNo("2");
        pizzaOrder2.setOrderTime(zonedDateTime2);
        pizzaOrder2.setOrderDetails(new ArrayList<>());

        PizzaOrder.OrderDetail pizzaOrder2Detail1 = new PizzaOrder.OrderDetail();
        pizzaOrder2Detail1.setName("PIZZA_TYPE_A");
        pizzaOrder2Detail1.setQuantity(1);
        pizzaOrder2Detail1.setSubtotal(BigDecimal.valueOf(100L));
        pizzaOrder2.getOrderDetails().add(pizzaOrder2Detail1);

        PizzaOrder.OrderDetail pizzaOrder2Detail2 = new PizzaOrder.OrderDetail();
        pizzaOrder2Detail2.setName("PIZZA_TYPE_B");
        pizzaOrder2Detail2.setQuantity(2);
        pizzaOrder2Detail2.setSubtotal(BigDecimal.valueOf(200L));
        pizzaOrder2.getOrderDetails().add(pizzaOrder2Detail2);

        CreateOrderRequest request2 = pizzaOrderDataConverter.transformToCreateOrderRequest(pizzaOrder2);

        Assert.assertNotNull(request2);
        Assert.assertEquals(request2.getOrderReferenceNo(), "2");
        Assert.assertEquals(ZonedDateTime.parse(request2.getOrderTime()).toEpochSecond(), zonedDateTime2.toEpochSecond());
        Assert.assertEquals(request2.getOrderDetails().size(), 2);

        for (CreateOrderRequest.OrderDetail orderDetail : request2.getOrderDetails()) {
            Assert.assertTrue("PIZZA_TYPE_A".equals(orderDetail.getName()) || "PIZZA_TYPE_B".equals(orderDetail.getName()));
            if ("PIZZA_TYPE_A".equals(orderDetail.getName())) {
                // PIZZA_TYPE_A
                Assert.assertEquals(orderDetail.getQuantity().intValue(), 1);
                Assert.assertEquals(orderDetail.getPrice().intValue(), 100);
            } else {
                // PIZZA_TYPE_B
                Assert.assertEquals(orderDetail.getQuantity().intValue(), 2);
                Assert.assertEquals(orderDetail.getPrice().intValue(), 200);
            }
        }
    }

    @Test
    public void test_TransformPizzaOrderToPlaceOrderResponse() {
        PizzaOrder pizzaOrder = new PizzaOrder();
        pizzaOrder.setOrderTransactionNo("12345");
        pizzaOrder.setTotalPrice(BigDecimal.valueOf(150L));

        PlaceOrderResponse response = pizzaOrderDataConverter.transformToPlaceOrderResponse(pizzaOrder);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getOrderTransNo(), "12345");
        Assert.assertEquals(response.getTotalPrice().intValue(), 150);
    }

    @Test
    public void test_UpdatePizzaOrderFromCreateOrderResponse() {
        CreateOrderResponse response = new CreateOrderResponse();
        response.setOrderTransNo("12345");
        response.setTotalPrice(BigDecimal.valueOf(50L));

        PizzaOrder pizzaOrder = new PizzaOrder();
        pizzaOrderDataConverter.updatePizzaOrder(pizzaOrder, response);

        Assert.assertEquals(pizzaOrder.getOrderTransactionNo(), "12345");
        Assert.assertEquals(pizzaOrder.getTotalPrice().intValue(), 50);
    }

}
