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
 * @author juans
 */
public class Administrador_UsuarioDao implements Administrador_Crud_Usuario<Administrador_Usuario> {

    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public List listar() {

        List<Administrador_Usuario> lista = new ArrayList<>();

        String sql = """
            SELECT
                u.id_usuario,
                u.documento,
                u.nombre,
                u.apellido,
                u.id_rol,
                r.nombre_rol,
                u.id_estado,
                e.nombre_estado,
                u.id_tipo_documento,
                l.correo
            FROM usuarios_sena u
            INNER JOIN roles r
                ON u.id_rol = r.id_rol
            INNER JOIN estados e
                ON u.id_estado = e.id_estado
            INNER JOIN login_de_usuarios l
                ON u.id_usuario = l.id_usuario
            """;

        try {

            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                Administrador_Usuario u = new Administrador_Usuario();

                u.setIdUsuario(rs.getInt(1));
                u.setDocumento(rs.getInt(2));
                u.setNombre(rs.getString(3));
                u.setApellido(rs.getString(4));

                u.setIdRol(rs.getInt(5));
                u.setNombreRol(rs.getString(6));

                u.setIdEstado(rs.getInt(7));
                u.setNombreEstado(rs.getString(8));

                u.setIdTipoDocumento(rs.getInt(9));

                u.setCorreo(rs.getString(10));

                lista.add(u);

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.toString(), "Error de consulta " + e.getMessage(), JOptionPane.ERROR_MESSAGE);

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

        return lista;

    }
@Override
public int setAgragar(Administrador_Usuario u) {

    String sqlUsuario = "INSERT INTO usuarios_sena(documento,nombre,apellido,id_rol,id_estado,id_tipo_documento) VALUES(?,?,?,?,?,?)";

    String sqlLogin = "INSERT INTO login_de_usuarios(id_usuario,correo,contraseña,id_estado) VALUES((SELECT id_usuario FROM usuarios_sena WHERE documento=?),?,?,?)";

    try {

        con = conectar.getConection();
        con.setAutoCommit(false);

        ps = con.prepareStatement(sqlUsuario);

        ps.setInt(1, u.getDocumento());
        ps.setString(2, u.getNombre());
        ps.setString(3, u.getApellido());
        ps.setInt(4, u.getIdRol());
        ps.setInt(5, u.getIdEstado());
        ps.setInt(6, u.getIdTipoDocumento());

        ps.executeUpdate();

        ps.close();

        ps = con.prepareStatement(sqlLogin);

        ps.setInt(1, u.getDocumento());
        ps.setString(2, u.getCorreo());
        ps.setString(3, u.getContraseña());
        ps.setInt(4, u.getIdEstado());

        ps.executeUpdate();

        con.commit();

        return 1;

    } catch (Exception e) {

        try {
            if (con != null) {
                con.rollback();
            }
        } catch (Exception ex) {
        }

        JOptionPane.showMessageDialog(null, e);

        return 0;

    } finally {

        try {

            if (ps != null) ps.close();
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }

        } catch (Exception e) {
        }

    }

}
    @Override
    public int setActualizarEstado(Administrador_Usuario u) {
    
        String sql = "UPDATE usuarios_sena SET id_estado=? WHERE documento=?";

    try{

        con = conectar.getConection();
        ps = con.prepareStatement(sql);

        ps.setInt(1, u.getIdEstado());
        ps.setInt(2, u.getDocumento());

        return ps.executeUpdate();

    }catch(Exception e){

        JOptionPane.showMessageDialog(null,e);

        return 0;

    }finally{

        try{

            if(ps!=null) ps.close();
            if(con!=null) con.close();

        }catch(Exception e){}

    }

}

  @Override
public int setActualizarRol(Administrador_Usuario u) {

    String sql = "UPDATE usuarios_sena SET id_rol=? WHERE documento=?";

    try {

        con = conectar.getConection();
        ps = con.prepareStatement(sql);

        ps.setInt(1, u.getIdRol());
        ps.setInt(2, u.getDocumento());

        return ps.executeUpdate();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(null, e);

        return 0;

    } finally {

        try {

            if (ps != null) ps.close();
            if (con != null) con.close();

        } catch (Exception e) {
        }

    }

}

@Override
public int setActualizarCorreo(Administrador_Usuario u) {

    String sql = "UPDATE login_de_usuarios SET correo=? WHERE id_usuario=(SELECT id_usuario FROM usuarios_sena WHERE documento=?)";

    try {

        con = conectar.getConection();
        ps = con.prepareStatement(sql);

        ps.setString(1, u.getCorreo());
        ps.setInt(2, u.getDocumento());

        return ps.executeUpdate();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(null, e);

        return 0;

    } finally {

        try {

            if (ps != null) ps.close();
            if (con != null) con.close();

        } catch (Exception e) {
        }

    }

}


}
