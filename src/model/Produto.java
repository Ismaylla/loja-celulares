package model;

import java.io.Serializable;

public class Produto implements Serializable {
	private String codigo;
	private String nome;
	private String modelo;
	private String marca;
	private double preco;
	private String cor;
	private String armazenamento;
	private String memoriaRAM;
	private double tamanhoTela;
	private boolean tem5G;
	private boolean resistenciaAgua;
	private int estoqueAtual;
	private int estoqueMinimo;


	public Produto(String codigo, String nome, String modelo, String marca, double preco, String cor, String armazenamento, String memoriaRAM, double tamanhoTela, boolean tem5G, boolean resistenciaAgua,  int estoqueAtual, int estoqueMinimo) {
		this.codigo = codigo;
		this.nome = nome;
		this.modelo = modelo;
		this.marca = marca;
		this.preco = preco;
		this.cor = cor;
		this.armazenamento = armazenamento;
		this.memoriaRAM = memoriaRAM;
		this.tamanhoTela = tamanhoTela;
		this.tem5G = tem5G;
		this.resistenciaAgua = resistenciaAgua;
		this.estoqueAtual = estoqueAtual;
		this.estoqueMinimo = estoqueMinimo;
	}


	public String getCodigo() { return codigo;} //Só possui o Get pois não será atualizado

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getModelo() { return modelo; }

	public void setModelo(String modelo) { this.modelo = modelo;}

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

	public String getCor() { return cor;}

	public void setCor(String cor) { this.cor = cor;}

	public String getArmazenamento() { return armazenamento;}

	public void setArmazenamento(String armazenamento) { this.armazenamento = armazenamento;}

	public String getMemoriaRAM() { return memoriaRAM;}

	public void setMemoriaRAM(String memoriaRAM) { this.memoriaRAM = memoriaRAM; }

	public double getTamanhoTela() { return tamanhoTela; }

	public void setTamanhoTela(double tamanhoTela) { this.tamanhoTela = tamanhoTela; }

	public boolean isTem5G() { return tem5G;}

	public void setTem5G(boolean tem5G) { this.tem5G = tem5G;}

	public boolean isResistenciaAgua() { return resistenciaAgua;}

	public void setResistenciaAgua(boolean resistenciaAgua) { this.resistenciaAgua = resistenciaAgua;}

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


	public Produto CriarProdutoPartiDaLinha(String CriarProduto) {
		String[] linhas = CriarProduto.split(";");
		String codigo = linhas[0];
		String nome = linhas[1];
		String modelo = linhas[2];
		String marca = linhas[3];
		double preco = Double.parseDouble(linhas[4]);
		String cor = linhas[5];
		String armazenamento = linhas[6];
		String memoriaRAM = linhas[7];
		double tamanhoTela = Double.parseDouble(linhas[8]);
		boolean tem5G = Boolean.parseBoolean(linhas[9]);
		boolean resistenciaAgua = Boolean.parseBoolean(linhas[10]);
		int estoqueAtual = Integer.parseInt(linhas[11]);
		int estoqueMinimo = Integer.parseInt(linhas[12]);
		return new Produto(codigo, nome, modelo, marca, preco, cor, armazenamento, memoriaRAM, tamanhoTela, tem5G, resistenciaAgua, estoqueAtual, estoqueMinimo);
	}

	@Override
	public String toString() {
		return "Produto{" +
				"codigo='" + codigo + '\'' +
				", nome='" + nome + '\'' +
				", modelo='" + modelo + '\'' +
				", marca='" + marca + '\'' +
				", preco=" + preco +
				", cor='" + cor + '\'' +
				", armazenamento='" + armazenamento + '\'' +
				", memoriaRAM='" + memoriaRAM + '\'' +
				", tamanhoTela=" + tamanhoTela +
				", tem5G=" + tem5G +
				", resistenciaAgua=" + resistenciaAgua +
				", estoqueAtual=" + estoqueAtual +
				", estoqueMinimo=" + estoqueMinimo +
				'}';
	}
}
