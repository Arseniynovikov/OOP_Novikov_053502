package Money;

import User.Client;

public class Credit {
    private double count;
    private double percent;
    private int month;
    private String number;
    private boolean open;
    private String bik;
    private boolean allowed;
    private String passport;

    public Credit(String number, double count, int month, String passport, double percent, String bik) {
        this.number = number;
        this.count = count;
        this.month = month;
        this.percent = percent;
        this.open = true;
        this.bik = bik;
        this.passport = passport;
    }
    public Credit(String number, double count, int month, String passport, double percent, String bik, boolean allowed, boolean open) {
        this.number = number;
        this.month = month;
        this.count = count;
        this.percent = percent;
        this.open = open;
        this.allowed = allowed;
        this.bik = bik;
        this.passport = passport;
    }

    public double getPercent() { return percent; }

    public boolean getAllowed() {
        return allowed;
    }

    public int getMonth() { return month; }

    public double getCount() { return count; }

    public String getNumber() { return number; }

    public boolean getOpen() { return open; }
    public void setOpen(boolean open) { this.open = open; }

    public String getBik() { return bik; }

    public String getPassport() { return passport; }

    public void setAllowed(boolean allowed) { this.allowed = allowed; }
}
