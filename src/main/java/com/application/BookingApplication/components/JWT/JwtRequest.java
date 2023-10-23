package com.application.BookingApplication.components.JWT;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtRequest {
    private String userId;
    private String password;
}
