package br.com.fiap.munchbox.infrastructure.mapper;

import br.com.fiap.munchbox.domain.core.Restaurante;
import br.com.fiap.munchbox.infrastructure.entity.RestauranteEntity;
import org.springframework.stereotype.Component;

@Component
public class RestauranteMapper {
    public static Restaurante toDomain(RestauranteEntity restauranteEntity) {
        return new Restaurante(restauranteEntity.getId(),
                               RestauranteTipoCozinhaMapper.toDomain(restauranteEntity.getRestauranteTipoCozinhaEntity()),
                               ProprietarioMapper.toDomain(restauranteEntity.getProprietarioEntity()),
                               restauranteEntity.getNome(),
                               restauranteEntity.getRua(),
                               restauranteEntity.getNumero(),
                               restauranteEntity.getComplemento(),
                               restauranteEntity.getBairro(),
                               restauranteEntity.getCidade(),
                               restauranteEntity.getEstado(),
                               restauranteEntity.getCep(),
                               restauranteEntity.getDataAtualizacao(),
                               restauranteEntity.getDataInclusao());
    }

    public static RestauranteEntity toEntity(Restaurante restaurante) {
        return new RestauranteEntity(restaurante.getId(),
                                     RestauranteTipoCozinhaMapper.toEntity(restaurante.getRestauranteTipoCozinha()),
                                     ProprietarioMapper.toEntity(restaurante.getProprietario()),
                                     restaurante.getNome(),
                                     restaurante.getRua(),
                                     restaurante.getNumero(),
                                     restaurante.getComplemento(),
                                     restaurante.getBairro(),
                                     restaurante.getCidade(),
                                     restaurante.getEstado(),
                                     restaurante.getCep(),
                                     restaurante.getDataAtualizacao(),
                                     restaurante.getDataInclusao());
    }

}
