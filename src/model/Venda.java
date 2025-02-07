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

        // Criar objeto Cliente
        Cliente cliente = new Cliente(nomeCliente, cpfCliente, telefoneCliente, emailCliente, quantidadeMoney, quantidadeVezesComprou);

        // Dados do produto
        String nomeProduto = linhas[6];
        String modeloProduto = linhas[7];
        String marcaProduto = linhas[8];
        double precoProduto = Double.parseDouble(linhas[9]);
        int estoqueAtualProduto = Integer.parseInt(linhas[10]);
        int estoqueMinimoProduto = Integer.parseInt(linhas[11]);

        // Criar lista de produtos
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(nomeProduto, modeloProduto, marcaProduto, precoProduto, estoqueAtualProduto, estoqueMinimoProduto));

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
