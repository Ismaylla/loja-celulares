package controller;

import model.Cliente;
import util.ArquivoUtil;
import validator.ClienteValidator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {

    private final String ARQUIVO_CLIENTES = System.getProperty("user.dir") + "/src/arquivos/Cliente";
    private List<Cliente> clientes;

    public ClienteController() {
        this.clientes = carregarClientes();
    }

    public void adicionarCliente(Cliente cliente) {
        // Validação completa do cliente usando um único método
        if (!validarDadosCliente(cliente)) return;

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

    public void atualizaClientePosCompra(Cliente clienteVenda, double valorTotal){
        clientes = carregarClientes();
        clienteVenda.setupClientePosCompra(valorTotal);
        atualizarCliente(clienteVenda.getCpf(), clienteVenda);
    }

    public Cliente buscarClientePorCpf(String cpf) {
        // Busca por CPF sem validação isolada
        clientes = carregarClientes();
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    public void removerCliente(String cpf) {
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
        // Validação dos novos dados do cliente com método único
        if (!validarDadosCliente(novosDados)) return;

        clientes = carregarClientes();
        Cliente cliente = buscarClientePorCpf(cpf);
        if (cliente != null) {
            cliente.setNome(novosDados.getNome());
            cliente.setTelefone(novosDados.getTelefone());
            cliente.setEmail(novosDados.getEmail());
            cliente.setDinheiroTotalGasto(novosDados.getDinheiroTotalGasto());
            salvarClientes();
            System.out.println("Cliente atualizado com sucesso.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private boolean validarDadosCliente(Cliente cliente) {
        // Valida todos os dados do cliente utilizando o ClienteValidator
        if (!ClienteValidator.validarCliente(cliente)) {
            System.out.println("Erro: Dados inválidos para o cliente.");
            return false;
        }
        return true;
    }

    private void salvarClientes() {
        List<String> linhas = new ArrayList<>();
        for (Cliente cliente : clientes) {
            linhas.add(cliente.toString());
        }
        ArquivoUtil.salvarLista(ARQUIVO_CLIENTES, linhas);
    }

    public List<Cliente> carregarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            List<String> linhas = ArquivoUtil.carregarLista(ARQUIVO_CLIENTES);
            for (String linha : linhas) {
                Cliente cliente = Cliente.CriarClientePartirdaLinha(linha);
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
        }
        return clientes;
    }


}
