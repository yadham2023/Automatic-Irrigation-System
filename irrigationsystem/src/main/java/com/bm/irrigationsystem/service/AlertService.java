package com.bm.irrigationsystem.service;

import com.bm.irrigationsystem.datamodel.IrrigationRequest;
import com.bm.irrigationsystem.entity.Alert;
import com.bm.irrigationsystem.entity.IrrigationSlot;
import com.bm.irrigationsystem.exception.ResourceNotFoundException;
import com.bm.irrigationsystem.repository.AlertRepository;
import com.bm.irrigationsystem.repository.IrrigationSlotRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import static com.bm.irrigationsystem.utilities.CommonConstants.ALERT_MESSAGE;

@Getter
@Setter
@AllArgsConstructor
@Service
public class AlertService {

    private final AlertRepository alertRepository;
    private final IrrigationSlotRepository irrigationSlotRepository;

    public void alert(IrrigationRequest irrigationRequest) {
        IrrigationSlot irrigationSlot = irrigationSlotRepository.findById(irrigationRequest.getSlotId()).orElseThrow(() ->
                new ResourceNotFoundException("IrrigationSlot", "id", irrigationRequest.getSlotId()));
        Alert alert = new Alert();
        alert.setMessage(ALERT_MESSAGE);
        alert.setIrrigationSlot(irrigationSlot);
        alertRepository.save(alert);
    }
}
