package model;

import java.io.Serializable;

public class Cliente implements Serializable {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Double quantidade_money;
    private int quantidade_vezes_comprou;


    public Cliente(String nome, String cpf, String telefone, String email, Double quantidade_money,int quantidade_vezes_comprou) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.quantidade_money = quantidade_money;
        this.quantidade_vezes_comprou = quantidade_vezes_comprou;
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
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getQuantidade_money() {
        return quantidade_money;
    }

    public void setQuantidade_money(Double quantidade_money) {
        this.quantidade_money = quantidade_money;
    }

    public int getQuantidade_vezes_comprou() {
        return quantidade_vezes_comprou;
    }

    public  void  setQuantidade_vezes_comprou(){
        this.quantidade_vezes_comprou = 1;
    }

    public Cliente CriarClientePartirdaLinha(String CriarCliente){
        String[] linhas = CriarCliente.split(";");
        String nome = linhas[0];
        String cpf = linhas[1];
        String telefone = linhas[2];
        String email = linhas[3];
        Double quantidade_money = Double.parseDouble(linhas[4]);
        int quantidade_vezes_comprou = Integer.parseInt(linhas[5]);
        return new Cliente(nome, cpf, telefone, email, quantidade_money, quantidade_vezes_comprou);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
