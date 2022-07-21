package utilitypays;

import org.junit.*;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.runner.OrderWith;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
//import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import utilitypays.entity.*;
import utilitypays.service.*;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import static org.junit.Assume.assumeTrue;


@RunWith(SpringRunner.class)
@SpringBootTest()
//@RunWith(Suite.class)

public class UtilityPaysUnitTest {

    @Autowired
    LegalPersonBusinessTypeService businessTypeService;// = ctx.getBean(LegalPersonBusinessTypeService.class);
    @Autowired
    LegalPersonBusinessType water;// = ctx.getBean(LegalPersonBusinessType.class);
//    @Mock
//    private LegalPersonBusinessType water2;

    @Autowired
    LegalPersonBusinessType energy;// = ctx.getBean(LegalPersonBusinessType.class);
    //    @Mock
//    private LegalPersonBusinessType energy2;
    @Autowired
    LegalPersonBusinessType gas;// = ctx.getBean(LegalPersonBusinessType.class);

    @Test
    public synchronized void businessTypesCreatingTest() {
        //businessTypeService.clear();
        water.setBusinessType("Water supply");
        water.setUnit("cube meters");
        businessTypeService.save(water);

        gas.setBusinessType("Gas");
        gas.setUnit("cube meters");
        businessTypeService.save(gas);


        energy.setBusinessType("Energy");
        energy.setUnit("kilowatt");
        businessTypeService.save(energy);
        Assert.assertTrue(businessTypeService.count() == 3);
    }

    @Autowired
    PhysicalPersonService physicalPersonService;// = ctx.getBean(PhysicalPersonService.class);
    @Autowired
    PhysicalPerson physic;// = ctx.getBean(PhysicalPerson.class);
    @Autowired
    AccountService accountService;// = ctx.getBean(AccountService.class);

    @Autowired
    Account acc;// = ctx.getBean(Account.class);
//    @Autowired
//    LegalPersonBusinessTypeService businessTypeService;// = ctx.getBean(LegalPersonBusinessTypeService.class);
//    @Autowired
//    LegalPersonBusinessType water;// = ctx.getBean(LegalPersonBusinessType.class);
////    @Mock
////    private LegalPersonBusinessType water2;
//
//    @Autowired
//    LegalPersonBusinessType energy;// = ctx.getBean(LegalPersonBusinessType.class);
////    @Mock
////    private LegalPersonBusinessType energy2;
//    @Autowired
//    LegalPersonBusinessType gas;// = ctx.getBean(LegalPersonBusinessType.class);

    @Autowired
    CostHistoryService costHistoryService;// = ctx.getBean(CostHistoryService.class);
    @Autowired
    CostHistory costHistoryWater;// = ctx.getBean(CostHistory.class);

    @Autowired
    CostHistory costHistoryGas;// = ctx.getBean(CostHistory.class);

    @Autowired
    CostHistory costHistoryEnergy;// = ctx.getBean(CostHistory.class);
    @Autowired
    LegalPerson lp;// = ctx.getBean(LegalPerson.class);

    @Autowired
    LegalPersonService lps;// = ctx.getBean(LegalPersonService.class);
    @Autowired
    Account accLegal;// = ctx.getBean(Account.class);
    @Autowired
    LegalPerson lp2;// = ctx.getBean(LegalPerson.class);
    @Autowired
    Account accLegal2;// = ctx.getBean(Account.class);
    @Autowired
    DebtService debtService;// = ctx.getBean(DebtService.class);
    @Autowired
    Debt debt;// = ctx.getBean(Debt.class);
    @Autowired
    Debt debt2 ;//= ctx.getBean(Debt.class);
    @Autowired
    Debt debt3;// = ctx.getBean(Debt.class);
    @Autowired
    Debt debt4;// = ctx.getBean(Debt.class);

//    @Test
//    public void beansTesting() throws NoSuchAlgorithmException {
//
//        physicsTesting();
//
//        businessTypesCreatingTest();
//
//        costHistoryCreatingTest();
//
//        legalPersonsCreatingTest();
//
//        accountsTest();
//
//        debtsTest();
//    }


//    public void prepareBeans(){
//        businessTypesCreatingTest();
//    }
    //@BeforeEach

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUp() throws InterruptedException {
        String methodName = testName.getMethodName();
        boolean needAddBt = methodName.equals("costHistoryCreatingTest") || methodName.equals("legalPersonsCreatingTest");
        boolean needAddPersons =  methodName.equals("accountsTest") || methodName.equals("debtsTest");
        if(!needAddBt &&  !needAddPersons)
            return;
//        costHistoryCreatingTest();
//        legalPersonsCreatingTest();
        businessTypesCreatingTest();

        if(!needAddPersons)
            return;
        physicsTesting();
        legalPersonsCreatingTest();
        // setup follows
    }

