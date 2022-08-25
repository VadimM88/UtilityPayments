package utilitypays.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utilitypays.pojos.AuthenticationData;
import utilitypays.entity.Account;
import utilitypays.entity.PhysicalPerson;
import utilitypays.repository.AccountRepository;

import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64String;

@Service
public class AccountService {


    private final AccountRepository repository;
    private final PhysicalPersonService physicalPersonService;



    @Autowired
    public AccountService(AccountRepository repository, PhysicalPersonService physicalPersonService){
        this.repository = repository;
        this.physicalPersonService = physicalPersonService;
    }
    //@Transactional
    public Account save(Account account){
        repository.save(account);
        return account;
    }

    @Transactional
    public void createAccountAndPhysicalPerson(AuthenticationData registrationData, Account account) throws NoSuchAlgorithmException {
        PhysicalPerson physicalPersonOnRegistrationData = physicalPersonService.createPhysicalPersonOnRegistrationData(registrationData);
        account.setLogin(registrationData.getLogin());
        byte[] salt = generateSalt();
        account.setSalt(salt);
        account.setPassword(getHashWithSalt( registrationData.getLogin() +
                registrationData.getPassword(), salt));
        account.setPhysicalPerson(physicalPersonOnRegistrationData);
        save(account);
    }

    public List<Account> findAcc(String login){
        return repository.findByLogin(login);
    }

    public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        return bytes;
    }

    public long count(){
        return repository.count();
    }
    public String bytesToString(byte[] input) {
        return encodeBase64String(input);
    }

    public byte[] getHashWithSalt(String input, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt);
        return digest.digest(stringToBytes(input));
    }
    public byte[] stringToBytes(String input) {
        if (Base64.isBase64(input)) {
            return Base64.decodeBase64(input);
        } else {
            return Base64.encodeBase64(input.getBytes());
        }
    }

    public boolean validatePassword(Account account, String password) throws NoSuchAlgorithmException {
        return Arrays.equals(account.getPassword(),
                    (getHashWithSalt(account.getLogin() + password, account.getSalt())));

    }

    public void clear() {
        repository.deleteAll();
    }
}
