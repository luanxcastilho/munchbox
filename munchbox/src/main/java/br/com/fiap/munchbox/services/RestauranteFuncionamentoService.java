package br.com.fiap.munchbox.services;

import br.com.fiap.munchbox.dtos.RestauranteFuncionamentoRequestDTO;
import br.com.fiap.munchbox.entities.Restaurante;
import br.com.fiap.munchbox.entities.RestauranteFuncionamento;
import br.com.fiap.munchbox.repositories.RestauranteFuncionamentoRepository;
import br.com.fiap.munchbox.repositories.RestauranteRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestauranteFuncionamentoService
{
    private final RestauranteRepository restauranteRepository;
    private final RestauranteFuncionamentoRepository restauranteFuncionamentoRepository;

    public RestauranteFuncionamentoService(RestauranteRepository restauranteRepository, RestauranteFuncionamentoRepository restauranteFuncionamentoRepository)
    {
        this.restauranteRepository = restauranteRepository;
        this.restauranteFuncionamentoRepository = restauranteFuncionamentoRepository;
    }

    public List<RestauranteFuncionamento> findAll(int page, int size)
    {
        Pageable pageable = PageRequest.of(
                page - 1,
                size
        );
        return this.restauranteFuncionamentoRepository.findAll(pageable).getContent();
    }

    public Optional<RestauranteFuncionamento> findById(Long id)
    {
        return this.restauranteFuncionamentoRepository.findById(id);
    }

    public void create(RestauranteFuncionamentoRequestDTO restauranteFuncionamentoRequestDTO)
    {
        Restaurante restaurante = this.restauranteRepository.findById
                (
                        restauranteFuncionamentoRequestDTO.getIdRestaurante()
                )
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        RestauranteFuncionamento restauranteFuncionamento = new RestauranteFuncionamento();

        restauranteFuncionamento.setRestaurante(restaurante);
        restauranteFuncionamento.setDiaDaSemana(restauranteFuncionamentoRequestDTO.getDiaDaSemana());
        restauranteFuncionamento.setHorarioAbertura(restauranteFuncionamentoRequestDTO.getHorarioAbertura());
        restauranteFuncionamento.setHorarioFechamento(restauranteFuncionamentoRequestDTO.getHorarioFechamento());
        restauranteFuncionamento.setDataAtualizacao(LocalDateTime.now());
        restauranteFuncionamento.setDataInclusao(LocalDateTime.now());

        restauranteFuncionamentoRepository.save(restauranteFuncionamento);
    }

    public void update(RestauranteFuncionamentoRequestDTO restauranteFuncionamentoRequestDTO, Long id)
    {
        RestauranteFuncionamento restauranteFuncionamento = this.restauranteFuncionamentoRepository.findById
                (
                        id
                )
                .orElseThrow(() -> new RuntimeException("Horário de funcionamento não encontrado"));

        Restaurante restaurante = this.restauranteRepository.findById
                        (
                                restauranteFuncionamentoRequestDTO.getIdRestaurante()
                        )
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        restauranteFuncionamento.setRestaurante(restaurante);
        restauranteFuncionamento.setDiaDaSemana(restauranteFuncionamentoRequestDTO.getDiaDaSemana());
        restauranteFuncionamento.setHorarioAbertura(restauranteFuncionamentoRequestDTO.getHorarioAbertura());
        restauranteFuncionamento.setHorarioFechamento(restauranteFuncionamentoRequestDTO.getHorarioFechamento());
        restauranteFuncionamento.setDataAtualizacao(LocalDateTime.now());

        this.restauranteFuncionamentoRepository.save(restauranteFuncionamento);
    }

    public void delete(Long id)
    {
        this.restauranteFuncionamentoRepository.deleteById(id);
    }
}
