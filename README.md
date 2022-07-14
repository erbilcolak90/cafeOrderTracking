# cafeOrderTracking

For use in cafeOrderTracking project, cafe-restaurant etc. workplaces
It is an order and order tracking system created. The table number of the customers
It includes creating an order for the table by processing and processing customer orders.
The ordered products are kept in the ticket with the product ID and show the product information of the order and the total amount.

## Entities

- Product,
- OrderProduct,
- Order,
- Desk

### Product

It is the class created for the product in the system.

- Id: `String`
- createDate: `Date`
- updateDate: `Date`
- isDeleted: `boolean`
- name: `String`
- price: `int`

### Order

It is the class created for the Order

- Id: `String`
- createDate: `Date`
- updateDate: `Date`
- isDeleted: `boolean`
- deskId: `String`
- orderProductList: `List<OrderProduct>`
- status: `boolean`
- isComplated: `boolean`
- total: `int`

_Notes:_ OrderProductList is not entered while creating the order. When the customer places an order. Product information is added later.

### OrderProduct

It is the class that contains the information of the product to be ordered.

- Id: `String`
- createDate: `Date`
- updateDate: `Date`
- isDeleted: `boolean`
- productId: `String`
- currentProductPrice: `int`
- orderId: `String`
- quantity: `int`

_Notes:_ What can be done about the price field in the product class currentProductPrice so that updates do not affect historical data in the database. held by the variable. Thus, the confusion that price changes may create in the database in possible updates is prevented.

### Desk

It is the class that contains the information of the desk.

- Id: `String`
- createDate: `Date`
- updateDate: `Date`
- isDeleted: `boolean`
- tableName: `String`
- activeOrderId: `String`

_Notes:_ It contains information about whether there is an active order on the table. The created order information is kept with activeOrderId.

# Rest Api

```
RestController("/products")
RestController("/orders")
RestController("/orderProduct")
RestController("/desk")
```

## Add Product

### Request

```
method: POST
url: /products/addProduct
requestSample: http://localhost:8080/products/addProduct
requestParams: -
requestBody: {
  name: String,
  price: int
}
```

### Response

```
{
  "success": true,
  "message": "Product added",
  "data": null
}
```

---

## Change Product Price

### Request

```
method: POST
url: /products/changeProductPrice
requestSample: http://localhost:8080/products/changeProductPrice?productId=1&productPrice=20
requestParams: productId: String, productPrice: int
requestBody: -
```

### Response

```
{
  "success": true,
  "message": "Product price changed",
  "data": {
    "id": "1",
    "createDate": "2022-07-04T20:54:29.559+00:00",
    "updateDate": "2022-07-14T11:14:30.307+00:00",
    "name": "Köfte Izgara",
    "price": 110,
    "deleted": false
  }
}
```

---

## Get a specific Product

### Request

```
method: GET
url: /products/getProduct
requestSample: http://localhost:8080/products/getProduct?productId=1
requestParams: productId: String
requestBody: -
```

### Response

```
{
  "success": true,
  "message": "Product listed",
  "data": {
    "id": "1",
    "createDate": "2022-07-04T20:54:29.559+00:00",
    "updateDate": "2022-07-14T11:14:30.307+00:00",
    "name": "Köfte Izgara",
    "price": 110,
    "deleted": false
  }
}
```

---

## Get All Products

### Request

```
method: GET
url: /products/getAllProduct
requestSample: http://localhost:8080/products/getAllProduct
requestParams: -
requestBody: -
```

### Response

```
  "success": true,
  "message": "Products listed",
  "data": [
    {
      "id": "1",
      "createDate": "2022-07-04T20:54:29.559+00:00",
      "updateDate": "2022-07-14T11:14:30.307+00:00",
      "name": "Köfte Izgara",
      "price": 110,
      "deleted": false
    },
  ]
```

---

## Page Product List

### Request

```
method: GET
url: /products/pageableProductList
requestSample: ttp://localhost:8080/products/pageableProductList?number=1&size=10
requestParams: pageNumber: int, pageSize: int
requestBody: -
```

### Response

