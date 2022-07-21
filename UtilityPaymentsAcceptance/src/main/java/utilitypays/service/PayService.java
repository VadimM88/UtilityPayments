package utilitypays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utilitypays.entity.Debt;
import utilitypays.entity.LegalPerson;
import utilitypays.entity.Pay;
import utilitypays.entity.PhysicalPerson;
import utilitypays.repository.PayRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PayService {
    @Autowired
    public PayService(LegalPersonService legalPersonService, PhysicalPersonService physicalPersonService, PayRepository payRepository, DebtService debtService) {
        this.legalPersonService = legalPersonService;
        this.physicalPersonService = physicalPersonService;
        this.payRepository = payRepository;
        this.debtService = debtService;
    }

    private final LegalPersonService legalPersonService;
    private final PhysicalPersonService physicalPersonService;
    private final PayRepository payRepository;

    private final  DebtService debtService;

    public List<Pay> findPayLegalByYearAndMonth(LegalPerson lp, int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate localStart = yearMonth.atDay(1);
        LocalDate localFinish = yearMonth.atEndOfMonth();
        Date start = Date.from(localStart.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date finish = Date.from(localFinish.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Pay> pays = payRepository.findAllByLegalPersonAndDatePayBetween(lp, start, finish);
        List<Pay> defpays = new ArrayList<>();
//        Pay defpay = new Pay();
//        defpay.setSumpay(10000);
//        defpay.setDatePay(new Date());
//        defpays.add(defpay);
        return pays == null ? defpays : pays;
    }

    public long count(){
        return payRepository.count();
    }
    @Transactional
    public void movePay(PhysicalPerson physicalPerson, LegalPerson legalPerson, Debt debt, Pay pay) {
        pay.setLegalPerson(legalPerson);
        pay.setPhysicalPerson(physicalPerson);
        pay.setDatePay(new Date());
        save(pay);
        debtService.updateDebt(debt, -pay.getSumpay());
        physicalPersonService.updateBalance(physicalPerson, -pay.getSumpay());
        legalPersonService.updateBalance(legalPerson, pay.getSumpay());
    }

    public void save(Pay pay) {
        payRepository.save(pay);
    }
}
