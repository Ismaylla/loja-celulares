package model;

public class Produto {
	private int id;
	private String nome;
	private String marca;
	private String modelo;
	private double preco;
	private int QuantEstoque;
	private int estoqueMin;
	
	public Produto(int id, String nome, double preco, int QuantEstoque, int estoqueMin) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.QuantEstoque = QuantEstoque;
		this.estoqueMin = estoqueMin;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
		this.marca =  marca;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public double getPreco() {
		return preco;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public int getQuantEstoque() {
		return QuantEstoque;
	}
	
	public void setQuantEstoque(int QuantEstoque) {
		this.QuantEstoque = QuantEstoque;
	}
	
	public int getEstoqueMin() {
		return estoqueMin;
	}
	
	public void setEstoqueMin(int estoqueMin) {
		this.estoqueMin = estoqueMin;
	}
	
	@Override
    public String toString() {
        return "Produto{" +
                "id =" + id +
                ", nome ='" + nome + '\'' +
                ", preco =" + preco +
                ", marca =" + marca +
                ", modelo =" + modelo +
                ", quantidadeEstoque =" + QuantEstoque +
                ", estoqueMinimo =" + estoqueMin +
                '}';
    }
}
