package utilitypays.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utilitypays.entity.LegalPersonBusinessType;

@Repository
public interface LegalPersonBusinessTypeRepository extends JpaRepository<LegalPersonBusinessType, Integer> {
}
