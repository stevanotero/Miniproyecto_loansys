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

public class Usuario_Dao implements Usuario_Crud_Buscar<Usuario_Elemento>,
        Usuario_Insert_soli<Usuario_Solicitud> {

    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Elemento_Dao.java - Modificar el método buscar()
    @Override
    public List<Usuario_Elemento> listar() {

        List<Usuario_Elemento> lista = new ArrayList<>();
        String sql = """
                SELECT e.id_elemento,
                       e.codigo_elemento,
                       e.nombre_elemento,
                       c.id_categoria,
                       c.nombre_categoria,
                       e.descripcion,
                       e.imagenes,
                       e.id_estado_elemento,
                       ee.nombre_estado_elemento
                FROM elemento e
                INNER JOIN categoria_elemento c ON e.id_categoria = c.id_categoria
                INNER JOIN estado_elemento ee ON e.id_estado_elemento = ee.id_estado_elemento
                WHERE e.id_estado_elemento = 1
                ORDER BY e.codigo_elemento
                """;
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario_Elemento e = new Usuario_Elemento();
                e.setId_elemento(rs.getInt("id_elemento"));
                e.setCodigo_elemento(rs.getInt("codigo_elemento"));
                e.setNombre_elemento(rs.getString("nombre_elemento"));
                e.setId_categoria(rs.getInt("id_categoria"));
                e.setCategoria_nombre(rs.getString("nombre_categoria"));
                e.setDescripcion(rs.getString("descripcion"));
                e.setImagen(rs.getString("imagenes"));
                e.setId_estado_elemento(rs.getInt("id_estado_elemento"));
                e.setEstado_nombre(rs.getString("nombre_estado_elemento"));
                lista.add(e);
            }
        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, "Error en consulta: " + a.toString(),
                    "Error de consulta", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    public Usuario_Elemento obtenerPrimerElemento() {
        String sql = """
                SELECT e.id_elemento,
                       e.codigo_elemento,
                       e.nombre_elemento,
                       c.id_categoria,
                       c.nombre_categoria,
                       e.descripcion,
                       e.imagenes,
                       e.id_estado_elemento,
                       ee.nombre_estado_elemento
                FROM elemento e
                INNER JOIN categoria_elemento c ON e.id_categoria = c.id_categoria
                INNER JOIN estado_elemento ee ON e.id_estado_elemento = ee.id_estado_elemento
                WHERE e.id_estado_elemento = 1
                LIMIT 1
                """;
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                Usuario_Elemento e = new Usuario_Elemento();
                e.setId_elemento(rs.getInt("id_elemento"));
                e.setCodigo_elemento(rs.getInt("codigo_elemento"));
                e.setNombre_elemento(rs.getString("nombre_elemento"));
                e.setId_categoria(rs.getInt("id_categoria"));
                e.setCategoria_nombre(rs.getString("nombre_categoria"));
                e.setDescripcion(rs.getString("descripcion"));
                e.setImagen(rs.getString("imagenes"));
                e.setId_estado_elemento(rs.getInt("id_estado_elemento"));
                e.setEstado_nombre(rs.getString("nombre_estado_elemento"));
                return e;
            }
        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, "Error al obtener el primer elemento: " + a.toString(),
                    "Error de consulta", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    
    @Override
    public int setAgregar(Usuario_Solicitud sr) {
        int r;
        String sql = "INSERT INTO solicitudes_usuario VALUES(?,?,?,?,?,?,?)";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, sr.getId_solicitud());
            ps.setInt(2, sr.getId_usuario());
            ps.setString(3, sr.getNombre());
            ps.setString(4, sr.getApellido());
            ps.setInt(5, sr.getDocumento());
            ps.setInt(6, sr.getId_elemento());
            ps.setTime(7, java.sql.Time.valueOf(sr.getFecha_envio()));

            r = ps.executeUpdate();
            return r;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(),
                    "Error de Inser"
                    + e.getMessage(), JOptionPane.ERROR_MESSAGE);
            return 0;
        } finally {
            if (con != null) {
                try {
                    con.close();
                    ps.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            }
        }

    }

}


