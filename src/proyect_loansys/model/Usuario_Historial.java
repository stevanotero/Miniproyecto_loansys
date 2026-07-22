/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

import java.sql.Timestamp;


public class Usuario_Historial {
    private int id_historial_prestamo;
    private int id_usuario;
    private int id_elemento;
    private String nombre_elemento;
    private Timestamp fecha_prestamo;
    private Timestamp fecha_limite;
    private int id_estado_entrega;
    private String nombre_estado_entrega;
    private int id_categoria;
    private String nombre_categoria;
    
    
    public Usuario_Historial(){
        
    }

    public int getId_historial_prestamo() {
        return id_historial_prestamo;
    }

    public void setId_historial_prestamo(int id_historial_prestamo) {
        this.id_historial_prestamo = id_historial_prestamo;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_elemento() {
        return nombre_elemento;
    }

    public void setNombre_elemento(String nombre_elemento) {
        this.nombre_elemento = nombre_elemento;
    }

    public Timestamp getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(Timestamp fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public Timestamp getFecha_limite() {
        return fecha_limite;
    }

    public void setFecha_limite(Timestamp fecha_limite) {
        this.fecha_limite = fecha_limite;
    }

    public int getId_estado_entrega() {
        return id_estado_entrega;
    }

    public void setId_estado_entrega(int id_estado_entrega) {
        this.id_estado_entrega = id_estado_entrega;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public int getId_elemento() {
        return id_elemento;
    }

    public void setId_elemento(int id_elemento) {
        this.id_elemento = id_elemento;
    }

    public String getNombre_estado_entrega() {
        return nombre_estado_entrega;
    }

    public void setNombre_estado_entrega(String nombre_estado_entrega) {
        this.nombre_estado_entrega = nombre_estado_entrega;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    @Override
    public String toString() {
        return "Usuario_Historial{" + "id_historial_prestamo=" + id_historial_prestamo + ", id_usuario=" + id_usuario + ", id_elemento=" + id_elemento + ", nombre_elemento=" + nombre_elemento + ", fecha_prestamo=" + fecha_prestamo + ", fecha_limite=" + fecha_limite + ", id_estado_entrega=" + id_estado_entrega + ", nombre_estado_entrega=" + nombre_estado_entrega + ", id_categoria=" + id_categoria + ", nombre_categoria=" + nombre_categoria + '}';
    }

    
    
}
