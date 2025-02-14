package controller;

import model.Produto;
import util.ArquivoUtil;
import validator.ProdutoValidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController {
    private static final String ARQUIVO_PRODUTOS = System.getProperty("user.dir") + "/src/arquivos/Produto";
    private List<Produto> produtos;
    public ProdutoController() {
        this.produtos = carregarProdutos();
    }

    public void adicionarProduto(Produto produto) {
        if (!ProdutoValidator.validarProduto(produto)) {
            System.out.println("Erro: Dados inválidos para o produto.");
            return;
        }

        produtos = carregarProdutos();

        if (buscarProdutoPorNome(produto.getNome()) != null) {
            System.out.println("Erro: Já existe um produto com este nome.");
            return;
        }

        produtos.add(produto);
        salvarProdutos();
        System.out.println("Produto adicionado com sucesso: " + produto.getNome());
    }

    public void listarProdutos() {
        produtos = carregarProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        }
    }

    public Produto buscarProdutoPorNome(String nome) {
        produtos = carregarProdutos();
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                return produto;
            }
        }
        return null;
    }

    public void removerProduto(String nome) {
        Produto produto = buscarProdutoPorNome(nome);
        if (produto != null) {
            produtos.remove(produto);
            salvarProdutos();
            System.out.println("Produto removido com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public void atualizarProduto(String nome, Produto novosDados) {
        if (!ProdutoValidator.validarProduto(novosDados)) {
            System.out.println("Erro: Dados inválidos para o produto.");
            return;
        }

        produtos = carregarProdutos();
        Produto produto = buscarProdutoPorNome(nome);
        if (produto != null) {
            produto.setNome(novosDados.getNome());
            produto.setModelo(novosDados.getModelo());
            produto.setMarca(novosDados.getMarca());
            produto.setPreco(novosDados.getPreco());
            produto.setCor(novosDados.getCor());
            produto.setArmazenamento(novosDados.getArmazenamento());
            produto.setMemoriaRAM(novosDados.getMemoriaRAM());
            produto.setTamanhoTela(novosDados.getTamanhoTela());
            produto.setTem5G(novosDados.isTem5G());
            produto.setResistenciaAgua(novosDados.isResistenciaAgua());
            produto.setEstoqueAtual(novosDados.getEstoqueAtual());
            produto.setEstoqueMinimo(novosDados.getEstoqueMinimo());
            salvarProdutos();
            System.out.println("Produto atualizado com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public void salvarProdutos() {
        List<String> linhas = new ArrayList<>();
        for (Produto produto : produtos) {
            linhas.add(produto.toString());
        }
        ArquivoUtil.salvarLista(ARQUIVO_PRODUTOS, linhas);
    }

    public List<Produto> carregarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        try {
            List<String> linhas = ArquivoUtil.carregarLista(ARQUIVO_PRODUTOS);
            for (String linha : linhas) {
                Produto produto = Produto.CriarProdutoPartiDaLinha(linha);
                produtos.add(produto);
            }
        } catch (Exception e) {
            System.out.println("Nenhum produto encontrado ou erro ao carregar: " + e.getMessage());
        }
        return produtos;
    }
}
