package com.kofteciyusuf.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("OrderProducts")
public class OrderProduct {

    @Id
    private String id;
    private Date createDate;
    private Date updateDate;
    private boolean isDeleted;
    private String productId;
    //product price eklemeliyiz. Product fiyatları değişirse diye.
    private int currentProductPrice;
    private String orderId;
    private int quantity;

}
