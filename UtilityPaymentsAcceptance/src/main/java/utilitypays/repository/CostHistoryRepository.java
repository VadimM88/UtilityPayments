package utilitypays.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utilitypays.entity.CostHistory;
import utilitypays.entity.LegalPersonBusinessType;

@Repository
public interface CostHistoryRepository extends JpaRepository<CostHistory, Integer> {
    CostHistory findTopByBusinessTypeOrderByYearDesc(LegalPersonBusinessType businessType);
}
