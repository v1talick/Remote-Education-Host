package com.phd.RemoteEducationHost.security.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    String jwt;
    String message;
    Boolean status;
}
