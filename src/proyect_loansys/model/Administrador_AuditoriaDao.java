/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

/**
 *
 * @author juans
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class Administrador_AuditoriaDao {

    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;


    public int registrarAccion(Administrador_Auditoria auditoria) {

        String sql = "INSERT INTO auditoria_usuario(id_usuario, accion) VALUES(?, ?)";

        try {

            con = conectar.getConection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, auditoria.getIdUsuario());
            ps.setString(2, auditoria.getAccion());

            return ps.executeUpdate();

        } catch (Exception e) {

            System.out.println("Error al registrar auditoría: " + e);

        }

        return 0;
    }
   public List<Administrador_Auditoria> listar() {

    List<Administrador_Auditoria> lista = new ArrayList<>();

            String sql = "SELECT u.documento, "
            + "CONCAT(u.nombre,' ',u.apellido) AS nombre, "
            + "a.accion, "
            + "a.fecha_hora "
            + "FROM auditoria_usuario a "
            + "INNER JOIN usuarios_sena u ON a.id_usuario = u.id_usuario "
            + "ORDER BY a.fecha_hora DESC";

    try {

        con = conectar.getConection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {

            Administrador_Auditoria au = new Administrador_Auditoria();

            au.setDocumento(rs.getInt("documento"));
            au.setNombreCompleto(rs.getString("nombre"));
            au.setAccion(rs.getString("accion"));
            au.setFechaHora(rs.getTimestamp("fecha_hora"));

            lista.add(au);

        }

    } catch (Exception e) {

        System.out.println(e);

    }

    return lista;

}

}