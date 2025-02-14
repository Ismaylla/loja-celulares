package validator;

import model.Venda;
import model.Cliente;
import model.Produto;

import java.util.List;

public class VendaValidator {

    public static boolean validarCliente(Cliente cliente) {
        return cliente != null && cliente.getNome() != null && !cliente.getNome().isEmpty();
    }

    public static boolean validarProdutosVendidos(List<Produto> produtosVendidos) {
        return produtosVendidos != null && !produtosVendidos.isEmpty();
    }

    public static boolean validarValorTotal(double valorTotal) {
        return valorTotal >= 0;
    }

    public static boolean validarVenda(Venda venda) {
        if (!validarCliente(venda.getCliente())) {
            System.out.println("Cliente inválido.");
            return false;
        }
        if (!validarProdutosVendidos(venda.getProdutosVendidos())) {
            System.out.println("Lista de produtos vendidos inválida.");
            return false;
        }
        if (!validarValorTotal(venda.getValorTotal())) {
            System.out.println("Valor total da venda inválido.");
            return false;
        }
        return true;
    }
}
