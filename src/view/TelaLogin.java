package view;

import controller.ProdutoController;
import model.Funcionario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TelaLogin {

    private ProdutoController produtoController;
    private List<Funcionario> funcionarios; // Lista de funcionários

    public TelaLogin() {
        produtoController = new ProdutoController(); // Inicializando o ProdutoController
        funcionarios = new ArrayList<>();
        adicionarFuncionarios(); // Adicionando funcionários à lista
        initializeUI();
    }

    // Método para adicionar funcionários
    private void adicionarFuncionarios() {
        funcionarios.add(new Funcionario(1, "samille", "vendedor", "1234")); // Exemplo de vendedor
        funcionarios.add(new Funcionario(2, "ismaylla", "vendedor", "4321"));
        funcionarios.add(new Funcionario(3, "ernandes", "vendedor", "12345"));
    }

    private void initializeUI() {
        // Criação do frame com as novas dimensões
        JFrame frame = new JFrame("Tela de Login");
        frame.setSize(746, 559);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Cor de fundo da tela
        frame.getContentPane().setBackground(new Color(196, 212, 228));

        // Rótulo do campo de usuário (Vendedor)
        JLabel lblUsuario = new JLabel("Vendedor");
        lblUsuario.setBounds(200, 150, 100, 30); // Posição ao lado do campo
        lblUsuario.setForeground(new Color(0, 92, 173));  // Cor das fontes
        frame.add(lblUsuario);

        // Campo de Login (Vendedor)
        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(300, 150, 150, 30);  // Posição ajustada
        frame.add(txtUsuario);

        // Rótulo do campo de senha
        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(200, 200, 100, 30); // Posição ao lado do campo
        lblSenha.setForeground(new Color(0, 92, 173));  // Cor das fontes
        frame.add(lblSenha);

        // Campo de Senha
        JPasswordField txtSenha = new JPasswordField();
        txtSenha.setBounds(300, 200, 150, 30);  // Posição ajustada
        frame.add(txtSenha);

        // Botão de Login
        JButton btnLogin = new JButton("Entrar");
        btnLogin.setBounds(300, 250, 150, 30);  // Posição centralizada
        frame.add(btnLogin);

        // Ação de login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String senha = new String(txtSenha.getPassword());

                // Validar o login
                if (validarLogin(usuario, senha)) {
                    TelaVendas telaVendas = new TelaVendas(produtoController); // Passando o controlador
                    telaVendas.setVisible(true);
                    frame.dispose(); // Fechar a tela de login
                } else {
                    JOptionPane.showMessageDialog(frame, "Usuário ou senha inválidos", "Erro de Login", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }

    // Método para validar o login
    private boolean validarLogin(String nome, String senha) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getNome().equalsIgnoreCase(nome) && funcionario.getSenha().equals(senha)) {
                return true; // Login bem-sucedido
            }
        }
        return false; // Login falhou
    }

    public static void main(String[] args) {
        new TelaLogin();
    }
}
