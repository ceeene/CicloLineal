



package ciclolineal;


import java.util.Objects;

public class Profesor {
    private int idProfesor;
    private String nombre;
    private String domicilio;
    private String correo;

    // Constructor con verificaciones
    public Profesor(int idProfesor, String nombre, String domicilio, String correo) {
        if (idProfesor <= 0) throw new IllegalArgumentException("ID del profesor debe ser positivo.");
        if (nombre == null || nombre.isEmpty()) throw new IllegalArgumentException("Nombre no puede ser vacío.");
        if (domicilio == null || domicilio.isEmpty()) throw new IllegalArgumentException("Domicilio no puede ser vacío.");
        if (correo == null || correo.isEmpty() || !correo.contains("@")) throw new IllegalArgumentException("Correo debe ser válido.");
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.correo = correo;
    }

    // Getters y Setters
    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        if (idProfesor <= 0) throw new IllegalArgumentException("ID del profesor debe ser positivo.");
        this.idProfesor = idProfesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) throw new IllegalArgumentException("Nombre no puede ser vacío.");
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        if (domicilio == null || domicilio.isEmpty()) throw new IllegalArgumentException("Domicilio no puede ser vacío.");
        this.domicilio = domicilio;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null || correo.isEmpty() || !correo.contains("@")) throw new IllegalArgumentException("Correo debe ser válido.");
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "idProfesor=" + idProfesor +
                ", nombre='" + nombre + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profesor profesor = (Profesor) o;
        return idProfesor == profesor.idProfesor &&
                Objects.equals(nombre, profesor.nombre) &&
                Objects.equals(domicilio, profesor.domicilio) &&
                Objects.equals(correo, profesor.correo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProfesor, nombre, domicilio, correo);
    }
}