```
{
  "success": true,
  "message": "Page(s) listed",
  "data": {
    "content": [
      {
        "id": "1",
        "createDate": "2022-07-04T20:57:52.808+00:00",
        "updateDate": "2022-07-05T16:57:03.433+00:00",
        "name": "Köfte Et Karışık Izgara",
        "price": 120,
        "deleted": false
      }
    ],
    "pageable": {
      "sort": {
        "empty": false,
        "unsorted": false,
        "sorted": true
      },
      "offset": 15,
      "pageNumber": 3,
      "pageSize": 5,
      "paged": true,
      "unpaged": false
    },
    "last": false,
    "totalElements": 30,
    "totalPages": 6,
    "size": 5,
    "number": 3,
    "sort": {
        "empty": false,
        "unsorted": false,
        "sorted": true
    },
    "first": false,
    "numberOfElements": 5,
    "empty": false
  }
}
```

---

## Delete Product

---

### Request

```
method: POST
url: /products/deleteProduct
requestSample: ttp://localhost:8080/products/deleteProduct?productId=1
requestParams: productId : String
requestBody: -
```

### Response

```
{
  "success": true,
  "message": "Product deleted",
  "data": null
}
```

---

## Create Order

---

### Request

```
method: POST
url: /orders/createOrder
requestSample:/ http://localhost:8080/orders/createOrder
requestParams : -
requestBody: {
    deskId: int
}
```

### Response


```
{
    "success": true,
    "message": "Order Created",
    "data": {
        "id": "1",
        "createDate": "2022-07-14T11:18:51.189+00:00",
        "updateDate": "2022-07-14T11:18:51.189+00:00",
        "deskId": "2",
        "orderProductList": [],
        "status": "WAITING",
        "total": 0,
        "complated": false,
        "deleted": false
    }
}
```

---

## Complete Order

---

### Request

```
method: PUT
url: /orders/completeOrder
requestSample:/ http://localhost:8080/orders/completeOrder?orderId=1
requestParams : orderId : String
requestBody: -
```

### Response

```
{
    "success": true,
    "message": "Orders listed",
    "data": [
        {
            "id": "62cc385eb2d856298d2ff66e",
            "createDate": "2022-07-11T14:49:02.345+00:00",
            "updateDate": "2022-07-13T14:36:35.633+00:00",
            "deskId": "",
            "orderProductList": [
                {
                    "id": null,
                    "createDate": null,
                    "updateDate": null,
                    "productId": "62c35385ffab2d56bb689d86",
                    "currentProductPrice": 120,
                    "orderId": null,
                    "quantity": 1,
                    "deleted": false
                },
                {
                    "id": "62cc38dfb2d856298d2ff66f",
                    "createDate": "2022-07-11T14:51:11.943+00:00",
                    "updateDate": "2022-07-11T14:51:11.943+00:00",
                    "productId": "62c35385ffab2d56bb689d86",
                    "currentProductPrice": 50,
                    "orderId": "62cc385eb2d856298d2ff66e",
                    "quantity": 1,
                    "deleted": false
                },
               ...
               }
            ],
            "status": "CANCEL",
            "total": 570,
            "complated": false,
            "deleted": true
        },
       ....
       }
    ]
}
```

---

## Change Order Desk

---

### Request

```
method: PUT
url: /orders/changeOrderDesk
requestSample:/ http://localhost:8080/orders/changeToOrderDesk?orderId=1
requestParams : orderId : String
requestBody: -
```

### Response

```
{
    "success": true,
    "message": "Desk changed",
    "data": null
}
```

---

## Get specific Order

---

### Request

```
method: GET
url: /orders/getOrder
requestSample:/ http://localhost:8080/orders/getOrder?orderId=1
requestParams : orderId : String
requestBody: -
```

### Response

```
{
    "success": true,
    "message": "Order listed",
    "data": {
        "id": "1",
        "createDate": "2022-07-05T13:26:43.478+00:00",
        "updateDate": "2022-07-12T21:03:08.839+00:00",
        "deskId": null,
        "orderProductList": [
            {
                "id": "2",
                "createDate": "2022-07-05T13:52:35.812+00:00",
                "updateDate": "2022-07-05T13:52:35.812+00:00",
                "productId": "3",
                "currentProductPrice": 0,
                "orderId": "1",
                "quantity": 4,
                "deleted": false
            },
          ...
          }
        ],
        "status": "COMPLETE",
        "total": 0,
        "complated": true,
        "deleted": true
    }
}
```

---

## Get All Order List

---

### Request

```
method: GET
url: /orders/getAllOrder
requestSample:/ http://localhost:8080/orders/getAllOrders
requestParams : -
requestBody: -
```

