package com.bm.irrigationsystem.datamodel;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IrrigationRequest {
    private Long plotId;
    private Long slotId;
    private Double waterAmount;
    private String integrationFramework;
}
