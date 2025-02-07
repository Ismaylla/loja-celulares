package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tela_Relatorios extends JFrame {
    private JLabel panel_relatorios;
    private JTable tabelaRelatorios;
    private JButton Botao_Listar;
    private JButton Botao_Voltar;

    public Tela_Relatorios() {
        this.setTitle("Relatórios Mensais");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panel_relatorios = new JLabel("Relatórios Mensais", SwingConstants.CENTER);
        panel_relatorios.setFont(new Font("Arial", Font.BOLD, 20));
        add(panel_relatorios, BorderLayout.NORTH);

        String[] colunas = {"Total de Vendas do Mês", "Produto Mais Vendido", "Produto Menos Vendido", "Melhor Cliente"};
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaRelatorios = new JTable(modeloTabela);

        JScrollPane scrollPane = new JScrollPane(tabelaRelatorios);
        add(scrollPane, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        Botao_Listar = new JButton("Listar Relatórios");
        Botao_Voltar = new JButton("Voltar");

        painelBotoes.add(Botao_Listar);
        painelBotoes.add(Botao_Voltar);

        add(painelBotoes, BorderLayout.SOUTH);

        Botao_Listar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarRelatoriosDeArquivo(modeloTabela, "relatorios.txt");
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

        setVisible(true);
    }

    private void carregarRelatoriosDeArquivo(DefaultTableModel modeloTabela, String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            modeloTabela.setRowCount(0); // Limpar tabela antes de carregar novos dados

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                // Certificar-se de que a linha tem os dados completos
                if (dados.length == 5) {
                    modeloTabela.addRow(dados);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao ler o arquivo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
