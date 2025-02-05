package model;

public class Cliente {
	private int id;
	private String nome;
	private String cpf;
	private String numCelular;
	private String email;
	private String endereco;
	
	public Cliente(int id, String nome, String cpf, String numCelular) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.numCelular = numCelular;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return numCelular;
    }

    public void setTelefone(String numCelular) {
        this.numCelular = numCelular;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public String getEndereco() {
    	return endereco;
    }
    
    public void setEndereco(String endereco) {
    	this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cliente {" +
                "id =" + id +
                ", nome ='" + nome + '\'' +
                ", cpf ='" + cpf + '\'' +
                ", número de contato ='" + numCelular + '\'' +
                ", e-mail =" + email + '\''+
                ", endereço =" + endereco + "}";
    }

}
