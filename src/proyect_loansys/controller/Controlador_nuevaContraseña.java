/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import proyect_loansys.view.Vista_NuevaContraseña;
import proyect_loansys.view.Vista_Login;
import proyect_loansys.controller.Controlador_Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import proyect_loansys.model.PersonaDao_nuevaContraseña;
import proyect_loansys.model.Persona_nuevaContraseña;

/**
 *
 * @author Alexis
 */
public class Controlador_nuevaContraseña implements ActionListener {

    private Vista_NuevaContraseña vista;
    private PersonaDao_nuevaContraseña dao;
    private String correoUsuario; 

    public Controlador_nuevaContraseña(Vista_NuevaContraseña vista, String correoUsuario) {
        this.vista = vista;
        this.dao = new PersonaDao_nuevaContraseña();
        this.correoUsuario = correoUsuario;

        // Activamos los botones para darle funcionalidad
        this.vista.botonGuardar.addActionListener(this);
        this.vista.botonCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Si da clic en guardar se aplica el metodo 
        if (e.getSource() == vista.botonGuardar) {
            procesarCambioContraseña();
        }

        if (e.getSource() == vista.botonCancelar) {
            regresarAlLogin();
        }
    }

    private void procesarCambioContraseña() {
        // Se captura la información que se puso en los campos a diligenciar
        String nuevaPass = String.valueOf(vista.ingresarNuevaContraseña.getPassword()).trim();
        String confirmarPass = String.valueOf(vista.confirmarContraseña.getPassword()).trim();

        // Validación para que no hayan campos vacíos
        if (nuevaPass.isBlank() || confirmarPass.isBlank()) {
            JOptionPane.showMessageDialog(vista, "Ambos campos son obligatorios.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Validación de que ambas contraseñas coincidan
        if (!nuevaPass.equals(confirmarPass)) {
            JOptionPane.showMessageDialog(vista, "Las contraseñas no coinciden. Inténtalo de nuevo.", "Error de Coincidencia", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validación aplicando el método de que la contraseña sea fuerte
        if (!validarRequisitosContraseña(nuevaPass)) {
            JOptionPane.showMessageDialog(vista, "Contraseña débil. Requiere mínimo 8 caracteres, una mayúscula, una minúscula y un símbolo.", "Contraseña Inválida", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Persona_nuevaContraseña modelo = new Persona_nuevaContraseña(nuevaPass, confirmarPass, correoUsuario);
        boolean exito = dao.setActualizar(modelo);

        if (exito) {
            JOptionPane.showMessageDialog(vista, "¡Tu contraseña ha sido restablecida con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            regresarAlLogin(); 
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo actualizar la contraseña en el sistema.", "Error de Actualización", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void regresarAlLogin() {
        vista.dispose(); 
        Vista_Login vistaLogin = new Vista_Login();
        Controlador_Login controladorLogin = new Controlador_Login(vistaLogin);
        vistaLogin.setVisible(true); 
    }
    // Expresión de seguridad robusta
    private boolean validarRequisitosContraseña(String contraseña) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(contraseña);
        return matcher.matches();
    }
}