package br.com.fiap.munchbox.infrastructure.mapper;

import br.com.fiap.munchbox.domain.core.Proprietario;
import br.com.fiap.munchbox.infrastructure.entity.ProprietarioEntity;
import org.springframework.stereotype.Component;

@Component
public class ProprietarioMapper
{
    public static Proprietario toDomain(ProprietarioEntity proprietarioEntity){
        return new Proprietario(
                proprietarioEntity.getId(),
                UsuarioMapper.toDomain(proprietarioEntity.getUsuarioEntity()),
                proprietarioEntity.getNome(),
                proprietarioEntity.getDataNascimento(),
                proprietarioEntity.getCelular(),
                proprietarioEntity.getEmail(),
                proprietarioEntity.getDataAtualizacao(),
                proprietarioEntity.getDataInclusao()
        );
    }
    public static ProprietarioEntity toEntity(Proprietario proprietario){
        return new ProprietarioEntity(
                proprietario.getId(),
                UsuarioMapper.toEntity(proprietario.getUsuario()),
                proprietario.getNome(),
                proprietario.getDataNascimento(),
                proprietario.getCelular(),
                proprietario.getEmail(),
                proprietario.getDataAtualizacao(),
                proprietario.getDataInclusao()
        );
    }
}
