package br.com.fiap.munchbox;

import br.com.fiap.munchbox.entities.UsuarioPerfil;
import br.com.fiap.munchbox.helpers.UsuarioPerfilHelper;
import br.com.fiap.munchbox.repositories.UsuarioPerfilRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

public class UsuarioPerfilRepositoryTest
{
    AutoCloseable mock;
    @Mock
    private UsuarioPerfilRepository usuarioPerfilRepository;
    private UsuarioPerfil usuarioPerfil;

    @BeforeEach
    void setUp()
    {
        mock = MockitoAnnotations.openMocks(this);
        usuarioPerfil = UsuarioPerfilHelper.gerarUsuarioPerfil();
    }

    @AfterEach
    void tearDown() throws Exception
    {
        mock.close();
    }

    @Test
    void deveSalvarUsuarioPerfil()
    {
        //Arrange - preparar
        when(usuarioPerfilRepository.save(any(UsuarioPerfil.class))).thenReturn(usuarioPerfil);

        //Act - atuar
        var usuarioPerfilArmazenado = usuarioPerfilRepository.save(usuarioPerfil);

        //Assert - validar
        verify(usuarioPerfilRepository,
               times(1)).save(usuarioPerfil);
    }

    @Test
    void deveConsultareUsuarioPerfil()
    {
        //Arrange - preparar
        when(usuarioPerfilRepository.findById(any(Long.class))).thenReturn(Optional.of(usuarioPerfil));

        //Act - atuar
        var usuarioPerfilEncontrado = usuarioPerfilRepository.findById(usuarioPerfil.getIdUsuarioPerfil());

        //Assert - validar
        assertThat(usuarioPerfilEncontrado).isNotNull().containsSame(usuarioPerfil);
    }

    @Test
    void deveDeletareUsuarioPerfil()
    {
        //Arrange - preparar
        doNothing().when(usuarioPerfilRepository).deleteById(any(Long.class));

        //Act - atuar
        usuarioPerfilRepository.deleteById(usuarioPerfil.getIdUsuarioPerfil());

        //Assert - validar
        verify(usuarioPerfilRepository,
               times(1)).deleteById(usuarioPerfil.getIdUsuarioPerfil());
    }
}
