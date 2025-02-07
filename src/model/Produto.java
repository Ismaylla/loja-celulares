package model;

import java.io.Serializable;

public class Produto implements Serializable {
	private String nome;
	private String modelo;
	private String marca;
	private double preco;
	private int estoqueAtual;
	private int estoqueMinimo;

	public Produto(String nome, String modelo, String marca, double preco, int estoqueAtual, int estoqueMinimo) {
		this.nome = nome;
		this.marca = marca;
		this.preco = preco;
		this.estoqueAtual = estoqueAtual;
		this.estoqueMinimo = estoqueMinimo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getEstoqueAtual() {
		return estoqueAtual;
	}

	public void setEstoqueAtual(int estoqueAtual) {
		this.estoqueAtual = estoqueAtual;
	}

	public int getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(int estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}

	public String getmodelo(){
		return modelo;
	}

	public void setmodelo(String modelo){
		this.modelo = modelo;
	}


	public Produto CriarProdutoPartiDaLinha(String CriarProduto) {
		String[] linhas = CriarProduto.split(";");
		String nome = linhas[0];
		String modelo = linhas[1];
		String marca = linhas[2];
		double preco = Double.parseDouble(linhas[3]);
		int estoqueAtual = Integer.parseInt(linhas[4]);
		int estoqueMinimo = Integer.parseInt(linhas[5]);
		return new Produto(nome, modelo, marca, preco, estoqueAtual, estoqueMinimo);
	}

	@Override
	public String toString() {
		return "Produto{" +
				"nome='" + nome + '\'' +
				", marca='" + marca + '\'' +
				", preco=" + preco +
				", estoqueAtual=" + estoqueAtual +
				", estoqueMinimo=" + estoqueMinimo +
				'}';
	}
}
