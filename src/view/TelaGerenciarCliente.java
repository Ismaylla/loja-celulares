package view;

import controller.ClienteController;
import model.Cliente;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class TelaGerenciarCliente extends JFrame {

    private final ClienteController clienteController;
    private TelaVendas telaVendas;

    public TelaGerenciarCliente(ClienteController clienteController, TelaVendas telaVendas) {
        this.clienteController = clienteController;
        this.telaVendas = telaVendas;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Gerenciar Cliente");
        setSize(746, 559);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.LIGHT_GRAY);

        JPanel painelRodape = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelRodape.setBackground(Color.LIGHT_GRAY);

        JButton btnAdicionarCliente = new JButton("Adicionar Cliente");
        JButton btnRemoverCliente = new JButton("Remover Cliente");
        JButton btnAtualizarCliente = new JButton("Atualizar Cliente");
        JButton btnListarCliente = new JButton("Listar Clientes");
        JButton btnAtualizarClientePosCompra = new JButton("Atualizar Pós-Compra");
        JButton btnVoltar = new JButton("Voltar");

        painelRodape.add(btnAdicionarCliente);
        painelRodape.add(btnRemoverCliente);
        painelRodape.add(btnAtualizarCliente);
        painelRodape.add(btnListarCliente);
        painelRodape.add(btnAtualizarClientePosCompra);
        painelRodape.add(btnVoltar);

        add(painelRodape, BorderLayout.SOUTH);

        // Ação do botão voltar
        btnVoltar.addActionListener(e -> {
            telaVendas.setVisible(true);
            dispose();
        });

        JPanel painelDetalhes = new JPanel();
        painelDetalhes.setLayout(new CardLayout());

        // Área de Adicionar Cliente
        JPanel painelAdicionar = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        String[] labels = {"Nome", "CPF", "Telefone", "Email", "Dinheiro Total Gasto", "Quantidade de compra"};
        JTextField[] textFields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            painelAdicionar.add(new JLabel(labels[i]), gbc);

            textFields[i] = new JTextField(20);
            gbc.gridx = 1;
            painelAdicionar.add(textFields[i], gbc);
        }

        JButton btnSalvarCliente = new JButton("Salvar Cliente");
        gbc.gridx = 1;
        gbc.gridy = labels.length;
        painelAdicionar.add(btnSalvarCliente, gbc);

        // Ação para salvar cliente
        btnSalvarCliente.addActionListener(e -> {
            if (validaCampos(textFields)) {
                String nome = textFields[0].getText();
                String cpf = textFields[1].getText();
                String telefone = textFields[2].getText();
                String email = textFields[3].getText();
                double dinheiroGasto = Double.parseDouble(textFields[4].getText());
                int qtdCompra = Integer.parseInt(textFields[5].getText());

                Cliente novoCliente = new Cliente(nome, cpf, telefone, email, dinheiroGasto, qtdCompra);
                clienteController.adicionarCliente(novoCliente);
                JOptionPane.showMessageDialog(this, "Cliente adicionado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
            }
        });

        // Área de Remover Cliente
        JPanel painelRemover = new JPanel(new GridBagLayout());
        JTextField txtBuscarRemover = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        painelRemover.add(new JLabel("CPF do Cliente para Remover:"), gbc);

        gbc.gridx = 1;
        painelRemover.add(txtBuscarRemover, gbc);

        JButton btnConfirmarRemocao = new JButton("Remover");
        gbc.gridx = 1;
        gbc.gridy = 1;
        painelRemover.add(btnConfirmarRemocao, gbc);

        btnRemoverCliente.addActionListener(e -> exibirDialogoRemoverCliente(txtBuscarRemover));

        JScrollPane scrollPane = new JScrollPane(painelDetalhes);
        add(scrollPane, BorderLayout.CENTER);

        // Ações dos Botões
        btnAdicionarCliente.addActionListener(e -> ((CardLayout) painelDetalhes.getLayout()).show(painelDetalhes, "Adicionar"));
        btnRemoverCliente.addActionListener(e -> ((CardLayout) painelDetalhes.getLayout()).show(painelDetalhes, "Remover"));

        btnAtualizarCliente.addActionListener(e -> {
            String cpfClienteParcial = JOptionPane.showInputDialog("Digite o CPF do cliente para atualizar:");
            if (cpfClienteParcial != null && !cpfClienteParcial.trim().isEmpty()) {
                atualizarCliente(cpfClienteParcial);
            }
        });
    }

    private boolean validaCampos(JTextField[] campos) {
        for (JTextField campo : campos) {
            if (campo.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private void exibirDialogoRemoverCliente(JTextField txtBuscarRemover) {
        String cpfCliente = txtBuscarRemover.getText().trim();

        if (cpfCliente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o CPF do cliente.");
            return;
        }

        List<Cliente> clientesEncontrados = clienteController.carregarClientes();

        if (clientesEncontrados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum cliente encontrado com esse CPF.");
        } else {
            String[] clientesStr = clientesEncontrados.stream()
                    .map(cliente -> cliente.getNome() + " - " + cliente.getCpf())
                    .toArray(String[]::new);

            String clienteSelecionado = (String) JOptionPane.showInputDialog(
                    this,
                    "Escolha um Cliente para remover:",
                    "Selecionar Cliente",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    clientesStr,
                    clientesStr[0]
            );

            if (clienteSelecionado != null) {
                String cpf = clienteSelecionado.split(" - ")[1];
                clienteController.removerCliente(cpf);
                JOptionPane.showMessageDialog(this, "Cliente removido com sucesso!");
            }
        }

        txtBuscarRemover.setText("");
    }

    private void removerClientePorCpf(String cpf) {
        if (!cpf.isEmpty()) {
            clienteController.removerCliente(cpf);
            JOptionPane.showMessageDialog(this, "Cliente removido com sucesso.");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, insira um CPF válido.");
        }
    }

    private void atualizarCliente(String cpfClienteParcial) {
        List<Cliente> clientesEncontrados = clienteController.carregarClientes();

        List<Cliente> clientesFiltrados = clientesEncontrados.stream()
                .filter(cliente -> cliente.getCpf().toLowerCase().contains(cpfClienteParcial.toLowerCase()))
                .collect(Collectors.toList());

        if (clientesFiltrados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum cliente encontrado com esse CPF.");
        } else {
            String[] clientesStr = clientesFiltrados.stream()
                    .map(cliente -> cliente.getNome() + " - " + cliente.getCpf())
                    .toArray(String[]::new);

            String clienteSelecionado = (String) JOptionPane.showInputDialog(
                    this,
                    "Escolha um cliente para atualizar:",
                    "Selecionar Cliente",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    clientesStr,
                    clientesStr[0]
            );

            if (clienteSelecionado != null) {
                String cpfCliente = clienteSelecionado.split(" - ")[1];
                Cliente cliente = clienteController.buscarClientePorCpf(cpfCliente);

                String novoNome = JOptionPane.showInputDialog("Alterar nome:");
                String novoTelefone = JOptionPane.showInputDialog("Alterar Telefone:");
                String novoEmail = JOptionPane.showInputDialog("Alterar E-mail:");

                cliente.setNome(novoNome);
                cliente.setTelefone(novoTelefone);
                cliente.setEmail(novoEmail);

                clienteController.atualizarCliente(cliente.getNome(), cliente);
                JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
            }

        }
    }







}
