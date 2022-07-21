package utilitypays.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Scope("prototype")
@Table(name = "debts")
public class Debt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "legal_person_id", nullable = false)
    private LegalPerson legalPerson;

    @ManyToOne(optional = false)
    @JoinColumn(name = "physical_person_id", nullable = false)
    private PhysicalPerson physicalPerson;

    int yearp;
    int monthp;
    private long sumDebt;

    public long getSumDebt() {
        return sumDebt;
    }

    public void setSumDebt(long sumDuty) {
        this.sumDebt = sumDuty;
    }

    public PhysicalPerson getPhysicalPerson() {
        return physicalPerson;
    }

    public void setPhysicalPerson(PhysicalPerson physicalPerson) {
        this.physicalPerson = physicalPerson;
    }

    public LegalPerson getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(LegalPerson legalPerson) {
        this.legalPerson = legalPerson;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getYearp() {
        return yearp;
    }

    public void setYearp(int yearp) {
        this.yearp = yearp;
    }

    public int getMonthp() {
        return monthp;
    }

    public void setMonthp(int monthp) {
        this.monthp = monthp;
    }

    public Debt get(){
        return this;
    }
}
