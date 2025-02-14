package validator;

import model.Cliente;

import java.util.regex.Pattern;

public class ClienteValidator {

    private static final Pattern CPF_PATTERN = Pattern.compile("\\d{11}");
    private static final Pattern NOME_PATTERN = Pattern.compile("^[A-Za-zÀ-ÿ ]+$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,6}$");
    private static final Pattern TELEFONE_PATTERN = Pattern.compile("(\\d{2}\\d{5}-\\d{4})");

    // Validação do CPF
    public static boolean validarCpf(String cpf) {
        return cpf != null && CPF_PATTERN.matcher(cpf).matches();
    }

    // Validação do Nome
    public static boolean validarNome(String nome) {
        return nome != null && NOME_PATTERN.matcher(nome).matches();
    }

    // Validação do Email
    public static boolean validarEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    // Validação do Telefone
    public static boolean validarTelefone(String telefone) {
        return telefone != null && TELEFONE_PATTERN.matcher(telefone).matches();
    }

    // Validação completa do cliente, utilizando os métodos individuais
    public static boolean validarCliente(Cliente cliente) {
        if (!validarNome(cliente.getNome())) {
            System.out.println("Nome inválido. Deve conter apenas letras.");
            return false;
        }
        if (!validarCpf(cliente.getCpf())) {
            System.out.println("CPF inválido. Deve conter 11 dígitos numéricos (APENAS NÚMEROS)");
            return false;
        }
        if (!validarEmail(cliente.getEmail())) {
            System.out.println("Email inválido.");
            return false;
        }
        if (!validarTelefone(cliente.getTelefone())) {
            System.out.println("Telefone inválido. Formato esperado: XX00000-0000");
            return false;
        }
        return true;
    }
}
