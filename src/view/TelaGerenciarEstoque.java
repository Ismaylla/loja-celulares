package view;

import controller.ProdutoController;
import model.Produto;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;


public class TelaGerenciarEstoque extends JFrame {

    private ProdutoController produtoController;
    private TelaVendas telaVendas;

    public TelaGerenciarEstoque(ProdutoController produtoController, TelaVendas telaVendas) {
        this.produtoController = produtoController;
        this.telaVendas = telaVendas;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Gerenciar Estoque");
        setSize(746, 559);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Definindo a cor de fundo de toda a tela
        getContentPane().setBackground(Color.LIGHT_GRAY); // Cor de fundo clara

        // Painel com botões para as operações
        JPanel painelRodape = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelRodape.setBackground(Color.LIGHT_GRAY);

        JButton btnAdicionar = new JButton("Adicionar Produto");
        JButton btnRemover = new JButton("Remover Produto");
        JButton btnAtualizarProduto = new JButton("Atualizar Produto");
        JButton btnListarProdutos = new JButton("Listar Produtos");
        JButton btnVoltar = new JButton("Voltar"); //botao para voltar a telavendas


        painelRodape.add(btnAdicionar);
        painelRodape.add(btnRemover);
        painelRodape.add(btnAtualizarProduto);
        painelRodape.add(btnListarProdutos);
        painelRodape.add(btnVoltar);

        add(painelRodape, BorderLayout.SOUTH);

        btnVoltar.addActionListener(e -> {
            telaVendas.setVisible(true);
            dispose();
        });


        // Painel para mostrar informações e interações
        JPanel painelDetalhes = new JPanel();
        painelDetalhes.setLayout(new CardLayout());

        // Área de Adicionar Produto
        JPanel painelAdicionar = new JPanel();
        painelAdicionar.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        String[] labels = {"Código:", "Nome:", "Modelo:", "Marca:", "Preço:", "Cor:", "Armazenamento:",
                "Memória RAM:", "Tamanho da Tela:", "Tem 5G?", "Resistência à Água:",
                "Estoque Atual:", "Estoque Mínimo:"};
        JTextField[] textFields = new JTextField[13];

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            painelAdicionar.add(new JLabel(labels[i]), gbc);

            textFields[i] = new JTextField(20);
            gbc.gridx = 1;
            painelAdicionar.add(textFields[i], gbc);
        }

        JButton btnSalvarProduto = new JButton("Salvar Produto");
        gbc.gridx = 1;
        gbc.gridy = 13;
        painelAdicionar.add(btnSalvarProduto, gbc);

        // Área de Remover Produto
        JPanel painelRemover = new JPanel();
        painelRemover.setLayout(new GridBagLayout());
        JTextField txtBuscarRemover = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        painelRemover.add(new JLabel("Buscar Produto para Remover:"), gbc);

        gbc.gridx = 1;
        painelRemover.add(txtBuscarRemover, gbc);

        JButton btnRemoverProduto = new JButton("Remover");
        gbc.gridx = 1;
        gbc.gridy = 1;
        painelRemover.add(btnRemoverProduto, gbc);

        painelDetalhes.add(painelAdicionar, "Adicionar");
        painelDetalhes.add(painelRemover, "Remover");

        JScrollPane scrollPane = new JScrollPane(painelDetalhes);
        add(scrollPane, BorderLayout.CENTER);

        // Ações dos Botões
        btnAdicionar.addActionListener(e ->
                ((CardLayout) painelDetalhes.getLayout()).show(painelDetalhes, "Adicionar")
        );

        btnRemover.addActionListener(e ->
                ((CardLayout) painelDetalhes.getLayout()).show(painelDetalhes, "Remover")
        );

        btnSalvarProduto.addActionListener(e -> {
            try {
                String codigo = textFields[0].getText().trim();
                String nome = textFields[1].getText().trim();
                String modelo = textFields[2].getText().trim();
                String marca = textFields[3].getText().trim();
                double preco = Double.parseDouble(textFields[4].getText().trim());
                String cor = textFields[5].getText().trim();
                String armazenamento = textFields[6].getText().trim();
                String memoriaRAM = textFields[7].getText().trim();
                double tamanhoTela = Double.parseDouble(textFields[8].getText().trim());
                boolean tem5G = Boolean.parseBoolean(textFields[9].getText().trim());
                boolean resistenciaAgua = Boolean.parseBoolean(textFields[10].getText().trim());
                int estoqueAtual = Integer.parseInt(textFields[11].getText().trim());
                int estoqueMinimo = Integer.parseInt(textFields[12].getText().trim());

                Produto novoProduto = new Produto(codigo, nome, modelo, marca, preco, cor, armazenamento,
                        memoriaRAM, tamanhoTela, tem5G, resistenciaAgua,
                        estoqueAtual, estoqueMinimo);

                produtoController.adicionarProduto(novoProduto);
                JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!");
                limparCampos(textFields);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira os dados corretamente.");
            }
        });

