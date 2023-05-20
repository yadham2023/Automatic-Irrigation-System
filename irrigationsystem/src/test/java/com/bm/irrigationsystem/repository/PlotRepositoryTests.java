package com.bm.irrigationsystem.repository;

import com.bm.irrigationsystem.datamodel.PlotLandDto;
import com.bm.irrigationsystem.entity.PlotOfLand;
import lombok.RequiredArgsConstructor;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PlotRepositoryTests {

    //@Autowired
    //private PlotRepository plotRepository;

    //@DisplayName("test for save plot repository")
    //@Test
    //public void givenPlotObject_whenSave_thenReturnSavedPlot(){
        //PlotOfLand plotOfLand = PlotOfLand.builder().name("test").area(44.5)
          //      .amountOfWater(100.5).cropType("wheat").build();

        //PlotOfLand savedPlot = plotRepository.save(plotOfLand);

        //assertThat(savedPlot).isNotNull();
        //assertThat(savedPlot.getId()).isGreaterThan(0);
    //}

}
