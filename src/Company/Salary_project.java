package Company;

public class Salary_project {
    private String name;
    private String bik;
    private String passport;
    private double count;
    private String account_name;
    private String company;
    private boolean approve;

    public Salary_project(String name, String bik, String passport, double count, String account_name,
                          String company, boolean approve){
        this.name = name;
        this.bik = bik;
        this.passport = passport;
        this.count = count;
        this.account_name = account_name;
        this.company = company;
        this.approve = approve;
    }

    public Salary_project() {

    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBik() { return bik; }
    public void setBik(String bik) { this.bik = bik; }
    public String getPassport() { return passport; }
    public void setPassport(String passport) { this.passport = passport; }
    public double getCount() { return count; }
    public void setCount(double count) {  this.count = count; }
    public String getAccount_name() { return account_name; }
    public void setAccount_name(String name) { this.account_name = account_name; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public boolean getApprove() { return approve; }
    public void setApprove(boolean approve) { this.approve = approve; }
}
