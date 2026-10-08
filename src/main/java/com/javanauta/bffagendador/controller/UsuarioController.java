package com.javanauta.bffagendador.controller;

import com.javanauta.bffagendador.business.UsuarioService;
import com.javanauta.bffagendador.business.dto.in.EnderecoDTORequest;
import com.javanauta.bffagendador.business.dto.in.TelefoneDTORequest;
import com.javanauta.bffagendador.business.dto.in.UsuarioDTORequest;
import com.javanauta.bffagendador.infrastructure.client.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(
        name = "Usuário",
        description = "Cadastro e login de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)

public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(
            summary = "Salvar Usuário",
            description = "Cria um novo usuário"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Usuário salvo com sucesso"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Usuário já cadastrado"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro de servidor"
    )
    public ResponseEntity<UsuarioDTORequest> salvaUsuario(
            @RequestBody UsuarioDTORequest usuarioDTO) {

        return ResponseEntity.ok(
                usuarioService.salvaUsuario(usuarioDTO)
        );
    }

    @PostMapping("/login")
    @Operation(
            summary = "Login Usuário",
            description = "Login do usuário"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Usuário logado com sucesso"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Credenciais inválidas"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro de servidor"
    )
    public String login(
            @RequestBody UsuarioDTORequest usuarioDTO) {

        return usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    @Operation(
            summary = "Buscar Usuário por Email",
            description = "Busca os dados do usuário pelo email"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Usuário encontrado"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro de servidor"
    )
    public ResponseEntity<UsuarioDTORequest> buscarUsuarioPorEmail(
            @RequestParam("email") String email,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(
                usuarioService.buscaUsuarioPorEmail(email, token)
        );
    }

    @DeleteMapping("/{email}")
    @Operation(
            summary = "Deletar Usuário por Email",
            description = "Deleta usuário pelo email"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Usuário deletado com sucesso"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro de servidor"
    )
    public ResponseEntity<Void> deletaUsuarioPorEmail(
            @PathVariable String email,
            @RequestHeader("Authorization") String token) {

        usuarioService.deletaUsuarioPorEmail(email, token);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(
            summary = "Atualizar Dados do Usuário",
            description = "Atualiza os dados do usuário"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Usuário atualizado com sucesso"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Usuário não cadastrado"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro de servidor"
    )
    public ResponseEntity<UsuarioDTORequest> atualizaDadosUsuario(
            @RequestBody UsuarioDTORequest usuarioDTO,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(
                usuarioService.atualizaDadosUsuario(
                        token,
                        usuarioDTO
                )
        );
    }

    @PutMapping("/endereco")
    @Operation(
            summary = "Atualizar Endereço do Usuário",
            description = "Atualiza o endereço do usuário"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Endereço atualizado com sucesso"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro de servidor"
    )
    public ResponseEntity<EnderecoDTORequest> atualizaEndereco(
            @RequestParam("id") Long id,
            @RequestHeader("Authorization") String token,
            @RequestBody EnderecoDTORequest enderecoDTO) {

        return ResponseEntity.ok(
                usuarioService.atualizaEndereco(
                        id,
                        enderecoDTO,
                        token
                )
        );
    }

    @PutMapping("/telefone")
    @Operation(
            summary = "Atualizar Telefone do Usuário",
            description = "Atualiza o telefone do usuário"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Telefone atualizado com sucesso"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro de servidor"
    )
    public ResponseEntity<TelefoneDTORequest> atualizaTelefone(
            @RequestParam("id") Long id,
            @RequestHeader("Authorization") String token,
            @RequestBody TelefoneDTORequest telefoneDTO) {

        return ResponseEntity.ok(
                usuarioService.atualizaTelefone(
                        id,
                        telefoneDTO,
                        token
                )
        );
    }

    @PostMapping("/endereco")
    @Operation(
            summary = "Salvar Endereço do Usuário",
            description = "Cadastra um endereço para o usuário"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Endereço salvo com sucesso"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro de servidor"
    )
    public ResponseEntity<EnderecoDTORequest> cadastraEndereco(
            @RequestParam("id") Long id,
            @RequestHeader("Authorization") String token,
            @RequestBody EnderecoDTORequest enderecoDTO) {

        return ResponseEntity.ok(
                usuarioService.cadastraEndereco(
                        id,
                        enderecoDTO,
                        token
                )
        );
    }

    @PostMapping("/telefone")
    @Operation(
            summary = "Salvar Telefone do Usuário",
            description = "Cadastra um telefone para o usuário"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Telefone salvo com sucesso"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro de servidor"
    )
    public ResponseEntity<TelefoneDTORequest> cadastraTelefone(
            @RequestParam("id") Long id,
            @RequestHeader("Authorization") String token,
            @RequestBody TelefoneDTORequest telefoneDTO) {

        return ResponseEntity.ok(
                usuarioService.cadastraTelefone(
                        id,
                        telefoneDTO,
                        token
                )
        );
    }
}