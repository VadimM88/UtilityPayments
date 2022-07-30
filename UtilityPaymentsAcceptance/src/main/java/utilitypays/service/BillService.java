package utilitypays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utilitypays.entity.Bill;
import utilitypays.entity.CostHistory;
import utilitypays.entity.LegalPerson;
import utilitypays.entity.PhysicalPerson;
import utilitypays.repository.BillRepository;

//import javax.transaction.Transactional;

@Service
public class BillService {
    private final BillRepository billRepository;
    private final DebtService debtService;
    private final CostHistoryService costHistoryService;
    @Autowired
    public BillService(BillRepository billRepository, DebtService debtService, CostHistoryService costHistoryService) {
        this.billRepository = billRepository;
        this.debtService = debtService;
        this.costHistoryService = costHistoryService;
    }

    public void save(Bill bill) {

        billRepository.save(bill);
    }

    public long count(){
        return billRepository.count();
    }

    @Transactional
    public void saveBillAndDebt(Bill bill, PhysicalPerson physicalPerson, LegalPerson legalPerson) {
        bill.setPhysicalPerson(physicalPerson);
        bill.setLegalPerson(legalPerson);
        CostHistory lastCost = costHistoryService.findLastCost(legalPerson.getBusinessType());
        bill.setSum((long) bill.getQuantity() * lastCost.getCost() / 100);
        save(bill);
        debtService.makeDebtOnBill(bill);
    }


    public Bill findBillByPhysicalPersonAndLegalPersonAndYearAndMonth(PhysicalPerson physicalPerson, LegalPerson legalPerson, int yearp, int monthp) {
        return billRepository.findBillByPhysicalPersonAndLegalPersonAndYearpAndMonthp(physicalPerson, legalPerson, yearp, monthp);
    }
}
