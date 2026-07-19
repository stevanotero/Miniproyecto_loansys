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

        // se agrego la consulta de los elemetos por id
    }

    public Usuario_Elemento mostrarFila(int id_elemento) {
        Usuario_Elemento e = null;
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
                 WHERE e.id_elemento = ?
                 ORDER BY e.id_elemento ASC
                 """;
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_elemento);
            rs = ps.executeQuery();

            if (rs.next()) {
                e = new Usuario_Elemento();
                e.setId_elemento(rs.getInt("id_elemento"));
                e.setCodigo_elemento(rs.getInt("codigo_elemento"));
                e.setNombre_elemento(rs.getString("nombre_elemento"));
                e.setId_categoria(rs.getInt("id_categoria"));
                e.setCategoria_nombre(rs.getString("nombre_categoria"));
                e.setDescripcion(rs.getString("descripcion"));
                e.setImagen(rs.getString("imagenes"));
                e.setId_estado_elemento(rs.getInt("id_estado_elemento"));
                e.setEstado_nombre(rs.getString("nombre_estado_elemento"));
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return e; // ← retorna el objeto
    }

    public Usuario_Model validarLogin2(int documento, String contraseña) {
    String sql = "SELECT u.id_usuario, u.nombre, u.id_rol, r.nombre_rol "
            + "FROM login_de_usuarios l "
            + "INNER JOIN usuarios_sena u ON l.id_usuario = u.id_usuario "
            + "INNER JOIN roles r ON u.id_rol = r.id_rol "
            + "WHERE u.documento = ? AND l.contraseña = ?";
    try {
        con = conectar.getConection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, documento);
        ps.setString(2, contraseña);
        rs = ps.executeQuery();

        if (rs.next()) {
            Usuario_Model u = new Usuario_Model();
            u.setId_usuario(rs.getInt("id_usuario"));
            u.setNombre(rs.getString("nombre"));
            u.setId_rol(rs.getInt("id_rol"));
            u.setNombre_rol(rs.getString("nombre_rol"));
            return u; // login correcto, con todos los datos
        }
        return null; // contraseña incorrecta o no existe

    } catch (Exception e) {
        System.out.println("Error en validarLogin: " + e.getMessage());
        return null;
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e) {
        }
    }
}

}
