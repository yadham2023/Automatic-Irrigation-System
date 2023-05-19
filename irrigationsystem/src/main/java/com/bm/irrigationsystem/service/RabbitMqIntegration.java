package com.bm.irrigationsystem.service;

import com.bm.irrigationsystem.datamodel.IrrigationRequest;
import com.bm.irrigationsystem.utilities.IntegrationModes;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqIntegration implements IntegrationMethod {

    @Override
    public String getType() {
        return IntegrationModes.RABBIT.value();
    }
    @Override
    public void irrigate(IrrigationRequest message) {
        System.out.println("Sending Message To RabbitMq Queue: " + message);
    }
}
