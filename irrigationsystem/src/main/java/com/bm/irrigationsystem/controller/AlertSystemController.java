package com.bm.irrigationsystem.controller;

import com.bm.irrigationsystem.datamodel.IrrigationRequest;
import com.bm.irrigationsystem.service.AlertService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/alert-api")
public class AlertSystemController {

    private final AlertService alertService;

    @PostMapping("/alert")
    public ResponseEntity<String> alert(@RequestBody IrrigationRequest irrigationRequest) {
        alertService.alert(irrigationRequest);
        return ResponseEntity.ok("Alert message saved and request Completed Successfully.");
    }
}
