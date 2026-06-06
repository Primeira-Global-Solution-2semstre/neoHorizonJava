package br.com.fiap.neohorizon.controller;

import br.com.fiap.neohorizon.dto.RecommendationOutputDto;
import br.com.fiap.neohorizon.service.RecommendationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/recommendation")
public class RecomendationController {

    private final RecommendationService recommendationService;

    public RecomendationController(RecommendationService recommendationService){
        this.recommendationService = recommendationService;
    }
    @GetMapping()
    public RecommendationOutputDto generateRecommendation(@RequestParam Float altitudeDesejada){
        return recommendationService.generateRecomendation(altitudeDesejada);
    }
}
