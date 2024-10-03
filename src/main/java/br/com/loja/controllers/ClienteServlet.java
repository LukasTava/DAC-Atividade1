package br.com.loja.controllers;

import br.com.loja.DAO.ClienteDAO;
import br.com.loja.entidades.Cliente;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = (Connection) getServletContext().getAttribute("DBConnection");
        ClienteDAO clienteDAO = new ClienteDAO(conn);

        try {
            List<Cliente> clientes = clienteDAO.listarClientes();
            request.setAttribute("clientes", clientes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/listaClientes.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém a conexão do contexto
        Connection conn = (Connection) getServletContext().getAttribute("DBConnection");
        ClienteDAO clienteDAO = new ClienteDAO(conn);

        // Obtém os parâmetros do formulário
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");

        // Cria o objeto Cliente
        Cliente cliente = new Cliente(0, nome, cpf, endereco, telefone);

        try {
            // Insere o cliente no banco de dados
            clienteDAO.inserirCliente(cliente);
            // Redireciona para a página de listagem de clientes após a inserção
            response.sendRedirect("clientes");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }
}
