package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.RestauranteRequestDTO;
import br.com.fiap.munchbox.entities.RestauranteTipoCozinha;
import br.com.fiap.munchbox.entities.Restaurante;
import br.com.fiap.munchbox.repositories.RestauranteRepository;
import br.com.fiap.munchbox.repositories.RestauranteTipoCozinhaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService
{
    private final RestauranteRepository restauranteRepository;
    private final RestauranteTipoCozinhaRepository restauranteTipoCozinhaRepository;

    public RestauranteService(RestauranteRepository restauranteRepository,
                                       RestauranteTipoCozinhaRepository restauranteTipoCozinhaRepository)
    {
        this.restauranteRepository = restauranteRepository;
        this.restauranteTipoCozinhaRepository = restauranteTipoCozinhaRepository;
    }

    public List<Restaurante> findAll(int page, int size)
    {
        Pageable pageable = PageRequest.of(
                page - 1,
                size
        );
        return this.restauranteRepository.findAll(pageable).getContent();
    }

    public Optional<Restaurante> findById(Long id)
    {
        return this.restauranteRepository.findById(id);
    }

    public void create(RestauranteRequestDTO restauranteRequestDTO)
    {
        RestauranteTipoCozinha restauranteTipoCozinha = this.restauranteTipoCozinhaRepository.findById(restauranteRequestDTO.getIdRestauranteTipoCozinha()).orElseThrow(() -> new RuntimeException("Tipo de cozinha não encontrado"));
        Restaurante restaurante = new Restaurante
                (
                        1L,
                        restauranteTipoCozinha,
                        restauranteRequestDTO.getNome().toUpperCase(),
                        restauranteRequestDTO.getRua().toUpperCase(),
                        restauranteRequestDTO.getNumero().toUpperCase(),
                        restauranteRequestDTO.getComplemento().toUpperCase(),
                        restauranteRequestDTO.getBairro().toUpperCase(),
                        restauranteRequestDTO.getCidade().toUpperCase(),
                        restauranteRequestDTO.getEstado().toUpperCase(),
                        restauranteRequestDTO.getCep(),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                );
        this.restauranteRepository.save(restaurante);
    }

    public void update(RestauranteRequestDTO restauranteRequestDTO, Long id)
    {
        RestauranteTipoCozinha restauranteTipoCozinha = this.restauranteTipoCozinhaRepository.findById(restauranteRequestDTO.getIdRestauranteTipoCozinha()).orElseThrow(() -> new RuntimeException("Tipo de cozinha não encontrado"));
        Restaurante restaurante = this.restauranteRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        restaurante.setNome(restauranteRequestDTO.getNome().toUpperCase());
        restaurante.setRua(restauranteRequestDTO.getRua().toUpperCase());
        restaurante.setNumero(restauranteRequestDTO.getNumero().toUpperCase());
        restaurante.setComplemento(restauranteRequestDTO.getComplemento().toUpperCase());
        restaurante.setBairro(restauranteRequestDTO.getBairro().toUpperCase());
        restaurante.setCidade(restauranteRequestDTO.getCidade().toUpperCase());
        restaurante.setEstado(restauranteRequestDTO.getEstado().toUpperCase());
        restaurante.setCep(restauranteRequestDTO.getCep());
        restaurante.setDataAtualizacao(LocalDateTime.now());
        restaurante.setRestauranteTipoCozinha(restauranteTipoCozinha);

        this.restauranteRepository.save(restaurante);
    }

    public void delete(Long id)
    {
        this.restauranteRepository.deleteById(id);
    }
}
