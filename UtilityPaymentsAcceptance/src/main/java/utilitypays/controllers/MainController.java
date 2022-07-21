package utilitypays.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import utilitypays.pojos.AuthenticationData;
import utilitypays.pojos.InnHolder;
import utilitypays.pojos.Person;
import utilitypays.entity.*;
import utilitypays.service.*;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    private final AuthenticationData authenticationData = new AuthenticationData();
    private final AuthenticationData registrationData = new AuthenticationData();
    @Autowired
    private AccountService accountService;
    @Autowired
    private ConfigurableApplicationContext configurableApplicationContext;

   // private Account account = new Account();

    @RequestMapping(value = "/authorization")
    public String authorizationController(Model model) {
        model.addAttribute("authenticationData", authenticationData);
        return "authorization";
    }

    @RequestMapping(value = "/registration")
    public String registerUser(Model model){
        model.addAttribute("registrationData", registrationData);
        return "registerUser";
    }

    @RequestMapping(value = "/registerUser1")
    public String reg(@ModelAttribute AuthenticationData registrationData) throws NoSuchAlgorithmException {
        accountService.createAccountAndPhysicalPerson(registrationData, new Account());
        return "index";
    }

    @RequestMapping(value = "/authenticateUser", method = RequestMethod.POST)
    public Object authenticateUser(@ModelAttribute AuthenticationData authenticationData, BindingResult errors, Model model) throws NoSuchAlgorithmException {
        if (errors!=null && errors.getAllErrors().size() > 0)
            return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
        List<Account> byLogin = accountService.findAcc(authenticationData.getLogin());
        Optional<Account> first = byLogin.stream().findFirst();
        if (first.isEmpty())
            return new ResponseEntity<>("User is not found", HttpStatus.NOT_FOUND);
        //InnHolder innHolder = null;
        PhysicalPerson physicalPerson;
        LegalPerson legalPerson;
        Account account = first.get();
        if (!accountService.validatePassword(account, authenticationData.getPassword())) {
            return new ResponseEntity<>("incorrect password", HttpStatus.NOT_ACCEPTABLE);
        }
            Person person = Person.getPerson(account);
            boolean isPhysic = person instanceof PhysicalPerson;
            PhysicalPersonController physicalPersonController;
            LegalPersonController legalPersonController;
            if (isPhysic) {
                physicalPerson = (PhysicalPerson) person;
                InnHolder innHolder = new InnHolder();

                model.addAttribute("innHolder", innHolder);
                physicalPersonController = configurableApplicationContext.getBean(PhysicalPersonController.class);
                physicalPersonController.setPhysicalPerson(physicalPerson);
                model.addAttribute("physicalPerson", physicalPerson);
            }
            else {
                legalPerson = (LegalPerson) person;
                //this.legalPerson = legalPerson;
                legalPersonController = configurableApplicationContext.getBean(LegalPersonController.class);
                legalPersonController.setLegalPerson(legalPerson);
                model.addAttribute("legalPerson", legalPerson);

            }
            return  isPhysic ? "physicalpersonpage" : "legalPersonPage";
    }


    @RequestMapping(value = "/logout")
    public String logout(){
       // this.account = new Account();
        return "index";
    }

}