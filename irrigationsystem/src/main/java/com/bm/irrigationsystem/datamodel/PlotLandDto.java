package com.bm.irrigationsystem.datamodel;

import com.bm.irrigationsystem.entity.IrrigationSlot;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlotLandDto {

    private Long id;

    @NotEmpty(message = "Plot Name should not be null or empty")
    private String name;

    private Double area;

    private Double amountOfWater;

    @NotEmpty(message = "CropType should not be null or empty")
    private String cropType;

    private List<IrrigationSlot> irrigationSlots;

}
