package utilitypays.controllers;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import utilitypays.entity.Bill;
import utilitypays.entity.LegalPerson;

import utilitypays.entity.PhysicalPerson;
import utilitypays.service.BillService;
import utilitypays.service.LegalPersonService;
import utilitypays.service.PayService;
import utilitypays.service.PhysicalPersonService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LegalPersonController{
    PhysicalPerson physicalPerson;
    LegalPerson legalPerson;
    Bill bill;
    @Autowired
    PhysicalPersonService physicalPersonService;
    @Autowired
    LegalPersonService legalPersonService;
    @Autowired
    PayService payService;
    @Autowired
    BillService billService;


    @RequestMapping(value = "/billCreation", method = RequestMethod.POST)
    public String billCreation(@ModelAttribute Bill bill, Model model){
        String message;
        boolean isOk = physicalPerson != null;
        if (isOk) {
            bill.setPhysicalPerson(physicalPerson);
            bill.setLegalPerson(legalPerson);
            billService.saveBillAndDebt(bill, physicalPerson, legalPerson);
            message = String.format("Blll on %s rub saved successfully!",
                    bill.getSum());
        }
        else message = "Physical person not found!";
        model.addAttribute("legalPerson", legalPerson);//.getName());
        model.addAttribute("message", message);
        return "legalPersonPage";
    }

    @RequestMapping(value = "/findPersonByPasspnum", method = RequestMethod.POST)
    public String physicalPersonByPassportNumReaddressing(@ModelAttribute Bill bill, Model model){
        physicalPerson = physicalPersonService.findByPasspnum(bill.getPasspnum());
        model.addAttribute("fio", physicalPerson == null ? "subscriber not found" : physicalPerson.getName());
        model.addAttribute("bill", bill);
        model.addAttribute("unit", legalPerson.getBusinessType().getUnit());
        return "createBill";
    }

    @RequestMapping(value = "/createBill")
    public String createBill(Model model){
        bill = new Bill();
        model.addAttribute("bill", bill);
        model.addAttribute("fio", "");
        model.addAttribute("unit", legalPerson.getBusinessType().getUnit());
        return "createBill";
    }

    public void setLegalPerson(LegalPerson legalPerson) {
        this.legalPerson = legalPerson;
    }


}
