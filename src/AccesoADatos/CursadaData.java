
package AccesoADatos;
import ciclolineal.Materia;
import ciclolineal.Alumno;
import ciclolineal.Cursada;
import ciclolineal.Profesor;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CursadaData {
    private Connection con= null;

    public CursadaData(Connection conexion) {
        con = Conexion.getConexion();
    }

    // Alta de una nueva cursada
    public void altaCursada(int id, Alumno alumno, Materia materia, int nota) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser positivo.");
        if (alumno == null) throw new IllegalArgumentException("Alumno no puede ser nulo.");
        if (materia == null) throw new IllegalArgumentException("Materia no puede ser nula.");
        if (nota < 0 || nota > 10) throw new IllegalArgumentException("Nota debe estar entre 0 y 10.");

        String sql = "INSERT INTO Cursada (id, idAlumno, idMateria, nota) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setInt(2, alumno.getId());
            ps.setInt(3, materia.getId());
            ps.setInt(4, nota);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    // Baja de una cursada
    public void bajaCursada(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser positivo.");

        String sql = "DELETE FROM Cursada WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    // Modificación de una cursada
    public void modificarCursada(int id, Alumno alumno, Materia materia, int nota) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser positivo.");
        if (alumno == null) throw new IllegalArgumentException("Alumno no puede ser nulo.");
        if (materia == null) throw new IllegalArgumentException("Materia no puede ser nula.");
        if (nota < 0 || nota > 10) throw new IllegalArgumentException("Nota debe estar entre 0 y 10.");

        String sql = "UPDATE Cursada SET idAlumno = ?, idMateria = ?, nota = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, alumno.getId());
            ps.setInt(2, materia.getId());
            ps.setInt(3, nota);
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    // Consulta de una cursada por ID
    public Cursada consultarCursada(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser positivo.");

        String sql = "SELECT * FROM Cursada WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idAlumno = rs.getInt("idAlumno");
                int idMateria = rs.getInt("idMateria");
                int nota = rs.getInt("nota");

                // Obtener Alumno y Materia desde sus respectivas tablas
                Alumno alumno = getAlumnoById(idAlumno);
                Materia materia = getMateriaById(idMateria);

                return new Cursada(id, alumno, materia, nota);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return null;
    }

    // Listar todas las cursadas
    public List<Cursada> listarCursadas() {
        List<Cursada> cursadas = new ArrayList<>();
        String sql = "SELECT * FROM Cursada";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int idAlumno = rs.getInt("idAlumno");
                int idMateria = rs.getInt("idMateria");
                int nota = rs.getInt("nota");

                // Obtener Alumno y Materia desde sus respectivas tablas
                Alumno alumno = getAlumnoById(idAlumno);
                Materia materia = getMateriaById(idMateria);

                cursadas.add(new Cursada(id, alumno, materia, nota));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
        return cursadas;
    }
    
    // Métodos auxiliares para obtener Alumno y Materia por ID
    private Alumno getAlumnoById(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser positivo.");

        String sql = "SELECT * FROM Alumno WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                LocalDate fecnac = rs.getDate("fecnac").toLocalDate();
                boolean activo = rs.getBoolean("activo");
                return new Alumno(id, nombre, fecnac, activo);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return null;
    }

    private Materia getMateriaById(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser positivo.");

        String sql = "SELECT * FROM Materia WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                int idProfesor = rs.getInt("idProfesor");

                // Obtener el objeto Profesor usando el idProfesor
                Profesor profesor = getProfesorPorId(idProfesor);

                return new Materia(id, nombre, profesor);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return null;
    }

    private Profesor getProfesorPorId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser positivo.");

        String sql = "SELECT * FROM Profesor WHERE idProfesor = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String domicilio = rs.getString("domicilio");
                String correo = rs.getString("correo");

                return new Profesor(id, nombre, domicilio, correo);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return null;
    }
}



    
