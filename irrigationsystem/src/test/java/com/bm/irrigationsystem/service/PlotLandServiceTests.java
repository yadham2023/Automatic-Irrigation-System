package com.bm.irrigationsystem.service;

import com.bm.irrigationsystem.datamodel.PlotLandDto;
import com.bm.irrigationsystem.entity.IrrigationSlot;
import com.bm.irrigationsystem.entity.PlotOfLand;
import com.bm.irrigationsystem.exception.ResourceNotFoundException;
import com.bm.irrigationsystem.repository.IrrigationSlotRepository;
import com.bm.irrigationsystem.repository.PlotRepository;
import com.bm.irrigationsystem.utilities.IrrigationStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlotLandServiceTests {

    @Mock
    private PlotRepository plotRepository;

    @Mock
    private IrrigationSlotRepository irrigationSlotRepository;

    @InjectMocks
    private PlotLandService plotLandService;

    private PlotOfLand plotOfLand;

    private PlotLandDto plotLandDto;

    private IrrigationSlot irrigationSlot;

    private List<IrrigationSlot> irrigationSlotList;

    @BeforeEach
    public void setup(){

        irrigationSlot = new IrrigationSlot();
        irrigationSlot.setId(1L);
        irrigationSlot.setStatus(IrrigationStatus.COMPLETED);
        irrigationSlot.setStartTime(LocalDateTime.now());
        irrigationSlot.setEndTime(LocalDateTime.now().plusHours(5L));

        plotLandDto = PlotLandDto.builder().id(1L).name("test").area(44.5)
                .amountOfWater(100.5).cropType("wheat").build();

        plotOfLand = PlotOfLand.builder().id(1L).name("test").area(44.5)
                .amountOfWater(100.5).cropType("wheat").build();

    }

    // JUnit test for saveEmployee method
    @DisplayName("JUnit test for addPlot method")
    @Test
    public void givenPlotObject_whenSavePlotOfLand_thenReturnPlotObject(){
        // given - precondition or setup
        given(plotRepository.save(any(PlotOfLand.class))).willReturn(plotOfLand);

        // when -  action or the behaviour that we are going test
        PlotLandDto savedPlot = plotLandService.addPlot(plotLandDto);

        System.out.println(savedPlot);
        // then - verify the output
        assertThat(savedPlot).isNotNull();
    }

    @DisplayName("JUnit test for addPlot with IrrigationSlot method")
    @Test
    public void givenPlotWithIrrigationSlotObject_whenSavePlotOfLand_thenReturnPlotObject(){
        // given - precondition or setup
        irrigationSlotList = new ArrayList<>();
        irrigationSlotList.add(irrigationSlot);
        plotLandDto.setIrrigationSlots(irrigationSlotList);
        given(plotRepository.save(any(PlotOfLand.class))).willReturn(plotOfLand);
        given(irrigationSlotRepository.saveAll(plotLandDto.getIrrigationSlots())).willReturn(irrigationSlotList);

        // when -  action or the behaviour that we are going test
        PlotLandDto savedPlot = plotLandService.addPlot(plotLandDto);

        System.out.println(savedPlot);
        // then - verify the output
        assertThat(savedPlot).isNotNull();
    }

    @DisplayName("JUnit test for getPlotById method")
    @Test
    public void givenPlotId_whenGetPlotById_thenReturnPlotObject(){
        // given
        given(plotRepository.findById(1L)).willReturn(Optional.ofNullable(plotOfLand));

        // when
        PlotLandDto plotOfLandDto = plotLandService.getPlotById(plotOfLand.getId());

        System.out.println(plotOfLandDto);

        // then
        Assertions.assertThat(plotOfLandDto).isNotNull();

    }
    //org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
      //  employeeService.saveEmployee(employee);
    //});

    @DisplayName("JUnit test for negative scenario for getPlotById when empty plot throw ex")
    @Test
    public void givenPlotId_whenNotFoundPlot_thenThrowResourceNotFoundException(){
        // given
        given(plotRepository.findById(1L)).willReturn(Optional.empty());

        // then
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            plotLandService.getPlotById(plotOfLand.getId());
        });

    }


}
