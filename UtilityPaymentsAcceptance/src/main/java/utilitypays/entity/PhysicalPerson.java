package utilitypays.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import utilitypays.pojos.Person;

import javax.persistence.*;
import java.util.Date;

@Entity
@Scope("prototype")
@Component//иначе бины не видит почему-то
@Table(name = "physical_persons")
public class PhysicalPerson  implements Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    private String firstname;
    private String lastname;

    @Column(unique = true)
    private String passpnum;
    private Date birthdate;
    private long balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String getName(){ return String.format("%s %s", getFirstname(), getLastname()); }

    public String getPasspnum() {
        return passpnum;
    }

    public void setPasspnum(String passpnum) {
        this.passpnum = passpnum;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
