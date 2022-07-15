package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Good;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GoodDao {

    public static Good getGoodFromResultSet(ResultSet resultSet) throws SQLException {
        Good good = null;
        if (resultSet.next()){
            good = new Good();
            good.setGoodId(resultSet.getInt("goodId"));
            good.setGoodName(resultSet.getString("goodName"));
            good.setGoodCost(resultSet.getFloat("goodCost"));
        }
        return good;
    }

    public static Good searchGood(String goodId) throws SQLException, ClassNotFoundException {
        String searchStatement = "select * from cashtest.goods where goodId=" + goodId;
        ResultSet resultSet = DatabaseManager.executeQuery(searchStatement);
        Good good = getGoodFromResultSet(resultSet);
        return good;
    }

    public static ObservableList<Good> searchGoods() throws SQLException, ClassNotFoundException {
        String statement = "Select * from cashtest.goods";
            ResultSet resultSet = DatabaseManager.executeQuery(statement);
            ObservableList<Good> observableList = getGoodsList(resultSet);
            return  observableList;
    }

    private static ObservableList<Good> getGoodsList(ResultSet resultSet) throws SQLException {
        ObservableList<Good> goods = FXCollections.observableArrayList();
        while (resultSet.next()){
            Good good = new Good();
            good.setGoodId(resultSet.getInt("goodId"));
            good.setGoodName(resultSet.getString("goodName"));
            good.setGoodCost(resultSet.getFloat("goodCost"));
            goods.add(good);
        }
        return goods;
    }



}
