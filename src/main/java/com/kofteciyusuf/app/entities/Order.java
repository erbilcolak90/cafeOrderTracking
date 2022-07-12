package com.kofteciyusuf.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
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
    @NotEmpty
    private List<OrderProduct> orderProductList;
    private String status;
    private boolean isComplated;
    private int total;

    public int calculateTotal(){
        int total = 0;
        for(OrderProduct orderProduct: orderProductList){
            total = total + orderProduct.getCurrentProductPrice();
        }
        return  total;
    }

}
