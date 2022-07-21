package utilitypays.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import utilitypays.entity.*;
import utilitypays.repository.DebtRepository;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DebtService {
    @Autowired
    public DebtService(DebtRepository debtRepository) {
        this.debtRepository = debtRepository;
    }

    private final DebtRepository debtRepository;

    public void save(Debt debt){
        debtRepository.save(debt);
    }

    public long count(){
        return debtRepository.count();
    }
    public Debt getDebtByPhysicalPersonAndLegalPerson(LegalPerson legalPerson, PhysicalPerson physicalPerson){
        Optional<Debt> debt = debtRepository.findTopByLegalPersonAndPhysicalPersonOrderByYearpDescMonthpDesc(
                legalPerson, physicalPerson);
        if (debt.isEmpty()) {
            Debt debt1 = new Debt();
            debt1.setPhysicalPerson(physicalPerson);
            debt1.setLegalPerson(legalPerson);
            debt1.setMonthp(new Date().getMonth() + 1);
            debt1.setYearp(new Date().getYear() + 1900);
            return debt1;
        } else
            return debt.get();
    }

    //public Debt
    public void updateDebt(Debt debt, long l) {
        debt.setSumDebt(debt.getSumDebt() + l);
        save(debt);
    }

    public void makeDebtOnBill(Bill bill){
        Debt debt = new Debt();
        debt.setYearp(bill.getYearp());
        debt.setMonthp(bill.getMonthp());
        Debt lastDebt = getDebtByPhysicalPersonAndLegalPerson(
                                            bill.getLegalPerson(), bill.getPhysicalPerson());
        debt.setLegalPerson(bill.getLegalPerson());
        debt.setPhysicalPerson(bill.getPhysicalPerson());
        debt.setSumDebt(lastDebt.getSumDebt() + bill.getSum());
        save(debt);
    }

    /**
     * Находим все долги физлица юрлицам и по каждому выбираем актуальный - последний по месяцу/году
     * для поиска максимального периода - месяца и года - переводим эту пару в год * 12 + месяц
     * если долг есть (сумма > 0), такой долг отобразится
     * */
    public List<Debt> findLastPhysicalPersonDebts(PhysicalPerson physicalPerson){
        List<Debt> topDebts = new ArrayList<>();
        List<Debt> allDebts = debtRepository.findAllByPhysicalPerson(physicalPerson);
        Map<Pair<LegalPerson, PhysicalPerson>, IntSummaryStatistics> collect = allDebts.stream().collect(
                Collectors.groupingBy(t -> Pair.of( t.getLegalPerson(),t.getPhysicalPerson()),
                        Collectors.summarizingInt(t -> t.getYearp() * 12 + t.getMonthp())));

        collect.forEach((key, value) -> {
            Debt personDebt = debtRepository.
                    findByLegalPersonAndPhysicalPersonAndYearpAndMonthp(key.getFirst(), key.getSecond(),
                            getYearFromYearMonth(value.getMax()), getMonthFromYearMonth(value.getMax()));
            if(personDebt.getSumDebt() > 0)
                topDebts.add(personDebt);
        });
        return topDebts;
    }

    public int getYearMonthNumber(int year, int month){
        return year * 12 + month;   //1st year 12th month = 24
    }

    public int getYearFromYearMonth(int yearMonth) {
        return (yearMonth - 1) / 12; // 1 = 23/12
    }

    public int getMonthFromYearMonth(int yearMonth){
        return (yearMonth % 12 == 0 ? 12 : yearMonth % 12); // 24%12 == 0 -> return 12
    }

    public void clear() {
        debtRepository.deleteAll();
    }
}
