/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * @author Alexis
 */
public class PrestamosActivosDao {

    // Variables globales de conexión al estilo del profesor
    Conexion_Registro conectar = new Conexion_Registro();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<Prestamos> listar() {
        List<Prestamos> listarp = new ArrayList<Prestamos>();
        
        // Query estructurado con los INNER JOINs necesarios para la Vista
        String sql = "SELECT "
                + "  p.id_prestamo, "
                + "  e.codigo_elemento, "
                + "  e.nombre_elemento, "
                + "  u.nombre, "
                + "  u.apellido, "
                + "  u.documento, "
                + "  p.fecha_inicio_prestamo, "
                + "  p.fecha_fin_devolucion, "
                + "  ee.nombre_estado_elemento, "
                + "  p.id_usuario, "
                + "  p.id_elemento "
                + "FROM prestamo p "
                + "INNER JOIN usuarios_sena u ON p.id_usuario = u.id_usuario "
                + "INNER JOIN elemento e ON p.id_elemento = e.id_elemento "
                + "INNER JOIN estado_elemento ee ON p.id_estado_elemento = ee.id_estado_elemento "
                + "ORDER BY p.fecha_inicio_prestamo DESC";
                
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Prestamos p = new Prestamos();
                
                // Mapeo campo por campo usando los setters del Modelo
                p.setIdPrestamo(rs.getInt("id_prestamo"));
                p.setCodigoElemento(rs.getInt("codigo_elemento"));
                p.setNombreElemento(rs.getString("nombre_elemento"));
                p.setNombreUsuario(rs.getString("nombre"));
                p.setApellidoUsuario(rs.getString("apellido"));
                p.setDocumentoUsuario(String.valueOf(rs.getInt("documento")));
                p.setFechaInicioPrestamo(rs.getTimestamp("fecha_inicio_prestamo"));
                p.setFechaFinDevolucion(rs.getTimestamp("fecha_fin_devolucion"));
                p.setNombreEstadoElemento(rs.getString("nombre_estado_elemento"));
                
                // IDs ocultos que el controlador usará al hacer doble clic para abrir la modal
                p.setIdUsuario(rs.getInt("id_usuario"));
                p.setIdElemento(rs.getInt("id_elemento"));

                listarp.add(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(),
                    "Error de consulta"
                    + e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
        
        return listarp;
    }
}