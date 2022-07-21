package utilitypays.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utilitypays.entity.LegalPerson;

@Repository
public interface LegalPersonRepository  extends JpaRepository<LegalPerson, Integer> {
    public LegalPerson findByInn(String inn);
}
