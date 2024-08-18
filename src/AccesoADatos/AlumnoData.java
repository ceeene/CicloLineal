package AccesoADatos;


import ciclolineal.Alumno;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlumnoData {
    private Connection con= null;

    public AlumnoData(Connection conexion) {
        con = Conexion.getConexion();
    }

    // Alta de un nuevo alumno
    public void altaAlumno(int id, String nombre, LocalDate fecnac, boolean activo) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser positivo.");
        if (nombre == null || nombre.isEmpty()) throw new IllegalArgumentException("Nombre no puede ser vacío.");
        if (fecnac == null) throw new IllegalArgumentException("Fecha de nacimiento no puede ser nula.");

        String sql = "INSERT INTO Alumno (id, nombre, fecnac, activo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setDate(3, Date.valueOf(fecnac));
            ps.setBoolean(4, activo);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    // Baja de un alumno
    public void bajaAlumno(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser positivo.");

        String sql = "DELETE FROM Alumno WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    // Modificacion de un alumno
    public void modificarAlumno(int id, String nombre, LocalDate fecnac, boolean activo) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser positivo.");
        if (nombre == null || nombre.isEmpty()) throw new IllegalArgumentException("Nombre no puede ser vacío.");
        if (fecnac == null) throw new IllegalArgumentException("Fecha de nacimiento no puede ser nula.");

        String sql = "UPDATE Alumno SET nombre = ?, fecnac = ?, activo = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setDate(2, Date.valueOf(fecnac));
            ps.setBoolean(3, activo);
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    // Consulta de un alumno por ID
    public Alumno consultarAlumno(int id) {
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

    // Listar todos los alumnos
    public List<Alumno> listarAlumnos() {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM Alumno";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                LocalDate fecnac = rs.getDate("fecnac").toLocalDate();
                boolean activo = rs.getBoolean("activo");
                alumnos.add(new Alumno(id, nombre, fecnac, activo));
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return alumnos;
    }
}
