package controller;

import model.Cliente;
import model.Produto;
import model.Venda;
import util.ArquivoUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VendaController {
    private static final String ARQUIVO_VENDAS = System.getProperty("user.dir") + "/src/arquivos/venda";
    private List<Venda> vendas;

    public VendaController() {
        this.vendas = carregarVendas();
    }

    private void carregarVendasAntes() {
        this.vendas = carregarVendas();
    }

    public void registrarVenda(Cliente cliente, List<Produto> produtosVendidos, double valorTotal) {
        carregarVendasAntes();
        Venda venda = new Venda(cliente, produtosVendidos, LocalDate.now(), valorTotal);
        vendas.add(venda);
        salvarVendas();
        System.out.println("Venda registrada com sucesso.");
    }

    public void listarVendas() {
        carregarVendasAntes();
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            for (Venda venda : vendas) {
                System.out.println(venda);
            }
        }
    }

    public double calcularTotalVendas() {
        carregarVendasAntes();
        double total = 0;
        for (Venda venda : vendas) {
            total += venda.getValorTotal();
        }
        return total;
    }

    private void salvarVendas() {
        try {
            ArquivoUtil.salvarLista(ARQUIVO_VENDAS, vendas);
        } catch (IOException e) {
            System.out.println("Erro ao salvar vendas: " + e.getMessage());
        }
    }

    private List<Venda> carregarVendas() {
        try {
            return ArquivoUtil.carregarLista(ARQUIVO_VENDAS);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nenhuma venda encontrada ou erro ao carregar: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
