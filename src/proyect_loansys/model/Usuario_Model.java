/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

public class Usuario_Model {
    private int id_usuario;
    private String  nombre;
    private String apellido;
    private int documento;
    private int  id_rol;
    private String  nombre_rol;
    
    public Usuario_Model(){
        
    }
    public Usuario_Model(int id_usuario,String  nombre,String apellido,
    int documento,int id_rol, String nombre_rol){
        this.id_usuario=id_usuario;
        this.nombre=nombre;
        this.id_rol=id_rol;
        this.nombre_rol=nombre_rol;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    @Override
    public String toString() {
        return "Id_Usuario{" + "id_usuario=" + id_usuario + ", nombre=" + nombre + ", apellido=" + apellido + ", documento=" + documento + ", id_rol=" + id_rol + ", nombre_rol=" + nombre_rol + '}';
    }
}

