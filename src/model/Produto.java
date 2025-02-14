package model;

import util.ArquivoUtil;
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


	public static Produto CriarProdutoPartiDaLinha(String linha) {
		String[] campos = ArquivoUtil.splitCSV(linha);

		if (campos.length < 13) {
			System.out.println("Erro: Dados insuficientes na linha: " + linha);
			return null;
		}

		String codigo = ArquivoUtil.unquote(campos[0]);
		String nome = ArquivoUtil.unquote(campos[1]);
		String modelo = ArquivoUtil.unquote(campos[2]);
		String marca = ArquivoUtil.unquote(campos[3]);
		double preco = Double.parseDouble(campos[4]);
		String cor = ArquivoUtil.unquote(campos[5]);
		String armazenamento = ArquivoUtil.unquote(campos[6]);
		String memoriaRAM = ArquivoUtil.unquote(campos[7]);
		double tamanhoTela = Double.parseDouble(campos[8]);
		boolean tem5G = Boolean.parseBoolean(campos[9]);
		boolean resistenciaAgua = Boolean.parseBoolean(campos[10]);
		int estoqueAtual = Integer.parseInt(campos[11]);
		int estoqueMinimo = Integer.parseInt(campos[12]);

		return new Produto(codigo, nome, modelo, marca, preco, cor, armazenamento, memoriaRAM, tamanhoTela, tem5G, resistenciaAgua, estoqueAtual, estoqueMinimo);
	}

	@Override
	public String toString() {
		return String.join(";",
				ArquivoUtil.quote(codigo),
				ArquivoUtil.quote(nome),
				ArquivoUtil.quote(modelo),
				ArquivoUtil.quote(marca),
				String.valueOf(preco),
				ArquivoUtil.quote(cor),
				ArquivoUtil.quote(armazenamento),
				ArquivoUtil.quote(memoriaRAM),
				String.valueOf(tamanhoTela),
				String.valueOf(tem5G),
				String.valueOf(resistenciaAgua),
				String.valueOf(estoqueAtual),
				String.valueOf(estoqueMinimo)
		);
	}


}
