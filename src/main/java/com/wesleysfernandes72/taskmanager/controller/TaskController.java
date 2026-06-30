package com.wesleysfernandes72.taskmanager.controller;


import com.wesleysfernandes72.taskmanager.dto.TaskRequest;
import com.wesleysfernandes72.taskmanager.dto.TaskResponse;
import com.wesleysfernandes72.taskmanager.dto.TaskSearchRequest;
import com.wesleysfernandes72.taskmanager.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tasks", description = "Gerenciamento de tarefas")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;


    @Operation(
            summary = "Criar uma tarefa",
            description = "Cria uma nova tarefa e retorna os dados da tarefa criada."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Tarefa criada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos"
            )
    })
    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody @Valid TaskRequest request) {
        TaskResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @Operation(
            summary = "Listar tarefas",
            description = """
                Retorna uma lista paginada de tarefas.

                É possível filtrar por:
                • status
                • prioridade

                Também é possível utilizar paginação e ordenação.
                """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de tarefas retornada com sucesso"
            )
    })
    @GetMapping
    public ResponseEntity<Page<TaskResponse>> findAll(
            @Valid TaskSearchRequest request,
            Pageable pageable) {

        return ResponseEntity.ok(
                service.findAll(request, pageable)
        );
    }

    @Operation(
            summary = "Buscar tarefa por ID",
            description = "Retorna uma tarefa específica pelo seu identificador."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Tarefa encontrada"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tarefa não encontrada"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(
            summary = "Atualizar tarefa",
            description = "Atualiza os dados de uma tarefa existente."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Tarefa atualizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tarefa não encontrada"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update (
            @PathVariable Long id,
            @RequestBody @Valid TaskRequest request
    ) {
        return  ResponseEntity.ok(service.update(id, request));
    }

    @Operation(
            summary = "Excluir tarefa",
            description = "Remove uma tarefa existente pelo seu identificador."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Tarefa removida com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tarefa não encontrada"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
