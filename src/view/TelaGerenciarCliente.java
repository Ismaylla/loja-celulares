package view;

import controller.ClienteController;
import validator.ClienteValidator;
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

        // Rodapé com os botões
        JPanel painelRodape = new JPanel(new GridLayout(1, 5, 10, 10)); // Ajustado para GridLayout
        painelRodape.setBackground(Color.LIGHT_GRAY);

        JButton btnAdicionarCliente = new JButton("Adicionar Cliente");
        JButton btnRemoverCliente = new JButton("Remover Cliente");
        JButton btnAtualizarCliente = new JButton("Atualizar Cliente");
        JButton btnListarCliente = new JButton("Listar Clientes");
        JButton btnVoltar = new JButton("Voltar");

        painelRodape.add(btnAdicionarCliente);
        painelRodape.add(btnRemoverCliente);
        painelRodape.add(btnAtualizarCliente);
        painelRodape.add(btnListarCliente);
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

        String[] labels = {"Nome", "CPF", "Telefone (XX00000-0000", "Email", "Dinheiro Total Gasto", "Quantidade de compra"};
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

        painelDetalhes.add(painelAdicionar, "Adicionar");

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

        painelDetalhes.add(painelRemover, "Remover");

        btnRemoverCliente.addActionListener(e -> {
            ((CardLayout) painelDetalhes.getLayout()).show(painelDetalhes, "Remover");
        });

        btnConfirmarRemocao.addActionListener(e -> exibirDialogoRemoverCliente(txtBuscarRemover));

        // Ações dos Botões
        btnAdicionarCliente.addActionListener(e -> ((CardLayout) painelDetalhes.getLayout()).show(painelDetalhes, "Adicionar"));
        btnRemoverCliente.addActionListener(e -> ((CardLayout) painelDetalhes.getLayout()).show(painelDetalhes, "Remover"));

        // Listar Cliente
        // Listar Cliente
        btnListarCliente.addActionListener(e -> {
            List<Cliente> clientes = clienteController.carregarClientes();
            StringBuilder clientesInfo = new StringBuilder();

            // Adiciona as informações de cada cliente com linha separadora
            for (Cliente cliente : clientes) {
                clientesInfo.append("Nome: ").append(cliente.getNome()).append("\n")
                        .append("CPF: ").append(cliente.getCpf()).append("\n")
                        .append("Telefone: ").append(cliente.getTelefone()).append("\n")
                        .append("Email: ").append(cliente.getEmail()).append("\n")
                        .append("Dinheiro Total Gasto: R$ ").append(cliente.getDinheiroTotalGasto()).append("\n")
                        .append("Quantidade de Compras: ").append(cliente.getQuantidadeVezesComprou()).append("\n")
                        .append("-----------------------------------------------------------------------------------------------------\n");
            }

            // Exibe os dados no JTextArea
            JTextArea textArea = new JTextArea(20, 50);  // Definindo o tamanho da área
            textArea.setText(clientesInfo.toString());
            textArea.setEditable(false);  // Torna a área somente leitura
            textArea.setBackground(Color.LIGHT_GRAY);  // Define a cor do fundo
            textArea.setFont(new Font("Arial", Font.PLAIN, 14));  // Fonte e tamanho

            // Cria um JScrollPane para tornar a área rolável
            JScrollPane scrollPane = new JScrollPane(textArea);
            JOptionPane.showMessageDialog(this, scrollPane, "Clientes cadastrados", JOptionPane.INFORMATION_MESSAGE);
        });


        // Atualizar Cliente
        btnAtualizarCliente.addActionListener(e -> {
            String cpfParcial = JOptionPane.showInputDialog(this, "Digite o CPF do cliente que deseja atualizar:");
            if (cpfParcial != null && !cpfParcial.isEmpty()) {
                atualizarCliente(cpfParcial);
            }
        });

        JScrollPane scrollPane = new JScrollPane(painelDetalhes);
        add(scrollPane, BorderLayout.CENTER);
    }

    private boolean validaCampos(JTextField[] campos) {
        // Valida se todos os campos foram preenchidos
        for (JTextField campo : campos) {
            if (campo.getText().isEmpty()) {
                return false;
            }
        }

        // Cria o cliente temporário para validação
        String nome = campos[0].getText();
        String cpf = campos[1].getText();
        String telefone = campos[2].getText();
        String email = campos[3].getText();
        double dinheiroGasto = Double.parseDouble(campos[4].getText());
        int qtdCompra = Integer.parseInt(campos[5].getText());

        Cliente clienteTemp = new Cliente(nome, cpf, telefone, email, dinheiroGasto, qtdCompra);

        // Valida o cliente usando o ClienteValidator
        if (!ClienteValidator.validarCliente(clienteTemp)) {
            JOptionPane.showMessageDialog(this, "Por favor, corrija os erros de validação.");
            return false;
        }

        return true;
    }


    private void exibirDialogoRemoverCliente(JTextField txtBuscarRemover) {
        String cpfCliente = txtBuscarRemover.getText().trim();

        if (cpfCliente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o CPF do cliente.");
            return;
        }

        Cliente cliente = clienteController.buscarClientePorCpf(cpfCliente);

        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Nenhum cliente encontrado com esse CPF.");
        } else {
            int resposta = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja remover o cliente: " + cliente.getNome(),
                    "Confirmar Remoção",
                    JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                clienteController.removerCliente(cpfCliente);
                JOptionPane.showMessageDialog(this, "Cliente removido com sucesso!");
            }
        }

        txtBuscarRemover.setText("");  // Limpa o campo de CPF
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

                String novoNome = JOptionPane.showInputDialog("Alterar nome:", cliente.getNome());
                if (novoNome != null && !novoNome.isEmpty()) {
                    cliente.setNome(novoNome);
                }

                String novoTelefone = JOptionPane.showInputDialog("Alterar Telefone:", cliente.getTelefone());
                if (novoTelefone != null && !novoTelefone.isEmpty()) {
                    cliente.setTelefone(novoTelefone);
                }

                String novoEmail = JOptionPane.showInputDialog("Alterar E-mail:", cliente.getEmail());
                if (novoEmail != null && !novoEmail.isEmpty()) {
                    cliente.setEmail(novoEmail);
                }

                clienteController.atualizarCliente(cliente.getCpf(), cliente);  // Ajuste para utilizar o CPF
                JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
                atualizarListaClientes();  // Recarrega a lista de clientes
            }
        }
    }

    private void atualizarListaClientes() {
        // Recarrega a lista de clientes ao atualizar um cliente
        clienteController.carregarClientes();
    }
}
