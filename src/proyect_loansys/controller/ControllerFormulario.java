/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

/**
 *
 * @author Sants
 */

import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import proyect_loansys.model.HistorialMantenimientoDao;
import proyect_loansys.model.HistorialMantenimiento;
import proyect_loansys.view.Formulario;

public class ControllerFormulario implements ActionListener {

    private Formulario vista;
    private HistorialMantenimientoDao dao = new HistorialMantenimientoDao();
    private int idUsuarioEncontrado = -1; //guarda el id del tecnico que se encuentra por documento

    public ControllerFormulario(Formulario vista) {
        this.vista = vista;
        this.vista.getBtnRegistrar().addActionListener(this);
        this.vista.getBtnCancelar().addActionListener(this);

        //pa q se vea 
        this.vista.getTxtTecnico().setEditable(false);
        this.vista.getTxtTecnico().setFocusable(false); //pa q no se pueda escribir ni nd
        this.vista.getTxtTecnico().setEnabled(true);
        this.vista.getTxtTecnico().setForeground(Color.BLACK); //paq se vea negra la letr a
        this.vista.getTxtTecnico().setBackground(new Color(230, 230, 230)); //el fondo gris y q no se mueva la suc
        this.vista.getTxtTecnico().setDisabledTextColor(Color.BLACK);

        // cuando termine de buscar pone al tecnico 
        this.vista.getTxtDocumento().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                buscarTecnico();
            }
        });
    }

    //l odel boton
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnRegistrar()) {
            registrar();
        }
        if (e.getSource() == vista.getBtnCancelar()) {
            cerrarFormulario();
        }
    }

    private void buscarTecnico() {
        String documento = vista.getTxtDocumento().getText().trim();
        if (documento.isEmpty()) {
            return;
        }

        Object[] resultado = dao.buscarTecnicoPorDocumento(documento);

        if (resultado != null) {
            idUsuarioEncontrado = (int) resultado[0];
            vista.getTxtTecnico().setText(resultado[1].toString());
        } else {
            idUsuarioEncontrado = -1;
            vista.getTxtTecnico().setText("");
            JOptionPane.showMessageDialog(
                    vista,
                    "No se encontró ningún técnico con ese número de documento.",
                    "Técnico no encontrado",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void registrar() {
        String nombreElemento = vista.getTxtNombreElemento().getText().trim();
        String categoria = vista.getCCategoria().getSelectedItem().toString();
        String codigoElementoTxt = vista.getTxtCodigoElemento().getText().trim();
        String tecnico = vista.getTxtTecnico().getText().trim();
        String estado = vista.getCEstado().getSelectedItem().toString();
        String documento = vista.getTxtDocumento().getText().trim();
        String descripcion = vista.getTxtDescripcion().getText().trim();
        String tipoMantenimiento = vista.getCTipoMantenimiento().getSelectedItem().toString();

        if (nombreElemento.isEmpty()
                || categoria.equals("Seleccione")
                || codigoElementoTxt.isEmpty()
                || tecnico.isEmpty()
                || estado.equals("Seleccione")
                || documento.isEmpty()
                || tipoMantenimiento.equals("Seleccione")) {
            JOptionPane.showMessageDialog(
                    vista, "Debes rellenar todos los campos obligatorios(*).\nLa descripción es opcional.", "Campos vacíos", JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        //el tecnico debe de llegar si osi por una consulta del documento
        if (idUsuarioEncontrado == -1) {
            JOptionPane.showMessageDialog(
                    vista, "Debes ingresar un número de documento válido y registrado en el sistema.", "Técnico no válido", JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int codigoElemento;
        try {
            codigoElemento = Integer.parseInt(codigoElementoTxt);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    vista, "El código de elemento debe ser un número.", "Dato inválido", JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        //el codigo de 5 digitos aleatorio
        String codigoMantenimiento = dao.generarCodigoMantenimiento();

        HistorialMantenimiento h = new HistorialMantenimiento();
        h.setCodigoMantenimiento(codigoMantenimiento);
        h.setNombreElemento(nombreElemento);
        h.setCodigoElemento(codigoElemento);
        h.setCategoria(categoria);
        h.setTipoMantenimiento(tipoMantenimiento);
        h.setEstadoElemento(estado);
        h.setIdUsuario(idUsuarioEncontrado);
        h.setDescripcion(descripcion.isEmpty() ? null : descripcion);

        int resultado = dao.setAgregar(h);

        if (resultado == 1) {
            JOptionPane.showMessageDialog(
                    vista,
                    "El registro ha sido exitoso.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );
            cerrarFormulario();
        }
    }

    private void cerrarFormulario() {
        Window ventana = SwingUtilities.getWindowAncestor(vista);
        if (ventana != null) {
            ventana.dispose();
        }
    }
}