package br.com.loja.DAO;


import br.com.loja.entidades.Servico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {
    private Connection connection;

    public ServicoDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserirServico(Servico servico) throws SQLException {
        String sql = "INSERT INTO servicos (descricao, valor) VALUES (?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, servico.getDescricao());
        stmt.setDouble(2, servico.getValor());
        stmt.executeUpdate();
    }

    public void atualizarServico(Servico servico) throws SQLException {
        String sql = "UPDATE servicos SET descricao = ?, valor = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, servico.getDescricao());
        stmt.setDouble(2, servico.getValor());
        stmt.setInt(3, servico.getId());
        stmt.executeUpdate();
    }

    public void excluirServico(int id) throws SQLException {
        String sql = "DELETE FROM servicos WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public Servico buscarServicoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM servicos WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Servico(rs.getInt("id"), rs.getString("descricao"), rs.getDouble("valor"));
        }
        return null;
    }

    public List<Servico> listarServicos() throws SQLException {
        String sql = "SELECT * FROM servicos";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Servico> servicos = new ArrayList<>();
        while (rs.next()) {
            Servico servico = new Servico(rs.getInt("id"), rs.getString("descricao"), rs.getDouble("valor"));
            servicos.add(servico);
        }
        return servicos;
    }
}
