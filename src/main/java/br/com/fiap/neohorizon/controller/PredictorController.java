package br.com.fiap.neohorizon.controller;

import br.com.fiap.neohorizon.dto.PredictionInputDto;
import br.com.fiap.neohorizon.model.Prediction;
import br.com.fiap.neohorizon.service.PredictionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/predict")
public class PredictorController {

    private final PredictionService predictionService;

    public PredictorController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @GetMapping()
    public Prediction generatePrediction(@RequestBody PredictionInputDto predictionInputDTO){
        return predictionService.generatePrediction(predictionInputDTO.getPeriodo(), predictionInputDTO.getSpaceObjectTag());
    }

}
