package br.com.fiap.neohorizon.assembler;

import br.com.fiap.neohorizon.controller.PredictorController;
import br.com.fiap.neohorizon.dto.PredictionInputDto;
import br.com.fiap.neohorizon.model.Prediction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PredictionAssembler
        implements RepresentationModelAssembler<Prediction,
                                                EntityModel<Prediction>> {

    @Override
    public EntityModel<Prediction> toModel(Prediction prediction) {

        return EntityModel.of(prediction,
                linkTo(methodOn(PredictorController.class)
                        .findAll())
                        .withRel("all-predictions"));
    }
}
