package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.RestauranteTipoCozinhaRequestDTO;
import br.com.fiap.munchbox.entities.RestauranteTipoCozinha;
import br.com.fiap.munchbox.repositories.RestauranteTipoCozinhaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestauranteTipoCozinhaService
{
    private final RestauranteTipoCozinhaRepository restauranteTipoCozinhaRepository;

    public RestauranteTipoCozinhaService(RestauranteTipoCozinhaRepository restauranteTipoCozinhaRepository)
    {
        this.restauranteTipoCozinhaRepository = restauranteTipoCozinhaRepository;
    }

    public List<RestauranteTipoCozinha> findAll(int page, int size)
    {
        Pageable pageable = PageRequest.of(page-1, size);
        return this.restauranteTipoCozinhaRepository.findAll(pageable).getContent();
    }

    public Optional<RestauranteTipoCozinha> findById(Long id)
    {
        return this.restauranteTipoCozinhaRepository.findById(id);
    }

    public void create(RestauranteTipoCozinhaRequestDTO restauranteTipoCozinhaRequestDTO)
    {
        RestauranteTipoCozinha restauranteTipoCozinha = new RestauranteTipoCozinha
                (
                        1L,
                        restauranteTipoCozinhaRequestDTO.getNome(),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                );
        this.restauranteTipoCozinhaRepository.save(restauranteTipoCozinha);
    }

    public void update(RestauranteTipoCozinhaRequestDTO restauranteTipoCozinhaRequestDTO, Long id)
    {
        RestauranteTipoCozinha restauranteTipoCozinha = this.restauranteTipoCozinhaRepository.findById(id).orElseThrow(() -> new RuntimeException("Perfil de usuário não encontrado"));

        restauranteTipoCozinha.setNome(restauranteTipoCozinhaRequestDTO.getNome());
        restauranteTipoCozinha.setDataAtualizacao(LocalDateTime.now());

        this.restauranteTipoCozinhaRepository.save(restauranteTipoCozinha);
    }

    public void delete(Long id)
    {
        this.restauranteTipoCozinhaRepository.deleteById(id);
    }
}
