package br.com.fiap.neohorizon.service;

import br.com.fiap.neohorizon.model.SpaceObjeto;
import br.com.fiap.neohorizon.repository.ObjetoRepository;
import org.springframework.stereotype.Service;

@Service
public class ObjetoService {

    private final ObjetoRepository objetoRepository;

    public ObjetoService(ObjetoRepository objetoRepository) {
        this.objetoRepository = objetoRepository;
    }

    public SpaceObjeto getObjeto(Long id) {
        SpaceObjeto spaceObjeto = objetoRepository.getReferenceById(id);
        return spaceObjeto;
    }

    public SpaceObjeto putObjeto(SpaceObjeto novoObjeto) throws ClassNotFoundException {
        if(!objetoRepository.existsById(novoObjeto.getId())){
            throw new ClassNotFoundException("Não existe objeto para atualização");
        }
        return objetoRepository.save(novoObjeto);
    }

    public SpaceObjeto postObjeto(SpaceObjeto novoObjeto){
        return objetoRepository.save(novoObjeto);
    }

    public void deleteObjeto(Long id) {
        objetoRepository.deleteById(id);
    }
}
