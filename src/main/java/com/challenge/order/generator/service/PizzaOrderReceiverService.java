package com.challenge.order.generator.service;

import com.challenge.order.generator.bean.common.PizzaOrder;
import com.challenge.order.generator.bean.outgoing.request.CreateOrderRequest;
import com.challenge.order.generator.bean.outgoing.response.CreateOrderResponse;
import com.challenge.order.generator.config.property.PizzaOrderReceiverProperties;
import com.challenge.order.generator.converter.PizzaOrderDataConverter;
import com.challenge.order.generator.exception.ApiCallException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PizzaOrderReceiverService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PizzaOrderReceiverService.class);

    @Autowired
    private PizzaOrderReceiverProperties pizzaOrderReceiverProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PizzaOrderDataConverter pizzaOrderDataConverter;

    /**
     * Create order to Pizza Order Receiver
     *
     * @param pizzaOrder pizza order to be created
     * @return {@link CreateOrderResponse}
     * @throws Exception error occurred during API call
     */
    public CreateOrderResponse createOrder(PizzaOrder pizzaOrder) throws Exception {
        JsonMapper jsonMapper = new JsonMapper();

        // Setup request headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        // Setup request body
        CreateOrderRequest requestBody = pizzaOrderDataConverter.transformToCreateOrderRequest(pizzaOrder);
        HttpEntity<String> request = new HttpEntity<>(jsonMapper.writeValueAsString(requestBody), headers);

        // Call service
        try {
            String requestUrl = pizzaOrderReceiverProperties.getBasePath() + pizzaOrderReceiverProperties.getRelativePath().getCreatePizzaOrder();
            LOGGER.debug("Request URL for creating pizza order: {}", requestUrl);
            ResponseEntity<String> response = restTemplate.postForEntity(requestUrl, request, String.class);

            String responseBody = response.getBody();
            if (response.getStatusCode().is2xxSuccessful()) {
                LOGGER.debug("Call create order API succeed. response: {}", responseBody);
                return jsonMapper.readValue(responseBody, CreateOrderResponse.class);
            } else {
                LOGGER.error("Call create order API failed. Status code: {}, response: {}", response.getStatusCode(), responseBody);
                throw new ApiCallException("Fail to call Create Pizza Order API from Pizza Order Receiver");
            }
        } catch (Exception e) {
            LOGGER.error("Unexpected error occurred when calling create order API.", e);
            throw new ApiCallException("Fail to call Create Pizza Order API from Pizza Order Receiver");
        }
    }

}
