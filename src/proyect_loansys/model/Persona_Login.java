/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

/**
 *
 * @author Alexis
 */
public class Persona_Login {
   private int documento;
    private String contraseña;
    
     public Persona_Login(int documento, String contraseña) {
        this.documento = documento;
        this.contraseña = contraseña;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getDocumento() {
        return documento;
    }
    
    public String getContraseña() {
        return contraseña;
    }
}