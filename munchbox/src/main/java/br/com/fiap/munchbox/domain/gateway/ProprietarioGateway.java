package br.com.fiap.munchbox.domain.gateway;

import br.com.fiap.munchbox.domain.core.Proprietario;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProprietarioGateway {

    void create(Proprietario proprietario);
    void update(Proprietario proprietario);
    void delete(Long id);
    Optional<Proprietario> findById(Long id);
    List<Proprietario> findAll(Pageable pageable);
}
