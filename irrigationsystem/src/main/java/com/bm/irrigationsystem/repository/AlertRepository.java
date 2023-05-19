package com.bm.irrigationsystem.repository;

import com.bm.irrigationsystem.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {
}
