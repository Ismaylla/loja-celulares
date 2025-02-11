package view;

import model.Cliente;
import controller.ClienteController;
import javax.swing.*;
import java.awt.*;

public class Gerenciar_Cliente extends JFrame {
    private JLabel painel;
    private JButton buttonAdicionar;
    private JButton buttonAtualizar;
    private JButton buttonRemover;
    private JButton buttonListar;
    private JButton buttonVoltar;

    public Gerenciar_Cliente() {
        this.setTitle("Sistema de Gerenciar Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        painel = new JLabel("Gerenciar Cliente", SwingConstants.CENTER);
        painel.setFont(new Font("Arial", Font.BOLD, 20));
        add(painel, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        buttonAdicionar = new JButton("Adicionar");
        buttonAtualizar = new JButton("Atualizar");
        buttonRemover = new JButton("Remover");
        buttonListar = new JButton("Listar");
        buttonVoltar = new JButton("Voltar");

        painelBotoes.add(buttonAdicionar);
        painelBotoes.add(buttonAtualizar);
        painelBotoes.add(buttonRemover);
        painelBotoes.add(buttonListar);
        painelBotoes.add(buttonVoltar);

        add(painelBotoes, BorderLayout.CENTER);

        configurarEventos();
        setVisible(true);
    }

    private void configurarEventos() {
        buttonAdicionar.addActionListener(e -> abrirFormularioAdicionarCliente());
        buttonAtualizar.addActionListener(e -> atualizarCliente());
        buttonRemover.addActionListener(e -> excluirCliente());
        buttonListar.addActionListener(e -> listarClientes());
        buttonVoltar.addActionListener(e -> voltarParaMenuPrincipal());
    }

    private void abrirFormularioAdicionarCliente() {
        String nome = JOptionPane.showInputDialog("Digite seu nome:");
        if (nome == null) return;

        while (nome.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo Nome não pode ser vazio.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            nome = JOptionPane.showInputDialog("Digite seu nome:");
            if (nome == null) return;
        }

        String cpf = JOptionPane.showInputDialog("Digite seu CPF:");
        if (cpf == null) return;

        while (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo CPF não pode ser vazio.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            cpf = JOptionPane.showInputDialog("Digite seu CPF:");
            if (cpf == null) return;
        }

        String telefone = JOptionPane.showInputDialog("Digite seu Telefone:");
        if (telefone == null) return;

        while (telefone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo Telefone não pode ser vazio.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            telefone = JOptionPane.showInputDialog("Digite seu Telefone:");
            if (telefone == null) return;
        }

        String email = JOptionPane.showInputDialog("Digite seu E-mail:");
        if (email == null) return;

        while (email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo E-mail não pode ser vazio.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            email = JOptionPane.showInputDialog("Digite seu E-mail:");
            if (email == null) return;
        }

        Cliente cliente = new Cliente(nome, cpf, telefone, email, 0.0, 0);
        ClienteController controller = new ClienteController();

        controller.adicionarCliente(cliente);

        JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
    }

    private void atualizarCliente() {
        ClienteController controller = new ClienteController();
        String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente que deseja atualizar:");
        if (cpf == null || cpf.trim().isEmpty()) return;

        Cliente clienteExistente = controller.buscarClientePorCpf(cpf);
        if (clienteExistente == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String novoNome = JOptionPane.showInputDialog("Novo nome (atual: " + clienteExistente.getNome() + "):", clienteExistente.getNome());
        String novoTelefone = JOptionPane.showInputDialog("Novo telefone (atual: " + clienteExistente.getTelefone() + "):", clienteExistente.getTelefone());
        String novoEmail = JOptionPane.showInputDialog("Novo email (atual: " + clienteExistente.getEmail() + "):", clienteExistente.getEmail());

        if (novoNome != null && novoTelefone != null && novoEmail != null) {
            clienteExistente.setNome(novoNome);
            clienteExistente.setTelefone(novoTelefone);
            clienteExistente.setEmail(novoEmail);
            controller.atualizarCliente(cpf, clienteExistente);
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
        }
    }

    private void excluirCliente() {
        ClienteController controller = new ClienteController();

        String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente que deseja excluir:");
        if (cpf == null || cpf.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo CPF não pode ser vazio.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = controller.buscarClientePorCpf(cpf);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(null,
                "Tem certeza que deseja excluir o cliente?\n" + cliente,
                "Confirmação",
                JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            controller.removerCliente(cpf);
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
        }
    }

    private void listarClientes() {
        ClienteController controller = new ClienteController();
        java.util.List<Cliente> clientes = controller.carregarClientes();

        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente encontrado!", "Lista de Clientes", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder listaClientes = new StringBuilder();
            for (Cliente cliente : clientes) {
                listaClientes.append(cliente.toString()).append("\n");
            }

            JTextArea textArea = new JTextArea(listaClientes.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);

            JOptionPane.showMessageDialog(null, scrollPane, "Lista de Clientes", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void voltarParaMenuPrincipal() {
        MainView f = new MainView();
        dispose();
        f.setVisible(true);
    }
}
