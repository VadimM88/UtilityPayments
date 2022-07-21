package utilitypays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import utilitypays.entity.Bill;
import utilitypays.entity.LegalPerson;
import utilitypays.repository.BillRepository;
import utilitypays.repository.LegalPersonRepository;
import utilitypays.repository.PhysicalPersonRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class LegalPersonService {

    public LegalPersonService(LegalPersonRepository repository, BillRepository br){//, SessionFactory sessionFactory) {
        this.repository = repository;
        this.br = br;
    }

    public long count(){
        return repository.count();
    }
    private final LegalPersonRepository repository;

    private final BillRepository br;
    public void save(LegalPerson l){
        repository.save(l);
    }

    public LegalPerson findByINN(String inn){
        return repository.findByInn(inn);
    }

    public LegalPerson findById(int id){
        return repository.findById(id).orElse(new LegalPerson());
    }

    public void saveBill(Bill bill){
        br.save(bill);
    }

    public void updateBalance(LegalPerson legalPerson, long sumpay) {
        legalPerson.setBalance(legalPerson.getBalance() + sumpay);
        save(legalPerson);
    }

    public void clear() {
        repository.deleteAll();
    }
}
