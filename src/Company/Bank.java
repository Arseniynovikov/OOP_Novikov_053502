package Company;

import Money.Bank_Account;
import Money.Credit;
import Money.Installment_Plan;
import User.Administrator;
import User.Client;
import User.Manager;
import User.Operator;

import java.util.ArrayList;

public class Bank extends Company{
    private ArrayList<Client> clients;
    private ArrayList<Bank_Account> accounts;
    private ArrayList<Credit> credits;
    private ArrayList<Installment_Plan> installment_plans;
    private ArrayList<Operator> operators;
    private ArrayList<Administrator> administrators;
    private ArrayList<Manager> managers;
    private ArrayList<Company> companies;

    public Bank(String type, String name, String unp, String bank_BIK, String adres, String password) {
        super(type, name, unp, bank_BIK, adres, password);
        clients = new ArrayList<Client>();
        accounts = new ArrayList<Bank_Account>();
        credits = new ArrayList<Credit>();
        installment_plans = new ArrayList<Installment_Plan>();
        operators = new ArrayList<Operator>();
        administrators = new ArrayList<Administrator>();
        managers = new ArrayList<Manager>();
        companies = new ArrayList<Company>();
    }



    public ArrayList<Client> getClients() { return clients; }
    public void setClients(ArrayList<Client> clients) { this.clients = clients; }

    public ArrayList<Bank_Account> getAccounts() { return accounts; }
    public void setAccounts(ArrayList<Bank_Account> accounts) { this.accounts = accounts; }

    public ArrayList<Credit> getCredits() { return credits; }
    public void setCredits(ArrayList<Credit> credits) { this.credits = credits; }

    public ArrayList<Installment_Plan> getInstallment_plans() { return installment_plans; }
    public void setInstallment_plans(ArrayList<Installment_Plan> installment_plans) { this.installment_plans = installment_plans; }

    public ArrayList<Operator> getOperators() { return operators; }
    public void setOperators(ArrayList<Operator> operators) { this.operators = operators; }

    public ArrayList<Administrator> getAdministrators() { return administrators; }
    public void setAdministrators(ArrayList<Administrator> administrators) { this.administrators = administrators; }

    public ArrayList<Manager> getManagers() { return managers; }
    public void setManagers(ArrayList<Manager> managers) { this.managers = managers; }

    public ArrayList<Company> getCompany() { return companies; }
    public void setCompany(ArrayList<Company> companies) { this.companies = companies; }
}
