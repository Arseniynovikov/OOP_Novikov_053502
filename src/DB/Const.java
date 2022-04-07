package DB;

public class Const {
    public final static String ADMINISTRATORS_TABLE = "administrators";

    public final static String ADMINISTRATORS_IDADMIN = "idadministrators";
    public final static String ADMINISTRATORS_NAME = "name";
    public final static String ADMINISTRATORS_PASSPORT = "passport";
    public final static String ADMINISTRATORS_ID = "id";
    public final static String ADMINISTRATORS_PHONENUMBER = "phone_number";
    public final static String ADMINISTRATORS_EMAIL = "email";
    public final static String ADMINISTRATORS_PASSWORD = "password";

    public final static String BANKACCOUNT_TABLE = "bank_accounts";

    public final static String BANKACCOUNT_ID_BANK_ACCOUNT = "idbank_accounts";
    public final static String BANKACCOUNT_USERPASS = "user_passport";
    public final static String BANKACCOUNT_NUMBER = "number";
    public final static String BANKACCOUNT_COUNT = "count";
    public final static String BANKACCOUNT_BLOKING = "bloking";
    public final static String BANKACCOUNT_FREEZING = "freezing";
    public final static String BANKACCOUNT_BIK = "bik";


    public final static String CLIENTS_TABLE = "clients";

    public final static String CLIENTS_IDADMIN = "idclients";
    public final static String CLIENTS_NAME = "name";
    public final static String CLIENTS_PASSPORT = "passport";
    public final static String CLIENTS_ID = "id";
    public final static String CLIENTS_PHONENUMBER = "phone_number";
    public final static String CLIENTS_EMAIL = "email";
    public final static String CLIENTS_PASSWORD = "password";
    public final static String CLIENTS_COUNTRY = "country";
    public final static String CLIENTS_COMPANY = "company";
    public final static String CLIENTS_APPROVE = "approve";

    public final static String CREDIT_TABLE = "credits";

    public final static String CREDIT_IDCREDIT = "idcredits";
    public final static String CREDIT_NUMBER = "number";
    public final static String CREDIT_MONTH = "month";
    public final static String CREDIT_PERCENT = "percent";
    public final static String CREDIT_CLIENTPASSPORT = "client_passport";
    public final static String CREDIT_BIK = "bik";
    public final static String CREDIT_OPEN = "open";
    public final static String CREDIT_ALLOWED = "allowed";
    public final static String CREDIT_COUNT = "count";

    public final static String INSTALLMENT_PLAN_TABLE = "installment_plan";

    public final static String INSTALLMENT_PLAN_IDINSTALLMENTPLAN = "idInstallment_Plan";
    public final static String INSTALLMENT_PLAN_NUMBER = "number";
    public final static String INSTALLMENT_PLAN_MONTH = "month";
    public final static String INSTALLMENT_PLAN_OPEN = "open";
    public final static String INSTALLMENT_PLAN_ALLOWED = "allowed";
    public final static String INSTALLMENT_PLAN_CLIENTPASSPORT = "client_passport";
    public final static String INSTALLMENT_PLAN_BIK = "bik";
    public final static String INSTALLMENT_PLAN_COUNT = "count";

    public final static String MANAGER_TABLE = "managers";

    public final static String MANAGER_IDADMIN = "idmanagers";
    public final static String MANAGER_NAME = "name";
    public final static String MANAGER_PASSPORT = "passport";
    public final static String MANAGER_ID = "id";
    public final static String MANAGER_PHONENUMBER = "phone_number";
    public final static String MANAGER_EMAIL = "email";
    public final static String MANAGER_PASSWORD = "password";

    public final static String OPERATORS_TABLE = "operators";

    public final static String OPERATORS_IDADMIN = "idoperators";
    public final static String OPERATORS_NAME = "name";
    public final static String OPERATORS_PASSPORT = "passport";
    public final static String OPERATORS_ID = "id";
    public final static String OPERATORS_PHONENUMBER = "phone_number";
    public final static String OPERATORS_EMAIL = "email";
    public final static String OPERATORS_PASSWORD = "password";

    public final static String BANKS_TABLE = "banks";

    public final static String BANKS_IDADMIN = "idbanks";
    public final static String BANKS_NAME = "name";
    public final static String BANKS_BIK = "bik";
    public final static String BANKS_UNP = "unp";
    public final static String BANKS_ADRES = "adres";
    public final static String BANKS_PASSWORD  = "password";

    public final static String COMPANY_TABLE = "companies";

    public final static String COMPANY_IDADMIN = "idbanks";
    public final static String COMPANY_NAME = "name";
    public final static String COMPANY_TYPE = "type";
    public final static String COMPANY_UNP = "unp";
    public final static String COMPANY_BIK = "bik";
    public final static String COMPANY_ADRES = "adres";
    public final static String COMPANY_PASSWORD  = "password";

    //зарплатный проект
    public final static String SALARY_PROJECT_TABLE = "salary_project";

    public final static String SALARY_PROJECT_IDADMIN = "idbanks";
    public final static String SALARY_PROJECT_NAME = "name";
    public final static String SALARY_PROJECT_BIK = "bik";
    public final static String SALARY_PROJECT_COMPANY = "company";
    public final static String SALARY_PROJECT_PASSPORT = "passport";
    public final static String SALARY_PROJECT_COUNT = "count";
    public final static String SALARY_PROJECT_ACCOUNT_NAME = "account_name";
    public final static String SALARY_PROJECT_APPROVE = "approve";


    //зависимости клиента/компании от банка
    public final static String CLIENT_BANK_TABLE = "client_bank";

    public final static String CLIENT_BANK_BANKID = "bank_id";
    public final static String CLIENT_BANK_CLIENTID = "client_id";

    public final static String COMPANY_BANK_TABLE = "company_bank";

    public final static String COMPANY_BANK_BANKID = "bank_id";
    public final static String COMPANY_BANK_COMPANYID = "company_id";
}
