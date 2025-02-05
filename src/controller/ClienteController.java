package controller;

import model.Cliente;
import util.ArquivoUtil;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private List<Cliente> clientes;
    
    
    public ClienteController() {
        clientes = new ArrayList<>();
    }

    // para adicionar um cliente
    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
        System.out.println("Cliente adicionado: " + cliente);
    }

    // para remover um cliente pelo ID
    public void removerCliente(int id) {
        Cliente clienteARemover = null;
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                clienteARemover = c;
                break;
            }
        }
        if (clienteARemover != null) {
            clientes.remove(clienteARemover);
            System.out.println("Cliente removido: " + clienteARemover);
        } else {
            System.out.println("Cliente com ID " + id + " não encontrado.");
        }
    }

    //  para atualizar um cliente
    public void atualizarCliente(Cliente cliente) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == cliente.getId()) {
                clientes.set(i, cliente); // Substitui o cliente pelo novo
                System.out.println("Cliente atualizado: " + cliente);
                return;
            }
        }
        System.out.println("Cliente com ID " + cliente.getId() + " não encontrado.");
    }

    //  buscar um cliente pelo ID
    public Cliente buscarClientePorId(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        System.out.println("Cliente com ID " + id + " não encontrado.");
        return null; // Retorna null se não encontrar
    }

    // para listar todos os clientes
    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados.");
        } else {
            System.out.println("Lista de clientes:");
            for (Cliente c : clientes) {
                System.out.println(c);
            }
        }
    }

    //Métodos para salvar e carregar a lista de clientes usando arquivoutil
    public void salvarClientes(String caminho) {
        try {
            ArquivoUtil.salvarLista(caminho, clientes);
            System.out.println("Clientes salvos com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao salvar os clientes: " + e.getMessage());
        }
    }

    public void carregarClientes(String caminho) {
        try {
            clientes = ArquivoUtil.carregarLista(caminho);
            System.out.println("Clientes carregados com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao carregar os clientes: " + e.getMessage());
        }
    }
}
