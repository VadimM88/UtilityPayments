package utilitypays.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utilitypays.entity.Bill;
import utilitypays.entity.Debt;
import utilitypays.entity.LegalPerson;
import utilitypays.entity.PhysicalPerson;

import java.util.List;
import java.util.Optional;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Integer> {
    Debt findByLegalPersonAndPhysicalPersonOrderByYearpDescMonthpDesc(
            LegalPerson legalPerson, PhysicalPerson physicalPerson);

    Optional<Debt> findTopByLegalPersonAndPhysicalPersonOrderByYearpDescMonthpDesc(
            LegalPerson legalPerson, PhysicalPerson physicalPerson);


//@Query("Select d from Debt d where exists" +
//        "(select  d1.yearp * 12 + d1.monthp from Debt d1 limit 1     " +
//        " where d1.legalPerson = d.legalPerson and d1.physicalPerson = d.physicalPerson) d1.yearp * 12 + d1.monthp)") //d inner join d d1 on d. = d1.")
    List<Debt> findAllByPhysicalPerson//AndSumDebtGreaterThan
                    (PhysicalPerson physicalPerson);

    Debt findByLegalPersonAndPhysicalPersonAndYearpAndMonthp(LegalPerson lp, PhysicalPerson pp, int year, int month);
}


//(PhysicalPerson physicalPerson, LegalPerson legalPerson);