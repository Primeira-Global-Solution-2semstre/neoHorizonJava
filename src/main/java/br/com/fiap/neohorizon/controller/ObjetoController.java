package br.com.fiap.neohorizon.controller;

import br.com.fiap.neohorizon.assembler.SpaceObjetoAssembler;
import br.com.fiap.neohorizon.model.SpaceObjeto;
import br.com.fiap.neohorizon.service.ObjetoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/objeto")
@Tag(name = "Objeto", description = "API para gerenciamento de entidades SpaceObjeto")
public class ObjetoController {

    private ObjetoService objetoService;
    private final SpaceObjetoAssembler assembler;

    public ObjetoController(
            ObjetoService objetoService,
            SpaceObjetoAssembler assembler) {

        this.objetoService = objetoService;
        this.assembler = assembler;
    }

    @GetMapping()
    @Operation(summary = "Buscar um objeto por ID",
            description = "Recupera uma entidade SpaceObjeto utilizando seu identificador único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Objeto recuperado com sucesso",
                    content = @Content(schema = @Schema(implementation = SpaceObjeto.class))),
            @ApiResponse(responseCode = "404", description = "Objeto não encontrado", content = @Content),
            @ApiResponse(responseCode = "400", description = "ID inválido fornecido", content = @Content)
    })
    public ResponseEntity<EntityModel<SpaceObjeto>>getObjeto(
            @Parameter(description = "ID do objeto que será recuperado", required = true, example = "1")
            @RequestParam Long id){
        try {
            SpaceObjeto spaceObjeto = objetoService.getObjeto(id);
            return ResponseEntity.ok(assembler.toModel(spaceObjeto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping()
    @Operation(summary = "Criar um novo objeto",
            description = "Adiciona uma nova entidade SpaceObjeto ao sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Objeto criado com sucesso",
                    content = @Content(schema = @Schema(implementation = SpaceObjeto.class))),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos", content = @Content),
            @ApiResponse(responseCode = "409", description = "Objeto já existe no sistema", content = @Content)
    })
    public ResponseEntity<EntityModel<SpaceObjeto>> addObjeto(
            @Parameter(description = "Objeto a ser criado", required = true)
            @RequestBody SpaceObjeto objeto){
        try {
            SpaceObjeto createdObjeto = objetoService.postObjeto(objeto);
            return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(createdObjeto));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping()
    @Operation(summary = "Atualizar um objeto existente",
            description = "Atualiza completamente uma entidade SpaceObjeto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Objeto atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = SpaceObjeto.class))),
            @ApiResponse(responseCode = "404", description = "Objeto não encontrado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos", content = @Content)
    })
    public ResponseEntity<EntityModel<SpaceObjeto>> updateObjeto( @Parameter(description = "Objeto atualizado", required = true)
                                                      @RequestBody SpaceObjeto objeto)
            throws ClassNotFoundException {
        try {
            SpaceObjeto updatedObjeto = objetoService.putObjeto(objeto);
            return ResponseEntity.ok(assembler.toModel(updatedObjeto));
        } catch (ClassNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping()
    @Operation(summary = "Excluir um objeto por ID",
            description = "Remove uma entidade SpaceObjeto do sistema permanentemente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Objeto excluído com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Objeto não encontrado", content = @Content),
            @ApiResponse(responseCode = "400", description = "ID inválido fornecido", content = @Content)
    })
    public ResponseEntity<Void> deleteObjeto(@Parameter(description = "ID do objeto que será excluído", required = true, example = "1")
                                                 @RequestParam Long id){
        try {
            objetoService.deleteObjeto(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}

