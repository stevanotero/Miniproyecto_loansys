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
 *
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

            //Tomamos los textos y les aplicamos .trim() para limpiar espacios muertos en los extremos
            String nombre = vistaEditar.textoNombre.getText().trim();
            String descripcion = vistaEditar.textoDescripcion.getText().trim();

            if (nombre.isBlank() || descripcion.isBlank()) {
                JOptionPane.showMessageDialog(vistaEditar,
                        "Los campos de Nombre/Detalles y Descripción no pueden estar vacíos ni contener solo espacios.",
                        "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                return; // Corta la ejecución de inmediato, impidiendo el registro erróneo
            }
            
            if (nombre.length() < 5){
            JOptionPane.showMessageDialog(vistaEditar,
                        "El nombre es demasiado corto (Minimo 5 caracteres).",
                        "Minimo de Caracteres", JOptionPane.WARNING_MESSAGE);
                return;
            }

            //Límite de longitud para evitar desbordes en la Base de Datos
            if (nombre.length() > 50) {
                JOptionPane.showMessageDialog(vistaEditar,
                        "El nombre es demasiado largo (Máximo 50 caracteres).",
                        "Límite de Caracteres", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (descripcion.length() > 60) {
                JOptionPane.showMessageDialog(vistaEditar,
                        "La descripción es demasiado larga (Máximo 60 caracteres).",
                        "Límite de Caracteres", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
             if (descripcion.length() < 5){
            JOptionPane.showMessageDialog(vistaEditar,
                        "La descripción es demasiada corta (Minimo 5)",
                        "Minimo de Caracteres", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            
            //Si el flujo llega hasta aquí, significa que los datos son validos
            elemento.setNombreElemento(nombre);
            elemento.setDescripcion(descripcion);

            // Se establece el id del estado
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
