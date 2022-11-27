package com.cagan.orderservice.rest.request;

import com.cagan.orderservice.domain.entity.Status;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateStatusRequest {
    @NotNull
    Status status;
}
