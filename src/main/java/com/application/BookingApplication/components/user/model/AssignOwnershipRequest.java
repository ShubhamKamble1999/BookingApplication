package com.application.BookingApplication.components.user.model;

import lombok.Data;

@Data
public class AssignOwnershipRequest {
    private String userId;
    private String[] bikeId;
}
