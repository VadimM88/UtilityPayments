package utilitypays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utilitypays.entity.LegalPersonBusinessType;
import utilitypays.repository.LegalPersonBusinessTypeRepository;

@Service
public class LegalPersonBusinessTypeService {

    @Autowired
    LegalPersonBusinessTypeRepository legalPersonBusinessTypeRepository;

    public void save(LegalPersonBusinessType businessType){
        legalPersonBusinessTypeRepository.save(businessType);
    }

    public long count(){
        return legalPersonBusinessTypeRepository.count();
    }

    public void clear() {
        legalPersonBusinessTypeRepository.deleteAll();
    }
}
