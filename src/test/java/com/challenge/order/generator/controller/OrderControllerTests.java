package com.challenge.order.generator.controller;

import com.challenge.order.generator.bean.outgoing.response.CreateOrderResponse;
import com.challenge.order.generator.exception.ApiCallException;
import com.challenge.order.generator.service.PizzaOrderReceiverService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PizzaOrderReceiverService pizzaOrderReceiverService;

    @Test
    public void test_PlaceOrder_Success() throws Exception {
        CreateOrderResponse response = new CreateOrderResponse();
        response.setOrderReferenceNo("654321");
        response.setOrderTime(ZonedDateTime.now().toString());
        response.setOrderTransNo("123456");
        response.setTotalPrice(BigDecimal.valueOf(400L));

        Mockito.when(pizzaOrderReceiverService.createOrder(Mockito.any()))
                .thenReturn(response);

        JsonMapper jsonMapper = new JsonMapper();

        ObjectNode pizzaDetail = jsonMapper.createObjectNode();
        pizzaDetail.put("name", "PIZZA_TYPE_A");
        pizzaDetail.put("quantity", 1);

        ObjectNode pizzaOrder = jsonMapper.createObjectNode();
        ArrayNode pizzaDetails = pizzaOrder.putArray("pizzas");
        pizzaDetails.add(pizzaDetail);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/order/place")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(pizzaOrder));

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(result.getResponse().getStatus(), 200);

        JsonNode responseBody = jsonMapper.readTree(result.getResponse().getContentAsString());

        Assert.assertNotNull(responseBody.get("orderTransNo"));
        Assert.assertNotNull(responseBody.get("totalPrice"));
        Assert.assertEquals(responseBody.get("orderTransNo").asText(), "123456");
        Assert.assertEquals(responseBody.get("totalPrice").asInt(), 400);
    }

    @Test
    public void test_PlaceOrder_Failure_Bad_Request() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/order/place")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(result.getResponse().getStatus(), 400);

        JsonMapper jsonMapper = new JsonMapper();
        JsonNode responseBody = jsonMapper.readTree(result.getResponse().getContentAsString());

        Assert.assertNotNull(responseBody.get("errorCode"));
        Assert.assertEquals(responseBody.get("errorCode").asText(), "E0002_400_0001");
    }

    @Test
    public void test_PlaceOrder_Failure_Resource_Not_Found() throws Exception {
        JsonMapper jsonMapper = new JsonMapper();

        ObjectNode pizzaDetail = jsonMapper.createObjectNode();
        pizzaDetail.put("name", "PIZZA_A");
        pizzaDetail.put("quantity", 1);

        ObjectNode pizzaOrder = jsonMapper.createObjectNode();
        ArrayNode pizzaDetails = pizzaOrder.putArray("pizzas");
        pizzaDetails.add(pizzaDetail);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/order/place")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(pizzaOrder));

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(result.getResponse().getStatus(), 404);

        JsonNode responseBody = jsonMapper.readTree(result.getResponse().getContentAsString());

        Assert.assertNotNull(responseBody.get("errorCode"));
        Assert.assertEquals(responseBody.get("errorCode").asText(), "E0002_404_0001");
    }

    @Test
    public void test_PlaceOrder_Failure_Call_API_Error() throws Exception {
         Mockito.when(pizzaOrderReceiverService.createOrder(Mockito.any()))
                .thenThrow(new ApiCallException("Mock API error"));

        JsonMapper jsonMapper = new JsonMapper();

        ObjectNode pizzaDetail = jsonMapper.createObjectNode();
        pizzaDetail.put("name", "PIZZA_TYPE_A");
        pizzaDetail.put("quantity", 1);

        ObjectNode pizzaOrder = jsonMapper.createObjectNode();
        ArrayNode pizzaDetails = pizzaOrder.putArray("pizzas");
        pizzaDetails.add(pizzaDetail);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/order/place")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(pizzaOrder));

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(result.getResponse().getStatus(), 500);

        JsonNode responseBody = jsonMapper.readTree(result.getResponse().getContentAsString());

        Assert.assertNotNull(responseBody.get("errorCode"));
        Assert.assertEquals(responseBody.get("errorCode").asText(), "E0002_500_0003");
    }
}
