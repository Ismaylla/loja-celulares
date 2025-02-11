package controller;

import model.Cliente;
import util.ArquivoUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ClienteController {
    private final String ARQUIVO_CLIENTES = System.getProperty("user.dir") + "/src/arquivos/cliente";
    private List<Cliente> clientes;

    private static final Pattern CPF_PATTERN = Pattern.compile("\\d{11}");
    private static final Pattern NOME_PATTERN = Pattern.compile("^[A-Za-zÀ-ÿ ]+$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,6}$");
    private static final Pattern TELEFONE_PATTERN = Pattern.compile("(\\d{2}\\d{5}-\\d{4})");

    public ClienteController() {
        this.clientes = carregarClientes();
    }

    public void adicionarCliente(Cliente cliente) {
        if (!validarCliente(cliente)) return;
        clientes = carregarClientes();

        if (buscarClientePorCpf(cliente.getCpf()) != null) {
            System.out.println("Erro: Já existe um cliente com este CPF.");
            return;
        }

        clientes.add(cliente);
        salvarClientes();
        System.out.println("Cliente adicionado com sucesso: " + cliente.getNome());
    }

    public void listarClientes() {
        clientes = carregarClientes();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    public Cliente buscarClientePorCpf(String cpf) {
        if (!validarCpf(cpf)) {
            System.out.println("CPF inválido. Deve conter 11 dígitos numéricos.");
            return null;
        }
        clientes = carregarClientes();
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    public void removerCliente(String cpf) {
        if (!validarCpf(cpf)) {
            System.out.println("CPF inválido. Deve conter 11 dígitos numéricos.");
            return;
        }
        clientes = carregarClientes();
        Cliente cliente = buscarClientePorCpf(cpf);
        if (cliente != null) {
            clientes.remove(cliente);
            salvarClientes();
            System.out.println("Cliente removido com sucesso.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void atualizarCliente(String cpf, Cliente novosDados) {
        if (!validarCpf(cpf)) {
            System.out.println("CPF inválido. Deve conter 11 dígitos numéricos.");
            return;
        }
        if (!validarCliente(novosDados)) return;

        clientes = carregarClientes();
        Cliente cliente = buscarClientePorCpf(cpf);
        if (cliente != null) {
            cliente.setNome(novosDados.getNome());
            cliente.setTelefone(novosDados.getTelefone());
            cliente.setEmail(novosDados.getEmail());
            cliente.setQuantidade_money(novosDados.getQuantidade_money());
            cliente.setQuantidade_vezes_comprou();
            salvarClientes();
            System.out.println("Cliente atualizado com sucesso.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private void salvarClientes() {
        try {
            ArquivoUtil.salvarLista(ARQUIVO_CLIENTES, clientes);
        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    public List<Cliente> carregarClientes() {
        try {
            return ArquivoUtil.carregarLista(ARQUIVO_CLIENTES);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nenhum cliente encontrado ou erro ao carregar: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean validarCpf(String cpf) {
        return cpf != null && CPF_PATTERN.matcher(cpf).matches();
    }

    public boolean validarNome(String nome) {
        return nome != null && NOME_PATTERN.matcher(nome).matches();
    }

    public boolean validarEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public boolean validarTelefone(String telefone) {
        return telefone != null && TELEFONE_PATTERN.matcher(telefone).matches();
    }

    public boolean validarCliente(Cliente cliente) {
        if (!validarNome(cliente.getNome())) {
            System.out.println("Nome inválido. Deve conter apenas letras.");
            return false;
        }
        if (!validarCpf(cliente.getCpf())) {
            System.out.println("CPF inválido. Deve conter 11 dígitos numéricos.");
            return false;
        }
        if (!validarEmail(cliente.getEmail())) {
            System.out.println("Email inválido.");
            return false;
        }
        return true;
    }
}
