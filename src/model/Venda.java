package model;

import util.ArquivoUtil;

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


    public static Venda criarVendaPartiDaLinha(String linhaVenda) {
        String[] linhas = ArquivoUtil.splitCSV(linhaVenda);

        String nomeCliente = ArquivoUtil.unquote(linhas[0]);
        String cpfCliente = ArquivoUtil.unquote(linhas[1]);
        String telefoneCliente = ArquivoUtil.unquote(linhas[2]);
        String emailCliente = ArquivoUtil.unquote(linhas[3]);
        double dinheiroTotalGasto = Double.parseDouble(linhas[4]);
        int quantidadeVezesComprou = Integer.parseInt(linhas[5]);

        Cliente cliente = new Cliente(nomeCliente, cpfCliente, telefoneCliente, emailCliente, dinheiroTotalGasto, quantidadeVezesComprou);

        String codigoProduto = ArquivoUtil.unquote(linhas[6]);
        String nomeProduto = ArquivoUtil.unquote(linhas[7]);
        String modeloProduto = ArquivoUtil.unquote(linhas[8]);
        String marcaProduto = ArquivoUtil.unquote(linhas[9]);
        double precoProduto = Double.parseDouble(linhas[10]);
        String corProduto = ArquivoUtil.unquote(linhas[11]);
        String armazenamentoProduto = ArquivoUtil.unquote(linhas[12]);
        String memoriaRAMProduto = ArquivoUtil.unquote(linhas[13]);
        double tamanhoTelaProduto = Double.parseDouble(linhas[14]);
        boolean tem5GProduto = Boolean.parseBoolean(linhas[15]);
        boolean resistenciaAguaProduto = Boolean.parseBoolean(linhas[16]);
        int estoqueAtualProduto = Integer.parseInt(linhas[17]);
        int estoqueMinimoProduto = Integer.parseInt(linhas[18]);

        Produto produto = new Produto(codigoProduto, nomeProduto, modeloProduto, marcaProduto, precoProduto, corProduto, armazenamentoProduto, memoriaRAMProduto, tamanhoTelaProduto, tem5GProduto, resistenciaAguaProduto, estoqueAtualProduto, estoqueMinimoProduto);

        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto);

        return new Venda(cliente, produtos, LocalDate.now(), dinheiroTotalGasto);
    }

    @Override
    public String toString() {
        StringBuilder produtosCSV = new StringBuilder();
        for (Produto produto : produtosVendidos) {
            produtosCSV.append(produto.toString()).append(";");
        }

        return ArquivoUtil.quote(cliente.getNome()) + ";" +
                ArquivoUtil.quote(cliente.getCpf()) + ";" +
                ArquivoUtil.quote(cliente.getTelefone()) + ";" +
                ArquivoUtil.quote(cliente.getEmail()) + ";" +
                String.valueOf(cliente.getDinheiroTotalGasto()) + ";" +
                String.valueOf(cliente.getQuantidadeVezesComprou()) + ";" +
                produtosCSV.toString() +
                String.valueOf(valorTotal);
    }
}
