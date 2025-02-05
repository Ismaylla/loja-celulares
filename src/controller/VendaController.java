package controller;

import model.Venda;
import model.Produto;
import java.util.ArrayList;
import java.util.List;


//nao implementei o arquivoutil aqui pq as vendas nao preccisam ser salvas permanentenmente (eu acho)
public class VendaController {
    private List<Venda> vendas;

 
    public VendaController() {
        vendas = new ArrayList<>();
    }


    public void realizarVenda(Venda venda) {
        vendas.add(venda);
        System.out.println("Venda realizada: " + venda);
    }

    // calcular o total da venda com base nos produtos
    public double calcularTotalVenda(List<Produto> produtos) {
        double total = 0;
        for (Produto produto : produtos) {
            total += produto.getPreco();
        }
        return total;
    }

    //  emitir um aviso de estoque baixo
    public void emitirAlertaEstoque(Produto produto) {
        if (produto.getQuantEstoque() < 5) { // Considerando estoque baixo abaixo de 5
            System.out.println("ALERTA: Estoque baixo para o produto: " + produto.getNome());
        }
    }

    // listar todas as vendas
    public void listarVendas() {
        if (vendas.isEmpty()) {
            System.out.println("Não há vendas registradas.");
        } else {
            System.out.println("Lista de Vendas:");
            for (Venda venda : vendas) {
                System.out.println(venda);
            }
        }
    }
}