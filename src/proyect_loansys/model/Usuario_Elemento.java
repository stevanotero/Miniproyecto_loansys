/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;


public class Usuario_Elemento {
    private int id_elemento;
    private int codigo_elemento;
    private String nombre_elemento;
    private int id_categoria;
    private String descripcion;
    private String imagen;
    private int id_estado_elemento;
    private String estado_nombre;
    private String categoria_nombre;
    
    
    public Usuario_Elemento(){
        
    }
    public Usuario_Elemento(int id_elemento, int codigo_elemento, String nombre_elemento,
            int id_categoria, String descripcion, String imagen, int id_estado_elemento){
        
        this.id_elemento=id_elemento;
        this.codigo_elemento=codigo_elemento;
        this.nombre_elemento=nombre_elemento;
        this.id_categoria=id_categoria;
        this.descripcion=descripcion;
        this.imagen=imagen;
        this.id_estado_elemento=id_estado_elemento;
        
    }

    public void setId_elemento(int id_elemento) {
        this.id_elemento = id_elemento;
    }

    public void setCodigo_elemento(int codigo_elemento) {
        this.codigo_elemento = codigo_elemento;
    }

    public void setNombre_elemento(String nombre_elemento) {
        this.nombre_elemento = nombre_elemento;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setId_estado_elemento(int id_estado_elemento) {
        this.id_estado_elemento = id_estado_elemento;
    }

    public int getId_elemento() {
        return id_elemento;
    }

    public int getCodigo_elemento() {
        return codigo_elemento;
    }

    public String getNombre_elemento() {
        return nombre_elemento;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public int getId_estado_elemento() {
        return id_estado_elemento;
    }
    
    public String getEstado_nombre() {
        return estado_nombre;
    }
    
    public String getCategoria_nombre() {
        return categoria_nombre;
    }

    public void setCategoria_nombre(String categoria_nombre) {
        this.categoria_nombre = categoria_nombre;
    }

    public void setEstado_nombre(String estado_nombre) {
        this.estado_nombre = estado_nombre;
    }

    @Override
    public String toString() {
        return "Elemento{" + "id_elemento=" + id_elemento + ", codigo_elemento=" + codigo_elemento + ", nombre_elemento=" + nombre_elemento + ", id_categoria=" + id_categoria + ", descripcion=" + descripcion + ", imagen=" + imagen + ", id_estado_elemento=" + id_estado_elemento + '}';
    }

}


    
