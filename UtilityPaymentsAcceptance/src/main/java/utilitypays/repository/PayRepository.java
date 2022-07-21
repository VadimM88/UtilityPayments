package utilitypays.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utilitypays.entity.LegalPerson;
import utilitypays.entity.Pay;

import java.util.Date;
import java.util.List;

@Repository
public interface PayRepository extends JpaRepository<Pay, Integer> {
    List<Pay> findAllByLegalPersonAndDatePayBetween(LegalPerson lp, Date start, Date finish);
}
