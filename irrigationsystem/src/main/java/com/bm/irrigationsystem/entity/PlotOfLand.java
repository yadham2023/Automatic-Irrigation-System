package com.bm.irrigationsystem.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "PLOT_OF_LAND")
public class PlotOfLand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double area;

    @Column(name = "amountOfWater")
    private Double amountOfWater;

    @Column(nullable = false)
    private String cropType;

    @ToString.Exclude
    @OneToMany(mappedBy = "plot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<IrrigationSlot> irrigationSlots;
}
