package br.unipar;

import java.sql.*;

public class MainCliente {

    private static final String url = "jdbc:postgresql://localhost:5432/Exemplo1";
    private static final String user = "postgres";
    private static final String password = "admin123";
}

    public static void main(String[] args) {

    }

    public static Connection connection() throws SQLException {
    return DriverManager.getConnection(url, user, password);
}
