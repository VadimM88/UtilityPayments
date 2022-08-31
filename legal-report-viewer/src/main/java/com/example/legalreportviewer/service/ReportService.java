package com.example.legalreportviewer.service;

import com.example.legalreportviewer.entity.LegalPerson;
import com.example.legalreportviewer.entity.LegalReportBean;
import com.example.legalreportviewer.repository.PayUnitRepository;
import com.example.legalreportviewer.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class ReportService {
    final ReportRepository reportRepository;

    final PayUnitRepository payUnitRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository, PayUnitRepository payUnitRepository) {
        this.reportRepository = reportRepository;
        this.payUnitRepository = payUnitRepository;
    }

    @Transactional
    public void saveReportAndUnits(LegalReportBean legalReportBean){
        reportRepository.save(legalReportBean);
        payUnitRepository.saveAll(legalReportBean.getPayUnits());
    }

    public LegalReportBean find(LegalPerson lp, Integer year, Integer month){
        Optional<LegalReportBean> findReport = reportRepository.findByLegalPersonAndYearpAndMonthp(lp, year, month);
        return findReport.isEmpty() ? null : findReport.get();
    }

    public List<LegalReportBean> getAll(){
        return reportRepository.findAll();
    }

}
