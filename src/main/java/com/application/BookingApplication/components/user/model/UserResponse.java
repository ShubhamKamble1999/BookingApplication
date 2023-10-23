package com.application.BookingApplication.components.user.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponse {
    private Boolean status;
    private String message;
    private Object data;
}
