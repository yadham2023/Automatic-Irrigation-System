package com.bm.irrigationsystem.service;

import com.bm.irrigationsystem.datamodel.IrrigationSlotDto;
import com.bm.irrigationsystem.entity.IrrigationSlot;
import com.bm.irrigationsystem.entity.PlotOfLand;
import com.bm.irrigationsystem.exception.InvalidInputException;
import com.bm.irrigationsystem.exception.ResourceNotFoundException;
import com.bm.irrigationsystem.repository.IrrigationSlotRepository;
import com.bm.irrigationsystem.repository.PlotRepository;
import com.bm.irrigationsystem.utilities.IrrigationStatus;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class IrrigationSlotService {

    private final IrrigationSlotRepository irrigationSlotRepository;
    private final PlotRepository plotRepository;
    private final PlotLandService plotLandService;

    @Transactional
    public IrrigationSlotDto reserveIrrigationSlot(Long plotId, IrrigationSlotDto irrigationSlotDto) {
        IrrigationSlot irrigationSlot = mapToEntity(irrigationSlotDto);
        PlotOfLand plot = plotRepository.findById(plotId).orElseThrow(() ->
                new ResourceNotFoundException("PlotOfLand", "id", plotId));
        irrigationSlot.setPlot(plot);
        IrrigationSlot newIrrigationSlot = irrigationSlotRepository.save(irrigationSlot);
        return mapToDto(newIrrigationSlot);
    }

    @Transactional
    public IrrigationSlotDto updateIrrigationSlot(Long plotId, Long irrigationSlotId, IrrigationSlotDto irrigationSlotDto) {
        PlotOfLand plot = plotRepository.findById(plotId).orElseThrow(() ->
                new ResourceNotFoundException("PlotOfLand", "id", plotId));
        IrrigationSlot irrigationSlot = irrigationSlotRepository.findById(irrigationSlotId).orElseThrow(() ->
                new ResourceNotFoundException("IrrigationSlot", "id", irrigationSlotId));
        if (irrigationSlot.getPlot() == null || !plot.getId().equals(irrigationSlot.getPlot().getId()))
            throw new InvalidInputException(HttpStatus.BAD_REQUEST, "irrigationSlot does not belong to plot");
        IrrigationSlot newIrrigationSlot = mapToEntity(irrigationSlotDto);
        newIrrigationSlot.setId(irrigationSlot.getId());
        newIrrigationSlot.setPlot(plot);
        return mapToDto(irrigationSlotRepository.save(newIrrigationSlot));
    }

    @Transactional
    public IrrigationSlotDto updateIrrigationSlotStatus(Long plotId, Long irrigationSlotId, String status) {
        PlotOfLand plot = plotRepository.findById(plotId).orElseThrow(() ->
                new ResourceNotFoundException("PlotOfLand", "id", plotId));
        IrrigationSlot irrigationSlot = irrigationSlotRepository.findById(irrigationSlotId).orElseThrow(() ->
                new ResourceNotFoundException("IrrigationSlot", "id", irrigationSlotId));
        if (irrigationSlot.getPlot() == null || !plot.getId().equals(irrigationSlot.getPlot().getId()))
            throw new InvalidInputException(HttpStatus.BAD_REQUEST, "irrigationSlot does not belong to plot");
        IrrigationStatus irrigationStatus = IrrigationStatus.fromValue(status);
        irrigationSlot.setStatus(irrigationStatus);
        return mapToDto(irrigationSlotRepository.save(irrigationSlot));
    }

    public List<IrrigationSlotDto> getIrrigationSlotsByPlotId(Long plotId) {
        List<IrrigationSlot> irrigationSlots = irrigationSlotRepository.findByPlotId(plotId);
        return irrigationSlots.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<IrrigationSlotDto> getAllIrrigationSlots() {
        List<IrrigationSlot> irrigationSlots = irrigationSlotRepository.findAll();
        return irrigationSlots.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private IrrigationSlotDto mapToDto(IrrigationSlot irrigationSlot) {
        IrrigationSlotDto irrigationSlotDto = new IrrigationSlotDto();
        irrigationSlotDto.setId(irrigationSlot.getId());
        irrigationSlotDto.setStartTime(irrigationSlot.getStartTime());
        irrigationSlotDto.setEndTime(irrigationSlot.getEndTime());
        irrigationSlotDto.setStatus(irrigationSlot.getStatus().toString());
        return irrigationSlotDto;
    }

    private IrrigationSlot mapToEntity(IrrigationSlotDto irrigationSlotDto) {
        IrrigationSlot irrigationSlot = new IrrigationSlot();
        irrigationSlot.setId(irrigationSlotDto.getId());
        irrigationSlot.setStartTime(irrigationSlotDto.getStartTime());
        irrigationSlot.setEndTime(irrigationSlotDto.getEndTime());
        IrrigationStatus status = IrrigationStatus.fromValue(irrigationSlotDto.getStatus());
        irrigationSlot.setStatus(status);
        return irrigationSlot;
    }

}