### Response

```
{
    "id": "1",
    "createDate": "2022-07-11T14:49:02.345+00:00",
    "updateDate": "2022-07-13T14:13:17.442+00:00",
    "deskId": "1",
    "orderProductList": [
        {
        {
            "id": "2",
            "createDate": "2022-07-11T14:51:11.943+00:00",
            "updateDate": "2022-07-11T14:51:11.943+00:00",
            "productId": "3",
            "currentProductPrice": 50,
            "orderId": "1",
            "quantity": 1,
            "deleted": false
        }
    ],

    ...

    "status": "COMPLETE",
    "total": 570,
    "deleted": false,
    "complated": true
}
```

---

## Delete Order

---

### Request

```
method: DELETE
url: /orders/deleteOrder
requestSample:/ http://localhost:8080/orders/deleteOrder?orderId=1
requestParams : orderId : String
requestBody: -
```

### Response

```
{
    "success": true,
    "message": "Order deleted",
    "data": null
}
```

---

## Add OrderProduct

---

### Request

```
method: POST
url: /orderProducts/addOrderProduct
requestSample:/ http://localhost:8080/orderProducts/addOrderProduct
requestParams : -
requestBody: 
{ productId: String
  orderId: String
  quantity: int }
```


### Response

---

```
{
    "success": true,
    "message": "OrderProduct added",
    "data": {
        "id": "1",
        "createDate": "2022-07-14T17:25:54.919+00:00",
        "updateDate": "2022-07-14T17:27:05.858+00:00",
        "deskId": "2",
        "orderProductList": [
            {
                "id": "3",
                "createDate": "2022-07-14T17:27:05.723+00:00",
                "updateDate": "2022-07-14T17:27:05.723+00:00",
                "productId": "4",
                "currentProductPrice": 110,
                "orderId": "1",
                "quantity": 17,
                "deleted": false
            }
        ],
        "status": "WAITING",
        "total": 0,
        "deleted": false,
        "complated": false
    }
}
```

---

## Get All OrderProduct

---

### Request

```
method: GET
url: /orderProducts/getAllOrderProduct
requestSample:/ http://localhost:8080/orderProducts/getAllOrderProduct
requestParams : -
requestBody: -
```
### Response

---

```
{
    "success": true,
    "message": "OrderProduct listed",
    "data": [
        {
            "id": "62c43d2e859caa5dd5883e58",
            "createDate": "2022-07-05T13:31:26.552+00:00",
            "updateDate": "2022-07-05T13:31:26.552+00:00",
            "productId": "62c35385ffab2d56bb689d86",
            "currentProductPrice": 180,
            "orderId": "62c43c13859caa5dd5883e57",
            "quantity": 2,
            "deleted": false
        },
       ...
    ]
}
```

---

## Delete specific OrderProduct

---

### Request

```
method: DELETE
url: /orderProducts/deleteOrderProduct
requestSample:/ http://localhost:8080/orderProducts/deleteOrderProduct?orderProductId=
requestParams : orderProductId : String
requestBody: -
```

### Response

---

```
{
    "success": true,
    "message": "OrderProduct deleted",
    "data": null
}
```

---

## Add Desk

---

### Request

```
method: POST
url: /desks/addDesk
requestSample:/ http://localhost:8080/desks/addDesk
requestParams : -
requestBody: {
    tableName : String
}
```
### Response

---

```
{
    "success": true,
    "message": "Desk added",
    "data": null
}
```

---

## Get All Desks

---

### Request

```
method: GET
url: /desks/getAllDesks
requestSample:/ http://localhost:8080/desks/getAllDesks?
requestParams : -
requestBody: -
```

### Response

---

```
{
    "success": true,
    "message": "Desks listed",
    "data": [
        {
            "id": "1",
            "createDate": "2022-07-04T20:54:29.559+00:00",
            "updateDate": "2022-07-14T17:25:55.761+00:00",
            "tableName": "masa 1",
            "activeOrderId": "",
            "deleted": false
        },
       ...
    ]
}
```

---

## Delete Desk

---

### Request

```
method: DELETE
url: /desks/deleteDesk
requestSample:/ http://localhost:8080/desks/deleteDesk?deskId=1
requestParams : deskId : String
requestBody: -
```

### Response

---

```
{
    "success": true,
    "message": "Desk deleted",
    "data": null
}
```

---
