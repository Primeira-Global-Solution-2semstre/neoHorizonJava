package br.com.fiap.neohorizon.service;

import br.com.fiap.neohorizon.model.Prediction;
import br.com.fiap.neohorizon.model.SpaceObjeto;
import br.com.fiap.neohorizon.model.Vector3D;
import br.com.fiap.neohorizon.repository.ObjetoRepository;
import br.com.fiap.neohorizon.repository.PredictionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredictionService {

    private static final double COLLISION_DISTANCE = 67;
    private final ObjetoRepository objetoRepository;
    private final PredictionRepository predictionRepository;

    public PredictionService(ObjetoRepository objetoRepository, PredictionRepository predictionRepository) {
        this.objetoRepository = objetoRepository;
        this.predictionRepository = predictionRepository;
    }

    public Prediction generatePrediction(Long periodo, String spaceObjectTag) {
        return generatePrediction(periodo, spaceObjectTag, (long) 1);
    }

    public Prediction generatePrediction(Long periodo, String spaceObjectTag, Long increase) {
         Prediction prediction = updatePositions(periodo, spaceObjectTag, increase);
         predictionRepository.save(prediction);
         return prediction;
    }

    private Prediction calculateColisions(String spaceObjectTag){
        Prediction prediction = new Prediction();
        List<SpaceObjeto> spaceObjetoList = objetoRepository.findByTag(spaceObjectTag);
        for (int i = 0; i < spaceObjetoList.size(); i++) {

            SpaceObjeto obj1 = spaceObjetoList.get(i);

            for (int j = i + 1; j < spaceObjetoList.size(); j++) {

                SpaceObjeto obj2 = spaceObjetoList.get(j);

                double distance =
                        obj1.getPosition().distanceTo(obj2.getPosition());

                if (distance < COLLISION_DISTANCE) {

                    System.out.println(
                            "Colisão entre  "
                                    + obj1.getId()
                                    + " e "
                                    + obj2.getId()
                                    + " detectada!! "
                    );
                    prediction.setGoingToCollide(true);
                    prediction.addObjetosEnvolvidosNoImpacto(obj1);
                    prediction.addObjetosEnvolvidosNoImpacto(obj2);
                    return prediction;
                }
            }
        }
        return null;
    }

    private Prediction updatePositions(Long periodo, String spaceObjectTag, Long increase){
        Vector3D velocidade;
        Vector3D aceleracao;
        Vector3D posicao;

        List<SpaceObjeto> spaceObjetoList = objetoRepository.findByTag(spaceObjectTag);
        for(Long tempo = (long)0.0; tempo < periodo; tempo+=increase ){
            for(SpaceObjeto spaceObjeto : spaceObjetoList){
                velocidade = spaceObjeto.getVelocity();
                aceleracao = spaceObjeto.getAcceleration();

                velocidade = velocidade.add(aceleracao.multiply(tempo));

                spaceObjeto.setVelocity(velocidade);

                posicao = spaceObjeto.getPosition();
                posicao = posicao.add(velocidade.multiply(tempo));
                spaceObjeto.setPosition(posicao);
                objetoRepository.save(spaceObjeto);
            }
            Prediction prediction = calculateColisions(spaceObjectTag);
            if( prediction != null && prediction.isGoingToCollide()){
                prediction.setTimeUntilImpact(tempo);
                return prediction;
            }

        }
        Prediction predictionSegura = new Prediction();
        return predictionSegura;
    }

    public List<Prediction> findAllPredictions() {
        return predictionRepository.findAll();
    }
}
