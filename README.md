# Pizza Order Generator

**Default Application Port:** 8081  
**API Doc:** https://127.0.0.1:8081/pizza-order-generator/swagger-ui.html

## Possible Pizza

| Pizza Name | Price |
| ---------- | ----- |
| PIZZA_TYPE_A | 100 |
| PIZZA_TYPE_B | 90 |
| PIZZA_TYPE_C | 60 |

## Steps for Startup

1. Build and run **Pizza Order Receiver**
2. Build and run **Pizza Order Generator**
3. Test API `https://127.0.0.1:8081/pizza-order-generator/order/place` using POST

Sample Request Body:
```
{
  "pizzas": [
        {
            "name": "PIZZA_TYPE_A",
            "quantity": 1
        },
        {
            "name": "PIZZA_TYPE_C",
            "quantity": 3
        }
  ]
}
```

## Assumption

- Storing keystore on GIT is just for demo purpose.
- Storing password on GIT is just for demo purpose. They are expected to be stored in Secret on k8s/Openshift.

## Error Code

Error code is formed by `E(Service Number)_(Status Code)_(Sequence Number)` and is used for developers to identify the exact issue.  
Service Number 1 refers to Pizza Order Receiver  
Service Number 2 refers to Pizza Order Generator

## Possible Enhancement

- Currently, HTTPs is used to secure the connection between services. In order to increase the security level, basic auth or client credentials grant flow can be added.
- Concurrency issue for concurrent pizza order creation.
