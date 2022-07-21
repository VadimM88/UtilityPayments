package com.example.legalreportviewer.controller;

import com.example.legalreportviewer.entity.LegalPerson;
import com.example.legalreportviewer.entity.LegalReportBean;
import com.example.legalreportviewer.entity.PayUnit;
import com.example.legalreportviewer.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
@Controller
public class LegalReportController {
   private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    ReportService reportService;

    @RequestMapping("/get-report/from/{legalInn}/year/{year}/month/{month}")
  //  @ResponseBody
    public String getReport(@PathVariable String legalInn, @PathVariable String year,
                                     @PathVariable String month, Model model){
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("legalInn", legalInn);
        uriVariables.put("year", year);
        uriVariables.put("month", month);
        ResponseEntity<LegalReportBean> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8080/get-report/from/{legalInn}/year/{year}/month/{month}", LegalReportBean.class,
                uriVariables);
        LegalReportBean response = responseEntity.getBody();
        List<PayUnit> payUnits = response.getPayUnits();
        LegalPerson legalPerson = response.getLegalPerson();
        LegalReportBean legalReportBean = new LegalReportBean(legalPerson, Integer.parseInt(year), Integer.parseInt(month),
                payUnits, response.getPort(), response.getDate());
        System.out.println(legalReportBean);
        //System.out.println(legalReportBean.getPasspnum());
        reportService.saveReportAndUnits(legalReportBean);
        model.addAttribute("message", legalPerson == null ?
                        "not found" : legalPerson.getName());
        model.addAttribute("Year", year);
        model.addAttribute("Month", month);
        model.addAttribute("pays", payUnits);

        return "reportPaysForLegal";// legalReportBean;
    }

}
