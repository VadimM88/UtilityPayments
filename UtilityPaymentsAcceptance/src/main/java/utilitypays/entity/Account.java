package utilitypays.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Scope("prototype")
@Table(name = "accounts", uniqueConstraints = {
        @UniqueConstraint(name = "uc_account_physical_person_id", columnNames = {"physical_person_id"})
})
    public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    //private Integer idPhysicalPerson;


    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "physical_person_id", unique = true)
    private PhysicalPerson physicalPerson;

    @ManyToOne
    @JoinColumn(name = "legal_person_id", unique = true)
    private LegalPerson legalPerson;

    public LegalPerson getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(LegalPerson legalPerson) {
        this.legalPerson = legalPerson;
    }

    public PhysicalPerson getPhysicalPerson() {
        return physicalPerson;
    }

    public void setPhysicalPerson(PhysicalPerson physicalPerson) {
        this.physicalPerson = physicalPerson;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Column(unique = true)
    public String login;
    private byte[] password;
    private byte[] salt;

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }



}
