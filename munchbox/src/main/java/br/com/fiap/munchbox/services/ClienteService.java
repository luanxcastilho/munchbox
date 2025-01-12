package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.cliente.ClienteRequestDTO;
import br.com.fiap.munchbox.entities.Cliente;
import br.com.fiap.munchbox.repositories.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> findAll(int page, int size) {
        int offset = (page - 1) * size;
        return this.clienteRepository.findAll(size, offset);
    }

    public Optional<Cliente> findById(Long id) {
        return this.clienteRepository.findById(id);
    }

    public void create(ClienteRequestDTO cliente) {
        var create = this.clienteRepository.create(cliente);
        Assert.state(create == 1, "Erro ao cadastrar cliente " + cliente.getNome());
    }

    public void update(ClienteRequestDTO cliente, Long id) {
        var update = this.clienteRepository.update(cliente, id);
        if(update == 0 ) {
            throw  new RuntimeException("Cliente não encontrado");
        }
    }

    public void delete(Long id) {
        var delete = this.clienteRepository.delete(id);
        if(delete == 0 ) {
            throw  new RuntimeException("Cliente não encontrado");
        }
    }
    
}
