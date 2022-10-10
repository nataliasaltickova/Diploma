package ru.netology.Data;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    public  static void cleanUpPayment () {
        var cleanSQL = "DELETE FROM app.payment_entity;";
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "9mREsvXDs9Gk89Ef");
                var countStmt = conn.createStatement();
                var rs = countStmt.executeQuery(cleanSQL);
        ) {}
        catch (SQLException e) {
            e.printStackTrace();
    }
    }
    public  static void cleanUpCredit () {
        var cleanSQL = "DELETE FROM app.credit_request_entity;";
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "9mREsvXDs9Gk89Ef");
                var countStmt = conn.createStatement();
                var rs = countStmt.executeQuery(cleanSQL);
        ) {}
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  static String getLastStatusCredit () {
        var statusSQL = "SELECT status  FROM credit_request_entity ORDER BY created;";
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "9mREsvXDs9Gk89Ef");
                var countStmt = conn.createStatement();
                var rs = countStmt.executeQuery(statusSQL);
        ) {
            if (rs.next()) {
            }
            var count = rs.getString(1);
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public  static String getLastStatusPayment () {
        var statusSQL = "SELECT status  FROM payment_entity ORDER BY created;";
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "9mREsvXDs9Gk89Ef");
                var countStmt = conn.createStatement();
                var rs = countStmt.executeQuery(statusSQL);
        ) {
            if (rs.next()) {
            }
            var count = rs.getString(1);
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
