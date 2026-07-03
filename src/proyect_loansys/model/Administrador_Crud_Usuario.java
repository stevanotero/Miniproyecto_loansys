/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

import java.util.List;

/**
 *
 * @author juans
 */
public interface Administrador_Crud_Usuario <T>{
      public List<T> listar();
    public int setAgragar(T tr);
    public int setActualizarEstado(T tr);
    public int setActualizarRol(T tr);
    public int setActualizarCorreo(T tr);
}
