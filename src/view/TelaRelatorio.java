package view;

import controller.VendaController;
import model.Venda;
import model.Produto;
import model.Cliente;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaRelatorio extends JFrame {

    private VendaController vendaController;
    private JTextArea textAreaRelatorio;

    public TelaRelatorio(VendaController vendaController) {
        this.vendaController = vendaController;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Relatório de Vendas");
        setSize(746, 559);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.LIGHT_GRAY);

        JPanel painelRodape = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelRodape.setBackground(Color.LIGHT_GRAY);

        JButton btnAtualizar = new JButton("Atualizar Relatório");
        JButton btnFechar = new JButton("Voltar");

        painelRodape.add(btnAtualizar);
        painelRodape.add(btnFechar);
        add(painelRodape, BorderLayout.SOUTH);

        textAreaRelatorio = new JTextArea(20, 60);
        textAreaRelatorio.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaRelatorio);
        add(scrollPane, BorderLayout.CENTER);

        btnAtualizar.addActionListener(e -> atualizarRelatorio());
        btnFechar.addActionListener(e -> dispose());

        atualizarRelatorio();
        setVisible(true);
    }

    private void atualizarRelatorio() {
        textAreaRelatorio.setText("");
        List<Venda> vendas = vendaController.carregarVendas();

        if (vendas.isEmpty()) {
            textAreaRelatorio.append("Nenhuma venda registrada.\n");
            return;
        }

        double totalVendas = vendaController.calcularTotalVendas();
        textAreaRelatorio.append("=== RELATÓRIO DE VENDAS ===\n");
        textAreaRelatorio.append("Total de vendas registradas: " + vendas.size() + "\n");
        textAreaRelatorio.append("Valor total vendido: R$ " + totalVendas + "\n\n");

        for (Venda venda : vendas) {
            Cliente cliente = venda.getCliente();
            textAreaRelatorio.append("Cliente: " + cliente.getNome() + " (CPF: " + cliente.getCpf() + ")\n");
            textAreaRelatorio.append("Data da venda: " + venda.getDataVenda() + "\n");
            textAreaRelatorio.append("Produtos vendidos:\n");
            for (Produto produto : venda.getProdutosVendidos()) {
                textAreaRelatorio.append("  - " + produto.getNome() + " (Preço: R$ " + produto.getPreco() + ")\n");
            }
            textAreaRelatorio.append("Total da venda: R$ " + venda.getValorTotal() + "\n");
            textAreaRelatorio.append("-----------------------------------------\n");
        }
    }

    public static void main(String[] args) {
        VendaController vendaController = new VendaController();
        new TelaRelatorio(vendaController);
    }
}