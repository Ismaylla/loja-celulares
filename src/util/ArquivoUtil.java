package util;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class ArquivoUtil {


    public static List<String> carregarLista(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void salvarLista(String filePath, List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
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

        // Divida a linha diretamente pelo ";"
        String[] tokens = line.split(";");

        // Remova as aspas dos tokens
        for (int i = 0; i < tokens.length; i++) {
            tokens[i] = tokens[i].replace("\"", "").trim();
        }

        return tokens;
    }

}


