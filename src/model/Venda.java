package model;

import java.util.List;
import java.util.Date;

public class Venda {
	private int id;
	private Cliente cliente;
	private List<Produto> produtos;
	private Date dataVenda;
	private double valorTotal;
	
	public Venda(int id, Cliente cliente, List<Produto> produtos, Date dataVenda, double valorTotal) {
		this.id = id;
		this.cliente = cliente;
		this.produtos = produtos;
		this.dataVenda = dataVenda;
		this.valorTotal = valorTotal;
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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", produtos=" + produtos +
                ", dataVenda=" + dataVenda +
                ", valorTotal=" + valorTotal +
                '}';
    }

}
