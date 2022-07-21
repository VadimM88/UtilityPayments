package com.example.legalreportviewer.service;

import com.example.legalreportviewer.entity.LegalReportBean;
import com.example.legalreportviewer.repository.PayUnitRepository;
import com.example.legalreportviewer.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;

    @Autowired
    PayUnitRepository payUnitRepository;

    @Transactional
    public void saveReportAndUnits(LegalReportBean legalReportBean){
        reportRepository.save(legalReportBean);
        payUnitRepository.saveAll(legalReportBean.getPayPairs());
    }

}
