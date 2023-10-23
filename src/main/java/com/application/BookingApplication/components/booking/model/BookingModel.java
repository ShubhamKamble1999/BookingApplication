package com.application.BookingApplication.components.booking.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "booking")
public class BookingModel {
    @Id
    private String id;
    private String userName;
    private String userId;
    private String userEmail;
    private String date;
    private String vehicleModel;
    private String paymentId;
    private String orderId;
    private String amount;
    private String signatureId;
}
