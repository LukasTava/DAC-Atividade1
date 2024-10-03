package br.com.loja.DAO;

import br.com.loja.entidades.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {
    private Connection connection;

    public VeiculoDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserirVeiculo(Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO veiculos (modelo, placa) VALUES (?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, veiculo.getModelo());
        stmt.setString(2, veiculo.getPlaca());
        stmt.executeUpdate();
    }

    public void atualizarVeiculo(Veiculo veiculo) throws SQLException {
        String sql = "UPDATE veiculos SET modelo = ?, placa = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, veiculo.getModelo());
        stmt.setString(2, veiculo.getPlaca());
        stmt.setInt(3, veiculo.getId());
        stmt.executeUpdate();
    }

    public void excluirVeiculo(int id) throws SQLException {
        String sql = "DELETE FROM veiculos WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public Veiculo buscarVeiculoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM veiculos WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Veiculo(rs.getInt("id"), rs.getString("modelo"), rs.getString("placa"));
        }
        return null;
    }

    public List<Veiculo> listarVeiculos() throws SQLException {
        String sql = "SELECT * FROM veiculos";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Veiculo> veiculos = new ArrayList<>();
        while (rs.next()) {
            Veiculo veiculo = new Veiculo(rs.getInt("id"), rs.getString("modelo"), rs.getString("placa"));
            veiculos.add(veiculo);
        }
        return veiculos;
    }
}
