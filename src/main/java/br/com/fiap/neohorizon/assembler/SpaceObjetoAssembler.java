package br.com.fiap.neohorizon.assembler;

import br.com.fiap.neohorizon.controller.ObjetoController;
import br.com.fiap.neohorizon.model.SpaceObjeto;
import br.com.fiap.neohorizon.model.SpaceObjetoModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SpaceObjetoAssembler implements RepresentationModelAssembler<SpaceObjeto, EntityModel<SpaceObjeto>> {

    @Override
    public EntityModel<SpaceObjeto> toModel(SpaceObjeto objeto) {

        try {
            return EntityModel.of(objeto,
                    linkTo(methodOn(ObjetoController.class)
                            .getObjeto(objeto.getId()))
                            .withSelfRel(),

                    linkTo(methodOn(ObjetoController.class)
                            .updateObjeto(objeto))
                            .withRel("update"),

                    linkTo(methodOn(ObjetoController.class)
                            .addObjeto(null))
                            .withRel("create"),

                    linkTo(methodOn(ObjetoController.class)
                            .deleteObjeto(objeto.getId())
                           ).withRel("delete")
            );
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
