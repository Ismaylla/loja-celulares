package util;

import java.io.*;
import java.util.List;

public class ArquivoUtil {

    // Método para salvar uma lista de objetos em um arquivo
    public static <T> void salvarLista(String caminho, List<T> lista) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(lista); // Serializa a lista e salva no arquivo
            System.out.println("Lista salva com sucesso no arquivo: " + caminho);
        }
    }

    // Método para carregar uma lista de objetos de um arquivo
    @SuppressWarnings("unchecked")
    public static <T> List<T> carregarLista(String caminho) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))) {
            return (List<T>) ois.readObject(); // Deserializa e retorna a lista
        }
    }

    // Método utilitário para manipular arquivos básicos com BufferedWriter
    public static void escreverArquivo(String caminho, String conteudo) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
            bw.write(conteudo);
            System.out.println("Arquivo escrito com sucesso em: " + caminho);
        }
    }

    // Método utilitário para manipular arquivos básicos com BufferedReader
    public static String lerArquivo(String caminho) throws IOException {
        StringBuilder conteudo = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        }
        return conteudo.toString();
    }
}
