package br.com.loja.DAO;

import br.com.loja.entidades.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    private Connection connection;

    public FuncionarioDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserirFuncionario(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionarios (nome, cargo, salario) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, funcionario.getNome());
        stmt.setString(2, funcionario.getCargo());
        stmt.setDouble(3, funcionario.getSalario());
        stmt.executeUpdate();
    }

    public void atualizarFuncionario(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE funcionarios SET nome = ?, cargo = ?, salario = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, funcionario.getNome());
        stmt.setString(2, funcionario.getCargo());
        stmt.setDouble(3, funcionario.getSalario());
        stmt.setInt(4, funcionario.getId());
        stmt.executeUpdate();
    }

    public void excluirFuncionario(int id) throws SQLException {
        String sql = "DELETE FROM funcionarios WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public Funcionario buscarFuncionarioPorId(int id) throws SQLException {
        String sql = "SELECT * FROM funcionarios WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Funcionario funcionario = new Funcionario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cargo"),
                    rs.getDouble("salario")
            );
            return funcionario;
        }
        return null;
    }

    public List<Funcionario> listarFuncionarios() throws SQLException {
        String sql = "SELECT * FROM funcionarios";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Funcionario> funcionarios = new ArrayList<>();
        while (rs.next()) {
            Funcionario funcionario = new Funcionario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cargo"),
                    rs.getDouble("salario")
            );
            funcionarios.add(funcionario);
        }
        return funcionarios;
    }
}
