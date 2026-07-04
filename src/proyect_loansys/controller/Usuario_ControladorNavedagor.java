/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import proyect_loansys.model.Usuario_Dao;
import proyect_loansys.view.Usuario_HistorialPrestamo;
import proyect_loansys.view.Usuario_Inicio;
import proyect_loansys.view.Usuario_Inventario;
import proyect_loansys.view.Usuario_Notificacion;
import proyect_loansys.view.Usuario_SolicitarPrestamo;


public class Usuario_ControladorNavedagor implements ActionListener{
    private boolean vali = false;
    //private boolean visible = false;
    Usuario_Dao prus = new Usuario_Dao();
    Usuario_Inicio inicio = new Usuario_Inicio("Aprendiz", "Aprendiz", "Miguel");
    Usuario_Inventario inven = new Usuario_Inventario("Aprendiz", "Aprendiz", "Miguel");
    Usuario_HistorialPrestamo pres = new Usuario_HistorialPrestamo("Aprendiz", "Aprendiz", "Miguel");
    Usuario_Notificacion noti = new Usuario_Notificacion("Aprendiz", "Aprendiz", "Miguel");
    Usuario_SolicitarPrestamo soli = new Usuario_SolicitarPrestamo("Aprendiz", "Aprendiz", "Miguel");

    public Usuario_ControladorNavedagor(Usuario_Inicio inicio) {
        this.inicio = inicio;
        this.inicio.inventario.addActionListener(this);
        this.inicio.prestamo.addActionListener(this);
        this.inicio.notificacion.addActionListener(this);

        ///////////////////////////////////////////////////
        this.inven.iniciod.addActionListener(this);
        this.inven.prestamo.addActionListener(this);
        this.inven.notificacion.addActionListener(this);
        this.inven.prueba.addActionListener(this);

        /////////////////////////////////////////////////////
        this.pres.iniciod.addActionListener(this);
        this.pres.inventario.addActionListener(this);
        this.pres.notificacion.addActionListener(this);
        /////////////////////////////////////////////////////
        this.noti.iniciod.addActionListener(this);
        this.noti.prestamo.addActionListener(this);
        this.noti.inventario.addActionListener(this);

        ////////////////////////////////////////////////////
        this.soli.volver.addActionListener(this);
        this.soli.solicitar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == inicio.inventario) {
            CargarInventario(inven);
            inicio.dispose();
        }

        if (e.getSource() == inicio.prestamo) {
            CargarHistorial(pres);
            inicio.dispose();
        }
        if (e.getSource() == inicio.notificacion) {

            inven.iniciod.setBackground(Color.cyan);
            CargarNotificacion(noti);
            inicio.dispose();
        }

        ////////////////////////////////////////////
        if (e.getSource() == inven.iniciod) {
            CargarInicio(inicio);
            inven.dispose();
        }
        if (e.getSource() == inven.prestamo) {
            CargarHistorial(pres);
            inven.dispose();
        }
        if (e.getSource() == inven.notificacion) {
            CargarNotificacion(noti);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba) {
            CargarSolicitud(soli);
            inven.dispose();
        }

        ////////////////////////////////////////////
        if (e.getSource() == pres.iniciod) {
            CargarInicio(inicio);
            pres.dispose();
        }
        if (e.getSource() == pres.inventario) {
            CargarInventario(inven);
            pres.dispose();
        }
        if (e.getSource() == pres.notificacion) {
            CargarNotificacion(noti);
            pres.dispose();
        }

        ///////////////////////////////////////////
        if (e.getSource() == noti.iniciod) {
            CargarInicio(inicio);
            noti.dispose();
        }
        if (e.getSource() == noti.inventario) {
            CargarInventario(inven);
            noti.dispose();
        }
        if (e.getSource() == noti.prestamo) {
            CargarHistorial(pres);
            noti.dispose();
        }

        //////////////////////////////////////
        if (e.getSource() == soli.volver) {
            CargarInventario(inven);
            soli.dispose();
        }

        if (e.getSource() == soli.solicitar) {
            JOptionPane.showMessageDialog(null, "Solicitud con exito");
        }
    

    //////////////////////////////////////
//        if (e.getSource() == soli.solicitar){
//    int fila = soli.tabla.getSelectedRow();
//    if (fila == -1) {
//        JOptionPane.showMessageDialog(null, "Selecciona un elemento de la tabla");
//    } else {
//        Solicitud s = new Solicitud();
//        s.setId_usuario(1);          // aquí deberías usar el usuario logueado real
//        s.setNombre("Miguel");
//        s.setApellido("Apellido");
//        s.setDocumento(12345678);
//        s.setId_elemento((int) soli.tabla.getValueAt(fila, 0)); // codigo_elemento de la fila
//        s.setFecha_envio(LocalTime.now());
//
//        int resultado = prus.setAgregar(s); // prus = Elemento_Dao ya existente
//        if (resultado > 0) {
//            JOptionPane.showMessageDialog(null, "Solicitud con éxito");
//        } else {
//            JOptionPane.showMessageDialog(null, "No se pudo registrar la solicitud");
//        }
//    }
//}
        
    }
    
    public void CargarInicio(Usuario_Inicio inicio) {
        inicio.setVisible(true);
        inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
//

    public void CargarInventario(Usuario_Inventario inven) {
        inven.setVisible(true);
        inven.setExtendedState(JFrame.MAXIMIZED_BOTH);
        inven.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void CargarHistorial(Usuario_HistorialPrestamo inven) {
        pres.setVisible(true);
        pres.setExtendedState(JFrame.MAXIMIZED_BOTH);
        pres.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void CargarNotificacion(Usuario_Notificacion noti) {
        noti.setVisible(true);
        noti.setExtendedState(JFrame.MAXIMIZED_BOTH);
        noti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void CargarSolicitud(Usuario_SolicitarPrestamo soli) {
        soli.setVisible(true);
        soli.setExtendedState(JFrame.MAXIMIZED_BOTH);
        soli.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
