package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tela_Relatorios extends JFrame {
    private JLabel panel_relatorios;
    private JButton Botao_Relatorio1;
    private JButton Botao_Relatorio2;
    private JButton Botao_Relatorio3;
    private JButton Botao_Relatorio4;
    private JButton Botao_Voltar;

    public Tela_Relatorios() {
        this.setTitle("Relatórios mensais ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panel_relatorios = new JLabel("Relatórios mensais", SwingConstants.CENTER);
        panel_relatorios.setFont(new Font("Arial", Font.BOLD, 20));
        add(panel_relatorios, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        Botao_Relatorio1 = new JButton("Mostrar total de vendas do mês");
        Botao_Relatorio2 = new JButton("Exibir produto mais vendido");
        Botao_Relatorio3 = new JButton("Exibir produto menos vendido");
        Botao_Relatorio4 = new JButton("Melhor cliente");
        Botao_Voltar = new JButton("Voltar");

        painelBotoes.add(Botao_Relatorio1);
        painelBotoes.add(Botao_Relatorio2);
        painelBotoes.add(Botao_Relatorio3);
        painelBotoes.add(Botao_Relatorio4);
        painelBotoes.add(Botao_Voltar);

        add(painelBotoes, BorderLayout.CENTER);

        setVisible(true);

        Botao_Relatorio1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        Botao_Relatorio2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        Botao_Relatorio3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        Botao_Relatorio4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        Botao_Voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tela_Funcionario f = new Tela_Funcionario();
                dispose();
                f.setVisible(true);
            }
        });



    }
}
