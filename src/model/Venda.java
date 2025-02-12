package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venda implements Serializable {
    private Cliente cliente;
    private List<Produto> produtosVendidos;
    private LocalDate dataVenda;
    private double valorTotal;

    public Venda(Cliente cliente, List<Produto> produtosVendidos, LocalDate dataVenda, double valorTotal) {
        this.cliente = cliente;
        this.produtosVendidos = produtosVendidos;
        this.dataVenda = dataVenda;
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutosVendidos() {
        return produtosVendidos;
    }

    public void setProdutosVendidos(List<Produto> produtosVendidos) {
        this.produtosVendidos = produtosVendidos;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Venda criarVendaPartiDaLinha(String linhaVenda) {
        String[] linhas = linhaVenda.split(";");

        // Dados do cliente
        String nomeCliente = linhas[0];
        String cpfCliente = linhas[1];
        String telefoneCliente = linhas[2];
        String emailCliente = linhas[3];
        double quantidadeMoney = Double.parseDouble(linhas[4]);
        int quantidadeVezesComprou = Integer.parseInt(linhas[5]);

        Cliente cliente = new Cliente(nomeCliente, cpfCliente, telefoneCliente, emailCliente, quantidadeMoney, quantidadeVezesComprou);

        String codigoProduto = linhas[6];
        String nomeProduto = linhas[7];
        String modeloProduto = linhas[8];
        String marcaProduto = linhas[9];
        double precoProduto = Double.parseDouble(linhas[10]);
        String corProduto = linhas[11];
        String armazenamentoProduto = linhas[12];
        String memoriaRAMProduto = linhas[13];
        double tamanhoTelaProduto = Double.parseDouble(linhas[14]);
        boolean tem5GProduto = Boolean.parseBoolean(linhas[15]);
        boolean resistenciaAguaProduto = Boolean.parseBoolean(linhas[16]);
        int estoqueAtualProduto = Integer.parseInt(linhas[17]);
        int estoqueMinimoProduto = Integer.parseInt(linhas[18]);

        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(codigoProduto, nomeProduto, modeloProduto, marcaProduto, precoProduto, corProduto, armazenamentoProduto, memoriaRAMProduto, tamanhoTelaProduto, tem5GProduto, resistenciaAguaProduto, estoqueAtualProduto, estoqueMinimoProduto));
        return new Venda(cliente, produtos, java.time.LocalDate.now(), quantidadeMoney);
    }

    @Override
    public String toString() {
        return "Venda{" +
                "cliente=" + cliente +
                ", produtosVendidos=" + produtosVendidos +
                ", dataVenda=" + dataVenda +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
