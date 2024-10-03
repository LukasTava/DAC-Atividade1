package br.com.loja.controllers;

import br.com.loja.DAO.VendaDAO;
import br.com.loja.entidades.Venda;
import br.com.loja.DAO.ClienteDAO;
import br.com.loja.DAO.FuncionarioDAO;
import br.com.loja.DAO.PecaDAO;
import br.com.loja.entidades.Cliente;
import br.com.loja.entidades.Funcionario;
import br.com.loja.entidades.Peca;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VendaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = (Connection) getServletContext().getAttribute("conexao");
        VendaDAO vendaDAO = new VendaDAO(conn);

        try {
            List<Venda> vendas = vendaDAO.listarVendas();
            request.setAttribute("vendas", vendas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/listaVendas.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = (Connection) getServletContext().getAttribute("conexao");
        VendaDAO vendaDAO = new VendaDAO(conn);
        ClienteDAO clienteDAO = new ClienteDAO(conn);
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(conn);
        PecaDAO pecaDAO = new PecaDAO(conn);

        try {
            int clienteId = Integer.parseInt(request.getParameter("clienteId"));
            int funcionarioId = Integer.parseInt(request.getParameter("funcionarioId"));
            int pecaId = Integer.parseInt(request.getParameter("pecaId"));
            int quantidade = Integer.parseInt(request.getParameter("quantidade"));

            Cliente cliente = clienteDAO.buscarClientePorId(clienteId);
            Funcionario funcionario = funcionarioDAO.buscarFuncionarioPorId(funcionarioId);
            Peca peca = pecaDAO.buscarPecaPorId(pecaId);

            if (cliente == null || funcionario == null || peca == null) {
                request.setAttribute("mensagemErro", "Cliente, funcionário ou peça não encontrados!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
                dispatcher.forward(request, response);
                return;
            }

            double total = peca.getPreco() * quantidade;

            Venda venda = new Venda(0, cliente, funcionario, peca, quantidade, total);

            vendaDAO.inserirVenda(venda);

            response.sendRedirect("vendas");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("mensagemErro", "Erro nos parâmetros da requisição.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }
    }
}
