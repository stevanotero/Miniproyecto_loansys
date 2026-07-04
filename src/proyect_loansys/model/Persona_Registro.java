/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

/**
 *
 * @author Alexis
 */

public class Persona_Registro {
    private int documento;
    private String correo;
    private String contraseña;
    private int id_estado;
    
     public Persona_Registro(int documento, String correo, String contraseña, int id_estado) {
        this.documento = documento;
        this.correo = correo;
        this.contraseña = contraseña;
        this.id_estado = id_estado;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    public void setId_Estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public int getDocumento() {
        return documento;
    }

    public String getCorreo() {
        return correo;
    }
    
    public String getContraseña() {
        return contraseña;
    }
    
    public int getId_Estado() {
        return id_estado;
    }
}
