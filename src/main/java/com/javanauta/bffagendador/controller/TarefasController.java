package com.javanauta.bffagendador.controller;

import com.javanauta.bffagendador.business.TarefasService;
import com.javanauta.bffagendador.business.dto.in.TarefasDTORequest;
import com.javanauta.bffagendador.business.dto.out.TarefasDTOResponse;
import com.javanauta.bffagendador.business.enums.StatusNotificacaoEnum;
import com.javanauta.bffagendador.infrastructure.client.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(
        name = "Tarefas",
        description = "Cadastro e gerenciamento de tarefas de usuários"
)
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(
            summary = "Salvar Tarefa",
            description = "Cria uma nova tarefa para o usuário"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Tarefa salva com sucesso"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Dados da tarefa inválidos"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Token inválido ou não informado"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro de servidor"
    )
    public ResponseEntity<TarefasDTOResponse> gravarTarefa(
            @RequestBody TarefasDTORequest dto,
            @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(
                tarefasService.gravarTarefa(token, dto)
        );
    }

    @GetMapping("/eventos")
    @Operation(
            summary = "Buscar Tarefas por Período",
            description = "Busca tarefas cadastradas dentro de um período"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Tarefas encontradas"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Token inválido ou não informado"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro de servidor"
    )
    public ResponseEntity<List<TarefasDTOResponse>> buscaListaDeTarefasPorPeriodo(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dataInicial,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dataFinal,

            @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(
                tarefasService.buscaTarefasAgendadasPorPeriodo(
                        dataInicial,
                        dataFinal,
                        token
                )
        );
    }

    @GetMapping
    @Operation(
            summary = "Buscar Tarefas por Usuário",
            description = "Busca a lista de tarefas cadastradas para o usuário"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Tarefas encontradas"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Token inválido ou não informado"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro de servidor"
    )
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(
            @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(
                tarefasService.buscaTarefasPorEmail(token)
        );
    }

    @DeleteMapping
    @Operation(
            summary = "Deletar Tarefa por ID",
            description = "Deleta uma tarefa cadastrada pelo seu ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Tarefa deletada com sucesso"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Token inválido ou não informado"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Tarefa não encontrada"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro de servidor"
    )
    public ResponseEntity<Void> deletaTarefaPorId(
            @RequestParam("id") String id,
            @RequestHeader(name = "Authorization", required = false) String token) {

        tarefasService.deletaTarefaPorId(id, token);

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(
            summary = "Alterar Status da Tarefa",
            description = "Altera o status de uma tarefa cadastrada"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Status da tarefa alterado com sucesso"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Token inválido ou não informado"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Tarefa não encontrada"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro de servidor"
    )
    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao(
            @RequestParam("status") StatusNotificacaoEnum status,
            @RequestParam("id") String id,
            @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(
                tarefasService.alteraStatus(
                        status,
                        id,
                        token
                )
        );
    }

    @PutMapping
    @Operation(
            summary = "Atualizar Tarefa",
            description = "Atualiza os dados de uma tarefa cadastrada"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Tarefa atualizada com sucesso"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Dados da tarefa inválidos"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Token inválido ou não informado"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Tarefa não encontrada"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro de servidor"
    )
    public ResponseEntity<TarefasDTOResponse> updateTarefas(
            @RequestBody TarefasDTORequest dto,
            @RequestParam("id") String id,
            @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(
                tarefasService.updateTarefas(
                        dto,
                        id,
                        token
                )
        );
    }
}