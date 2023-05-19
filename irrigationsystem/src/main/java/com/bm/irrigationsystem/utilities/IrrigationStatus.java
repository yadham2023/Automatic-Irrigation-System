package com.bm.irrigationsystem.utilities;

import com.bm.irrigationsystem.exception.InvalidStatusException;

public enum IrrigationStatus {
    IDLE, IRRIGATING, FAILED, COMPLETED;

    public static IrrigationStatus fromValue(String type) {
        for (IrrigationStatus e : values()) {
            if (e.name().equals(type)) {
                return e;
            }
        }
        throw new InvalidStatusException(IrrigationStatus.values());
    }
}
