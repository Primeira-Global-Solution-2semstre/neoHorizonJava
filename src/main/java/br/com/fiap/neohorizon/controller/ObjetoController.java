package br.com.fiap.neohorizon.controller;

import br.com.fiap.neohorizon.model.SpaceObjeto;
import br.com.fiap.neohorizon.repository.ObjetoRepository;
import br.com.fiap.neohorizon.service.ObjetoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/objeto")
public class ObjetoController {

    private ObjetoService objetoService;

    public ObjetoController(ObjetoService objetoService){
        this.objetoService = objetoService;
    }

    @GetMapping()
    public SpaceObjeto getObjeto(@RequestParam Long id){
        return objetoService.getObjeto(id);
    }

    @PutMapping()
    public SpaceObjeto updateObjeto(@RequestBody SpaceObjeto objeto) throws ClassNotFoundException {
        return objetoService.putObjeto(objeto);
    }

    @PostMapping()
    public SpaceObjeto addObjeto(@RequestBody SpaceObjeto objeto){
        return objetoService.postObjeto(objeto);
    }

    @DeleteMapping()
    public void deleteObjeto(@RequestParam Long id){
        objetoService.deleteObjeto(id);
    }

}

