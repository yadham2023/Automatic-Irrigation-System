package com.bm.irrigationsystem.entity;

import com.bm.irrigationsystem.utilities.IrrigationStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Entity
@Table(name = "IRRIGATION_SLOT")
public class IrrigationSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "startTime", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "endTime", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "status", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private IrrigationStatus status;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "plot_id", nullable = false)
    private PlotOfLand plot;
}
