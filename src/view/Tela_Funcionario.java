package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tela_Funcionario extends JFrame {

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel Titulo;

    public Tela_Funcionario() {

        this.setTitle("ElloCell");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        Titulo = new JLabel("ELLOCELL", SwingConstants.CENTER);
        Titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(Titulo, BorderLayout.NORTH);


        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));


        button1 = new JButton("Gerenciar Clientes");
        button2 = new JButton("Realizar Vendas");
        button3 = new JButton("Relatório do mês");


        painelBotoes.add(button1);
        painelBotoes.add(button2);
        painelBotoes.add(button3);


        add(painelBotoes, BorderLayout.CENTER);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Gerenciar_Cliente g = new Gerenciar_Cliente();
                dispose();
                g.setVisible(true);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Realizar_Vendas r = new Realizar_Vendas();
                dispose();
                r.setVisible(true);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Tela_Relatorios t = new Tela_Relatorios();
                dispose();
                t.setVisible(true);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Tela_Funcionario::new);
    }
}
