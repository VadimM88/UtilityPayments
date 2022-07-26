package utilitypays.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import utilitypays.pojos.InnHolder;
import utilitypays.pojos.PersonBalance;
import utilitypays.entity.Debt;
import utilitypays.entity.LegalPerson;
import utilitypays.entity.Pay;
import utilitypays.entity.PhysicalPerson;
import utilitypays.service.DebtService;
import utilitypays.service.LegalPersonService;
import utilitypays.service.PayService;
import utilitypays.service.PhysicalPersonService;

import java.util.List;

@Controller
@Component
public class PhysicalPersonController {
    @Autowired
    PhysicalPersonService physicalPersonService;
    @Autowired
    private PayService payService;
    @Autowired
    private LegalPersonService legalPersonService;
    @Autowired
    private DebtService debtService;

    private PhysicalPerson physicalPerson;
    private LegalPerson legalPerson;
    private Debt debt;
    private final PersonBalance personBalance = new PersonBalance();


    public void setPhysicalPerson(PhysicalPerson physicalPerson) {
        this.physicalPerson = physicalPerson;
    }


    @RequestMapping(value = "/updateBalance")
    public String updateBalance(@ModelAttribute PersonBalance personBalance, Model model){
        physicalPersonService.updateBalance(physicalPerson, personBalance.getBalance());
        InnHolder value = new InnHolder();
        model.addAttribute("message", "Successfully updated!");
        model.addAttribute("innHolder", value);
        model.addAttribute("physicalPerson", physicalPerson);
        return "physicalpersonpage";
    }

    @RequestMapping(value = "/topup")
    public String topupBalance(Model model){
        personBalance.setPerson(physicalPerson);
        model.addAttribute("physicalPerson", physicalPerson);
        model.addAttribute("personBalance", personBalance);
        return "topup";
    }

    @RequestMapping(value = "/redirectToPayTheBill", method = RequestMethod.POST)
    public String redirectToPayTheBill(@ModelAttribute Pay pay, /*@ModelAttribute Debt debt1,*/ Model model){

        String messageToPhysicalPerson; //хватает денег - успешно оплачен; нет - пополните баланс!
        if (pay.getSumpay() > physicalPerson.getBalance())
             messageToPhysicalPerson = String.format("insufficient funds! Your balance %s, needed %s",
                    physicalPerson.getBalance(), pay.getSumpay());
        else {
            payService.movePay(physicalPerson, legalPerson, debt, pay);
            messageToPhysicalPerson = "Successfully payed!";
        }
        model.addAttribute("message", messageToPhysicalPerson);
        model.addAttribute("physicalPerson", physicalPerson);
        model.addAttribute("innHolder", new InnHolder());

        return "physicalpersonpage";
    }

    @RequestMapping(value = "/payTheBill", method = RequestMethod.POST)
    public String payTheBill(@ModelAttribute Pay pay, Model model){
        model.addAttribute("name", physicalPerson.getName());
        model.addAttribute("inn", "");
        model.addAttribute("balance", physicalPerson.getBalance());
        return "physicalpersonpage";
    }

    @RequestMapping(value = "/findLegalPersonByINN", method = RequestMethod.POST)
    public String legalPersonByINNReaddressing(@ModelAttribute InnHolder innHolder, Model model){
        legalPerson = legalPersonService.findByINN(innHolder.getInn());
        model.addAttribute("legalName", legalPerson == null ? "service provider not found" : legalPerson.getName());
        debt = debtService.getDebtByPhysicalPersonAndLegalPerson(legalPerson, physicalPerson);
        model.addAttribute("sumDebt", legalPerson == null ? 0 : debt.getSumDebt());
        model.addAttribute("pay", new Pay());
        return "formPayForLegal";
    }

    @RequestMapping(value = "/findPersonDebts", method = RequestMethod.POST)
    public String findPersonDebts(Model model){
        List<Debt> allPhysicalPersonDebts = debtService.findLastPhysicalPersonDebts(physicalPerson);
        model.addAttribute("debts", allPhysicalPersonDebts);
        return "allPhysicalPersonDebts";
    }

}
