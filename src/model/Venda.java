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

        String[] campos = ArquivoUtil.splitCSV(linhaVenda);

        if (campos.length < 19) {
            System.out.println("Erro: Dados insuficientes na linha: " + linhaVenda);
            return null;
        }

        String nomeCliente = ArquivoUtil.unquote(campos[0]);
        String cpfCliente = ArquivoUtil.unquote(campos[1]);
        String telefoneCliente = ArquivoUtil.unquote(campos[2]);
        String emailCliente = ArquivoUtil.unquote(campos[3]);
        double dinheiroTotalGasto = Double.parseDouble(campos[4]);
        int quantidadeVezesComprou = Integer.parseInt(campos[5]);

        Cliente cliente = new Cliente(nomeCliente, cpfCliente, telefoneCliente, emailCliente, dinheiroTotalGasto, quantidadeVezesComprou);

        String codigoProduto = ArquivoUtil.unquote(campos[6]);
        String nomeProduto = ArquivoUtil.unquote(campos[7]);
        String modeloProduto = ArquivoUtil.unquote(campos[8]);
        String marcaProduto = ArquivoUtil.unquote(campos[9]);
        double precoProduto = Double.parseDouble(campos[10]);
        String corProduto = ArquivoUtil.unquote(campos[11]);
        String armazenamentoProduto = ArquivoUtil.unquote(campos[12]);
        String memoriaRAMProduto = ArquivoUtil.unquote(campos[13]);
        double tamanhoTelaProduto = Double.parseDouble(campos[14]);
        boolean tem5GProduto = Boolean.parseBoolean(campos[15]);
        boolean resistenciaAguaProduto = Boolean.parseBoolean(campos[16]);
        int estoqueAtualProduto = Integer.parseInt(campos[17]);
        int estoqueMinimoProduto = Integer.parseInt(campos[18]);

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
