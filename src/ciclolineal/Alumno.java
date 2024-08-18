package ciclolineal;

import java.time.LocalDate;
import java.util.Objects;

public class Alumno {
    private int id;
    private String nombre;
    private LocalDate fecnac;
    private boolean activo;

    // Constructor con verificaciones
    public Alumno(int id, String nombre, LocalDate fecnac, boolean activo) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser positivo.");
        if (nombre == null || nombre.isEmpty()) throw new IllegalArgumentException("Nombre no puede ser vacío.");
        if (fecnac == null) throw new IllegalArgumentException("Fecha de nacimiento no puede ser nula.");
        this.id = id;
        this.nombre = nombre;
        this.fecnac = fecnac;
        this.activo = activo;
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

    public LocalDate getFecnac() {
        return fecnac;
    }

    public void setFecnac(LocalDate fecnac) {
        if (fecnac == null) throw new IllegalArgumentException("Fecha de nacimiento no puede ser nula.");
        this.fecnac = fecnac;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fecnac=" + fecnac +
                ", activo=" + activo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return id == alumno.id &&
                activo == alumno.activo &&
                Objects.equals(nombre, alumno.nombre) &&
                Objects.equals(fecnac, alumno.fecnac);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, fecnac, activo);
    }
}
