package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.clienteendereco.ClienteEnderecoRequestDTO;
import br.com.fiap.munchbox.entities.ClienteEndereco;
import br.com.fiap.munchbox.repositories.ClienteEnderecoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteEnderecoService {

    private final ClienteEnderecoRepository clienteEnderecoRepository;

    public ClienteEnderecoService(ClienteEnderecoRepository clienteEnderecoRepository) {
        this.clienteEnderecoRepository = clienteEnderecoRepository;
    }

    public List<ClienteEndereco> findAll(int page, int size) {
        int offset = (page - 1) * size;
        return this.clienteEnderecoRepository.findAll(size, offset);
    }

    public Optional<ClienteEndereco> findById(Long id) {
        return this.clienteEnderecoRepository.findById(id);
    }

    public void create(ClienteEnderecoRequestDTO cliente) {
        var create = this.clienteEnderecoRepository.create(cliente);
        Assert.state(create == 1, "Erro ao cadastrar endereço do cliente " + cliente.getIdCliente());
    }

    public void update(ClienteEnderecoRequestDTO cliente, Long id) {
        var update = this.clienteEnderecoRepository.update(cliente, id);
        if (update == 0) {
            throw new RuntimeException("Endereço não encontrado");
        }
    }

    public void delete(Long id) {
        var delete = this.clienteEnderecoRepository.delete(id);
        if (delete == 0) {
            throw new RuntimeException("Endereço não encontrado");
        }
    }
}
