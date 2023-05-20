package com.bm.irrigationsystem.service;

import com.bm.irrigationsystem.datamodel.PlotLandDto;
import com.bm.irrigationsystem.datamodel.PlotResponse;
import com.bm.irrigationsystem.entity.IrrigationSlot;
import com.bm.irrigationsystem.entity.PlotOfLand;
import com.bm.irrigationsystem.exception.ResourceNotFoundException;
import com.bm.irrigationsystem.repository.IrrigationSlotRepository;
import com.bm.irrigationsystem.repository.PlotRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlotLandService {

    private final PlotRepository plotRepository;
    private ModelMapper mapper;
    private final IrrigationSlotRepository irrigationSlotRepository;

    @Transactional
    public PlotLandDto addPlot(PlotLandDto plotDto) {
        PlotOfLand plot = plotRepository.save(mapToEntity(plotDto));
        if (plotDto.getIrrigationSlots() != null && !plotDto.getIrrigationSlots().isEmpty()) {
            plotDto.getIrrigationSlots().forEach(s -> s.setPlot(plot));
            plot.setIrrigationSlots(irrigationSlotRepository.saveAll(plotDto.getIrrigationSlots()));
        }
        return mapToDto(plot);
    }

    @Transactional
    public PlotLandDto updatePlot(Long plotId, PlotLandDto plotDto) {
        PlotOfLand oldPlot = plotRepository.findById(plotId).orElseThrow(() -> new ResourceNotFoundException("PlotOfLand", "id", plotId));
        PlotOfLand updatedPlot = mapToEntity(plotDto);
        updatedPlot.setId(oldPlot.getId());
        if (plotDto.getIrrigationSlots() != null && !plotDto.getIrrigationSlots().isEmpty()) {
            plotDto.getIrrigationSlots().forEach(s -> s.setPlot(updatedPlot));
            updatedPlot.setIrrigationSlots(plotDto.getIrrigationSlots());
        }
        return mapToDto(plotRepository.save(updatedPlot));
    }

    @Transactional
    public void deletePlotById(Long plotId) {
        PlotOfLand plotToBeDeleted = plotRepository.findById(plotId).orElseThrow(() -> new ResourceNotFoundException("PlotOfLand", "id", plotId));
        plotRepository.delete(plotToBeDeleted);
    }

    public PlotResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<PlotOfLand> plots = plotRepository.findAll(pageable);

        List<PlotOfLand> listOfPlots = plots.getContent();

        List<PlotLandDto> content = listOfPlots.stream().map(this::mapToDto).collect(Collectors.toList());

        PlotResponse plotResponse = new PlotResponse();
        plotResponse.setContent(content);
        plotResponse.setPageNo(plots.getNumber());
        plotResponse.setPageSize(plots.getSize());
        plotResponse.setTotalElements(plots.getTotalElements());
        plotResponse.setTotalPages(plots.getTotalPages());
        plotResponse.setLast(plots.isLast());

        return plotResponse;
    }

    public PlotLandDto getPlotById(Long plotId) {
        PlotOfLand plot = plotRepository.findById(plotId).orElseThrow(() -> new ResourceNotFoundException("PlotOfLand", "id", plotId));
        return mapToDto(plot);
    }

    public PlotOfLand mapToEntity(PlotLandDto plotDto) {
        if (plotDto == null)
            return null;
        PlotOfLand plot = new PlotOfLand();
        plot.setId(plotDto.getId());
        plot.setName(plotDto.getName());
        plot.setArea(plotDto.getArea());
        plot.setCropType(plotDto.getCropType());
        plot.setAmountOfWater(plotDto.getAmountOfWater());
        //if (plotDto.getIrrigationSlots() != null && !plotDto.getIrrigationSlots().isEmpty())
        //plot.setIrrigationSlots(plotDto.getIrrigationSlots());
        return plot;
    }

    public PlotLandDto mapToDto(PlotOfLand plot) {
        if (plot == null)
            return null;
        PlotLandDto plotLandDto = new PlotLandDto();
        plotLandDto.setId(plot.getId());
        plotLandDto.setName(plot.getName());
        plotLandDto.setArea(plot.getArea());
        plotLandDto.setCropType(plot.getCropType());
        plotLandDto.setAmountOfWater(plot.getAmountOfWater());
        if (plot.getIrrigationSlots() != null && !plot.getIrrigationSlots().isEmpty())
            plotLandDto.setIrrigationSlots(plot.getIrrigationSlots());
        return plotLandDto;
    }

}
