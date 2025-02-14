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

    public static String quote(String text) {
        if (text == null) return "";
        return "\"" + text.replace("\"", "\"\"") + "\"";
    }

    public static String unquote(String text) {
        if (text == null) return "";
        if (text.startsWith("\"") && text.endsWith("\"")) {
            text = text.substring(1, text.length() - 1);
        }
        return text.replace("\"\"", "\"");
    }

    public static String[] splitCSV(String line) {
        if (line == null || line.trim().isEmpty()) {
            return new String[0];
        }
        return line.split(",(?=(?:[^\"]\"[^\"]\")[^\"]$)"); }
}
