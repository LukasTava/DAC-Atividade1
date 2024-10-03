package br.com.loja.controllers;

import br.com.loja.DAO.FuncionarioDAO;
import br.com.loja.entidades.Funcionario;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = (Connection) getServletContext().getAttribute("conexao");
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(conn);

        try {
            List<Funcionario> funcionarios = funcionarioDAO.listarFuncionarios();
            request.setAttribute("funcionarios", funcionarios);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/listaFuncionarios.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = (Connection) getServletContext().getAttribute("conexao");
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(conn);

        String nome = request.getParameter("nome");
        String cargo = request.getParameter("cargo");
        double salario = Double.parseDouble(request.getParameter("salario"));

        Funcionario funcionario = new Funcionario(0, nome, cargo, salario);

        try {
            funcionarioDAO.inserirFuncionario(funcionario);
            response.sendRedirect("funcionarios");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }
}
