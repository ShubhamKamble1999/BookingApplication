package com.application.BookingApplication.components.user.model;

import lombok.Data;

import java.util.Date;

@Data
public class AssignTestDriveRequest {
    private String fullname;
    private String bikeId;
    private String contact;
    private String testRideId;
    private Date date;
}
