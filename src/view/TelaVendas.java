package view;

import controller.ProdutoController;
import model.Produto;
import javax.swing.*;
import java.awt.*;

public class TelaVendas extends JFrame {

    private ProdutoController produtoController;
    private JPanel painelProdutoInfo;
    private JPanel barraCarrinho;
    private JLabel lblProdutoNoCarrinho;

    public TelaVendas(ProdutoController produtoController) {
        this.produtoController = produtoController;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Tela de Vendas");
        setSize(900, 559);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criação da barra de tarefas (barra superior)
        JPanel barraTarefas = createBarraTarefas();
        add(barraTarefas, BorderLayout.NORTH);

        // Campo de busca do produto
        JPanel painelBusca = createPainelBusca();
        add(painelBusca, BorderLayout.NORTH);

        // Painel para exibir informações do produto encontrado
        painelProdutoInfo = createPainelProdutoInfo();
        JScrollPane scrollPane = new JScrollPane(painelProdutoInfo);
        add(scrollPane, BorderLayout.CENTER);

        // Barra Lateral (Carrinho)
        barraCarrinho = createBarraCarrinho();
        add(barraCarrinho, BorderLayout.EAST);

        // Rodapé (barra inferior) com vários botões
        JPanel rodape = createRodape();
        add(rodape, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createBarraTarefas() {
        JPanel barraTarefas = new JPanel();
        barraTarefas.setBackground(new Color(196, 212, 228));
        barraTarefas.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton btnGerenciarClientes = new JButton("Gerenciar Clientes");
        JButton btnGerenciarEstoque = new JButton("Gerenciar Estoque");
        JButton btnRelatorios = new JButton("Relatórios");

        barraTarefas.add(btnGerenciarClientes);
        barraTarefas.add(btnGerenciarEstoque);
        barraTarefas.add(btnRelatorios);

        return barraTarefas;
    }

    private JPanel createPainelBusca() {
        JPanel painelBusca = new JPanel();
        painelBusca.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel lblBuscaProduto = new JLabel("Buscar Produto:");
        JTextField txtBuscaProduto = new JTextField(20);
        JButton btnBuscarProduto = new JButton("Buscar");

        painelBusca.add(lblBuscaProduto);
        painelBusca.add(txtBuscaProduto);
        painelBusca.add(btnBuscarProduto);

        // Função para o botão "Buscar"
        btnBuscarProduto.addActionListener(e -> {
            String nomeProduto = txtBuscaProduto.getText();
            Produto produto = produtoController.buscarProdutoPorNome(nomeProduto);

            if (produto != null) {
                JLabel lblProdutoInfo = new JLabel("<html><strong>Nome:</strong> " + produto.getNome() +
                        "<br><strong>Modelo:</strong> " + produto.getModelo() +
                        "<br><strong>Marca:</strong> " + produto.getMarca() +
                        "<br><strong>Preço:</strong> R$ " + produto.getPreco() +
                        "<br><strong>Estoque Atual:</strong> " + produto.getEstoqueAtual() +
                        "</html>");
                painelProdutoInfo.removeAll();
                painelProdutoInfo.add(lblProdutoInfo);

                JButton btnAdicionarCarrinho = new JButton("Adicionar ao Carrinho");
                btnAdicionarCarrinho.setVisible(true);
                painelProdutoInfo.add(btnAdicionarCarrinho);

                // Lógica para adicionar o produto ao carrinho
                btnAdicionarCarrinho.addActionListener(e1 -> {
                    lblProdutoNoCarrinho.setText("<html><strong>Produto:</strong> " + produto.getNome() +
                            "<br><strong>Preço:</strong> R$ " + produto.getPreco() + "</html>");
                });
                painelProdutoInfo.revalidate();
                painelProdutoInfo.repaint();

            } else {
                JOptionPane.showMessageDialog(this, "Produto não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                painelProdutoInfo.removeAll();
                painelProdutoInfo.revalidate();
                painelProdutoInfo.repaint();
                lblProdutoNoCarrinho.setText("Carrinho Vazio");
            }
        });

        return painelBusca;
    }

    private JPanel createPainelProdutoInfo() {
        JPanel painelProdutoInfo = new JPanel();
        painelProdutoInfo.setLayout(new BoxLayout(painelProdutoInfo, BoxLayout.Y_AXIS));
        return painelProdutoInfo;
    }

    private JPanel createBarraCarrinho() {
        JPanel barraCarrinho = new JPanel();
        barraCarrinho.setLayout(new BoxLayout(barraCarrinho, BoxLayout.Y_AXIS));
        barraCarrinho.setPreferredSize(new Dimension(200, getHeight()));
        barraCarrinho.setBackground(new Color(230, 230, 230));

        lblProdutoNoCarrinho = new JLabel("Carrinho Vazio");
        lblProdutoNoCarrinho.setAlignmentX(Component.CENTER_ALIGNMENT);
        barraCarrinho.add(lblProdutoNoCarrinho);

        barraCarrinho.add(Box.createVerticalGlue());

        JButton btnFinalizarCompra = new JButton("Finalizar Compra");
        btnFinalizarCompra.setAlignmentX(Component.CENTER_ALIGNMENT);
        barraCarrinho.add(btnFinalizarCompra);

        // Função para o botão "Finalizar Compra"
        btnFinalizarCompra.addActionListener(e -> {
            if (!lblProdutoNoCarrinho.getText().equals("Carrinho Vazio")) {
                JOptionPane.showMessageDialog(this, "Compra finalizada com sucesso!", "Finalizar Compra", JOptionPane.INFORMATION_MESSAGE);
                lblProdutoNoCarrinho.setText("Carrinho Vazio");
            } else {
                JOptionPane.showMessageDialog(this, "Adicione um produto ao carrinho para finalizar a compra.", "Erro", JOptionPane.WARNING_MESSAGE);
            }
        });

        return barraCarrinho;
    }

    private JPanel createRodape() {
        JPanel rodape = new JPanel();
        rodape.setBackground(new Color(196, 212, 228));
        rodape.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton btnGerenciarClientesRodape = new JButton("Gerenciar Clientes");
        JButton btnGerenciarEstoqueRodape = new JButton("Gerenciar Estoque");
        JButton btnRelatoriosRodape = new JButton("Relatórios");
        JButton btnSair = new JButton("Sair");

        rodape.add(btnGerenciarClientesRodape);
        rodape.add(btnGerenciarEstoqueRodape);
        rodape.add(btnRelatoriosRodape);
        rodape.add(btnSair);

        // Função para o botão "Sair"
        btnSair.addActionListener(e -> System.exit(0));

        // Função para o botão "Gerenciar Estoque"
        btnGerenciarEstoqueRodape.addActionListener(e -> {
            TelaGerenciarEstoque telaGerenciarEstoque = new TelaGerenciarEstoque(produtoController, TelaVendas.this);
            telaGerenciarEstoque.setVisible(true);
            this.setVisible(false); // Apenas oculta a TelaVendas
        });

        return rodape;
    }

    public static void main(String[] args) {
        ProdutoController produtoController = new ProdutoController();
        new TelaVendas(produtoController);  // Passando o controlador
    }
}
