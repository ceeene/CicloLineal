

package AccesoADatos;

import java.sql.Connection;
import ciclolineal.Profesor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorData {
    private Connection con= null;

    public ProfesorData() {
       
         con = Conexion.getConexion();
    }

    

        // Alta de un nuevo profesor
    public void altaProfesor(int idProfesor, String nombre, String domicilio, String correo) {
        if (idProfesor <= 0) throw new IllegalArgumentException("ID del profesor debe ser positivo.");
        if (nombre == null || nombre.isEmpty()) throw new IllegalArgumentException("Nombre no puede ser vacío.");
        if (domicilio == null || domicilio.isEmpty()) throw new IllegalArgumentException("Domicilio no puede ser vacío.");
        if (correo == null || correo.isEmpty() || !correo.contains("@")) throw new IllegalArgumentException("Correo debe ser válido.");

        String sql = "INSERT INTO Profesor (idProfesor, nombre, domicilio, correo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProfesor);
            ps.setString(2, nombre);
            ps.setString(3, domicilio);
            ps.setString(4, correo);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }

    // Baja de un profesor
    public void bajaProfesor(int idProfesor) {
        if (idProfesor <= 0) throw new IllegalArgumentException("ID del profesor debe ser positivo.");

        String sql = "DELETE FROM Profesor WHERE idProfesor = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProfesor);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }

    // Modificación de un profesor
    public void modificarProfesor(int idProfesor, String nombre, String domicilio, String correo) {
        if (idProfesor <= 0) throw new IllegalArgumentException("ID del profesor debe ser positivo.");
        if (nombre == null || nombre.isEmpty()) throw new IllegalArgumentException("Nombre no puede ser vacío.");
        if (domicilio == null || domicilio.isEmpty()) throw new IllegalArgumentException("Domicilio no puede ser vacío.");
        if (correo == null || correo.isEmpty() || !correo.contains("@")) throw new IllegalArgumentException("Correo debe ser válido.");

        String sql = "UPDATE Profesor SET nombre = ?, domicilio = ?, correo = ? WHERE idProfesor = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, domicilio);
            ps.setString(3, correo);
            ps.setInt(4, idProfesor);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }

    // Consulta de un profesor por ID
    public Profesor consultarProfesor(int idProfesor) {
        if (idProfesor <= 0) throw new IllegalArgumentException("ID del profesor debe ser positivo.");

        String sql = "SELECT * FROM Profesor WHERE idProfesor = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProfesor);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String domicilio = rs.getString("domicilio");
                String correo = rs.getString("correo");

                return new Profesor(idProfesor, nombre, domicilio, correo);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return null;
    }

    // Listar todos los profesores
    public List<Profesor> listarProfesores() {
        List<Profesor> profesores = new ArrayList<>();
        String sql = "SELECT * FROM Profesor";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int idProfesor = rs.getInt("idProfesor");
                String nombre = rs.getString("nombre");
                String domicilio = rs.getString("domicilio");
                String correo = rs.getString("correo");

                profesores.add(new Profesor(idProfesor, nombre, domicilio, correo));
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return profesores;
    }
}
