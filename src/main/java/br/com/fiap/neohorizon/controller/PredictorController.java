package br.com.fiap.neohorizon.controller;

import br.com.fiap.neohorizon.assembler.PredictionAssembler;
import br.com.fiap.neohorizon.dto.PredictionInputDto;
import br.com.fiap.neohorizon.model.Prediction;
import br.com.fiap.neohorizon.service.PredictionService;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/predict")
public class PredictorController {

    private final PredictionAssembler predictionAssembler;
    private final PredictionService predictionService;

    public PredictorController(PredictionService predictionService, PredictionAssembler predictionAssembler) {
        this.predictionService = predictionService;
        this.predictionAssembler = predictionAssembler;
    }

    @PostMapping()
    public EntityModel<Prediction> generatePrediction(@RequestBody PredictionInputDto predictionInputDTO){
        Prediction prediction = predictionService.generatePrediction(predictionInputDTO.getPeriodo(), predictionInputDTO.getSpaceObjectTag());

        return predictionAssembler.toModel(prediction);
    }

    @GetMapping()
    public List<Prediction> findAll(){
        return predictionService.findAllPredictions();
    }
}
