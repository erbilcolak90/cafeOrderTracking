package com.kofteciyusuf.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("Desks")
public class Desk {

    @Id
    private String id;
    private Date createDate;
    private Date updateDate;
    private boolean isDeleted;
    @NonNull
    private String tableName;
    @NonNull
    private String activeOrderId;

}
