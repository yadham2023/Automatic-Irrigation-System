package com.bm.irrigationsystem.datamodel;

import com.bm.irrigationsystem.entity.PlotOfLand;
import com.bm.irrigationsystem.utilities.IrrigationStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IrrigationSlotDto {
    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @NotEmpty
    @Pattern(regexp = "^(IDLE|IRRIGATING|FAILED|COMPLETED)$", message = "invalid Status accepted list is {IDLE,IRRIGATING,FAILED,COMPLETED}")
    private String status;
}
