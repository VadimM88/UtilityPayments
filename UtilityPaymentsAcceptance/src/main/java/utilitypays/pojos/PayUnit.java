package utilitypays.pojos;

import java.util.Date;

public class PayUnit {
    public PayUnit(String passpnum, long sumpay, Date date) {
        //super();
        this();
        this.passpnum = passpnum;
        this.sumpay = sumpay;
        this.date = date;
    }

    public PayUnit() {
    }


    private Date date;
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

    private long sumpay;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
