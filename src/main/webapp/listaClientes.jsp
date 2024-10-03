<%@ page import="br.com.loja.entidades.Cliente" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de Clientes</title>
</head>
<body>
<h1>Lista de Clientes</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>CPF</th>
        <th>Endereço</th>
        <th>Telefone</th>
    </tr>
    <%
        List<br.com.loja.entidades.Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
        for (br.com.loja.entidades.Cliente cliente : clientes) {
    %>
    <tr>
        <td><%= cliente.getId() %></td>
        <td><%= cliente.getNome() %></td>
        <td><%= cliente.getCpf() %></td>
        <td><%= cliente.getEndereco() %></td>
        <td><%= cliente.getTelefone() %></td>
    </tr>
    <% } %>
</table>

<a href="index.jsp">Voltar à Página Inicial</a>
</body>
</html>
