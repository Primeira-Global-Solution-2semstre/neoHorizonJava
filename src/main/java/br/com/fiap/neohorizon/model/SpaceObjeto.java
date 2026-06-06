package br.com.fiap.neohorizon.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class SpaceObjeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    double mass;

    @ElementCollection
    List<String> tags = new ArrayList<>();

    @Embedded
    Vector3D position;      // km

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "x", column = @Column(name = "velocity_x")),
        @AttributeOverride(name = "y", column = @Column(name = "velocity_y")),
        @AttributeOverride(name = "z", column = @Column(name = "velocity_z"))
    })
    Vector3D velocity;      // km/s

    @Embedded

    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "acceleration_x")),
            @AttributeOverride(name = "y", column = @Column(name = "acceleration_y")),
            @AttributeOverride(name = "z", column = @Column(name = "acceleration_z"))
    })
    Vector3D acceleration;  // km/s²

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public Vector3D getPosition() {
        return position;
    }

    public void setPosition(Vector3D position) {
        this.position = position;
    }

    public Vector3D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector3D velocity) {
        this.velocity = velocity;
    }

    public Vector3D getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector3D acceleration) {
        this.acceleration = acceleration;
    }
}
