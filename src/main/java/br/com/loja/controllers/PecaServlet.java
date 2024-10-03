package br.com.loja.controllers;

import br.com.loja.DAO.PecaDAO;
import br.com.loja.entidades.Peca;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PecaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = (Connection) getServletContext().getAttribute("conexao");
        PecaDAO pecaDAO = new PecaDAO(conn);

        try {
            List<Peca> pecas = pecaDAO.listarPecas();
            request.setAttribute("pecas", pecas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/listaPecas.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = (Connection) getServletContext().getAttribute("conexao");
        PecaDAO pecaDAO = new PecaDAO(conn);

        String nome = request.getParameter("nome");
        double preco = Double.parseDouble(request.getParameter("preco"));

        Peca peca = new Peca(0, nome, preco);

        try {
            pecaDAO.inserirPeca(peca);
            response.sendRedirect("pecas");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }
}

