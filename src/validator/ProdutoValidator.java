package validator;

import model.Produto;
import java.util.regex.Pattern;

public class ProdutoValidator {

    private static final Pattern CODIGO_PATTERN = Pattern.compile("^[A-Za-z0-9]+$");
    private static final Pattern TEXTO_PATTERN = Pattern.compile("^[A-Za-zÀ-ÿ0-9 ]+$");

    public static boolean validarCodigo(String codigo) {
        return codigo != null && CODIGO_PATTERN.matcher(codigo).matches();
    }

    public static boolean validarNome(String nome) {
        return nome != null && TEXTO_PATTERN.matcher(nome).matches();
    }

    public static boolean validarModelo(String modelo) {
        return modelo != null && TEXTO_PATTERN.matcher(modelo).matches();
    }

    public static boolean validarMarca(String marca) {
        return marca != null && TEXTO_PATTERN.matcher(marca).matches();
    }

    public static boolean validarPreco(double preco) {
        return preco >= 0;
    }

    public static boolean validarEstoque(int estoque) {
        return estoque >= 0;
    }

    public static boolean validarProduto(Produto produto) {
        if (!validarCodigo(produto.getCodigo())) {
            System.out.println("Código do produto inválido. Use apenas letras e números.");
            return false;
        }
        if (!validarNome(produto.getNome())) {
            System.out.println("Nome do produto inválido. Deve conter apenas letras e números.");
            return false;
        }
        if (!validarModelo(produto.getModelo())) {
            System.out.println("Modelo do produto inválido.");
            return false;
        }
        if (!validarMarca(produto.getMarca())) {
            System.out.println("Marca do produto inválida.");
            return false;
        }
        if (!validarPreco(produto.getPreco())) {
            System.out.println("Preço do produto não pode ser negativo.");
            return false;
        }
        if (!validarEstoque(produto.getEstoqueAtual()) || !validarEstoque(produto.getEstoqueMinimo())) {
            System.out.println("Estoque inválido. Estoque atual ou mínimo não podem ser negativos.");
            return false;
        }
        return true;
    }
}
