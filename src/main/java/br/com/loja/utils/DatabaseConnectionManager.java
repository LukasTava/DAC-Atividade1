package br.com.loja.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionManager {

    // Método para obter a conexão com o banco de dados SQLite
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // O caminho para o banco de dados
            String url = "jdbc:sqlite:loja.db"; // Cria o arquivo loja.db no diretório atual
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    // Método para criar uma tabela de exemplo
    public static void createTables() {
        String sql = "CREATE TABLE IF NOT EXISTS clientes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "cpf TEXT NOT NULL," +
                "endereco TEXT," +
                "telefone TEXT" +
                ");";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
