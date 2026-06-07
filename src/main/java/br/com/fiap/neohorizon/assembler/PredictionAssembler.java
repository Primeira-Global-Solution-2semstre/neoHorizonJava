package br.com.fiap.neohorizon.assembler;

import br.com.fiap.neohorizon.controller.PredictorController;
import br.com.fiap.neohorizon.dto.PredictionInputDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PredictionAssembler implements RepresentationModelAssembler<PredictionInputDto, EntityModel<PredictionInputDto>> {

    @Override
    public EntityModel<PredictionInputDto> toModel(PredictionInputDto prediction) {

        try{
            return EntityModel.of(prediction,
                    linkTo(methodOn(PredictorController.class)
                            .generatePrediction(prediction))
                            .withRel("prever colisões"),
                    linkTo(methodOn(PredictorController.class)
                            .findAll())
                            .withRel("prever colisões"));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
