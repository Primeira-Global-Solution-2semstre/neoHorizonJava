package br.com.fiap.neohorizon.controller;

import br.com.fiap.neohorizon.assembler.SpaceObjetoAssembler;
import br.com.fiap.neohorizon.model.SpaceObjeto;
import br.com.fiap.neohorizon.model.SpaceObjetoModel;
import br.com.fiap.neohorizon.repository.ObjetoRepository;
import br.com.fiap.neohorizon.service.ObjetoService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/objeto")
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
    public EntityModel<SpaceObjeto> getObjeto(@RequestParam Long id){
        SpaceObjeto spaceObjeto = objetoService.getObjeto(id);
        return assembler.toModel(spaceObjeto);
    }

    @PostMapping()
    public EntityModel<SpaceObjeto> addObjeto(@RequestBody SpaceObjeto objeto){
        return assembler.toModel(
                objetoService.postObjeto(objeto)
        );
    }

    @PutMapping()
    public EntityModel<SpaceObjeto> updateObjeto(@RequestBody SpaceObjeto objeto)
            throws ClassNotFoundException {

        return assembler.toModel(
                objetoService.putObjeto(objeto)
        );
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteObjeto(@RequestParam Long id){
        objetoService.deleteObjeto(id);
        return ResponseEntity.noContent().build();
    }
}

