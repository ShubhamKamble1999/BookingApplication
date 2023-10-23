package com.application.BookingApplication.components.bike.model;

import com.application.BookingApplication.components.user.model.UserModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "bike")
public class BikeModel {
    @Id
    private String id;
    private String bikeId;
    private String bikeName;
    private String bikeModel;

}
