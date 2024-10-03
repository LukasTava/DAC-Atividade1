package br.com.loja.listeners;

import br.com.loja.utils.DatabaseConnectionManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Inicializa a conexão e cria as tabelas ao iniciar o servidor
        DatabaseConnectionManager.createTables();
        Connection connection = DatabaseConnectionManager.getConnection();
        sce.getServletContext().setAttribute("DBConnection", connection);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Fecha a conexão ao encerrar o servidor
        Connection connection = (Connection) sce.getServletContext().getAttribute("DBConnection");
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
