package br.com.fiap.munchbox.repositories;

import br.com.fiap.munchbox.dtos.proprietarioendereco.ProprietarioEnderecoRequestDTO;
import br.com.fiap.munchbox.entities.ProprietarioEndereco;

import java.util.List;
import java.util.Optional;

public interface ProprietarioEnderecoRepository {

    Optional<ProprietarioEndereco> findById(Long id);

    List<ProprietarioEndereco> findAll(int size, int offset);

    Integer create(ProprietarioEnderecoRequestDTO proprietarioEndereco);

    Integer update(ProprietarioEnderecoRequestDTO proprietarioEndereco, Long id);

    Integer delete(Long id);

}
