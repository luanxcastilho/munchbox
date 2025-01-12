package br.com.fiap.munchbox.repositories;

import br.com.fiap.munchbox.dtos.proprietario.ProprietarioRequestDTO;
import br.com.fiap.munchbox.entities.Proprietario;

import java.util.List;
import java.util.Optional;

public interface ProprietarioRepository {

    Optional<Proprietario> findById(Long id);

    List<Proprietario> findAll(int size, int offset);

    Integer create(ProprietarioRequestDTO proprietario);

    Integer update(ProprietarioRequestDTO proprietario, Long id);

    Integer delete(Long id);
}
