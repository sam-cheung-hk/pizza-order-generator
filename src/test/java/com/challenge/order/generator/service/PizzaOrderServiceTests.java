package com.challenge.order.generator.service;

import com.challenge.order.generator.bean.common.PizzaOrder;
import com.challenge.order.generator.bean.outgoing.response.CreateOrderResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PizzaOrderServiceTests {

    @MockBean
    private PizzaOrderReceiverService pizzaOrderReceiverService;

    @Autowired
    private PizzaOrderService pizzaOrderService;

    @Test
    public void test_PlaceOrder() throws Exception {
        CreateOrderResponse response = new CreateOrderResponse();
        response.setOrderReferenceNo("54321");
        response.setOrderTime(ZonedDateTime.now().toString());
        response.setOrderTransNo("12345");
        response.setTotalPrice(BigDecimal.valueOf(50L));

        Mockito.when(pizzaOrderReceiverService.createOrder(Mockito.any()))
                .thenReturn(response);

        // Test 1
        PizzaOrder pizzaOrder1 = new PizzaOrder();
        pizzaOrder1.setOrderDetails(new ArrayList<>());

        PizzaOrder.OrderDetail pizzaOrder1Detail1 = new PizzaOrder.OrderDetail();
        pizzaOrder1Detail1.setName("PIZZA_TYPE_A");
        pizzaOrder1Detail1.setQuantity(1);
        pizzaOrder1.getOrderDetails().add(pizzaOrder1Detail1);

        pizzaOrderService.placeOrder(pizzaOrder1);

        Assert.assertNotNull(pizzaOrder1.getOrderReferenceNo());
        Assert.assertNotNull(pizzaOrder1.getOrderTime());
        Assert.assertEquals(pizzaOrder1.getOrderTransactionNo(), "12345");
        Assert.assertEquals(pizzaOrder1.getTotalPrice().intValue(), 50);
        Assert.assertEquals(pizzaOrder1Detail1.getSubtotal().intValue(), 100);

        // Test 2
        PizzaOrder pizzaOrder2 = new PizzaOrder();
        pizzaOrder2.setOrderDetails(new ArrayList<>());

        PizzaOrder.OrderDetail pizzaOrder2Detail1 = new PizzaOrder.OrderDetail();
        pizzaOrder2Detail1.setName("PIZZA_TYPE_A");
        pizzaOrder2Detail1.setQuantity(1);
        pizzaOrder2.getOrderDetails().add(pizzaOrder2Detail1);

        PizzaOrder.OrderDetail pizzaOrder2Detail2 = new PizzaOrder.OrderDetail();
        pizzaOrder2Detail2.setName("PIZZA_TYPE_B");
        pizzaOrder2Detail2.setQuantity(2);
        pizzaOrder2.getOrderDetails().add(pizzaOrder2Detail2);

        pizzaOrderService.placeOrder(pizzaOrder2);

        Assert.assertNotNull(pizzaOrder2.getOrderReferenceNo());
        Assert.assertNotNull(pizzaOrder2.getOrderTime());
        Assert.assertEquals(pizzaOrder2.getOrderTransactionNo(), "12345");
        Assert.assertEquals(pizzaOrder2.getTotalPrice().intValue(), 50);
        Assert.assertEquals(pizzaOrder2Detail1.getSubtotal().intValue(), 100);
        Assert.assertEquals(pizzaOrder2Detail2.getSubtotal().intValue(), 180);
    }

}
