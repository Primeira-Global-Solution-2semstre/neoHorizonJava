package br.com.fiap.neohorizon.repository;

import br.com.fiap.neohorizon.model.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredictionRepository extends JpaRepository<Prediction, Long> {
}
