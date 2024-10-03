package br.com.loja.utils;

import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {
        try {
            String url = "jdbc:sqlite:loja.db";
            Connection conn = DriverManager.getConnection(url);
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
