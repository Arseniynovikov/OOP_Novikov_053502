package Money;

public class Bank_Account {
    private String clientpass;
    private String number;
    private double count;
    private String bik;
    private boolean bloking;
    private boolean freezing;

    public Bank_Account(){}
    public Bank_Account(String clientpass, String number, double count, String bik){
        this.clientpass = clientpass;
        this.number = number;
        this.bik = bik;
        this.count = count;
        bloking = false;
        freezing = false;
    }
    public Bank_Account(String clientpass, String number, double count, boolean bloking, boolean freezing, String bik){
        this.clientpass = clientpass;
        this.number = number;
        this.count = count;
        this.bik = bik;
        this.bloking = bloking;
        this.freezing = freezing;
    }

    public String getClientpass() { return clientpass; }
    public void setClientpass(String clientpass) { this.clientpass = clientpass; }
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public double getCount() { return count; }
    public void setCount(double count) { this.count = count; }

    public boolean getBloking() { return bloking; }
    public void setBloking(boolean bloking) { this.bloking = bloking; }

    public boolean getFreezing() { return freezing; }
    public void setFreezing(boolean freezing) { this.freezing = freezing; }

    public void setBik(String bik) { this.bik = bik; }
    public String getBik() { return bik; }
}
