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
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido fornecido");
        }

        return objetoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Objeto não encontrado com ID: " + id));
    }

    public SpaceObjeto putObjeto(SpaceObjeto novoObjeto) throws ClassNotFoundException {
        if (novoObjeto == null || novoObjeto.getId() == null) {
            throw new IllegalArgumentException("Dados de entrada inválidos");
        }

        if (!objetoRepository.existsById(novoObjeto.getId())) {
            throw new ClassNotFoundException("Não existe objeto para atualização com ID: " + novoObjeto.getId());
        }

        return objetoRepository.save(novoObjeto);
    }

    public SpaceObjeto postObjeto(SpaceObjeto novoObjeto) {
        if (novoObjeto == null) {
            throw new IllegalArgumentException("Dados de entrada inválidos");
        }

        // Check for duplicate - assuming you have a method to check by unique fields
        // This is a placeholder - adjust based on your actual unique constraints
        if (novoObjeto.getId() != null && objetoRepository.existsById(novoObjeto.getId())) {
            throw new IllegalStateException("Objeto já existe no sistema com ID: " + novoObjeto.getId());
        }

        return objetoRepository.save(novoObjeto);
    }

    public void deleteObjeto(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido fornecido para exclusão");
        }

        // Check if object exists before deletion
        if (!objetoRepository.existsById(id)) {
            throw new IllegalArgumentException("Objeto não encontrado com ID: " + id);
        }

        objetoRepository.deleteById(id);
    }

}
