package com.example.legalreportviewer.controller;

import com.example.legalreportviewer.Caching;
import com.example.legalreportviewer.ReportStorage;
import com.example.legalreportviewer.entity.LegalPerson;
import com.example.legalreportviewer.entity.LegalReportBean;
import com.example.legalreportviewer.entity.PayUnit;
import com.example.legalreportviewer.service.LegalPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Controller
public class LegalReportController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    boolean isStorageCaching;

    ReportStorage reportStorage;
    LegalPersonService legalPersonService;

    /**
     * если хранилище аннотировано маркером Caching - работаем с базой,
     * в противном случае создаем set и складываем LegalReportBean's туда */

    @Autowired
    public LegalReportController(LegalPersonService legalPersonService,
                                 ReportStorage reportStorage) {
        this.legalPersonService = legalPersonService;
        this.reportStorage = reportStorage;
        isStorageCaching = reportStorage.getClass().isAnnotationPresent(Caching.class);
        if (isStorageCaching)
            reportStorage.getSetFromBase();
        else
            reportStorage.createNewSet();
    }

    @RequestMapping("/get-report/from/{legalInn}/year/{year}/month/{month}")
    //@ResponseBody
    public String getReport(@PathVariable String legalInn, @PathVariable Integer year,
                                     @PathVariable Integer month, Model model){
        LegalReportBean mapKeyByInnYearMonth = reportStorage.findMapKeyByInnYearMonth(legalInn, year, month);
        if (mapKeyByInnYearMonth != null)
        {
            LegalPerson legalPerson = mapKeyByInnYearMonth.getLegalPerson();
            model.addAttribute("Organization", legalPerson == null ?
                    "not found" : legalPerson.getName() + " returned");
            model.addAttribute("Year", year);
            model.addAttribute("Month", month);
            model.addAttribute("pays", mapKeyByInnYearMonth.getPayUnits());

            return "reportPaysForLegal";
        }

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("legalInn", legalInn);
        uriVariables.put("year", year.toString());
        uriVariables.put("month", month.toString());
        ResponseEntity<LegalReportBean> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8080/get-report/from/{legalInn}/year/{year}/month/{month}", LegalReportBean.class,
                uriVariables);
        LegalReportBean response = responseEntity.getBody();
        List<PayUnit> payUnits = response.getPayUnits();
        LegalPerson legalPerson = response.getLegalPerson();
        LegalReportBean legalReportBean = new LegalReportBean(legalPerson, year, month,
                payUnits, response.getPort(), response.getDate());

        reportStorage.add(legalReportBean);
        if (isStorageCaching)
            reportStorage.writeReport(legalReportBean);
        model.addAttribute("Organization", legalPerson == null ?
                        "not found" : legalPerson.getName());
        model.addAttribute("Year", year);
        model.addAttribute("Month", month);
        model.addAttribute("pays", payUnits);

        return "reportPaysForLegal";
    }



}
