package br.com.fiap.neohorizon.service;

import br.com.fiap.neohorizon.dto.RecommendationOutputDto;
import br.com.fiap.neohorizon.model.SpaceObjeto;
import br.com.fiap.neohorizon.repository.ObjetoRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class RecommendationService {

    private final ObjetoRepository objetoRepository;

    public RecommendationService(ObjetoRepository objetoRepository) {
        this.objetoRepository = objetoRepository;
    }

    public RecommendationOutputDto generateRecomendation(Float altitudeDesejada) {

        List<SpaceObjeto> objetos = objetoRepository.findAll();

        int objetosProximos = 0;

        // Margin of ±50 km around the desired altitude
        double margem = 50.0;

        for (SpaceObjeto objeto : objetos) {

            double altitudeObjeto = objeto.getPosition().getZ();

            if (Math.abs(altitudeObjeto - altitudeDesejada) <= margem) {
                objetosProximos++;
            }
        }

        RecommendationOutputDto dto = new RecommendationOutputDto();

        // Risk score from 0.0 to 1.0
        float riskScore = Math.min(objetosProximos / 20.0f, 1.0f);

        dto.setRiskScore(riskScore);

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_MONTH, objetosProximos);

        dto.setRecomemendedLaunchDate(calendar.getTime());

        return dto;
    }
}
