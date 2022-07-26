package utilitypays.controllers;

//import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class LegalPersonController{
    PhysicalPerson physicalPerson;
    LegalPerson legalPerson;
    Bill bill;
    final
    PhysicalPersonService physicalPersonService;
    final
    LegalPersonService legalPersonService;
    final
    PayService payService;
    final
    BillService billService;

    public LegalPersonController(PhysicalPersonService physicalPersonService, LegalPersonService legalPersonService, PayService payService, BillService billService) {
        this.physicalPersonService = physicalPersonService;
        this.legalPersonService = legalPersonService;
        this.payService = payService;
        this.billService = billService;
    }


    //сохраняем данные в bill, если такого не было за этот период, и возвращаемся на основную страницу legal
    @RequestMapping(value = "/billCreation", method = RequestMethod.POST)
    public String billCreation(@ModelAttribute Bill bill, Model model){
        String message;
        boolean physicFound = physicalPerson != null;
        Bill foundBill = billService.findBillByPhysicalPersonAndLegalPersonAndYearAndMonth(
                physicalPerson, legalPerson, bill.getYearp(), bill.getMonthp());
        boolean billExists = foundBill != null;
        if (physicFound && ! billExists) {
            bill.setPhysicalPerson(physicalPerson);
            bill.setLegalPerson(legalPerson);
            billService.saveBillAndDebt(bill, physicalPerson, legalPerson);
            message = String.format("Bill on %s rub saved successfully!",
                    bill.getSum());
        }
        else if (billExists)
            message = "Bill already exists! Cannot duplicate it";
        else message = "Physical person not found!";
        model.addAttribute("legalPerson", legalPerson);//.getName());
        model.addAttribute("message", message);
        return "legalPersonPage";
    }

    //по нажатию кнопки find ищем физлицо по паспорту и передаем ФИО во вью
    @RequestMapping(value = "/findPersonByPasspnum", method = RequestMethod.POST)
    public String physicalPersonByPassportNumReaddressing(@ModelAttribute Bill bill, Model model){
        physicalPerson = physicalPersonService.findByPasspnum(bill.getPasspnum());
        model.addAttribute("fio", physicalPerson == null ? "subscriber not found" : physicalPerson.getName());
        model.addAttribute("bill", bill);
        model.addAttribute("unit", legalPerson.getBusinessType().getUnit());
        return "createBill";
    }

    //переход на страницу создания счёта
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
