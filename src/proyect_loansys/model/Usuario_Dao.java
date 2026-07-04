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
 *
 * @author Miguel
 */
public class Usuario_Dao {

    public List<Usuario_Elemento> buscar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public class Elemento_Dao implements Usuario_Crud_Buscar<Usuario_Elemento>,
            Usuario_Insert_soli<Usuario_Solicitud> {

        Conexion conectar = new Conexion();
        Connection con;

        PreparedStatement ps;
        ResultSet rs;

        @Override
        public List buscar() {
            List<Usuario_Elemento> listare = new ArrayList<Usuario_Elemento>();
            String sql = "select * from elemento";
            try {
                con = conectar.getConection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Usuario_Elemento e = new Usuario_Elemento();
                    e.setId_elemento(rs.getInt(1));
                    e.setCodigo_elemento(rs.getInt(2));
                    e.setNombre_elemento(rs.getString(3));
                    e.setId_categoria(rs.getInt(4));
                    e.setDescripcion(rs.getString(5));
                    e.setImagen(rs.getString(6));
                    e.setId_estado_elemento(rs.getInt(7));
                    listare.add(e);
                }
            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, a.toString(),
                        "Error de consulta"
                        + a.getMessage(), JOptionPane.ERROR_MESSAGE);
            }
            return listare;

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
}
