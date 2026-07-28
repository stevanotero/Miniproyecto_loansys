/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import proyect_loansys.model.Administrador_Auditoria;
import proyect_loansys.model.Administrador_AuditoriaDao;
import proyect_loansys.model.Administrador_Sesion;
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

    //Metodo para cargar datos del campo en la modal para el elemento
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

            // Si el elemento está Prestado se bloquea la modificación
            if ("Prestado".equalsIgnoreCase(elemento.getEstado()) || elemento.getIdEstadoElemento() == 2) {
                JOptionPane.showMessageDialog(
                        vistaEditar,
                        "No es posible modificar este elemento porque se encuentra actualmente PRESTADO a un usuario.",
                        "Elemento Prestado",
                        JOptionPane.WARNING_MESSAGE
                );
                return; 
            }

            // Validaciones de campos de texto
            String nombre = vistaEditar.textoNombre.getText().trim();
            String descripcion = vistaEditar.textoDescripcion.getText().trim();
            if (nombre.isBlank() || descripcion.isBlank()) {
                JOptionPane.showMessageDialog(vistaEditar,
                        "Los campos de Nombre/Detalles y Descripción no pueden estar vacíos ni contener solo espacios.",
                        "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            //Validacion de minimo de caracteres en el nombre
            if (nombre.length() < 5) {
                JOptionPane.showMessageDialog(vistaEditar,
                        "El nombre es demasiado corto (Mínimo 5 caracteres).",
                        "Mínimo de Caracteres", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            //Validacion de maximo de caracteres en el nombre
            if (nombre.length() > 50) {
                JOptionPane.showMessageDialog(vistaEditar,
                        "El nombre es demasiado largo (Máximo 50 caracteres).",
                        "Límite de Caracteres", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            //Validacion de maximo de caracteres en la descripcion
            if (descripcion.length() > 60) {
                JOptionPane.showMessageDialog(vistaEditar,
                        "La descripción es demasiado larga (Máximo 60 caracteres).",
                        "Límite de Caracteres", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            //Validacion de minimo de caracteres en la descripcion
            if (descripcion.length() < 5) {
                JOptionPane.showMessageDialog(vistaEditar,
                        "La descripción es demasiado corta (Mínimo 5 caracteres).",
                        "Mínimo de Caracteres", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Asignar los nuevos datos al objeto
            elemento.setNombreElemento(nombre);
            elemento.setDescripcion(descripcion);

            // Mapeo dinámico del texto del ComboBox al ID exacto de la base de datos
            String estadoTexto = vistaEditar.ListaEstado.getSelectedItem().toString();
            int idEstado = obtenerIdEstadoPorNombre(estadoTexto);
            elemento.setIdEstadoElemento(idEstado);

            if (dao.setActualizar(elemento)) {
                Administrador_Auditoria auditoria = new Administrador_Auditoria();
auditoria.setIdUsuario(Administrador_Sesion.getIdUsuario());
auditoria.setAccion("Modificacion de elemento");
new Administrador_AuditoriaDao().registrarAccion(auditoria);
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
    private int obtenerIdEstadoPorNombre(String nombreEstado) {
        switch (nombreEstado) {
            case "Disponible":
                return 1;
            case "En Mantenimiento":
                return 3;
            case "Dañado":
                return 4;
            case "Dado de Baja":
                return 5;
            default:
                return 1;
        }
    }
}
