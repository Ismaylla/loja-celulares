package util;

import java.io.*;
import java.util.List;

public class ArquivoUtil {

    public static <T> void salvarLista(String caminho, List<T> lista) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(lista);
            System.out.println("Lista salva com sucesso no arquivo: " + caminho);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> carregarLista(String caminho) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))) {
            return (List<T>) ois.readObject();
        }
    }
}
