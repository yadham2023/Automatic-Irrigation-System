package com.bm.irrigationsystem.utilities;

public enum SensorDeviceStatus {

    ACTIVE, INACTIVE;

    public static SensorDeviceStatus fromValue(String type) {
        for (SensorDeviceStatus e : values()) {
            if (e.name().equals(type)) {
                return e;
            }
        }
        return null;
    }
}
