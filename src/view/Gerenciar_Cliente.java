package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gerenciar_Cliente extends JFrame {
    private JLabel painel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;

    public Gerenciar_Cliente() {
        this.setTitle("Gerenciar Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        painel = new JLabel("Gerenciar Cliente", SwingConstants.CENTER);
        painel.setFont(new Font("Arial", Font.BOLD, 20));
        add(painel, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        button1 = new JButton("Adicionar");
        button2 = new JButton("Atualizar");
        button3 = new JButton("Remover");
        button4 = new JButton("Listar");
        button5 = new JButton("Voltar");

        painelBotoes.add(button1);
        painelBotoes.add(button2);
        painelBotoes.add(button3);
        painelBotoes.add(button4);
        painelBotoes.add(button5);

        add(painelBotoes, BorderLayout.CENTER);

        setVisible(true);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirFormularioAdicionarCliente();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirCliente();
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tela_Funcionario f = new Tela_Funcionario();
                dispose();
                f.setVisible(true);
            }
        });
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

        String email = JOptionPane.showInputDialog("Digite seu E-mail:");
        if (email == null) return;

        while (email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo E-mail não pode ser vazio.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            email = JOptionPane.showInputDialog("Digite seu E-mail:");
            if (email == null) return; // Se o usuário cancelar, sai do método
        }

        JOptionPane.showMessageDialog(null, "Nome: " + nome + "\nCPF: " + cpf + "\nE-mail: " + email, "Dados do Cliente", JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
    }


    public void excluirCliente() {
        String nome_excluir = "";
        while (nome_excluir == null || nome_excluir.isEmpty()) {
            nome_excluir = JOptionPane.showInputDialog("Digite o nome do cliente que deseja excluir:");
            if (nome_excluir == null) return;

            if (nome_excluir == null || nome_excluir.isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo Nome não pode ser vazio.", "Erro de validação", JOptionPane.ERROR_MESSAGE);
            }
            String cpf_excluir = "";
            while (cpf_excluir == null || cpf_excluir.isEmpty()) {
                cpf_excluir = JOptionPane.showInputDialog("Digite o CPF do cliente que deseja excluir:");
                if (cpf_excluir == null) return;

                if (cpf_excluir == null || cpf_excluir.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "O campo Cpf não pode ser vazio", "Erro de validação", JOptionPane.ERROR_MESSAGE);
                }
            }
            String email_exclui = "";
            while (email_exclui == null || email_exclui.isEmpty()) {
                email_exclui = JOptionPane.showInputDialog("Digite o E-mail do cliente que deseja excluir:");
                if (email_exclui == null) return;

                if (email_exclui == null || email_exclui.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "O campo E-mail não pode ser vazio", "Erro de validação", JOptionPane.ERROR_MESSAGE);
                }
            }
            JOptionPane.showMessageDialog(null, "Nome: " + nome_excluir + "\nCPF: " + cpf_excluir + "\nE-mail: " + email_exclui, "Dados do Cliente", JOptionPane.INFORMATION_MESSAGE);

            JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso!");

        }


    }
}
