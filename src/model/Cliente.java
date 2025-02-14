package model;
import util.ArquivoUtil;

import java.io.Serializable;

public class Cliente implements Serializable {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Double dinheiroTotalGasto;
    private int quantidadeVezesComprou;


    public Cliente(String nome, String cpf, String telefone, String email, Double dinheiroTotalGasto,int quantidadeVezesComprou) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.dinheiroTotalGasto = dinheiroTotalGasto;
        this.quantidadeVezesComprou = quantidadeVezesComprou;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    } //Só precisa do get pois o valor nunca será atualizado atualizado

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

    public Double getDinheiroTotalGasto() {
        return dinheiroTotalGasto;
    }

    public void setDinheiroTotalGasto(Double dinheiroTotalGasto) {
        this.dinheiroTotalGasto += dinheiroTotalGasto;
    }

    public int getQuantidadeVezesComprou() {
        return quantidadeVezesComprou;
    }

    public  void  contadorCompras(){
        this.quantidadeVezesComprou++;
    }

    public void setupClientePosCompra(double valorTotal){
        contadorCompras();
        setDinheiroTotalGasto(valorTotal);
    }

    public static Cliente CriarClientePartirdaLinha(String linhaCliente) {

        String[] campos = ArquivoUtil.splitCSV(linhaCliente);

        if (campos.length < 6) {
            System.out.println("Erro: Dados insuficientes na linha: " + linhaCliente);
            return null;
        }

        String nome = ArquivoUtil.unquote(campos[0]);
        String cpf = ArquivoUtil.unquote(campos[1]);
        String telefone = ArquivoUtil.unquote(campos[2]);
        String email = ArquivoUtil.unquote(campos[3]);
        Double dinheiroTotalGasto = Double.parseDouble(campos[4]);
        int quantidadeVezesComprou = Integer.parseInt(campos[5]);

        return new Cliente(nome, cpf, telefone, email, dinheiroTotalGasto, quantidadeVezesComprou);
    }

    @Override
    public String toString() {
        return ArquivoUtil.quote(nome) + ";" +
                ArquivoUtil.quote(cpf) + ";" +
                ArquivoUtil.quote(telefone) + ";" +
                ArquivoUtil.quote(email) + ";" +
                String.valueOf(dinheiroTotalGasto) + ";" +
                String.valueOf(quantidadeVezesComprou);
    }

}
