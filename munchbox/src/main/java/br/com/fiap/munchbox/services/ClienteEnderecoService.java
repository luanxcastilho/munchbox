package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.ClienteEnderecoRequestDTO;
import br.com.fiap.munchbox.entities.Cliente;
import br.com.fiap.munchbox.entities.ClienteEndereco;
import br.com.fiap.munchbox.repositories.ClienteEnderecoRepository;
import br.com.fiap.munchbox.repositories.ClienteRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteEnderecoService
{

    private final ClienteEnderecoRepository clienteEnderecoRepository;
    private final ClienteRepository clienteRepository;

    public ClienteEnderecoService(ClienteEnderecoRepository clienteEnderecoRepository, ClienteRepository clienteRepository)
    {
        this.clienteEnderecoRepository = clienteEnderecoRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteEndereco> findAll(int page, int size)
    {
        Pageable pageable = PageRequest.of(page - 1, size);
        return this.clienteEnderecoRepository.findAll(pageable).getContent();
    }

    public Optional<ClienteEndereco> findById(Long id)
    {
        return this.clienteEnderecoRepository.findById(id);
    }

    public void create(ClienteEnderecoRequestDTO clienteEnderecoRequestDTO)
    {
        Cliente cliente = this.clienteRepository.findById(clienteEnderecoRequestDTO.getIdCliente()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        ClienteEndereco clienteEndereco = new ClienteEndereco
                (
                        clienteEnderecoRequestDTO.getRua().toUpperCase(),
                        clienteEnderecoRequestDTO.getNumero().toUpperCase(),
                        clienteEnderecoRequestDTO.getComplemento().toUpperCase(),
                        clienteEnderecoRequestDTO.getBairro().toUpperCase(),
                        clienteEnderecoRequestDTO.getCidade().toUpperCase(),
                        clienteEnderecoRequestDTO.getEstado().toUpperCase(),
                        clienteEnderecoRequestDTO.getCep(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        cliente

                );
        this.clienteEnderecoRepository.save(clienteEndereco);
    }

    public void update(ClienteEnderecoRequestDTO clienteEnderecoRequestDTO, Long id)
    {
        ClienteEndereco clienteEndereco = this.clienteEnderecoRepository.findById(id).orElseThrow(() -> new RuntimeException("Endereço do cliente não encontrado"));
        Cliente cliente = this.clienteRepository.findById(clienteEnderecoRequestDTO.getIdCliente()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        clienteEndereco.setRua(clienteEnderecoRequestDTO.getRua().toUpperCase());
        clienteEndereco.setNumero(clienteEnderecoRequestDTO.getNumero().toUpperCase());
        clienteEndereco.setComplemento(clienteEnderecoRequestDTO.getComplemento().toUpperCase());
        clienteEndereco.setBairro(clienteEnderecoRequestDTO.getBairro().toUpperCase());
        clienteEndereco.setCidade(clienteEnderecoRequestDTO.getCidade().toUpperCase());
        clienteEndereco.setEstado(clienteEnderecoRequestDTO.getEstado().toUpperCase());
        clienteEndereco.setCep(clienteEnderecoRequestDTO.getCep());
        clienteEndereco.setDataAtualizacao(LocalDateTime.now());
        clienteEndereco.setCliente(cliente);

        this.clienteEnderecoRepository.save(clienteEndereco);
    }

    public void delete(Long id)
    {
        this.clienteEnderecoRepository.deleteById(id);
    }
}
