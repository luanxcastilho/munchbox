package br.com.fiap.munchbox.infrastructure.mapper;

import br.com.fiap.munchbox.domain.core.ClienteEndereco;
import br.com.fiap.munchbox.infrastructure.entity.ClienteEnderecoEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteEnderecoMapper
{
    public static ClienteEndereco toDomain(ClienteEnderecoEntity clienteEnderecoEntity)
    {
        return new ClienteEndereco(
                clienteEnderecoEntity.getId(),
                ClienteMapper.toDomain(clienteEnderecoEntity.getClienteEntity()),
                clienteEnderecoEntity.getRua(),
                clienteEnderecoEntity.getNumero(),
                clienteEnderecoEntity.getComplemento(),
                clienteEnderecoEntity.getBairro(),
                clienteEnderecoEntity.getCidade(),
                clienteEnderecoEntity.getEstado(),
                clienteEnderecoEntity.getCep(),
                clienteEnderecoEntity.getDataAtualizacao(),
                clienteEnderecoEntity.getDataInclusao()
        );
    }

    public static ClienteEnderecoEntity toEntity(ClienteEndereco clienteEndereco)
    {
        return new ClienteEnderecoEntity(
                clienteEndereco.getId(),
                ClienteMapper.toEntity(clienteEndereco.getCliente()),
                clienteEndereco.getRua(),
                clienteEndereco.getNumero(),
                clienteEndereco.getComplemento(),
                clienteEndereco.getBairro(),
                clienteEndereco.getCidade(),
                clienteEndereco.getEstado(),
                clienteEndereco.getCep(),
                clienteEndereco.getDataAtualizacao(),
                clienteEndereco.getDataInclusao()
        );
    }
}
