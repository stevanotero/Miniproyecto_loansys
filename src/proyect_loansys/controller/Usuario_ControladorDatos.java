/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import proyect_loansys.model.Usuario_Dao;
import proyect_loansys.model.Usuario_Elemento;
import proyect_loansys.view.Usuario_Inventario;
import proyect_loansys.view.Usuario_SolicitarPrestamo;


public class Usuario_ControladorDatos implements ActionListener{
    
    
    
    private Usuario_SolicitarPrestamo soliprestamo1;
    private Usuario_Inventario inveta;
    public Usuario_Dao elementoDao = new Usuario_Dao();
    public Usuario_Elemento elementos = new Usuario_Elemento();
    DefaultTableModel modelo = new DefaultTableModel();

    public Usuario_ControladorDatos(Usuario_Inventario inveta, Usuario_SolicitarPrestamo soliprestamo) {
        this.soliprestamo1 = soliprestamo;
        this.inveta = inveta;

        this.inveta.prueba.addActionListener(this);
        getListar(soliprestamo.tabla);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inveta.prueba) {
            getListar(soliprestamo1.tabla);

        }
    }

    public void getListar(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();

        List<Usuario_Elemento> lista = elementoDao.listar();
        Object[] object = new Object[4];
        for (int indice = 0; indice < lista.size(); indice++) {
            //Object[] object = new Object[4];
            object[0] = lista.get(indice).getCodigo_elemento();
            object[1] = lista.get(indice).getNombre_elemento();
            object[2] = lista.get(indice).getCategoria_nombre();
            object[3] = lista.get(indice).getDescripcion();
            modelo.addRow(object);
            
        }
        soliprestamo1.tabla.setModel(modelo);
        //limpiarTabla();

    }
    
    public void limpiarTabla() {
        for (int i = 0; i < soliprestamo1.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void cargarPrimerElemento(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0); // Limpiar la tabla

        Usuario_Elemento elemento = elementoDao.obtenerPrimerElemento();

        if (elemento != null) {
            Object[] object = new Object[4];
            object[0] = elemento.getCodigo_elemento();
            object[1] = elemento.getNombre_elemento();
            object[2] = elemento.getCategoria_nombre();
            object[3] = elemento.getDescripcion();
            modelo.addRow(object);
        } else {
            JOptionPane.showMessageDialog(null, "No hay elementos disponibles en este momento");
        }

        soliprestamo1.tabla.setModel(modelo);
    }

    public void cargarSegundoElemento(JTable tabla) {
//        modelo = (DefaultTableModel) tabla.getModel();
//        modelo.setRowCount(1);
//
//        Elemento elemento = elementoDao.obtenerPrimerElemento();
//
//        if (elemento == ) {
//            Object[] object = new Object[4];
//            object[0] = elemento.getCodigo_elemento();
//            object[1] = elemento.getNombre_elemento();
//            object[2] = elemento.getCategoria_nombre();
//            object[3] = elemento.getDescripcion();
//            modelo.addRow(object);
//        } else {
//            JOptionPane.showMessageDialog(null, "No hay elementos disponibles en este momento");
//        }
//
//        soliprestamo1.tabla.setModel(modelo);

    }

}
