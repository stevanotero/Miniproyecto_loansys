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
public class DevolucionDao {

    Conexion_Registro conectar = new Conexion_Registro();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<Devolucion> listarDevoluciones() {
        List<Devolucion> lista = new ArrayList<>();
        String sql = "SELECT "
                + "  d.id_devolucion, "
                + "  d.id_prestamo, "
                + "  e.nombre_elemento, "
                + "  d.fecha_inicio_prestamo, "
                + "  d.fecha_devolucion, "
                + "  d.observaciones "
                + "FROM devolucion d "
                + "LEFT JOIN elemento e ON d.id_elemento = e.id_elemento "
                + "ORDER BY d.fecha_devolucion DESC";

        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Devolucion dev = new Devolucion();
                dev.setIdDevolucion(rs.getInt("id_devolucion"));
                dev.setIdPrestamo(rs.getInt("id_prestamo"));
                dev.setNombreElemento(rs.getString("nombre_elemento"));
                dev.setFechaInicioPrestamo(rs.getTimestamp("fecha_inicio_prestamo"));
                dev.setFechaDevolucion(rs.getTimestamp("fecha_devolucion"));
                dev.setObservaciones(rs.getString("observaciones"));

                lista.add(dev);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar devoluciones: " + e.getMessage(),
                    "Error SQL", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {}
        }
        return lista;
    }
}