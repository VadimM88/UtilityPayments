package utilitypays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utilitypays.entity.CostHistory;
import utilitypays.entity.LegalPersonBusinessType;
import utilitypays.repository.CostHistoryRepository;

@Service
public class CostHistoryService {
    private final CostHistoryRepository costHistoryRepository;

    @Autowired
    public CostHistoryService(CostHistoryRepository costHistoryRepository) {
        this.costHistoryRepository = costHistoryRepository;
    }

    public CostHistory findLastCost(LegalPersonBusinessType businessType){
        return costHistoryRepository.findTopByBusinessTypeOrderByYearDesc(businessType);
    }

    public void save(CostHistory costHistory) {

        costHistoryRepository.save(costHistory);
    }

    public long count() {
        return costHistoryRepository.count();
    }

    public void clear() {
        costHistoryRepository.deleteAll();
    }
}
