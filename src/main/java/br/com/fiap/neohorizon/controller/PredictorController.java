package br.com.fiap.neohorizon.controller;

import br.com.fiap.neohorizon.assembler.PredictionAssembler;
import br.com.fiap.neohorizon.dto.PredictionInputDto;
import br.com.fiap.neohorizon.model.Prediction;
import br.com.fiap.neohorizon.service.PredictionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/predict")
@Tag(name = "Predição", description = "API para geração e consulta de predições de órbitas de objetos espaciais")
public class PredictorController {

    private final PredictionAssembler predictionAssembler;
    private final PredictionService predictionService;

    public PredictorController(PredictionService predictionService, PredictionAssembler predictionAssembler) {
        this.predictionService = predictionService;
        this.predictionAssembler = predictionAssembler;
    }

    @PostMapping()
    @Operation(summary = "Gerar uma nova predição",
            description = "Gera uma predição de órbita com base no período informado e na tag do objeto espacial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Predição gerada com sucesso",
                    content = @Content(schema = @Schema(implementation = Prediction.class))),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos (período ou tag inválidos)",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Objeto espacial não encontrado para a tag fornecida",
                    content = @Content),
            @ApiResponse(responseCode = "422", description = "Não foi possível processar a predição com os parâmetros fornecidos",
                    content = @Content)
    })    public EntityModel<Prediction> generatePrediction(
            @Parameter(description = "Dados de entrada para a predição contendo período e tag do objeto",
                    required = true)
            @RequestBody PredictionInputDto predictionInputDTO){
        Prediction prediction = predictionService.generatePrediction(predictionInputDTO.getPeriodo(), predictionInputDTO.getSpaceObjectTag());
        return predictionAssembler.toModel(prediction);
    }

    @GetMapping()
    @Operation(summary = "Listar todas as predições",
            description = "Recupera uma lista com todas as predições geradas anteriormente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de predições recuperada com sucesso",
                    content = @Content(schema = @Schema(implementation = Prediction.class))),
            @ApiResponse(responseCode = "204", description = "Nenhuma predição encontrada", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar as predições", content = @Content)
    })    public CollectionModel<EntityModel<Prediction>> findAll(){
        List<EntityModel<Prediction>> predictions =
                predictionService.findAllPredictions()
                        .stream()
                        .map(predictionAssembler::toModel)
                        .toList();

        return CollectionModel.of(
                predictions,
                linkTo(methodOn(PredictorController.class).findAll())
                        .withSelfRel()
        );
    }
}
