package utilitypays.service;

import org.springframework.stereotype.Service;
import utilitypays.pojos.AuthenticationData;
import utilitypays.entity.PhysicalPerson;
import utilitypays.repository.PhysicalPersonRepository;


@Service
//@ComponentScan
public class PhysicalPersonService {

    public PhysicalPersonService(PhysicalPersonRepository repository){//, SessionFactory sessionFactory) {
        this.repository = repository;
    }

   private final PhysicalPersonRepository repository;

  public PhysicalPerson findByPasspnum(String passpnum){
      return repository.findByPasspnum(passpnum);
  }

  public long count(){
      return repository.count();
  }

  public PhysicalPerson findById(Integer id){
      return repository.findById(id).orElse(null);
  }
    public PhysicalPerson createPhysicalPersonOnRegistrationData(AuthenticationData registrationData) {
        PhysicalPerson physicalPerson = new PhysicalPerson();
        physicalPerson.setFirstname(registrationData.getFirstname());
        physicalPerson.setLastname(registrationData.getLastname());
        physicalPerson.setBirthdate(registrationData.getBirthdate());
        physicalPerson.setBalance(0);
        physicalPerson.setPasspnum(registrationData.getPasspnum());
        save(physicalPerson);
        return physicalPerson;
    }

  public void save(PhysicalPerson p){
        repository.save(p);
    }

    public void updateBalance(PhysicalPerson person, long balance) {
        person.setBalance(person.getBalance() + balance);
        save(person);
    }

    public void clear() {
      repository.deleteAll();
    }
}
