package com.bm.irrigationsystem.controller;

import com.bm.irrigationsystem.datamodel.IrrigationRequest;
import com.bm.irrigationsystem.service.IrrigationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/irrigation-api")
public class IrrigationController {

    private final IrrigationService irrigationService;

    @PostMapping("/irrigate")
    public ResponseEntity<String> irrigate(@RequestBody IrrigationRequest irrigationRequest) {
        irrigationService.irrigatePlot(irrigationRequest);
        return ResponseEntity.ok("Irrigation request received and processed.");
    }
}
