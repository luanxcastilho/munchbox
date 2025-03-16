package br.com.fiap.munchbox.infrastructure.mapper;

import br.com.fiap.munchbox.domain.core.Cliente;
import br.com.fiap.munchbox.infrastructure.entity.ClienteEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper
{
    public static Cliente toDomain(ClienteEntity clienteEntity)
    {
        return new Cliente(
                clienteEntity.getId(),
                UsuarioMapper.toDomain(clienteEntity.getUsuarioEntity()),
                clienteEntity.getNome(),
                clienteEntity.getDataNascimento(),
                clienteEntity.getCelular(),
                clienteEntity.getEmail(),
                clienteEntity.getDataAtualizacao(),
                clienteEntity.getDataInclusao()
        );
    }

    public static ClienteEntity toEntity(Cliente cliente)
    {
        return new ClienteEntity(
                cliente.getId(),
                UsuarioMapper.toEntity(cliente.getUsuario()),
                cliente.getNome(),
                cliente.getDataNascimento(),
                cliente.getCelular(),
                cliente.getEmail(),
                cliente.getDataAtualizacao(),
                cliente.getDataInclusao()
        );
    }
}
