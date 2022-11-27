package com.cagan.orderservice.rest.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateOrderRequest {

    @NotNull
    @NotEmpty
    private String productId;
}
