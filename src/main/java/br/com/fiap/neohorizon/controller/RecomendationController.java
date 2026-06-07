package br.com.fiap.neohorizon.controller;

import br.com.fiap.neohorizon.dto.RecommendationOutputDto;
import br.com.fiap.neohorizon.service.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/recommendation")
@Tag(name = "Recomendação", description = "API para geração de recomendações de objetos espaciais com base na altitude desejada")
public class RecomendationController {

    private final RecommendationService recommendationService;

    public RecomendationController(RecommendationService recommendationService){
        this.recommendationService = recommendationService;
    }

    @GetMapping()
    @Operation(summary = "Gerar recomendação de objeto espacial",
            description = "Gera uma recomendação de objeto espacial com base na altitude desejada pelo usuário. " +
                    "O sistema analisa os objetos disponíveis e sugere aqueles que melhor se adequam à altitude informada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recomendação gerada com sucesso",
                    content = @Content(schema = @Schema(implementation = RecommendationOutputDto.class))),
            @ApiResponse(responseCode = "400", description = "Altitude inválida (valor negativo, zero ou acima do limite máximo permitido)",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Nenhum objeto espacial encontrado para a altitude informada",
                    content = @Content),
            @ApiResponse(responseCode = "422", description = "Não foi possível processar a recomendação com o parâmetro fornecido",
                    content = @Content)
    })
    public RecommendationOutputDto generateRecommendation(
            @Parameter(description = "Altitude desejada em quilômetros (km). Deve ser um valor positivo maior que zero.",
            required = true,
            example = "550.5") @RequestParam Float altitudeDesejada){
        return recommendationService.generateRecomendation(altitudeDesejada);
    }
}
