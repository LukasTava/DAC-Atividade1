package br.com.loja.controllers;

import br.com.loja.DAO.ServicoDAO;
import br.com.loja.entidades.Servico;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ServicoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = (Connection) getServletContext().getAttribute("conexao");
        ServicoDAO servicoDAO = new ServicoDAO(conn);

        try {
            List<Servico> servicos = servicoDAO.listarServicos();
            request.setAttribute("servicos", servicos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/listaServicos.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = (Connection) getServletContext().getAttribute("conexao");
        ServicoDAO servicoDAO = new ServicoDAO(conn);

        String descricao = request.getParameter("descricao");
        double valor = Double.parseDouble(request.getParameter("valor"));

        Servico servico = new Servico(0, descricao, valor);

        try {
            servicoDAO.inserirServico(servico);
            response.sendRedirect("servicos");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }
}
