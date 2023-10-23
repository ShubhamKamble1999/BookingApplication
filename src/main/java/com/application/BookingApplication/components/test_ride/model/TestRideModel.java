package com.application.BookingApplication.components.test_ride.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "test-ride")
public class TestRideModel {
    @Id
    private String id;
    private String testRideId;
    private String fullname;
    private String bikeId;
    private String phoneNumber;
    private String email;
    private String location;
    private Date date;
}
