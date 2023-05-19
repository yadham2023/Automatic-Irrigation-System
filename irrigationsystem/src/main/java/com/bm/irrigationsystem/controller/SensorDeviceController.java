package com.bm.irrigationsystem.controller;

import com.bm.irrigationsystem.datamodel.IrrigationRequest;
import com.bm.irrigationsystem.service.IrrigationService;
import com.bm.irrigationsystem.service.SensorDeviceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/sensor-device-api")
public class SensorDeviceController {

    private final SensorDeviceService sensorDeviceService;

    @PostMapping("/irrigate")
    public ResponseEntity<String> irrigate(@RequestBody IrrigationRequest irrigationRequest) {
        sensorDeviceService.irrigate(irrigationRequest);
        return ResponseEntity.ok("Irrigation request Completed Successfully.");
    }

}
