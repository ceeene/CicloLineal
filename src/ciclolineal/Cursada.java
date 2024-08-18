
package ciclolineal;

import ciclolineal.Materia;

import ciclolineal.Alumno;
import java.util.Objects;

public class Cursada {
    private int id;
    private Alumno alumno;
    private Materia materia;
    private int nota;

    // Constructor con verificaciones
    public Cursada(int id, Alumno alumno, Materia materia, int nota) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser positivo.");
        if (alumno == null) throw new IllegalArgumentException("Alumno no puede ser nulo.");
        if (materia == null) throw new IllegalArgumentException("Materia no puede ser nula.");
        if (nota < 0 || nota > 10) throw new IllegalArgumentException("Nota debe estar entre 0 y 10.");
        this.id = id;
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser positivo.");
        this.id = id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        if (alumno == null) throw new IllegalArgumentException("Alumno no puede ser nulo.");
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        if (materia == null) throw new IllegalArgumentException("Materia no puede ser nula.");
        this.materia = materia;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        if (nota < 0 || nota > 10) throw new IllegalArgumentException("Nota debe estar entre 0 y 10.");
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Cursada{" +
                "id=" + id +
                ", alumno=" + alumno +
                ", materia=" + materia +
                ", nota=" + nota +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cursada cursada = (Cursada) o;
        return id == cursada.id &&
                nota == cursada.nota &&
                Objects.equals(alumno, cursada.alumno) &&
                Objects.equals(materia, cursada.materia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alumno, materia, nota);
    }
}
