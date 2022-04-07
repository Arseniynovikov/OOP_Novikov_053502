package Init;

import DB.Const;
import DB.DatabaseHandler;
import User.Client;
import User.Operator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Operator_Init {
    public static Operator Init (Operator operator){
        ResultSet resSet = null;
        DatabaseHandler handler = new DatabaseHandler();




        Operator _operator = new Operator();
        String select = "SELECT * FROM " + Const.OPERATORS_TABLE + " WHERE " +
                Const.OPERATORS_PASSPORT + "=? AND " + Const.OPERATORS_PASSWORD + "=? ";

        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
            prSt.setString(1, operator.getPassport());
            prSt.setString(2, operator.getPassword());

            resSet = prSt.executeQuery();

            while (resSet.next()) {
                _operator.setName(resSet.getString(Const.OPERATORS_NAME));
                _operator.setPassword(resSet.getString(Const.OPERATORS_PASSWORD));
                _operator.setPassport(resSet.getString(Const.OPERATORS_PASSPORT));
                _operator.setEmail(resSet.getString(Const.OPERATORS_EMAIL));
                _operator.setId(resSet.getString(Const.OPERATORS_ID));
                //counter++;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return _operator;
    }
}

