package org.example.Veritabani;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnection {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/ders_kayit_sistemi";
    private static final String KULLANICI_ADI = "postgres";
    private static final String SIFRE = "admin";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, KULLANICI_ADI, SIFRE);
    }
}

