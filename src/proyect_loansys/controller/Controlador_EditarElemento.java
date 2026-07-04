/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import proyect_loansys.model.Elemento;
import proyect_loansys.model.PersonaDao_Inventario;
import proyect_loansys.view.VentanaEditarElemento;

/**
 * @author Alexis
 */
public class Controlador_EditarElemento implements ActionListener {
    
    private VentanaEditarElemento vistaEditar;
    private Elemento elemento;
    private PersonaDao_Inventario dao;

    public Controlador_EditarElemento(VentanaEditarElemento vistaEditar, Elemento elemento) {
        this.vistaEditar = vistaEditar;
        this.elemento = elemento;
        this.dao = new PersonaDao_Inventario();

        this.vistaEditar.botonGuardar.addActionListener(this); 
        this.vistaEditar.botonCancelar.addActionListener(this);

        cargarDatosEnCampos();
    }

    private void cargarDatosEnCampos() {
        vistaEditar.textoCodigo.setText(String.valueOf(elemento.getCodigoElemento()));
        vistaEditar.textoNombre.setText(elemento.getNombreElemento());
        vistaEditar.textoDescripcion.setText(elemento.getDescripcion());
        
        vistaEditar.textoCategoria.setText(elemento.getCategoria());
        vistaEditar.ListaEstado.setSelectedItem(elemento.getEstado());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaEditar.botonGuardar) {
            
            elemento.setNombreElemento(vistaEditar.textoNombre.getText());
            elemento.setDescripcion(vistaEditar.textoDescripcion.getText());
            
            //  se establece el id del estado
            int idEstado = vistaEditar.ListaEstado.getSelectedIndex() + 1;
            elemento.setIdEstadoElemento(idEstado);

            if (dao.setActualizar(elemento)) {
                JOptionPane.showMessageDialog(vistaEditar, "¡Elemento actualizado con éxito!");
                vistaEditar.dispose(); 
            } else {
                JOptionPane.showMessageDialog(vistaEditar, "Error al intentar actualizar el elemento.");
            }
        }
        
        if (e.getSource() == vistaEditar.botonCancelar) {
            vistaEditar.dispose(); 
        }
    }
}