package com.bm.irrigationsystem.exception;

import com.bm.irrigationsystem.utilities.IrrigationStatus;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidStatusException extends RuntimeException {
    public InvalidStatusException(IrrigationStatus[] irrigationStatuses) {
        super("invalid Status accepted list is "+ Arrays.toString(irrigationStatuses));
    }
}
