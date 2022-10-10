package ru.netology;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;
import ru.netology.Data.DBHelper;

import java.sql.DriverManager;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class Connection {


    @Test
    void stubTest() {
        var countSQL = "SELECT COUNT(*) FROM app.payment_entity;";

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "9mREsvXDs9Gk89Ef");
                var countStmt = conn.createStatement();

                var rs = countStmt.executeQuery(countSQL);
        ) {

                if (rs.next()) {
                    // выборка значения по индексу столбца (нумерация с 1) — лучше выбирать по имени
                }
                var count = rs.getInt(1);
                // TODO: использовать
                System.out.println(count);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Test
    void cleanCreditTable() {
        DBHelper.cleanUpCredit();
    }

    @Test
    void cleanPaymentTable() {
        DBHelper.cleanUpPayment();
    }
    @Test
    void getLastStatusPayment() {
        DBHelper.getLastStatusPayment();
    }
    @Test
    void getLastStatusCredit () {
        DBHelper.getLastStatusCredit();
    }


}
