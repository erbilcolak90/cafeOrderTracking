package com.kofteciyusuf.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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
    @NonNull
    private String productId;
    //product price eklemeliyiz. Product fiyatları değişirse diye.
    @NonNull
    private int currentProductPrice;
    @NonNull
    private String orderId;
    @NonNull
    private int quantity;

}
