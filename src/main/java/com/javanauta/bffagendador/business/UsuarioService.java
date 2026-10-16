package com.javanauta.bffagendador.business;

import com.javanauta.bffagendador.business.dto.in.EnderecoDTORequest;
import com.javanauta.bffagendador.business.dto.in.TelefoneDTORequest;
import com.javanauta.bffagendador.business.dto.in.UsuarioDTORequest;
import com.javanauta.bffagendador.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public UsuarioDTORequest salvaUsuario(UsuarioDTORequest usuarioDTO) {
        return usuarioClient.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(UsuarioDTORequest usuarioDTO) {
        return usuarioClient.login(usuarioDTO);
    }

    public UsuarioDTORequest buscaUsuarioPorEmail(
            String email,
            String token) {

        return usuarioClient.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(
            String email,
            String token) {

        usuarioClient.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTORequest atualizaDadosUsuario(
            String token,
            UsuarioDTORequest dto) {

        return usuarioClient.atualizaDadosUsuario(dto, token);
    }

    public EnderecoDTORequest atualizaEndereco(
            Long idEndereco,
            EnderecoDTORequest enderecoDTO,
            String token) {

        return usuarioClient.atualizaEndereco(
                enderecoDTO,
                idEndereco,
                token
        );
    }

    public TelefoneDTORequest atualizaTelefone(
            Long idTelefone,
            TelefoneDTORequest dto,
            String token) {

        return usuarioClient.atualizaTelefone(
                dto,
                idTelefone,
                token
        );
    }

    public EnderecoDTORequest cadastraEndereco(
            Long idUsuario,
            EnderecoDTORequest enderecoDTO,
            String token) {

        return usuarioClient.cadastraEndereco(
                enderecoDTO,
                idUsuario,
                token
        );
    }

    public TelefoneDTORequest cadastraTelefone(
            Long idUsuario,
            TelefoneDTORequest dto,
            String token) {

        return usuarioClient.cadastraTelefone(
                dto,
                idUsuario,
                token
        );
    }
}