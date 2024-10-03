<%@ page import="br.com.loja.entidades.Venda" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de Vendas</title>
</head>
<body>
<h1>Lista de Vendas</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Cliente</th>
        <th>Funcionário</th>
        <th>Peça</th>
        <th>Quantidade</th>
        <th>Total</th>
    </tr>
    <%
        List<br.com.loja.entidades.Venda> vendas = (List<Venda>) request.getAttribute("vendas");
        for (br.com.loja.entidades.Venda venda : vendas) {
    %>
    <tr>
        <td><%= venda.getId() %></td>
        <td><%= venda.getCliente().getNome() %></td>
        <td><%= venda.getFuncionario().getNome() %></td>
        <td><%= venda.getPeca().getNome() %></td>
        <td><%= venda.getQuantidade() %></td>
        <td><%= venda.getTotal() %></td>
    </tr>
    <% } %>
</table>

<a href="index.jsp">Voltar à Página Inicial</a>
</body>
</html>
