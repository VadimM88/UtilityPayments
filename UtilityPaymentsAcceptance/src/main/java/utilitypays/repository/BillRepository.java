package utilitypays.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utilitypays.entity.Account;
import utilitypays.entity.Bill;
import utilitypays.entity.LegalPerson;
import utilitypays.entity.PhysicalPerson;

import java.util.List;

@Repository
public interface BillRepository  extends JpaRepository<Bill, Integer> {

    //List<Bill> findAllBySumdutyGreaterThanAndPhysicalPerson(Long i, PhysicalPerson p);

    Bill findBillByPhysicalPersonAndLegalPersonAndYearpAndMonthp(PhysicalPerson physicalPerson, LegalPerson legalPerson, int year, int month);


}
