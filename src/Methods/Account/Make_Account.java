package Methods.Account;

import DB.Const;
import DB.DatabaseHandler;
import Money.Bank_Account;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Make_Account {
    public static void New_Account(Bank_Account bank_account){
        String insert = "INSERT INTO " + Const.BANKACCOUNT_TABLE + "(" + Const.BANKACCOUNT_USERPASS + "," +
                Const.BANKACCOUNT_NUMBER + "," + Const.BANKACCOUNT_COUNT + "," + Const.BANKACCOUNT_BLOKING + "," +
                Const.BANKACCOUNT_FREEZING + "," + Const.CREDIT_BIK + ")" + "VALUES(?,?,?,?,?,?)";

        DatabaseHandler handler = new DatabaseHandler();
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(insert);
            prSt.setString(1, bank_account.getClientpass());
            prSt.setString(2, bank_account.getNumber());
            prSt.setDouble(3, bank_account.getCount());
            prSt.setBoolean(4, bank_account.getBloking());
            prSt.setBoolean(5, bank_account.getFreezing());
            prSt.setString(6, bank_account.getBik());

            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
