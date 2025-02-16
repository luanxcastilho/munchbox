package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.cliente.ClienteRequestDTO;
import br.com.fiap.munchbox.entities.Cliente;
import br.com.fiap.munchbox.entities.Usuario;
import br.com.fiap.munchbox.repositories.ClienteRepository;
import br.com.fiap.munchbox.repositories.UsuarioRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class ClienteService
{
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;

    public ClienteService(ClienteRepository clienteRepository, UsuarioRepository usuarioRepository)
    {
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Cliente> findAll(int page, int size)
    {
        Pageable pageable = PageRequest.of(page-1, size);
        return this.clienteRepository.findAll(pageable).getContent();
    }

    public Optional<Cliente> findById(Long id)
    {
        return this.clienteRepository.findById(id);
    }

    public void create(ClienteRequestDTO clienteRequestDTO)
    {
        Usuario usuario = this.usuarioRepository.findById(clienteRequestDTO.getIdUsuario()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Cliente cliente = new Cliente
                (
                        clienteRequestDTO.getNome().toUpperCase(),
                        clienteRequestDTO.getEmail().toUpperCase(),
                        clienteRequestDTO.getCelular(),
                        clienteRequestDTO.getDataNascimento(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        usuario
                );
        this.clienteRepository.save(cliente);
    }

    public void update(ClienteRequestDTO clienteRequestDTO, Long id)
    {
        Cliente cliente = this.clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        cliente.setNome(clienteRequestDTO.getNome());
        cliente.setEmail(clienteRequestDTO.getEmail());
        cliente.setCelular(clienteRequestDTO.getCelular());
        cliente.setDataAtualizacao(LocalDateTime.now());
        cliente.setUsuario(usuario);

        this.clienteRepository.save(cliente);
    }

    public void delete(Long id)
    {
        this.clienteRepository.deleteById(id);
    }

}
