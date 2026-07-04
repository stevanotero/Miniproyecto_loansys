/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyect_loansys.model;

import java.util.List;

/**
 *
 * @author Sants
 */
public interface Crud<T> {
    public List<T> listar();
    public int setAgregar(T t);
    public int setActualizar(T t);
    public int setEliminar(int id);
}