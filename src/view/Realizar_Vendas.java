package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Realizar_Vendas extends JFrame {
    private JLabel painel;
    private JButton Botao_Vendas1;
    private JButton Botao_Vendas2;
    private JButton Botao_Vendas3;
    private JButton Botao_Vendas4;
    private JButton Botao_Voltar;


    public Realizar_Vendas() {
        this.setTitle("Realizar Vendas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        painel = new JLabel("Realizar Vendas", SwingConstants.CENTER);
        painel.setFont(new Font("Arial", Font.BOLD, 20));
        add(painel, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        Botao_Vendas1 = new JButton("Comprar");
        Botao_Vendas2 = new JButton("Tabela de produtos");
        Botao_Vendas3 = new JButton("Adicionar produto");
        Botao_Vendas4 = new JButton("Finalizar venda");
        Botao_Voltar = new JButton("Voltar");

        painelBotoes.add(Botao_Vendas1);
        painelBotoes.add(Botao_Vendas2);
        painelBotoes.add(Botao_Vendas3);
        painelBotoes.add(Botao_Vendas4);
        painelBotoes.add(Botao_Voltar);

        add(painelBotoes, BorderLayout.CENTER);

        setVisible(true);

        Botao_Vendas1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        Botao_Vendas2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        Botao_Vendas3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        Botao_Vendas4.addActionListener(new ActionListener() {
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
