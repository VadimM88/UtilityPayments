package com.example.legalreportviewer.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Component
@Table(name = "reports_pays_for_legals")
public class LegalReportBean {

    @ManyToOne
    @JoinColumn(name = "legal_person_id", nullable = false)
    private LegalPerson legalPerson;

    public LegalReportBean() {

    }

    public LegalReportBean(LegalPerson legalPerson, int yearp, int monthp,
                           List<PayUnit>  payUnits, int port, Date date) {
        super();
        this.legalPerson = legalPerson;
        this.yearp = yearp;
        this.monthp = monthp;
        this.payUnits = payUnits;
        this.port = port;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    private int yearp;
    private int monthp;
    private Date date;
    private int port;

    @OneToMany
    @JoinColumn(name = "legal_report_id", nullable = false)
    private List<PayUnit>  payUnits;

    public LegalPerson getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(LegalPerson legalPerson) {
        this.legalPerson = legalPerson;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<PayUnit> getPayUnits() {
        return payUnits;
    }

    public void setPayUnits(List<PayUnit> payUnits) {
        this.payUnits = payUnits;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
//    }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
