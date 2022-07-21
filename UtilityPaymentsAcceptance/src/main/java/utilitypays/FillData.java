package utilitypays;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import utilitypays.entity.*;
import utilitypays.service.*;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

@Component
public class FillData {
    static void fillTestData(ConfigurableApplicationContext ctx) throws NoSuchAlgorithmException {
        PhysicalPersonService personService = ctx.getBean(PhysicalPersonService.class);
        PhysicalPerson physic = ctx.getBean(PhysicalPerson.class);
        physic.setFirstname("Jorge");
        physic.setLastname("Babel");
        physic.setBalance(555L);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.JULY, 10);
        physic.setBirthdate(calendar.getTime());
        physic.setPasspnum("6002 223344");
        personService.save(physic);

        //AuthenticationData authenticationData = ctx.getBean(AuthenticationData.class);
        AccountService accountService = ctx.getBean(AccountService.class);
        Account acc = ctx.getBean(Account.class);
        acc.setLogin("VadimM88");
        acc.setSalt(accountService.generateSalt());
        acc.setPassword(accountService.getHashWithSalt(acc.getLogin() + "Uassia1!", acc.getSalt()));
        acc.setPhysicalPerson(physic);
        accountService.save(acc);

        LegalPersonBusinessTypeService businessTypeService = ctx.getBean(LegalPersonBusinessTypeService.class);
        LegalPersonBusinessType water = ctx.getBean(LegalPersonBusinessType.class);
        water.setBusinessType("Water supply");
        water.setUnit("cube meters");
        businessTypeService.save(water);

        LegalPersonBusinessType gas = ctx.getBean(LegalPersonBusinessType.class);
        gas.setBusinessType("Gas");
        gas.setUnit("cube meters");
        businessTypeService.save(gas);

        LegalPersonBusinessType energy = ctx.getBean(LegalPersonBusinessType.class);
        energy.setBusinessType("Energy");
        energy.setUnit("kilowatt");
        businessTypeService.save(energy);

        CostHistoryService costHistoryService = ctx.getBean(CostHistoryService.class);
        CostHistory costHistoryWater = ctx.getBean(CostHistory.class);
        costHistoryWater.setBusinessType(water);
        costHistoryWater.setYear(2020);
        costHistoryWater.setCost(3300);
        costHistoryService.save(costHistoryWater);

        CostHistory costHistoryGas = ctx.getBean(CostHistory.class);
        costHistoryGas.setBusinessType(gas);
        costHistoryGas.setYear(2020);
        costHistoryGas.setCost(691);
        costHistoryService.save(costHistoryGas);

        CostHistory costHistoryEnergy = ctx.getBean(CostHistory.class);
        costHistoryEnergy.setBusinessType(energy);
        costHistoryEnergy.setYear(2020);
        costHistoryEnergy.setCost(510);
        costHistoryService.save(costHistoryEnergy);

        //System.out.println(accountService.findAcc(acc.getLogin()).stream().findFirst().get().getId());
        LegalPerson lp = ctx.getBean(LegalPerson.class);
        lp.setName("AO Vodokanal");
        lp.setBalance(5000000);
        lp.setInn("9999999999");
        LegalPersonService lps = ctx.getBean(LegalPersonService.class);
        lp.setBusinessType(water);
        lps.save(lp);
        //lp.getBusinessType()

        LegalPerson lp2 = ctx.getBean(LegalPerson.class);
        lp2.setName("AO RostovEnergy");
        lp2.setBalance(10000000);
        lp2.setInn("6666666666");
        lp2.setBusinessType(energy);
        lps.save(lp2);

        LegalPerson lp3 = ctx.getBean(LegalPerson.class);
        lp3.setName("AO GorGaz");
        lp3.setBalance(7000000);
        lp3.setInn("7777777777");
        lp3.setBusinessType(gas);
        lps.save(lp3);

        Account accLegal = ctx.getBean(Account.class);
        accLegal.setLogin("vdk");
        accLegal.setSalt(accountService.generateSalt());
        accLegal.setPassword(accountService.getHashWithSalt(accLegal.getLogin() + "Uassia1!", accLegal.getSalt()));
        accLegal.setLegalPerson(lp);
        accountService.save(accLegal);

        Account accLegal2 = ctx.getBean(Account.class);
        accLegal2.setLogin("energy");
        accLegal2.setSalt(accountService.generateSalt());
        accLegal2.setPassword(accountService.getHashWithSalt(accLegal2.getLogin() + "Uassia1!", accLegal2.getSalt()));
        accLegal2.setLegalPerson(lp2);
        accountService.save(accLegal2);

        Account accLegal3 = ctx.getBean(Account.class);
        accLegal3.setLogin("gaz");
        accLegal3.setSalt(accountService.generateSalt());
        accLegal3.setPassword(accountService.getHashWithSalt(accLegal3.getLogin() + "Uassia1!", accLegal3.getSalt()));
        accLegal3.setLegalPerson(lp3);
        accountService.save(accLegal3);

        DebtService debtService = ctx.getBean(DebtService.class);
        Debt debt = ctx.getBean(Debt.class);
        debt.setLegalPerson(lp3);
        debt.setPhysicalPerson(physic);
        debt.setYearp(2022);
        debt.setMonthp(5);
        debt.setSumDebt(1333);
        debtService.save(debt);

        Debt debt2 = ctx.getBean(Debt.class);
        debt2.setLegalPerson(lp2);
        debt2.setPhysicalPerson(physic);
        debt2.setYearp(2022);
        debt2.setMonthp(5);
        debt2.setSumDebt(3333);
        debtService.save(debt2);

        Debt debt3 = ctx.getBean(Debt.class);
        debt3.setLegalPerson(lp);
        debt3.setPhysicalPerson(physic);
        debt3.setYearp(2022);
        debt3.setMonthp(5);
        debt3.setSumDebt(1000);
        debtService.save(debt3);

        Debt debt4 = ctx.getBean(Debt.class);
        debt4.setLegalPerson(lp);
        debt4.setPhysicalPerson(physic);
        debt4.setYearp(2022);
        debt4.setMonthp(2);
        debt4.setSumDebt(5000);
        debtService.save(debt4);

        BillService billService = ctx.getBean(BillService.class);
        Bill bill = ctx.getBean(Bill.class);
        bill.setLegalPerson(lp3);
        bill.setPhysicalPerson(physic);
        bill.setYearp(2022);
        bill.setMonthp(4);
        bill.setSum(1333);
        billService.save(bill);

        Bill bill2 = ctx.getBean(Bill.class);
        bill2.setLegalPerson(lp3);
        bill2.setPhysicalPerson(physic);
        bill2.setYearp(2022);
        bill2.setMonthp(5);
        bill2.setSum(3333);
        billService.save(bill2);

        Bill bill3 = ctx.getBean(Bill.class);
        bill3.setLegalPerson(lp);
        bill3.setPhysicalPerson(physic);
        bill3.setYearp(2022);
        bill3.setMonthp(3);
        bill3.setSum(1000);
        billService.save(bill3);

        Bill bill4 = ctx.getBean(Bill.class);
        bill4.setLegalPerson(lp);
        bill4.setPhysicalPerson(physic);
        bill4.setYearp(2022);
        bill4.setMonthp(2);
        bill4.setSum(5000);
        billService.save(bill4);
    }
}
