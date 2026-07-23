/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyect_loansys.model;

/**
 *
 * @author Alexis
 */
public interface Crud<T> {
    default boolean setAgregar(T tr) {
        return false;
    }
    default boolean setActualizar(T tr) {
        return false;
    }
}
