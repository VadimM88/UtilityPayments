package utilitypays.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utilitypays.entity.Debt;
import utilitypays.entity.PhysicalPerson;

import java.util.List;

@Repository
public interface PhysicalPersonRepository extends JpaRepository<PhysicalPerson, Integer> {

    PhysicalPerson findByPasspnum(String passpnum);

}
