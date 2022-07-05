package com.kofteciyusuf.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("Orders")
public class Order {

    @Id
    private String id;
    private Date createDate;
    private Date updateDate;
    private boolean isDeleted;
    private String deskId;
    private List<OrderProduct> orderProductList;
    private String status;
    private boolean isComplated;

}
