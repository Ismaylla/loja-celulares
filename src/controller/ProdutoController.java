package controller;

import model.Produto;
import util.ArquivoUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController {
    private static final String ARQUIVO_PRODUTOS = System.getProperty("user.dir") + "/src/arquivos/Produto";
    private List<Produto> produtos;

    public ProdutoController() {
        this.produtos = carregarProdutos();
    }

    private void carregarProdutosAntes() {
        this.produtos = carregarProdutos();
    }

    public void adicionarProduto(Produto produto) {
        carregarProdutosAntes();
        produtos.add(produto);
        salvarProdutos();
        System.out.println("Produto adicionado com sucesso: " + produto.getNome());
    }

    public void listarProdutos() {
        carregarProdutosAntes();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        }
    }

    public Produto buscarProdutoPorNome(String nome) {
        carregarProdutosAntes();
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                return produto;
            }
        }
        return null;
    }

    public void removerProduto(String nome) {
        carregarProdutosAntes();
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
        carregarProdutosAntes();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getNome().equalsIgnoreCase(nome)) {
                produtos.set(i, novosDados); // Substitui o produto na lista
                salvarProdutos();
                System.out.println("Produto atualizado com sucesso.");
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }


    public void salvarProdutos() {
        try {
            ArquivoUtil.salvarLista(ARQUIVO_PRODUTOS, produtos);
        } catch (IOException e) {
            System.out.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    public List<Produto> carregarProdutos() {
        try {
            return ArquivoUtil.carregarLista(ARQUIVO_PRODUTOS);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nenhum produto encontrado ou erro ao carregar: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}