package utilitypays.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import utilitypays.pojos.LegalReportBean;
import utilitypays.pojos.PayUnit;
import utilitypays.entity.LegalPerson;
import utilitypays.entity.Pay;
import utilitypays.service.LegalPersonService;
import utilitypays.service.PayService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LegalReportController {

    @Autowired
    LegalPersonService legalPersonService;
    @Autowired
    private Environment environment;
    @Autowired
    PayService payService;

    @GetMapping("/get-report/from/{legalInn}/year/{year}/month/{month}")
    public LegalReportBean retrieveValue
            (@PathVariable String legalInn, @PathVariable Integer year, @PathVariable Integer month){
        LegalPerson byINN = legalPersonService.findByINN(legalInn);
        List<Pay> payLegalByYearAndMonth = payService.findPayLegalByYearAndMonth(byINN, year, month);
        List<PayUnit> payUnits = new ArrayList<>();
        payLegalByYearAndMonth.forEach(t->payUnits.add(new PayUnit(t.getPhysicalPerson().getPasspnum(),
                t.getSumpay(), t.getDatePay() ) ));
        return new LegalReportBean( byINN, year, month,
                payUnits,  Integer.parseInt(environment.getProperty("local.server.port")));
    }
}
