package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.ProprietarioRestauranteRequestDTO;
import br.com.fiap.munchbox.entities.Proprietario;
import br.com.fiap.munchbox.entities.ProprietarioRestaurante;
import br.com.fiap.munchbox.entities.Restaurante;
import br.com.fiap.munchbox.repositories.ProprietarioRestauranteRepository;
import br.com.fiap.munchbox.repositories.ProprietarioRepository;
import br.com.fiap.munchbox.repositories.RestauranteRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProprietarioRestauranteService
{
    private final ProprietarioRestauranteRepository proprietarioRestauranteRepository;
    private final ProprietarioRepository proprietarioRepository;
    private final RestauranteRepository restauranteRepository;

    public ProprietarioRestauranteService(ProprietarioRestauranteRepository proprietarioRestauranteRepository,
                                          ProprietarioRepository proprietarioRepository, RestauranteRepository restauranteRepository)
    {
        this.proprietarioRestauranteRepository = proprietarioRestauranteRepository;
        this.proprietarioRepository = proprietarioRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public List<ProprietarioRestaurante> findAll(int page,
                                              int size)
    {
        Pageable pageable = PageRequest.of(
                page - 1,
                size
        );
        return this.proprietarioRestauranteRepository.findAll(pageable).getContent();
    }

    public Optional<ProprietarioRestaurante> findById(Long id)
    {
        return this.proprietarioRestauranteRepository.findById(id);
    }

    public void create(ProprietarioRestauranteRequestDTO proprietarioRestauranteRequestDTO)
    {
        Proprietario proprietario = this.proprietarioRepository.findById(proprietarioRestauranteRequestDTO.getIdProprietario()).orElseThrow(() -> new RuntimeException("Proprietário não encontrado"));
        Restaurante restaurante = this.restauranteRepository.findById(proprietarioRestauranteRequestDTO.getIdRestaurante()).orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        ProprietarioRestaurante proprietarioRestaurante = new ProprietarioRestaurante
                (
                        1L,
                        proprietario,
                        restaurante,
                        LocalDateTime.now(),
                        LocalDateTime.now()
                );
        this.proprietarioRestauranteRepository.save(proprietarioRestaurante);
    }

    public void update(ProprietarioRestauranteRequestDTO proprietarioRestauranteRequestDTO, Long id)
    {
        ProprietarioRestaurante proprietarioRestaurante = this.proprietarioRestauranteRepository.findById(id).orElseThrow(() -> new RuntimeException("Vínculo de proprietário com restaurante não encontrado"));
        Proprietario proprietario = this.proprietarioRepository.findById(proprietarioRestauranteRequestDTO.getIdProprietario()).orElseThrow(() -> new RuntimeException("Proprietário não encontrado"));
        Restaurante restaurante = this.restauranteRepository.findById(proprietarioRestauranteRequestDTO.getIdRestaurante()).orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        proprietarioRestaurante.setProprietario(proprietario);
        proprietarioRestaurante.setRestaurante(restaurante);
        proprietarioRestaurante.setDataAtualizacao(LocalDateTime.now());

        this.proprietarioRestauranteRepository.save(proprietarioRestaurante);
    }

    public void delete(Long id)
    {
        this.proprietarioRestauranteRepository.deleteById(id);
    }
}

