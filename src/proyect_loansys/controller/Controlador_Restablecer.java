/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import proyect_loansys.model.PersonaDao_Restablecer;
import proyect_loansys.util.ServicioEmail; 
import proyect_loansys.view.Vista_Login;
import proyect_loansys.view.Vista_NuevaContraseña;
import proyect_loansys.view.Vista_RestablecerContraseña;

/**
 *
 * @author Alexis
 */
public class Controlador_Restablecer implements ActionListener {

    // Variables para el control de intentos y bloqueo temporal
    private int intentosFallidos = 0;
    private long tiempoBloqueoHasta = 0; // Guardará el tiempo en milisegundos
    private final long TIEMPO_ESPERA = 60000; 

    public PersonaDao_Restablecer pdao = new PersonaDao_Restablecer();
    public Vista_RestablecerContraseña vista;

    public Controlador_Restablecer(Vista_RestablecerContraseña vista) {
        this.vista = vista;
        // Se activan los botones que están en la vista
        this.vista.botonOlvidar.addActionListener(this);
        this.vista.botonCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Cuando el usuario le da clic en restablecer Contraseña
        if (e.getSource() == vista.botonOlvidar) {

            // Condición que verifica si actualmente está bloqueado
            if (System.currentTimeMillis() < tiempoBloqueoHasta) {
                long segundosRestantes = (tiempoBloqueoHasta - System.currentTimeMillis()) / 1000;
                JOptionPane.showMessageDialog(vista,
                        "Acceso temporalmente bloqueado por demasiados intentos.\nIntente de nuevo en " + segundosRestantes + " segundos.",
                        "Módulo Bloqueado", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String correoABuscar = vista.textoDelCorreo.getText().trim();

            if (!correoABuscar.isBlank()) {

                //Validar formato de correo y existencia en BD
                if (validarYVerificarCorreo(correoABuscar)) {

                    //Generar código de verificación aleatorio de 6 dígitos
                    int numeroAleatorio = (int) (Math.random() * 900000) + 100000;
                    String codigoGenerado = String.valueOf(numeroAleatorio);

                    //Enviar el correo usando ServicioEmail
                    String asunto = "Código de Recuperación - LoanSys";
                    String mensaje = "Hola,\n\nTu código de verificación para restablecer la contraseña en LoanSys es:\n\n"
                            + codigoGenerado + "\n\nSi no solicitaste este cambio, por favor ignora este correo.";

                    boolean enviado = ServicioEmail.enviarCorreo(correoABuscar, asunto, mensaje);

                    if (enviado) {
                        //Pedir el código al usuario en una ventana emergente
                        String codigoIngresado = JOptionPane.showInputDialog(
                                vista,
                                "Hemos enviado un código de 6 dígitos a " + correoABuscar + ".\nIngrésalo a continuación:",
                                "Verificación de Código",
                                JOptionPane.QUESTION_MESSAGE
                        );

                        //Validar que el código coincida
                        if (codigoIngresado != null && codigoIngresado.trim().equals(codigoGenerado)) {
                            JOptionPane.showMessageDialog(vista, "¡Código verificado correctamente! Procede a ingresar la nueva contraseña.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                            intentosFallidos = 0; // Se reinicia el contador de intentos
                            borrador();

                            // Cerramos la ventana de restablecer contraseña
                            vista.dispose();

                            // Se instancia la ventana de las nuevas contraseñas
                            Vista_NuevaContraseña vista_Nueva = new Vista_NuevaContraseña();
                            Controlador_nuevaContraseña conNueva = new Controlador_nuevaContraseña(vista_Nueva, correoABuscar);                 
                            vista_Nueva.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
                            vista_Nueva.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
                            vista_Nueva.setVisible(true);

                        } else if (codigoIngresado != null) {
                            JOptionPane.showMessageDialog(vista, "El código ingresado es incorrecto.", "Error de Verificación", JOptionPane.ERROR_MESSAGE);
                            registrarIntentoFallido();
                        }

                    } else {
                        JOptionPane.showMessageDialog(vista, "No se pudo enviar el correo. Revisa tu conexión a internet.", "Error de Envío", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    registrarIntentoFallido();
                }

            } else {
                JOptionPane.showMessageDialog(vista, "Por favor, ingrese un correo electrónico", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            }
        }

        // Se le da clic al botón de cancelar para volver atrás
        if (e.getSource() == vista.botonCancelar) {
            vista.dispose();
            Vista_Login vistaLogin = new Vista_Login();
            Controlador_Login controlador = new Controlador_Login(vistaLogin);
            vistaLogin.setVisible(true);
        }
    }

    // Método auxiliar para registrar intentos fallidos y activar el bloqueo de 1 minuto
    private void registrarIntentoFallido() {
        intentosFallidos++;
        if (intentosFallidos >= 5) {
            tiempoBloqueoHasta = System.currentTimeMillis() + TIEMPO_ESPERA;
            JOptionPane.showMessageDialog(vista,
                    "Has superado los 5 intentos permitidos.\nEl formulario se ha bloqueado por 1 minuto.",
                    "Límite Superado", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Valida el formato del correo y verifica si existe en la Base de Datos
    public boolean validarYVerificarCorreo(String correo) {
        // Validar que el correo no tenga espacios internos
        if (correo.contains(" ")) {
            JOptionPane.showMessageDialog(vista, "El correo electrónico no puede contener espacios en blanco", "Formato de Correo", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //Se valida que el correo tenga el formato correcto
        if (!validarCorreo(correo)) {
            JOptionPane.showMessageDialog(vista, "El correo debe tener el formato: usuario@gmail.com", "Correo Inválido", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Llamamos al método del DAO para verificar que exista en la BD
        boolean existeCorreo = pdao.verificarCorreo(correo);

        if (!existeCorreo) {
            JOptionPane.showMessageDialog(vista, "El correo ingresado no se encuentra registrado en el sistema", "Usuario no encontrado", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    // Método para validar la sintaxis con Regex
    private boolean validarCorreo(String correo) {
        String regex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    void borrador() {
        vista.textoDelCorreo.setText("");
    }
}