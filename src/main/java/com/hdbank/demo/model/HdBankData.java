package com.hdbank.demo.model;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class HdBankData {

    @NotNull(message = "Internal id is empty !!!")
    private String internalId;

    @NotNull(message = "Request id is empty !!!")
    private String requestId;

    @NotNull(message = "Request time is empty !!!")
    private String requestTime;

    @Max(value = 10, message = "Phone number maximum 10 !!!")
    private String phoneNumber;
}
