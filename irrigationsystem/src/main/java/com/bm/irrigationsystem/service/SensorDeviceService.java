package com.bm.irrigationsystem.service;


import com.bm.irrigationsystem.datamodel.IrrigationRequest;
import com.bm.irrigationsystem.entity.IrrigationSlot;
import com.bm.irrigationsystem.entity.PlotOfLand;
import com.bm.irrigationsystem.exception.InvalidInputException;
import com.bm.irrigationsystem.exception.ResourceNotFoundException;
import com.bm.irrigationsystem.repository.IrrigationSlotRepository;
import com.bm.irrigationsystem.repository.PlotRepository;
import com.bm.irrigationsystem.utilities.IrrigationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SensorDeviceService {

    private final IrrigationSlotRepository irrigationSlotRepository;
    private final PlotRepository plotRepository;

    public void irrigate(IrrigationRequest irrigationRequest) {
        PlotOfLand plot = plotRepository.findById(irrigationRequest.getPlotId()).orElseThrow(() ->
                new ResourceNotFoundException("PlotOfLand", "id", irrigationRequest.getPlotId()));
        IrrigationSlot irrigationSlot = irrigationSlotRepository.findById(irrigationRequest.getSlotId()).orElseThrow(() ->
                new ResourceNotFoundException("IrrigationSlot", "id", irrigationRequest.getSlotId()));
        if (irrigationSlot.getPlot() == null || !plot.getId().equals(irrigationSlot.getPlot().getId()))
            throw new InvalidInputException(HttpStatus.BAD_REQUEST, "irrigationSlot does not belong to plot");

        plot.setAmountOfWater(irrigationRequest.getWaterAmount());
        irrigationSlot.setPlot(plot);
        irrigationSlot.setStatus(IrrigationStatus.COMPLETED);
        irrigationSlotRepository.save(irrigationSlot);
    }
}
