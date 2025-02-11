package view;

import controller.ClienteController;
import controller.ProdutoController;
import model.Cliente;
import model.Produto;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Realizar_Vendas extends JFrame {
    private JLabel painel;
    private JButton botaoComprar;
    private JButton botaoTabelaProdutos;
    private JButton botaoAdicionarProduto;
    private JButton botaoFinalizarVenda;
    private JButton botaoVoltar;

    // Carrinho de compras
    private Map<Produto, Integer> carrinhoDeCompras;

    public Realizar_Vendas() {
        this.setTitle("Realizar Vendas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        carrinhoDeCompras = new HashMap<>();

        painel = new JLabel("Realizar Vendas", SwingConstants.CENTER);
        painel.setFont(new Font("Arial", Font.BOLD, 20));
        add(painel, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        botaoComprar = new JButton("Comprar");
        botaoTabelaProdutos = new JButton("Tabela de Produtos");
        botaoAdicionarProduto = new JButton("Adicionar Produto");
        botaoFinalizarVenda = new JButton("Finalizar Venda");
        botaoVoltar = new JButton("Voltar");

        painelBotoes.add(botaoComprar);
        painelBotoes.add(botaoTabelaProdutos);
        painelBotoes.add(botaoAdicionarProduto);
        painelBotoes.add(botaoFinalizarVenda);
        painelBotoes.add(botaoVoltar);

        add(painelBotoes, BorderLayout.CENTER);

        setVisible(true);

        configurarEventos();
    }

    private void configurarEventos() {
        botaoComprar.addActionListener(e -> realizarCompra());
        botaoTabelaProdutos.addActionListener(e -> exibirTabelaProdutos());
        botaoAdicionarProduto.addActionListener(e -> adicionarProduto());
        botaoFinalizarVenda.addActionListener(e -> finalizarVenda());
        botaoVoltar.addActionListener(e -> voltarParaMenuPrincipal());
    }

    private void realizarCompra() {
        ClienteController clienteController = new ClienteController();
        ProdutoController produtoController = new ProdutoController();

        int opcao = JOptionPane.showConfirmDialog(
                null,
                "O cliente já é cadastrado?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );

        if (opcao == JOptionPane.NO_OPTION) {
            Gerenciar_Cliente telaCadastro = new Gerenciar_Cliente();
            telaCadastro.setVisible(true);
            return;
        }

        String cpfCliente = JOptionPane.showInputDialog("Digite o CPF do cliente:");
        if (cpfCliente == null || cpfCliente.trim().isEmpty()) return;

        Cliente cliente = clienteController.buscarClientePorCpf(cpfCliente);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nomeProduto = JOptionPane.showInputDialog("Digite o nome do produto:");
        if (nomeProduto == null || nomeProduto.trim().isEmpty()) return;

        Produto produto = produtoController.buscarProdutoPorNome(nomeProduto);
        if (produto == null) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade a ser comprada:"));
            if (quantidade > produto.getEstoqueAtual()) {
                JOptionPane.showMessageDialog(null, "Estoque insuficiente!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                carrinhoDeCompras.put(produto, carrinhoDeCompras.getOrDefault(produto, 0) + quantidade);
                produto.setEstoqueAtual(produto.getEstoqueAtual() - quantidade);
                produtoController.atualizarProduto(nomeProduto, produto);
                JOptionPane.showMessageDialog(null, "Produto adicionado ao carrinho com sucesso!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, digite um número.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exibirTabelaProdutos() {
        ProdutoController controller = new ProdutoController();
        List<Produto> produtos = controller.carregarProdutos();

        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum produto cadastrado!", "Tabela de Produtos", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String[] colunas = {"Nome", "Modelo", "Marca", "Preço", "Estoque Atual"};
            String[][] dados = new String[produtos.size()][5];

            for (int i = 0; i < produtos.size(); i++) {
                Produto p = produtos.get(i);
                dados[i][0] = p.getNome();
                dados[i][1] = p.getmodelo();
                dados[i][2] = p.getMarca();
                dados[i][3] = String.format("R$ %.2f", p.getPreco());
                dados[i][4] = String.valueOf(p.getEstoqueAtual());
            }

            JTable tabela = new JTable(dados, colunas);
            JScrollPane scrollPane = new JScrollPane(tabela);

            tabela.setAutoCreateRowSorter(true);

            JOptionPane.showMessageDialog(null, scrollPane, "Tabela de Produtos", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void adicionarProduto() {
        try {
            String nome = JOptionPane.showInputDialog("Digite o nome do produto:");
            if (nome == null || nome.trim().isEmpty()) return;

            String modelo = JOptionPane.showInputDialog("Digite o modelo do produto:");
            if (modelo == null || modelo.trim().isEmpty()) return;

            String marca = JOptionPane.showInputDialog("Digite a marca do produto:");
            if (marca == null || marca.trim().isEmpty()) return;

            double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do produto:"));
            int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade em estoque:"));

            Produto produto = new Produto(nome, modelo, marca, preco, quantidade, 0);
            ProdutoController controller = new ProdutoController();
            controller.adicionarProduto(produto);

            JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, digite valores numéricos corretos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void finalizarVenda() {
        if (carrinhoDeCompras.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum produto foi vendido!", "Finalizar Venda", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        double totalVenda = 0.0;
        StringBuilder resumoVenda = new StringBuilder("Resumo da Venda:\n");

        for (Map.Entry<Produto, Integer> item : carrinhoDeCompras.entrySet()) {
            Produto produto = item.getKey();
            int quantidadeVendida = item.getValue();
            double subtotal = produto.getPreco() * quantidadeVendida;
            totalVenda += subtotal;
            resumoVenda.append(produto.getNome())
                    .append(" x ")
                    .append(quantidadeVendida)
                    .append(" = R$ ")
                    .append(String.format("%.2f", subtotal))
                    .append("\n");
        }

        resumoVenda.append("\nValor total: R$ ").append(String.format("%.2f", totalVenda));
        JOptionPane.showMessageDialog(null, resumoVenda.toString(), "Finalizar Venda", JOptionPane.INFORMATION_MESSAGE);

        carrinhoDeCompras.clear();
    }

    private void voltarParaMenuPrincipal() {
        MainView mainView = new MainView();
        dispose();
        mainView.setVisible(true);
    }
}