        // Ação para Remover Produto
        btnRemoverProduto.addActionListener(e -> {
            String nomeProdutoParcial = txtBuscarRemover.getText().trim();

            // impede uma busca se o campo estiver vazio
            if(nomeProdutoParcial.isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor, insira o nome do produto.");
                return;
            }

            List<Produto> produtosEncontrados = produtoController.carregarProdutos();

            // Verifica se encontrou algum produto
            if (produtosEncontrados.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum produto encontrado com esse nome.");
            } else {
                // Exibe os produtos encontrados para o usuário
                String[] produtosStr = produtosEncontrados.stream()
                        .map(produto -> produto.getNome() + " - " + produto.getModelo())
                        .toArray(String[]::new);

                // Mostrar um diálogo com a lista de produtos encontrados
                String produtoSelecionado = (String) JOptionPane.showInputDialog(
                        null,
                        "Escolha um produto para remover:",
                        "Selecionar Produto",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        produtosStr,
                        produtosStr[0]
                );

                if (produtoSelecionado != null) {
                    // Obtém o nome do produto para remoção
                    String nomeProduto = produtoSelecionado.split(" - ")[0];
                    produtoController.removerProduto(nomeProduto);

                    // Exibe mensagem de sucesso
                    JOptionPane.showMessageDialog(null, "Produto removido com sucesso!");
                }
            }

            // Limpa o campo de busca após a operação
            txtBuscarRemover.setText("");
        });

        btnAtualizarProduto.addActionListener(e -> {
            // Solicita ao usuário o nome do produto para atualizar
            String nomeProdutoParcial = JOptionPane.showInputDialog("Digite o nome do produto para atualizar:");

            // Verifica se o usuário clicou em "Cancelar" ou fechou a caixa de diálogo
            if (nomeProdutoParcial == null || nomeProdutoParcial.trim().isEmpty()) {
                return; // Sai do método sem fazer mais nada
            }

            // Carrega a lista de produtos
            List<Produto> produtosEncontrados = produtoController.carregarProdutos();

            // Filtra os produtos para encontrar aqueles que contêm o nome inserido
            List<Produto> produtosFiltrados = produtosEncontrados.stream()
                    .filter(produto -> produto.getNome().toLowerCase().contains(nomeProdutoParcial.toLowerCase()))
                    .collect(Collectors.toList());

            // Verifica se encontrou produtos
            if (produtosFiltrados.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum produto encontrado com esse nome.");
            } else {
                // Exibe os produtos encontrados para o usuário
                String[] produtosStr = produtosFiltrados.stream()
                        .map(produto -> produto.getNome() + " - " + produto.getModelo())
                        .toArray(String[]::new);

                // Mostrar um diálogo com a lista de produtos encontrados
                String produtoSelecionado = (String) JOptionPane.showInputDialog(
                        null,
                        "Escolha um produto para atualizar:",
                        "Selecionar Produto",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        produtosStr,
                        produtosStr[0]
                );

                if (produtoSelecionado != null) {
                    // Obtém o nome do produto para atualização
                    String nomeProduto = produtoSelecionado.split(" - ")[0];

                    // Busca o produto completo para atualização
                    Produto produto = produtoController.buscarProdutoPorNome(nomeProduto);

                    // Pede o novo valor e estoque
                    String novoValorStr = JOptionPane.showInputDialog("Digite o novo valor do produto:");
                    String novoEstoqueStr = JOptionPane.showInputDialog("Digite o novo estoque do produto:");

                    try {
                        // Atualiza o produto com o novo preço e estoque
                        double novoValor = Double.parseDouble(novoValorStr);
                        int novoEstoque = Integer.parseInt(novoEstoqueStr);

                        // Atualiza os dados do produto
                        produto.setPreco(novoValor);
                        produto.setEstoqueAtual(novoEstoque);

                        // Chama o método de atualização no controlador, passando o nome e o Produto atualizado
                        produtoController.atualizarProduto(produto.getNome(), produto);

                        JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos para preço e estoque.");
                    }
                }
            }
        });


        // Área de Exibição de Produtos
        JPanel painelListarProdutos = new JPanel();
        painelListarProdutos.setLayout(new BorderLayout());

        JTextArea textAreaProdutos = new JTextArea(15, 50);
        textAreaProdutos.setEditable(false); // Para que o usuário não consiga editar
        JScrollPane scrollPaneProdutos = new JScrollPane(textAreaProdutos);
        painelListarProdutos.add(scrollPaneProdutos, BorderLayout.CENTER);
        painelDetalhes.add(painelListarProdutos, "Listar");

        btnListarProdutos.addActionListener(e -> {
            // Limpa o JTextArea para não duplicar os produtos
            textAreaProdutos.setText("");

            // Carrega os produtos
            List<Produto> produtos = produtoController.carregarProdutos();

            if (produtos.isEmpty()) {
                textAreaProdutos.append("Nenhum produto cadastrado.\n");
            } else {
                // Exibe os produtos no JTextArea
                for (Produto produto : produtos) {
                    textAreaProdutos.append("Nome: " + produto.getNome() + "\n");
                    textAreaProdutos.append("Preço: " + produto.getPreco() + "\n");
                    textAreaProdutos.append("Estoque Atual: " + produto.getEstoqueAtual() + "\n");
                    textAreaProdutos.append("--------------------------------------------------------------------------\n");
                }
            }

            // Exibe o painel de Listagem de Produtos
            ((CardLayout) painelDetalhes.getLayout()).show(painelDetalhes, "Listar");
        });


        setVisible(true);
    }

    private void limparCampos(JTextField[] campos) {
        for (JTextField campo : campos) {
            campo.setText("");
        }
    }
}