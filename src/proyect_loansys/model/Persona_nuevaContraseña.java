/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

/**
 *
 * @author Alexis
 */
public class Persona_nuevaContraseña {

    private String contraseña;
    private String confirmar_Contraseña;
    private String correo;

    public Persona_nuevaContraseña(String contraseña, String confirmar_Contraseña, String correo) {
        this.contraseña = contraseña;
        this.confirmar_Contraseña = confirmar_Contraseña;
        this.correo = correo;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setConfirmar_Contraseña(String confirmar_Contraseña) {
        this.confirmar_Contraseña = confirmar_Contraseña;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getConfirmar_Contraseña() {
        return confirmar_Contraseña;
    }

    public String getCorreo() {
        return correo;
    }    
}


