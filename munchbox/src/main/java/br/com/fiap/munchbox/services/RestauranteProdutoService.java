package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.RestauranteProdutoRequestDTO;
import br.com.fiap.munchbox.entities.Restaurante;
import br.com.fiap.munchbox.entities.RestauranteProduto;
import br.com.fiap.munchbox.repositories.RestauranteProdutoRepository;
import br.com.fiap.munchbox.repositories.RestauranteRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestauranteProdutoService
{
    private final RestauranteRepository restauranteRepository;
    private final RestauranteProdutoRepository restauranteProdutoRepository;

    public RestauranteProdutoService(RestauranteRepository restauranteRepository, RestauranteProdutoRepository restauranteProdutoRepository)
    {
        this.restauranteRepository = restauranteRepository;
        this.restauranteProdutoRepository = restauranteProdutoRepository;
    }

    public List<RestauranteProduto> findAll(int page, int size)
    {
        Pageable pageable = PageRequest.of(
                page - 1,
                size
        );
        return this.restauranteProdutoRepository.findAll(pageable).getContent();
    }

    public Optional<RestauranteProduto> findById(Long id)
    {
        return this.restauranteProdutoRepository.findById(id);
    }

    public void create(RestauranteProdutoRequestDTO restauranteProdutoRequestDTO)
    {
        Restaurante restaurante = this.restauranteRepository.findById
                        (
                                restauranteProdutoRequestDTO.getIdRestaurante()
                        )
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        RestauranteProduto restauranteProduto = new RestauranteProduto();

        restauranteProduto.setRestaurante(restaurante);
        restauranteProduto.setNome(restauranteProdutoRequestDTO.getNome().toUpperCase());
        restauranteProduto.setDescricao(restauranteProdutoRequestDTO.getDescricao().toUpperCase());
        restauranteProduto.setValor(restauranteProduto.getValor());
        restauranteProduto.setImagem(restauranteProdutoRequestDTO.getImagem());
        restauranteProduto.setFlagPermiteEntrega(restauranteProdutoRequestDTO.getFlagPermiteEntrega().toUpperCase());
        restauranteProduto.setDataAtualizacao(LocalDateTime.now());
        restauranteProduto.setDataInclusao(LocalDateTime.now());

        restauranteProdutoRepository.save(restauranteProduto);
    }

    public void update(RestauranteProdutoRequestDTO restauranteProdutoRequestDTO, Long id)
    {
        RestauranteProduto restauranteProduto = this.restauranteProdutoRepository.findById
                        (
                                id
                        )
                .orElseThrow(() -> new RuntimeException("Horário de funcionamento não encontrado"));

        Restaurante restaurante = this.restauranteRepository.findById
                        (
                                restauranteProdutoRequestDTO.getIdRestaurante()
                        )
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        restauranteProduto.setRestaurante(restaurante);
        restauranteProduto.setNome(restauranteProdutoRequestDTO.getNome().toUpperCase());
        restauranteProduto.setDescricao(restauranteProdutoRequestDTO.getDescricao().toUpperCase());
        restauranteProduto.setValor(restauranteProduto.getValor());
        restauranteProduto.setImagem(restauranteProdutoRequestDTO.getImagem());
        restauranteProduto.setFlagPermiteEntrega(restauranteProdutoRequestDTO.getFlagPermiteEntrega().toUpperCase());
        restauranteProduto.setDataAtualizacao(LocalDateTime.now());

        this.restauranteProdutoRepository.save(restauranteProduto);
    }

    public void delete(Long id)
    {
        this.restauranteProdutoRepository.deleteById(id);
    }
}
