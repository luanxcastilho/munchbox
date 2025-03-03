package br.com.fiap.munchbox.controllers;

import br.com.fiap.munchbox.dtos.UsuarioPerfilRequestDTO;
import br.com.fiap.munchbox.entities.UsuarioPerfil;
import br.com.fiap.munchbox.exceptions.GlobalExceptionHandler;
import br.com.fiap.munchbox.helpers.UsuarioPerfilHelper;
import br.com.fiap.munchbox.repositories.UsuarioPerfilRepository;
import br.com.fiap.munchbox.services.UsuarioPerfilService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UsuarioPerfilControllerTest
{
    private MockMvc mockMvc;

    @Mock
    private UsuarioPerfilRepository usuarioPerfilRepository;

    @Mock
    private UsuarioPerfilService usuarioPerfilService;

    private UsuarioPerfil usuarioPerfil;
    private UsuarioPerfilRequestDTO usuarioPerfilRequestDTO;

    @BeforeEach
    void setup()
    {
        MockitoAnnotations.openMocks(this);
        UsuarioPerfilController usuarioPerfilController = new UsuarioPerfilController(usuarioPerfilService);
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioPerfilController)
                                    .setControllerAdvice(new GlobalExceptionHandler())
                                    .addFilter((request, response, filterChain) -> {
                                        filterChain.doFilter(request, response);
                                    }, "/*").build();

        usuarioPerfil = UsuarioPerfilHelper.gerarUsuarioPerfil();

        usuarioPerfilRequestDTO = new UsuarioPerfilRequestDTO();
        usuarioPerfilRequestDTO.setNome(usuarioPerfil.getNome());
    }

    @Test
    public void devePermitirConsultarTodosUsuarioPerfil() throws Exception
    {
        mockMvc.perform(get("/v1/usuarios-perfis")
                        .param("page", "1")
                        .param("size", "15"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void devePermitirConsultarUmUsuarioPerfil() throws Exception
    {
        mockMvc.perform(get("/v1/usuarios-perfis/{id}", usuarioPerfil.getIdUsuarioPerfil()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void devePermitirCriarUsuarioPerfil() throws Exception
    {
        when(usuarioPerfilRepository.save(any(UsuarioPerfil.class))).thenReturn(usuarioPerfil);

        mockMvc.perform(post("/v1/usuarios-perfis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(usuarioPerfilRequestDTO)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void devePermitirAtualizarUsuarioPerfil() throws Exception
    {
        when(usuarioPerfilRepository.findById(usuarioPerfil.getIdUsuarioPerfil())).thenReturn(Optional.of(usuarioPerfil));
        when(usuarioPerfilRepository.save(any(UsuarioPerfil.class))).thenReturn(usuarioPerfil);

        mockMvc.perform(put("/v1/usuarios-perfis/{id}", usuarioPerfil.getIdUsuarioPerfil())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(usuarioPerfilRequestDTO)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void devePermitirRemoverUsuarioPerfil() throws Exception
    {
        doNothing().when(usuarioPerfilRepository).deleteById(usuarioPerfil.getIdUsuarioPerfil());

        mockMvc.perform(delete("/v1/usuarios-perfis/{id}", usuarioPerfil.getIdUsuarioPerfil()))
                .andDo(print())
                .andExpect(status().isOk());
    }
}