package br.com.loja.entidades;

public class Venda {
    private int id;
    private Cliente cliente;
    private Funcionario funcionario;
    private Peca peca;
    private int quantidade;
    private double total;

    public Venda(int id, Cliente cliente, Funcionario funcionario, Peca peca, int quantidade, double total) {
        this.id = id;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.peca = peca;
        this.quantidade = quantidade;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}