package br.com.fiap.munchbox.infrastructure.mapper;

import br.com.fiap.munchbox.domain.core.ProprietarioEndereco;
import br.com.fiap.munchbox.infrastructure.entity.ProprietarioEnderecoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProprietarioEnderecoMapper
{
    public static ProprietarioEndereco toDomain(ProprietarioEnderecoEntity proprietarioEnderecoEntity){
        return new ProprietarioEndereco(
                proprietarioEnderecoEntity.getId(),
                ProprietarioMapper.toDomain(proprietarioEnderecoEntity.getProprietarioEntity()),
                proprietarioEnderecoEntity.getRua(),
                proprietarioEnderecoEntity.getNumero(),
                proprietarioEnderecoEntity.getComplemento(),
                proprietarioEnderecoEntity.getBairro(),
                proprietarioEnderecoEntity.getCidade(),
                proprietarioEnderecoEntity.getEstado(),
                proprietarioEnderecoEntity.getCep(),
                proprietarioEnderecoEntity.getDataAtualizacao(),
                proprietarioEnderecoEntity.getDataInclusao()
        );
    }

    public static ProprietarioEnderecoEntity toEntity(ProprietarioEndereco proprietarioEndereco){
        return new ProprietarioEnderecoEntity(
                proprietarioEndereco.getId(),
                ProprietarioMapper.toEntity(proprietarioEndereco.getProprietario()),
                proprietarioEndereco.getRua(),
                proprietarioEndereco.getNumero(),
                proprietarioEndereco.getComplemento(),
                proprietarioEndereco.getBairro(),
                proprietarioEndereco.getCidade(),
                proprietarioEndereco.getEstado(),
                proprietarioEndereco.getCep(),
                proprietarioEndereco.getDataAtualizacao(),
                proprietarioEndereco.getDataInclusao()
        );
    }
}
