

package AccesoADatos;



import ciclolineal.Profesor;
import ciclolineal.Materia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MateriaData {
    private Connection con= null;

    public MateriaData() {
        con = Conexion.getConexion();
    }

    // Alta de una nueva materia
    public void altaMateria(String nombre, Profesor profesor) {
        if (nombre == null || nombre.isEmpty()) throw new IllegalArgumentException("Nombre no puede ser vacío.");
        if (profesor == null) throw new IllegalArgumentException("Profesor no puede ser nulo.");

        String sql = "INSERT INTO Materia (nombre, idProfesor) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nombre);
            ps.setInt(2, profesor.getIdProfesor());
            ps.executeUpdate();

            // Obtener el ID generado
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                // Crear un nuevo objeto Materia con el ID generado
                Materia materia = new Materia(id, nombre, profesor);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }

    // Baja de una materia
    public void bajaMateria(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser positivo.");

        String sql = "DELETE FROM Materia WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }

    // Modificación de una materia
    public void modificarMateria(int id, String nombre, Profesor profesor) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser positivo.");
        if (nombre == null || nombre.isEmpty()) throw new IllegalArgumentException("Nombre no puede ser vacío.");
        if (profesor == null) throw new IllegalArgumentException("Profesor no puede ser nulo.");

        String sql = "UPDATE Materia SET nombre = ?, idProfesor = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setInt(2, profesor.getIdProfesor());
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }

    // Consulta de una materia por ID
    public Materia consultarMateria(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser positivo.");

        String sql = "SELECT * FROM Materia WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                int idProfesor = rs.getInt("idProfesor");

                // Obtener el Profesor desde la base de datos
                Profesor profesor = getProfesorPorId(idProfesor);

                return new Materia(id, nombre, profesor);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
        return null;
    }

    // Listar todas las materias
    public List<Materia> listarMaterias() {
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT * FROM Materia";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int idProfesor = rs.getInt("idProfesor");

                // Obtener el Profesor desde la base de datos
                Profesor profesor = getProfesorPorId(idProfesor);

                materias.add(new Materia(id, nombre, profesor));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
        return materias;
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

