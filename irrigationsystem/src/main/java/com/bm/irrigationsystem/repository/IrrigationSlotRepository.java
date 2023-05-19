package com.bm.irrigationsystem.repository;

import com.bm.irrigationsystem.entity.IrrigationSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IrrigationSlotRepository extends JpaRepository<IrrigationSlot, Long> {
    List<IrrigationSlot> findByPlotId(long plotId);
}
