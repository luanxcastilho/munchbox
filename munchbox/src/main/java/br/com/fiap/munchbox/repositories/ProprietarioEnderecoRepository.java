package br.com.fiap.munchbox.repositories;

import br.com.fiap.munchbox.dtos.proprietarioendereco.ProprietarioEnderecoRequestDTO;
import br.com.fiap.munchbox.entities.ProprietarioEndereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProprietarioEnderecoRepository extends JpaRepository<ProprietarioEndereco, Long>
{

}
