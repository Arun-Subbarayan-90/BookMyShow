package com.scaler.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupResponseDTO {
    private Long UserId;

    private ResponseStatus  responseStatus;
}
