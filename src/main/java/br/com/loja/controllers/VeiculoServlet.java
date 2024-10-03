package br.com.loja.controllers;

import br.com.loja.DAO.VeiculoDAO;
import br.com.loja.entidades.Veiculo;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VeiculoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = (Connection) getServletContext().getAttribute("conexao");
        VeiculoDAO veiculoDAO = new VeiculoDAO(conn);

        try {
            List<Veiculo> veiculos = veiculoDAO.listarVeiculos();
            request.setAttribute("veiculos", veiculos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/listaVeiculos.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = (Connection) getServletContext().getAttribute("conexao");
        VeiculoDAO veiculoDAO = new VeiculoDAO(conn);

        String modelo = request.getParameter("modelo");
        String placa = request.getParameter("placa");

        Veiculo veiculo = new Veiculo(0, modelo, placa);

        try {
            veiculoDAO.inserirVeiculo(veiculo);
            response.sendRedirect("veiculos");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }
}
