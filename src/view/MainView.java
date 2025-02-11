package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JButton btnGerenciarClientes;
    private JButton btnRealizarVendas;
    private JButton btnRelatorios;
    private JLabel titulo;

    public MainView() {
        setTitle("ElloCell - Sistema de Gerenciamento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        titulo = new JLabel("Bem-vindo ao Sistema ElloCell", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

        btnGerenciarClientes = new JButton("Gerenciar Clientes");
        btnRealizarVendas = new JButton("Realizar Vendas");
        btnRelatorios = new JButton("Relatórios");

        painelBotoes.add(btnGerenciarClientes);
        painelBotoes.add(btnRealizarVendas);
        painelBotoes.add(btnRelatorios);

        add(painelBotoes, BorderLayout.CENTER);


        btnGerenciarClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gerenciar_Cliente gerenciarClienteView = new Gerenciar_Cliente();
                dispose();
                gerenciarClienteView.setVisible(true);
            }
        });

        btnRealizarVendas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Realizar_Vendas realizarVendasView = new Realizar_Vendas();
                dispose();
                realizarVendasView.setVisible(true);
            }
        });

        btnRelatorios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tela_Relatorios relatoriosView = new Tela_Relatorios();
                dispose();
                relatoriosView.setVisible(true);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainView::new);
    }
}