    @After
    public void clear(){
        accountService.clear();
        debtService.clear();
        costHistoryService.clear();
        lps.clear();
        physicalPersonService.clear();
        businessTypeService.clear();
    }

    @Test
    public void costHistoryCreatingTest() {
        //businessTypesCreatingTest();
        costHistoryWater.setBusinessType(water);
        costHistoryWater.setYear(2020);
        costHistoryWater.setCost(3300);
        costHistoryService.save(costHistoryWater);

        costHistoryGas.setBusinessType(gas);
        costHistoryGas.setYear(2020);
        costHistoryGas.setCost(691);
        costHistoryService.save(costHistoryGas);


        costHistoryEnergy.setBusinessType(energy);
        costHistoryEnergy.setYear(2020);
        costHistoryEnergy.setCost(510);
        costHistoryService.save(costHistoryEnergy);
        Assert.assertTrue(costHistoryService.count() == 3);
//        businessTypeService.clear();
    }

    @Test
    public void physicsTesting() {
        physic.setFirstname("Jorge");
        physic.setLastname("Babel");
        physic.setBalance(555L);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.JULY, 10);
        physic.setBirthdate(calendar.getTime());
        physic.setPasspnum("6002 22 56");
        physicalPersonService.save(physic);
        Assert.assertTrue(physicalPersonService.count() == 1);
    }

    @Test
    public void legalPersonsCreatingTest() throws InterruptedException {
        //businessTypesCreatingTest();
//        if (water.getId()==null)
//            water = water2;
//        if(energy.getId()==null)
//            energy = energy2;
        lp.setName("AO Vodokanal");
        lp.setBalance(5000000);
        lp.setInn("99999999");

        lp.setBusinessType(water);
        lps.save(lp);

        lp2.setName("AO RostovEnergy");
        lp2.setBalance(10000000);
        lp2.setInn("66666666");
        lp2.setBusinessType(energy);
        lps.save(lp2);
        Assert.assertTrue(lps.count() == 2);
//        lps.clear();
    }


    @Test
    public void accountsTest() throws NoSuchAlgorithmException {
        acc.setLogin("VadimM88");
        acc.setSalt(accountService.generateSalt());
        acc.setPassword(accountService.getHashWithSalt(acc.getLogin() + "Uassia1!", acc.getSalt()));
        acc.setPhysicalPerson(physic);
        accountService.save(acc);

        accLegal.setLogin("vdk");
        accLegal.setSalt(accountService.generateSalt());
        accLegal.setPassword(accountService.getHashWithSalt(accLegal.getLogin() + "Uassia1!", accLegal.getSalt()));
        accLegal.setLegalPerson(lp);
        accountService.save(accLegal);


        accLegal2.setLogin("gaz");
        accLegal2.setSalt(accountService.generateSalt());
        accLegal2.setPassword(accountService.getHashWithSalt(accLegal2.getLogin() + "Uassia1!", accLegal2.getSalt()));
        accLegal2.setLegalPerson(lp2);
        accountService.save(accLegal2);
        Assert.assertTrue(accountService.count() == 3);
    }

    @Test
    public void debtsTest() {
        debt.setLegalPerson(lp2);
        debt.setPhysicalPerson(physic);
        debt.setYearp(2022);
        debt.setMonthp(4);
        debt.setSumDebt(1333);
        debtService.save(debt);


        debt2.setLegalPerson(lp2);
        debt2.setPhysicalPerson(physic);
        debt2.setYearp(2022);
        debt2.setMonthp(5);
        debt2.setSumDebt(3333);
        debtService.save(debt2);


        debt3.setLegalPerson(lp);
        debt3.setPhysicalPerson(physic);
        debt3.setYearp(2022);
        debt3.setMonthp(3);
        debt3.setSumDebt(1000);
        debtService.save(debt3);


        debt4.setLegalPerson(lp);
        debt4.setPhysicalPerson(physic);
        debt4.setYearp(2022);
        debt4.setMonthp(2);
        debt4.setSumDebt(5000);
        debtService.save(debt4);
        Assert.assertTrue(debtService.count() == 4);
    }
}