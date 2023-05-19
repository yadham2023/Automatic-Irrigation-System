package com.bm.irrigationsystem.service;

import com.bm.irrigationsystem.datamodel.IrrigationRequest;
import com.bm.irrigationsystem.utilities.IntegrationModes;
import org.springframework.stereotype.Service;

@Service
public class KafkaIntegration implements IntegrationMethod {
    @Override
    public String getType() {
        return IntegrationModes.KAFKA.value();
    }

    @Override
    public void irrigate(IrrigationRequest message) {
        System.out.println("Sending Message To Kafka Queue: " + message);
    }
}
