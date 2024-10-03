<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adicionar Cliente</title>
</head>
<body>
<h1>Adicionar Cliente</h1>
<form action="clientes" method="post">
    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="nome" required><br>

    <label for="cpf">CPF:</label>
    <input type="text" id="cpf" name="cpf" required><br>

    <label for="endereco">Endereço:</label>
    <input type="text" id="endereco" name="endereco"><br>

    <label for="telefone">Telefone:</label>
    <input type="text" id="telefone" name="telefone"><br>

    <input type="submit" value="Adicionar Cliente">
</form>
</body>
</html>
