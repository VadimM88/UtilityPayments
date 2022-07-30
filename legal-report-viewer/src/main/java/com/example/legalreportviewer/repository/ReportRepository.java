package com.example.legalreportviewer.repository;

import com.example.legalreportviewer.entity.LegalPerson;
import com.example.legalreportviewer.entity.LegalReportBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<LegalReportBean, Integer> {
    public Optional<LegalReportBean> findByLegalPersonAndYearpAndMonthp(LegalPerson legalPerson,
                                                                  Integer yearp, Integer monthp);
}
