package com.example.legalreportviewer.repository;

import com.example.legalreportviewer.entity.PayUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayUnitRepository extends JpaRepository<PayUnit, Integer> {
}