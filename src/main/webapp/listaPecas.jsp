<%@ page import="br.com.loja.entidades.Peca" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de Peças</title>
</head>
<body>
<h1>Lista de Peças</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Preço</th>
    </tr>
    <%
        List<br.com.loja.entidades.Peca> pecas = (List<Peca>) request.getAttribute("pecas");
        for (br.com.loja.entidades.Peca peca : pecas) {
    %>
    <tr>
        <td><%= peca.getId() %></td>
        <td><%= peca.getNome() %></td>
        <td><%= peca.getPreco() %></td>
    </tr>
    <% } %>
</table>

<a href="index.jsp">Voltar à Página Inicial</a>
</body>
</html>
