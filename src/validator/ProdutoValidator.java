package validator;

import model.Produto;

public class ProdutoValidator {

    public static void validar(Produto produto) {

        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser vazio.");
        }

        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("Preço do produto deve ser maior que zero.");
        }

      //  if (produto.getQuantidadeEstoque() < 0) {
          //  throw new IllegalArgumentException("Quantidade em estoque não pode ser negativa.");
      //  }

       // if (produto.getEstoqueMinimo() < 0) {
       //     throw new IllegalArgumentException("Estoque mínimo não pode ser negativo.");
      //  }
        
    }
}
