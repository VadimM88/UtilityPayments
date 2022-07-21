package com.example.legalreportviewer.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Entity
@Component
@Scope("prototype")
public class PayUnit {
    public PayUnit(String passpnum, long sumpay, Date date) {
        this.passpnum = passpnum;
        this.sumpay = sumpay;
        this.date = date;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    public PayUnit(){

    }
    private String passpnum;

    public String getPasspnum() {
        return passpnum;
    }

    public void setPasspnum(String passpnum) {
        this.passpnum = passpnum;
    }

    public long getSumpay() {
        return sumpay;
    }

    public void setSumpay(long sumpay) {
        this.sumpay = sumpay;
    }
    private Date date;
    private long sumpay;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
