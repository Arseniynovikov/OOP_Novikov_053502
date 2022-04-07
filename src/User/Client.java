package User;

import Money.Bank_Account;
import Money.Credit;
import Money.Installment_Plan;

import java.util.ArrayList;

public class Client extends User {
    private ArrayList<Bank_Account> accounts;
    private ArrayList<Credit> credits;
    private ArrayList<Installment_Plan> installment_plans;
    private String country;
    private String company;
    private boolean approve;


    public Client(String name, String passport, String id, String phone_number, String email, String password,
                  String country, String company) {
        super(name, passport, id, phone_number, email ,password);
        this.country = country;
        this.company = company;
        approve = false;
        accounts = new ArrayList<Bank_Account>();
        credits = new ArrayList<>();
        installment_plans = new ArrayList<>();
    }

    public Client(String name, String passport, String id, String phone_number, String email, String password,
                  String country, String company, boolean approve) {
        super(name, passport, id, phone_number, email ,password);
        this.country = country;
        this.company = company;
        this.approve = approve;
        accounts = new ArrayList<Bank_Account>();
        credits = new ArrayList<>();
        installment_plans = new ArrayList<>();
    }

    public Client() {
        super();
        accounts = new ArrayList<Bank_Account>();
        credits = new ArrayList<>();
        installment_plans = new ArrayList<>();
    }

    public ArrayList<Bank_Account> getAccounts() { return accounts; }
    public void setAccounts(ArrayList<Bank_Account> accounts) { this.accounts = accounts; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public void setCredits(ArrayList<Credit> credits) { this.credits = credits; }
    public ArrayList<Credit> getCredits() { return credits; }

    public void setInstallment_plans(ArrayList<Installment_Plan> installment_plans) { this.installment_plans = installment_plans; }
    public ArrayList<Installment_Plan> getInstallment_plans() { return installment_plans; }

    public void setCompany(String company) { this.company = company; }
    public String getCompany() { return company; }

    public void setApprove(boolean approve) { this.approve = approve; }
    public boolean getApprove() { return approve; }
}
