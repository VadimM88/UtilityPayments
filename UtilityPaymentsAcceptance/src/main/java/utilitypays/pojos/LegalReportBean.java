package utilitypays.pojos;

import utilitypays.entity.LegalPerson;

import java.util.List;

public class LegalReportBean {
    private LegalPerson legalPerson;

    public LegalReportBean(){

    }

    public LegalReportBean(LegalPerson legalPerson, int yearp, int monthp,
                           List<PayUnit> payPairs, int port) {
        super();
        this.legalPerson = legalPerson;
        this.yearp = yearp;
        this.monthp = monthp;
        this.payPairs = payPairs;
        this.port = port;
    }

    private int id;
    private int port;
    private int yearp;
    private int monthp;
    private List<PayUnit> payPairs;

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

    public List<PayUnit> getPayPairs() {
        return payPairs;
    }

    public void setPayPairs(List<PayUnit> payPairs) {
        this.payPairs = payPairs;
    }


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
