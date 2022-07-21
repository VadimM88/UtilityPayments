package com.example.legalreportviewer.controller;

import com.example.legalreportviewer.entity.LegalReportBean;
import com.example.legalreportviewer.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
@RestController
public class LegalReportController {
   private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    ReportService reportService;

    @GetMapping("/get-report/from/{legalInn}/year/{year}/month/{month}")
    public LegalReportBean getReport(@PathVariable String legalInn, @PathVariable String year, @PathVariable String month){
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("legalInn", legalInn);
        uriVariables.put("year", year);
        uriVariables.put("month", month);
        ResponseEntity<LegalReportBean> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8080/get-report/from/{legalInn}/year/{year}/month/{month}", LegalReportBean.class,
                uriVariables);
        LegalReportBean response = responseEntity.getBody();
        LegalReportBean legalReportBean = new LegalReportBean(response.getLegalPerson(), Integer.parseInt(year), Integer.parseInt(month),
                response.getPayPairs(), response.getPort(), response.getDate());
        System.out.println(legalReportBean);
        //System.out.println(legalReportBean.getPasspnum());
        reportService.saveReportAndUnits(legalReportBean);
        return legalReportBean;

    }

}
