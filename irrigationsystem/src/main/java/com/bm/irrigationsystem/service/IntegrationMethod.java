package com.bm.irrigationsystem.service;

import com.bm.irrigationsystem.datamodel.IrrigationRequest;

public interface IntegrationMethod {

    String getType();
    void irrigate(IrrigationRequest message);
}
