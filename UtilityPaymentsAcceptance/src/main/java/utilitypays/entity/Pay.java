package utilitypays.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Entity
@Component
@Scope("prototype")
@Table(name = "pays")
public class Pay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    private Date datePay;
    private long sumpay;
//    private int yearp;
//    private int monthp;

    @ManyToOne
    @JoinColumn(name = "physical_person_id")
    private PhysicalPerson physicalPerson;

    @ManyToOne
    @JoinColumn(name = "legal_person_id")
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDatePay() {
        return datePay;
    }

    public void setDatePay(Date datePay) {
        this.datePay = datePay;
    }

    public long getSumpay() {
        return sumpay;
    }

    public void setSumpay(long sumpay) {
        this.sumpay = sumpay;
    }


}
