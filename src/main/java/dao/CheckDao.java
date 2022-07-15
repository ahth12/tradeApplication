package dao;

import model.Check;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class CheckDao {

    public static Check getCheckFromResultSet(ResultSet resultSet) throws SQLException {
        Check check = null;
        if (resultSet.next()){
            check = new Check();
            check.setCheckId(resultSet.getInt("checkId"));
            check.setCheckDate(resultSet.getDate("checkDate"));
            check.setCheckSummary(resultSet.getDouble("checkSummary"));
        }
        return check;
    }

    public static int getLastCheckId() throws SQLException, ClassNotFoundException {
        String getLastCheckIdStatement = "select * from cashtest.checks where checkid = (select max(checkid) from cashtest.checks c )";
        ResultSet resultSet = DatabaseManager.executeQuery(getLastCheckIdStatement);
        Check check = getCheckFromResultSet(resultSet);
        return check.getCheckId();
    }


    public static void addCheck(Double summary) throws SQLException, ClassNotFoundException {
        LocalDate localDateTime = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        String insertStatement = "insert into cashtest.checks(checkdate, checktime, checksummary) values ("
                + "'" + localDateTime + "', '" + localTime + "', " + summary + ")";
        System.out.println(insertStatement);
        DatabaseManager.updateQuery(insertStatement);
        System.out.println("executed :"+ localDateTime + ", " + localDateTime + ":" + ", " + summary);
    }

}
