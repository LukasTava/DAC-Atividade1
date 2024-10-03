package br.com.loja.DAO;

import br.com.loja.entidades.Venda;
import br.com.loja.entidades.Cliente;
import br.com.loja.entidades.Funcionario;
import br.com.loja.entidades.Peca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {
    private Connection connection;

    public VendaDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserirVenda(Venda venda) throws SQLException {
        String sql = "INSERT INTO vendas (cliente_id, funcionario_id, peca_id, quantidade, total) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, venda.getCliente().getId());
        stmt.setInt(2, venda.getFuncionario().getId());
        stmt.setInt(3, venda.getPeca().getId());
        stmt.setInt(4, venda.getQuantidade());
        stmt.setDouble(5, venda.getTotal());
        stmt.executeUpdate();
    }

    public List<Venda> listarVendas() throws SQLException {
        String sql = "SELECT * FROM vendas";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Venda> vendas = new ArrayList<>();
        while (rs.next()) {
            Cliente cliente = new ClienteDAO(connection).buscarClientePorId(rs.getInt("cliente_id"));
            Funcionario funcionario = new FuncionarioDAO(connection).buscarFuncionarioPorId(rs.getInt("funcionario_id"));
            Peca peca = new PecaDAO(connection).buscarPecaPorId(rs.getInt("peca_id"));

            Venda venda = new Venda(
                    rs.getInt("id"),
                    cliente,
                    funcionario,
                    peca,
                    rs.getInt("quantidade"),
                    rs.getDouble("total")
            );
            vendas.add(venda);
        }
        return vendas;
    }
}
