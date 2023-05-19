package com.bm.irrigationsystem.service.client;

import com.bm.irrigationsystem.datamodel.IrrigationRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:8080", value = "SENSOR-DEVICE-SERVICE")
public interface APIClient {

    @PostMapping("/api/sensor-device-api/irrigate")
    @Retry(name = "${spring.application.name}", fallbackMethod = "alert")
    ResponseEntity<String> irrigate(@RequestBody IrrigationRequest irrigationRequest);

    @PostMapping("/api/alert-api/alert")
    ResponseEntity<String> alert(@RequestBody IrrigationRequest irrigationRequest);

    default ResponseEntity<String> alert(IrrigationRequest irrigationRequest, Exception exception) {
        this.alert(irrigationRequest);
        return new ResponseEntity<>(irrigationRequest.toString(), HttpStatus.OK);
    }
}
