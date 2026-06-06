package br.com.fiap.neohorizon.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Vector3D {
    private double x;
    private double y;
    private double z;

    public Vector3D(double x, double y, double z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D() {

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Vector3D add(Vector3D other) {
        return new Vector3D(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Vector3D subtract(Vector3D other) {
        return new Vector3D(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public double dot(Vector3D other) {
        return (this.x * other.x) + (this.y * other.y) + (this.z * other.z);
    }

    public Vector3D multiply(double scalar) {
        return new Vector3D(
                this.x * scalar,
                this.y * scalar,
                this.z * scalar
        );
    }

    public Vector3D cross(Vector3D other) {
        return new Vector3D(
                (this.y * other.z) - (this.z * other.y),
                (this.z * other.x) - (this.x * other.z),
                (this.x * other.y) - (this.y * other.x)
        );
    }

    public double magnitude() {
        return Math.sqrt((x * x) + (y * y) + (z * z));
    }

    public double distanceTo(Vector3D other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double dz = this.z - other.z;

        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
}
