package utilitypays.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utilitypays.entity.Account;
import utilitypays.entity.Bill;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    //@Query("select u from User u where u.emailAddress = :emailAddress")
    List<Account> findByLogin(String s);

}
