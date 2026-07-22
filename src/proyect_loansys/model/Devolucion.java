/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

import java.sql.Timestamp;

/**
 * @author Alexis
 * 
 */
public class Devolucion {
    private int idDevolucion;
    private int idPrestamo;
    private String nombreElemento;
    private Timestamp fechaInicioPrestamo;
    private Timestamp fechaDevolucion;
    private String observaciones;

    public Devolucion() {
    }

    public int getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(int idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getNombreElemento() {
        return nombreElemento;
    }

    public void setNombreElemento(String nombreElemento) {
        this.nombreElemento = nombreElemento;
    }

    public Timestamp getFechaInicioPrestamo() {
        return fechaInicioPrestamo;
    }

    public void setFechaInicioPrestamo(Timestamp fechaInicioPrestamo) {
        this.fechaInicioPrestamo = fechaInicioPrestamo;
    }

    public Timestamp getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Timestamp fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}