package com.bm.irrigationsystem.repository;

import com.bm.irrigationsystem.entity.PlotOfLand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlotRepository extends JpaRepository<PlotOfLand, Long> {
}
