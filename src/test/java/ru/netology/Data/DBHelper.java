package ru.netology.Data;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBHelper {

    public static final String DB_URL_MYSQL = "jdbc:mysql://localhost:3306/app";
    public static final String DB_URL_POSTGRESQL = "jdbc:postgresql://localhost:5432/postgres";

    public static void cleanUpPayment(String url) {

        try (
                var conn = DriverManager.getConnection(
                        url, "app", "9mREsvXDs9Gk89Ef");
                Statement stmt = conn.createStatement();
        ) {
            String sql = "DELETE FROM payment_entity;";
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cleanUpCredit(String url) {
        try (
                var conn = DriverManager.getConnection(
                        url, "app", "9mREsvXDs9Gk89Ef");
                Statement stmt = conn.createStatement();
        ) {
            String sql = "DELETE FROM credit_request_entity;";
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getLastStatusCredit(String url) {
        var statusSQL = "SELECT * FROM credit_request_entity;";
        try (
                var conn = DriverManager.getConnection(
                        url, "app", "9mREsvXDs9Gk89Ef");
                var countStmt = conn.createStatement();
                var rs = countStmt.executeQuery(statusSQL);
        ) {
            if (rs.next()) {
                return rs.getString("status");
            }
            return "";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getLastStatusPayment(String url) {
        var statusSQL = "SELECT * FROM payment_entity;";
        try (
                var conn = DriverManager.getConnection(
                        url, "app", "9mREsvXDs9Gk89Ef");
                var statusStmt = conn.createStatement();
                var rs = statusStmt.executeQuery(statusSQL);
        ) {
            if (rs.next()) {
                return rs.getString("status");
            }
            return "";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getLastAmountPayment(String url) {
        var amountSQL = "SELECT *  FROM payment_entity;";
        try (
                var conn = DriverManager.getConnection(
                        url, "app", "9mREsvXDs9Gk89Ef");
                var amountStmt = conn.createStatement();
                var rs = amountStmt.executeQuery(amountSQL);
        ) {
            if (rs.next()) {
                return rs.getString("amount");
            }
            return "";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
