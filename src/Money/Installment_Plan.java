package Money;

import User.Client;

public class Installment_Plan {
    private int month;
    private String number;
    private boolean open;
    private boolean allowed;
    private String bik;
    private double count;
    private String passport;

    public Installment_Plan(String number, int month, String passport, String bik, double count) {
        this.number = number;
        this.month = month;
        this.open = true;
        this.bik = bik;
        this.count = count;
        this.passport = passport;
    }

    public Installment_Plan(String number, int month, double count, String passport, String bik, boolean allowed, boolean open) {
        this.number = number;
        this.month = month;
        this.open = open;
        this.bik = bik;
        this.count = count;
        this.passport = passport;
        this.allowed = allowed;
    }

    public Installment_Plan() {

    }

    public int getMonth() { return month; }

    public String getNumber() { return number; }

    public boolean getOpen() { return open; }
    public void setOpen(boolean open) { this.open = open; }

    public boolean getAllowed() { return allowed; }
    public void setAllowed(boolean allowed) { this.allowed = allowed; }

    public double getCount() { return count; }
    public void setCount(double count) { this.count = count; }

    public String getBik() { return bik; }
    public void setBik(String bik) { this.bik = bik; }

    public String getPassport() { return passport; }
    public void setPassport(String passport) { this.passport = passport; }
}
