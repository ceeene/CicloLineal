/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ciclolineal;

import AccesoADatos.Conexion;
import AccesoADatos.ProfesorData;
import java.sql.Connection;

public class Main {
    

    public static void main(String[] args) {
        // Establecer conexión
        
        Connection con= Conexion.getConexion();
 // Crear instancia de ProfesorData con la conexión
            ProfesorData profesorData = new ProfesorData();
        
        
            

            // Cargar datos en la tabla Profesor
            profesorData.altaProfesor(1, "Juan Pérez", "Calle Falsa 123", "juan.perez@example.com");
            profesorData.altaProfesor(2, "Ana Gómez", "Avenida Siempre Viva 456", "ana.gomez@example.com");

            // Imprimir mensaje de éxito
            System.out.println("Datos cargados exitosamente.");


    }
}

