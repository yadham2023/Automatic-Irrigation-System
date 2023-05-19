package com.bm.irrigationsystem.service;

import com.bm.irrigationsystem.datamodel.IrrigationRequest;
import com.bm.irrigationsystem.entity.IrrigationSlot;
import com.bm.irrigationsystem.entity.PlotOfLand;
import com.bm.irrigationsystem.exception.InvalidInputException;
import com.bm.irrigationsystem.exception.ResourceNotFoundException;
import com.bm.irrigationsystem.repository.IrrigationSlotRepository;
import com.bm.irrigationsystem.repository.PlotRepository;
import com.bm.irrigationsystem.service.client.APIClient;
import com.bm.irrigationsystem.utilities.IntegrationModes;
import com.bm.irrigationsystem.utilities.IrrigationStatus;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Service
public class RestIntegration implements IntegrationMethod {

    //Logger logger = LoggerFactory.getLogger(RestIntegration.class);
    private APIClient apiClient;

    @Override
    public String getType() {
        return IntegrationModes.REST.value();
    }


    @Override
    public void irrigate(IrrigationRequest irrigationRequest) {
        ResponseEntity<String> response = apiClient.irrigate(irrigationRequest);
    }

}
