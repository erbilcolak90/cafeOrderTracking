# cafeOrderTracking

## proje tanıtımı nasıl çalıştığı yazılacak

### Turkish
cafeOrderTracking projesi,cafe-restorant vb iş yerlerinde kullanılması için
oluşturulmuş sipariş ve sipariş takip sistemidir.Müşterilerin masa numarası, sisteme
işlenerek masaya ait sipariş oluşturulması ve müşteri siparişlerinin işlenmesini içerir.
Sipariş edilen ürünler, ürün Id si ile adisyonda tutulur ve siparişe ait ürün bilgisi ile toplam tutarı gösterir.

### English
For use in cafeOrderTracking project, cafe-restaurant etc. workplaces
It is an order and order tracking system created. The table number of the customers
It includes creating an order for the table by processing and processing customer orders.
The ordered products are kept in the ticket with the product ID and show the product information of the order and the total amount.


## entities

- Product,
- OrderProduct,
- Order,
- Desk

### Product
It is the class created for the product in the system.

- Id;
- createDate;
- updateDate;
- isDeleted;
- name;
- price;
- 
contains information

## Order
It is the class created for the Order
- id;
- createDate;
- updateDate;
- isDeleted;
- deskId;
- orderProductList;
- status;
- isComplated;
- total;
- 
contains information

OrderProductList is not entered while creating the order. When the customer places an order
Product information is added later.

## OrderProduct
It is the class that contains the information of the product to be ordered.
- Id;
- createDate;
- updateDate;
- isDeleted;
- productId;
- currentProductPrice;
- orderId;
- quantity;

contains information.

What can be done about the price field in the product class
  currentProductPrice so that updates do not affect historical data in the database.
  held by the variable. Thus, the confusion that price changes may create in the database in possible updates is prevented.
## Desk
- id;
- createDate;
- updateDate;
- isDeleted;
- tableName;
- activeOrderId;

contains information.

It contains information about whether there is an active order on the table.
The created order information is kept with activeOrderId.

# Rest Api

```
RestController("/products")
RestController("/orders")
RestController("/orderProduct")
RestController("/desk")
```

---------

## Add Product

----------

## Request


### /addProduct

`Post /addProduct/`

----------

`
url: application/json'  @Valid @RequestBody ('name','price') http://localhost:8080/products/addProduct
`

---------------

### Response

---------

```` {
{
    "success": true,
    "message": "Product added",
    "data": null
}
````

--------

## Change Product Price

---------

## Request


`Post /changeProductPrice`

-----------

`url: application/json' @Valid @RequestParam (productId,productPrice) http://localhost:8080/products/changeProductPrice?id=1&price=100`

----------

### Response

----------

````
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
````

----------

## Get a specific Product

---------

### Request 

`Get /getProduct`

---------

`
url: application/json' @Valid @RequestParam ('productId')  http://localhost:8080/products/getProduct?productId=1
`

### Response

-----------

````
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
````
----------


## Get All Products

----------

### Request

`Get /getAllProduct/`

-----------
`
url : application/json' http://localhost:8080/products/getAllProducts
`

--------

### Response

-------

````

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
        {
            "id": "2",
            "createDate": "2022-07-04T20:54:56.183+00:00",
            "updateDate": "2022-07-13T13:42:43.979+00:00",
            "name": "Sucuk Izgara",
            "price": 55,
            "deleted": true
        },
        ....
        }
````

-----------

## Page Product List

-------------

### Request

`Get /pageableProductList/`

------------

`
url : application/json' (@RequestParam int pageNumber,@RequestParam int pageSize) http://localhost:8080/products/pageableProductList?number&size
`
---------

### Response

````
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
            },
            {
                "id": "2",
                "createDate": "2022-07-04T20:56:18.502+00:00",
                "updateDate": "2022-07-04T20:56:18.502+00:00",
                "name": "Kuzu Şiş",
                "price": 70,
                "deleted": false
            },
           
           .....
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
````

------------

## Delete Product

---------

### Request

`Delete /deleteProduct`

------------

`
url: application/json' @Valid @RequestParam ('productId')  http://localhost:8080/products/deleteProduct?id=1
`
----------

### Response


-------

````
{
    "success": true,
    "message": "Product deleted",
    "data": null
}
````

-----------

## Create Order

---------

### Request

`Post /createOrder`

----------

`
url: application/json' @Valid @RequestParam ('productId')  http://localhost:8080/products/deleteProduct?id=1
`
----------

### Response


-------

````
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
````

-----------

## Complete Order

---------

### Request

`Put /completeOrder`

---------

`
url: application/json' @Valid @RequestParam ('orderId')  http://localhost:8080/orders/completeOrder?orderId=1
`
----------

### Response


-------

````
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
````

-----------

## Create Order

---------


### Request

