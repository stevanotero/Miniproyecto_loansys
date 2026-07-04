/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import proyect_loansys.model.Usuario_Dao;
import proyect_loansys.model.Usuario_Elemento;
import proyect_loansys.view.Usuario_SolicitarPrestamo;


public class Usuario_ControladorDatos implements ActionListener{
    
    public Usuario_SolicitarPrestamo soliprestamo;
    DefaultTableModel modelo = new DefaultTableModel();
    public Usuario_Dao edao = new Usuario_Dao();
    public Usuario_Elemento elementos = new Usuario_Elemento();
    
    
    public Usuario_ControladorDatos(Usuario_SolicitarPrestamo soliprestamo){
        
        this.soliprestamo = soliprestamo;
        this.soliprestamo.solicitar.addActionListener(this);
        
        getListar(soliprestamo.tabla);
    }

    public Usuario_ControladorDatos() {
        
    }


    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == soliprestamo.solicitar) // JOptionPane.showMessageDialog(vista, "Estamos Provando");
        {
            getListar(soliprestamo.tabla);
           
        }
    }
    public void getListar(JTable tabla){
        modelo = (DefaultTableModel) tabla.getModel();
        
        List<Usuario_Elemento> lista = edao.buscar();
        Object[] object = new Object[4];
        for (int indice =0; indice < lista.size(); indice++){
            object[0] = lista.get(indice).getCodigo_elemento();
            object[1] = lista.get(indice).getNombre_elemento();
            object[2] = lista.get(indice).getId_categoria();
            object[3] = lista.get(indice).getDescripcion();
            modelo.addRow(object);
        }
        soliprestamo.tabla.setModel(modelo);
        
    }
}
