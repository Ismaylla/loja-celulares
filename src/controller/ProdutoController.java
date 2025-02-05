package controller;

import model.Produto;
import util.ArquivoUtil; //arquivo que criei no pakcage util para manipulação 
import java.util.ArrayList;
import java.util.List;


public class ProdutoController {
	private List<Produto> produtos;
	
	public ProdutoController() {
		produtos = new ArrayList<>();
	}
	
	//adicionar produto
	public void adicionarProduto(Produto produto) {
		produtos.add(produto);
		System.out.println("Produto adiconado: " + produto);
	}
	
	//remover produto pelo id
	public void removerPoduto(int id) {
		Produto produtoRemover = null;
		for (Produto p : produtos) {
			if(p.getId() == id) {
				produtoRemover = p;
				break;
			}
		}
		if(produtoRemover != null) {
			produtos.remove(produtoRemover);
			System.out.println("Produto removido: " + produtoRemover);
		} else {
			System.out.println("Produto com ID: " + id + " não foi encontrado.");
		}
	}
	
	//metodo que atualiza um prdouto
	public void atualizarProduto(Produto produto) {
		for(int i = 0; i< produtos.size(); i++) {
			if(produtos.get(i).getId() == produto.getId()) {
				produtos.set(i, produto); // ele substitui por um produto novo
				System.out.println("PRoduto atualizado: "+ produto);
				return;
			}
		}
		System.out.println("Produtos com ID "+ produto.getId() + " não foi encontrado");
	}
	
	//procurar produto pelo id
	public Produto buscarProdutoPorId(int id) {
		for(Produto p:produtos) {
			if (p.getId() == id) {
				return p;
			}
		}
		System.out.println("produto com id " + id + "nao foi encontrado");
		return null; // se ele nao encontrar
	}
	
	//listar todos os produtos
	public void ListarProdutos() {
		if (produtos.isEmpty()) {
			System.out.println("não tem produtos cadastrados");
		} else {
			System.out.println("Lista dos Produtos:");
			for(Produto p : produtos) {
				System.out.println(p);
			}
		}
	}
	
	// salvar e carregar a lista dos produtoss usando o arquivoutil da package util
	public void SalvarProdutos(String caminho) {
		try {
            ArquivoUtil.salvarLista(caminho, produtos);
            System.out.println("Produtos salvos com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao salvar os produtos: " + e.getMessage());
        }
    }
	 public void carregarProdutos(String caminho) {
	        try {
	            produtos = ArquivoUtil.carregarLista(caminho);
	            System.out.println("Produtos carregados com sucesso.");
	        } catch (Exception e) {
	            System.out.println("Erro ao carregar os produtos: " + e.getMessage());
	        }
	    }
}