`Put /changeOrderDesk`

---------

`
url: application/json' @Valid @RequestParam ('orderId','deskId')  http://localhost:8080/orders/changeToOrderDesk?orderId=1&deskId=1
`
----------

### Response


-------

````
{
    "success": true,
    "message": "Desk changed",
    "data": null
}
````

-----------




## Get specific Order

---------

### Request

`Get /getOrder`

----------

`
url: application/json' @Valid @RequestParam ('orderId')  http://localhost:8080/orders/getOrder?orderId=
`
----------

### Response


-------

````
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
````

-----------

## Get All Order List

---------

### Request

`Get /getAllOrder`

-----------

`
url: application/json'  http://localhost:8080/orders/getAllOrders
`
----------

### Response


-------

````
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
````

-----------


## Delete Order

---------

### Request

`Delete /deleteOrder`

-------------

`
url: application/json' @Valid @RequestParam (orderId) http://localhost:8080/orders/deleteOrder?orderId=1
`
----------

### Response


-------

````
{
    "id": "1",
    "createDate": "2022-07-11T14:49:02.345+00:00",
    "updateDate": "2022-07-13T14:13:17.442+00:00",
    "deskId": "1",
    "orderProductList": [
        {  
        {
            "id": "62cc38dfb2d856298d2ff66f",
            "createDate": "2022-07-11T14:51:11.943+00:00",
            "updateDate": "2022-07-11T14:51:11.943+00:00",
            "productId": "62c35385ffab2d56bb689d86",
            "currentProductPrice": 50,
            "orderId": "62cc385eb2d856298d2ff66e",
            "quantity": 1,
            "deleted": false
        }
    ],
    ...
    
    "status": "CANCEL",
    "total": 570,
    "deleted": true,
    "complated": false
}
````

-----------

## Add OrderProduct

---------

### Request

`Post /addOrderProduct`

-------------

`
url: application/json' @Valid @RequestBody (productId,orderId,quantity) http://localhost:8080/orderProducts/addOrderProduct
`
----------

### Response


-------

````
{
    "success": true,
    "message": "OrderProduct added",
    "data": {
        "id": "1",
        "createDate": "2022-07-11T14:49:02.345+00:00",
        "updateDate": "2022-07-14T11:28:36.493+00:00",
        "deskId": "",
        "orderProductList": [
            {
                "id": null,
                "createDate": null,
                "updateDate": null,
                "productId": "2",
                "currentProductPrice": 120,
                "orderId": null,
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
    }
}
````

-----------

## Get All OrderProduct

---------

### Request

`Get /getAllOrderProduct`

-------------

`
url: application/json' http://localhost:8080/orderProducts/getAllOrderProducts
`
----------

### Response


-------

````
{
    "success": true,
    "message": "OrderProduct listed",
    "data": [
        {
            "id": "62c43d2e859caa5dd5883e58",
            "createDate": "2022-07-05T13:31:26.552+00:00",
            "updateDate": "2022-07-05T13:31:26.552+00:00",
            "productId": "62c35385ffab2d56bb689d86",
            "currentProductPrice": 0,
            "orderId": "62c43c13859caa5dd5883e57",
            "quantity": 2,
            "deleted": false
        },
        ...
        }
    ]
}
````

-----------

## Delete specific OrderProduct

---------

### Request

`Delete /deleteOrderProduct`

-------------

`
url: application/json' @Valid @RequestParam (orderProductId) http://localhost:8080/orderProducts/deleteOrderProduct?orderProductId=1
`
----------

### Response


-------

````
{
    "success": true,
    "message": "OrderProduct deleted",
    "data": null
}
````

-----------


## Add Desk

---------

### Request

`Post /addDesk`

-------------

`
url: application/json' @Valid @RequestBody (deskName) http://localhost:8080/desks/addDesk
`
----------

### Response


-------

````
{
    "success": true,
    "message": "Desk added",
    "data": null
}
````

-----------

## Get All Desks

---------

### Request

`Get /getAllDesks`

-------------

`
url: application/json' http://localhost:8080/desks/getAllDesks
`
----------

### Response


-------

````
{
    "success": true,
    "message": "Desks listed",
    "data": [
        {
            "id": "1",
            "createDate": "2022-07-04T20:54:29.559+00:00",
            "updateDate": "2022-07-14T11:23:29.213+00:00",
            "tableName": "masa 1",
            "activeOrderId": null,
            "deleted": false
        },
        ...
        }
    ]
}
````

-----------

## Delete Desk

---------

### Request

`Delete /deleteDesk`

-------------

`
url: application/json' @Valid @RequestParam (deskId)  http://localhost:8080/desks/deleteDesk
`
----------

### Response


-------

````
{
    "success": true,
    "message": "Desk deleted",
    "data": null
}
````

-----------



