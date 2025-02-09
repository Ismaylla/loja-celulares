package controller;

import model.Cliente;
import util.ArquivoUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private final String ARQUIVO_CLIENTES = System.getProperty("user.dir") + "/src/arquivos/cliente";
    private List<Cliente> clientes;

    public ClienteController() {
        this.clientes = carregarClientes();
    }

    public void adicionarCliente(Cliente cliente) {
        clientes = carregarClientes();
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
        clientes = carregarClientes();
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    public void removerCliente(String cpf) {
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
}
