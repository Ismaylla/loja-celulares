package model;

import java.io.Serializable;

public class Funcionario implements Serializable {
    private int id;
    private String nome;
    private String cargo;
    private String senha;  // Adicionado o atributo senha

    public Funcionario(int id, String nome, String cargo, String senha) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.senha = senha;  // Inicializando o atributo senha
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;  // Getter para senha
    }

    public void setSenha(String senha) {
        this.senha = senha;  // Setter para senha
    }

    public Funcionario CriarFuncionarioPartirdaLinha(String CriarFuncionario) {
        String[] linhas = CriarFuncionario.split(";");
        int id = Integer.parseInt(linhas[0]);
        String nome = linhas[1];
        String cargo = linhas[2];
        String senha = linhas[3];  // Agora inclui senha
        return new Funcionario(id, nome, cargo, senha);
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cargo='" + cargo + '\'' +
                '}';
    }
}
