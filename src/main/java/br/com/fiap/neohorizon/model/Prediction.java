package br.com.fiap.neohorizon.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    boolean isGoingToCollide;
    Long timeUntilImpact;

    @ManyToMany
    @JoinTable(
            name = "prediction_space_objetos",
            joinColumns = @JoinColumn(name = "prediction_id"),
            inverseJoinColumns = @JoinColumn(name = "space_objeto_id")
    )
    List<SpaceObjeto> objetosEnvolvidosNoImpacto = new ArrayList<>();

    public Prediction() {
        this.timeUntilImpact = 0L;
        this.isGoingToCollide = false;
    }

    public boolean isGoingToCollide() {
        return isGoingToCollide;
    }

    public void setGoingToCollide(boolean goingToCollide) {
        isGoingToCollide = goingToCollide;
    }

    public long getTimeUntilImpact() {
        return timeUntilImpact;
    }

    public void setTimeUntilImpact(long timeUntilImpact) {
        this.timeUntilImpact = timeUntilImpact;
    }

    public List<SpaceObjeto> getObjetosEnvolvidosNoImpacto() {
        return objetosEnvolvidosNoImpacto;
    }

    public void setObjetosEnvolvidosNoImpacto(SpaceObjeto[] objetosEnvolvidosNoImpacto) {
        this.objetosEnvolvidosNoImpacto = List.of(objetosEnvolvidosNoImpacto);
    }

    public void addObjetosEnvolvidosNoImpacto(SpaceObjeto objeto) {
        this.objetosEnvolvidosNoImpacto.add(objeto);
    }
}
