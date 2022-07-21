package utilitypays.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import utilitypays.pojos.Person;

import javax.persistence.*;

@Entity
@Component
@Scope("prototype")
@Table(name = "legal_persons")
public class LegalPerson implements Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    private String name;
    @Column(unique = true)
    private String inn;

    @ManyToOne(optional = false)
    @JoinColumn(name = "business_type_id", nullable = false)
    private LegalPersonBusinessType businessType;
    private long balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @Override
    public long getBalance() {
        return balance;
    }
    @Override
    public void setBalance(long balance) {
        this.balance = balance;
    }

    public LegalPersonBusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(LegalPersonBusinessType businessType) {
        this.businessType = businessType;
    }
}
