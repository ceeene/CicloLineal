
package ciclolineal;

import java.util.Objects;

public class Materia {
    private int id;
    private String nombre;
    private Profesor profesor;

    // Constructor con verificaciones
    public Materia(int id, String nombre, Profesor profesor) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser positivo.");
        if (nombre == null || nombre.isEmpty()) throw new IllegalArgumentException("Nombre no puede ser vacío.");
        if (profesor == null) throw new IllegalArgumentException("Profesor no puede ser nulo.");
        this.id = id;
        this.nombre = nombre;
        this.profesor = profesor;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser positivo.");
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) throw new IllegalArgumentException("Nombre no puede ser vacío.");
        this.nombre = nombre;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        if (profesor == null) throw new IllegalArgumentException("Profesor no puede ser nulo.");
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "Materia{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", profesor=" + profesor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Materia materia = (Materia) o;
        return id == materia.id &&
                Objects.equals(nombre, materia.nombre) &&
                Objects.equals(profesor, materia.profesor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, profesor);
    }
}
