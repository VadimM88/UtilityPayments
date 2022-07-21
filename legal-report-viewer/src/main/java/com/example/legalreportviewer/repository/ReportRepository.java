package com.example.legalreportviewer.repository;

import com.example.legalreportviewer.entity.LegalReportBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<LegalReportBean, Integer> {
}
