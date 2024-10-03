package br.com.loja.DAO;

import br.com.loja.entidades.Peca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PecaDAO {
    private Connection connection;

    public PecaDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserirPeca(Peca peca) throws SQLException {
        String sql = "INSERT INTO pecas (nome, preco) VALUES (?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, peca.getNome());
        stmt.setDouble(2, peca.getPreco());
        stmt.executeUpdate();
    }

    public void atualizarPeca(Peca peca) throws SQLException {
        String sql = "UPDATE pecas SET nome = ?, preco = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, peca.getNome());
        stmt.setDouble(2, peca.getPreco());
        stmt.setInt(3, peca.getId());
        stmt.executeUpdate();
    }

    public void excluirPeca(int id) throws SQLException {
        String sql = "DELETE FROM pecas WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public Peca buscarPecaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM pecas WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Peca(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"));
        }
        return null;
    }

    public List<Peca> listarPecas() throws SQLException {
        String sql = "SELECT * FROM pecas";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Peca> pecas = new ArrayList<>();
        while (rs.next()) {
            Peca peca = new Peca(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"));
            pecas.add(peca);
        }
        return pecas;
    }
}