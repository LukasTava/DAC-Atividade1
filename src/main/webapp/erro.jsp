<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Erro</title>
</head>
<body>
<h1>Ocorreu um erro</h1>
<p><%= request.getAttribute("mensagemErro") %></p>
<a href="index.jsp">Voltar à Página Inicial</a>
</body>
</html>
