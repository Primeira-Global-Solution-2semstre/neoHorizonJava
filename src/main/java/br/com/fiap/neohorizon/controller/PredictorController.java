package br.com.fiap.neohorizon.controller;

import br.com.fiap.neohorizon.dto.PredictionInputDto;
import br.com.fiap.neohorizon.model.Prediction;
import br.com.fiap.neohorizon.service.PredictionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/predict")
public class PredictorController {

    private final PredictionService predictionService;

    public PredictorController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping()
    public Prediction generatePrediction(@RequestBody PredictionInputDto predictionInputDTO){
        return predictionService.generatePrediction(predictionInputDTO.getPeriodo(), predictionInputDTO.getSpaceObjectTag());
    }

    @GetMapping()
    public List<Prediction> findAll(){
        return predictionService.findAllPredictions();
    }
}
