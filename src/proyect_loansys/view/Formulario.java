/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.view;

/**
 *
 * @author Sants
 */

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Formulario extends JPanel {

    private JTextField txtNombreElemento;
    private JTextField txtCodigoElemento;
    private JComboBox<String> cCategoria;
    private JComboBox<String> cTipoMantenimiento;//el // del tipo mantimiento
    private JComboBox<String> cEstadoMantenimiento; //el desplegable del estado del manteniimiento
    private JTextField txtDocumento;
    private JTextField txtTecnico;
    private JTextArea txtDescripcion;

    private JButton btnRegistrar;
    private JButton btnCancelar;

    public Formulario() {
        setLayout(null);
        setBackground(new Color(245, 245, 245));
        comienzoFormulario();
    }

    private void comienzoFormulario() {
        JPanel formulario = new JPanel(null);
        formulario.setBounds(70, 40, 620, 430);
        formulario.setBackground(new Color(215, 215, 215));
        add(formulario);

        JLabel titulo = new JLabel("Registrar mantenimiento");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(25, 15, 300, 30);
        formulario.add(titulo);

        //f1
        JLabel lblNombreElem = new JLabel("Nombre de Elemento *");
        lblNombreElem.setBounds(30, 60, 150, 20);
        formulario.add(lblNombreElem);

        txtNombreElemento = new JTextField();
        txtNombreElemento.setBounds(30, 80, 150, 25);
        formulario.add(txtNombreElemento);

        JLabel lblCodigoElem = new JLabel("Código de Elemento *");
        lblCodigoElem.setBounds(220, 60, 150, 20);
        formulario.add(lblCodigoElem);

        txtCodigoElemento = new JTextField();
        txtCodigoElemento.setBounds(220, 80, 150, 25);
        formulario.add(txtCodigoElemento);

        //f2
        JLabel lblCategoria = new JLabel("Categoría *");
        lblCategoria.setBounds(30, 115, 150, 20);
        formulario.add(lblCategoria);
        //m desplegable
        cCategoria = new JComboBox<>(new String[]{"Seleccione", "Herramientas", "Portatiles", "Tecnologia", "Maquinas"});
        cCategoria.setBounds(30, 135, 150, 25);
        formulario.add(cCategoria);
        
        JLabel lblTipoMant = new JLabel("Tipo de Mantenimiento *");
        lblTipoMant.setBounds(220, 115, 150, 20);
        formulario.add(lblTipoMant);
        // md //
        cTipoMantenimiento = new JComboBox<>(new String[]{"Seleccione", "Preventivo", "Correctivo"});
        cTipoMantenimiento.setBounds(220, 135, 150, 25);
        formulario.add(cTipoMantenimiento);

        //f3
        JLabel lblEstado = new JLabel("Estado del Elemento *");
        lblEstado.setBounds(30, 170, 150, 20);
        formulario.add(lblEstado);
        // md //
        cEstadoMantenimiento = new JComboBox<>(new String[]{"Seleccione", "En mantenimiento", "Realizado", "Pendiente"});
        cEstadoMantenimiento.setBounds(30, 190, 150, 25);
        formulario.add(cEstadoMantenimiento);

        JLabel lblDocumento = new JLabel("Número de documento *");
        lblDocumento.setBounds(220, 170, 150, 20);
        formulario.add(lblDocumento);
        //paq escriba 
        txtDocumento = new JTextField();
        txtDocumento.setBounds(220, 190, 150, 25);
        formulario.add(txtDocumento);

        //f4
        JLabel lblTecnico = new JLabel("Técnico responsable");
        lblTecnico.setBounds(30, 225, 150, 20);
        formulario.add(lblTecnico);

        txtTecnico = new JTextField();
        txtTecnico.setEnabled(false);
        txtTecnico.setBounds(30, 245, 150, 25);
        formulario.add(txtTecnico);

        JLabel lblDescripcion = new JLabel("Descripción");
        lblDescripcion.setBounds(220, 225, 150, 20);
        formulario.add(lblDescripcion);
        
        txtDescripcion = new JTextArea();
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        scrollDescripcion.setBounds(220, 245, 280, 80); // Se mantiene ancho y alto para texto largo y tmb para desplegar hacia abajo
        formulario.add(scrollDescripcion);

        //botones
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(410, 370, 90, 30);
        btnRegistrar.setBackground(new Color(61, 201, 90));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        formulario.add(btnRegistrar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(510, 370, 90, 30);
        btnCancelar.setBackground(new Color(235, 40, 40));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        formulario.add(btnCancelar);
    }

    public JTextField getTxtNombreElemento() {
        return txtNombreElemento;
    }

    public JTextField getTxtCodigoElemento() {
        return txtCodigoElemento;
    }

    public JComboBox<String> getCCategoria() {
        return cCategoria;
    }

    public JComboBox<String> getCTipoMantenimiento() {
        return cTipoMantenimiento;
    }

    public JComboBox<String> getCEstado() {
        return cEstadoMantenimiento;
    }

    public JTextField getTxtDocumento() {
        return txtDocumento;
    }

    public JTextField getTxtTecnico() {
        return txtTecnico;
    }

    public JTextArea getTxtDescripcion() {
        return txtDescripcion;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }
}