package com.example.legalreportviewer;

import com.example.legalreportviewer.entity.LegalReportBean;
import com.example.legalreportviewer.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Caching
public class ReportStorage {
    private Set<LegalReportBean> set;

    private final ReportService reportService;

    @Autowired
    public ReportStorage(ReportService reportService) {
        this.reportService = reportService;
    }

    public void createNewSet() {
        set = new HashSet<>();
    }

    public void getSetFromBase(){
        set = new HashSet<>(this.reportService.getAll());
    }

    public LegalReportBean findMapKeyByInnYearMonth(String legalInn, int year, int month) {
        Optional<LegalReportBean> first = set.stream().filter(t -> t.getLegalPerson().getInn().equals(legalInn)
                && t.getYearp() == year && t.getMonthp() == month).findFirst();
        return first.isEmpty() ? null : first.get();
    }

    public void add(LegalReportBean legalReportBean, boolean isStorageCaching){
        set.add(legalReportBean);
    }

    public void writeReport(LegalReportBean legalReportBean){
        reportService.saveReportAndUnits(legalReportBean);
    }
}
