/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

/**
 *
 * @author Alexis
 */
import proyect_loansys.view.Vista_Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import proyect_loansys.view.Vista_NuevaContraseña;
import proyect_loansys.model.PersonaDao_Restablecer;
import proyect_loansys.view.Vista_RestablecerContraseña;

public class Controlador_Restablecer implements ActionListener {

    // Variables para el control de intentos y bloqueo temporal
    private int intentosFallidos = 0;
    private long tiempoBloqueoHasta = 0; // Guardará el tiempo en milisegundos
    private final long TIEMPO_ESPERA = 60000; // 60,000 ms = 1 minuto de bloqueo

    public PersonaDao_Restablecer pdao = new PersonaDao_Restablecer();
    public Vista_RestablecerContraseña vista;

    public Controlador_Restablecer(Vista_RestablecerContraseña vista) {
        this.vista = vista;

        //se activan los botones que están en la vista
        this.vista.botonOlvidar.addActionListener(this);
        this.vista.botonCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //cuando el usuario le da clic en restablecer Contraseña
        if (e.getSource() == vista.botonOlvidar) {
            //Condicion que verifica si actualmente está bloqueado
            if (System.currentTimeMillis() < tiempoBloqueoHasta) {
                long segundosRestantes = (tiempoBloqueoHasta - System.currentTimeMillis()) / 1000;
                JOptionPane.showMessageDialog(vista,
                        "Acceso temporalmente bloqueado por demasiados intentos.\nIntente de nuevo en " + segundosRestantes + " segundos.",
                        "Módulo Bloqueado", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Se guarda el correo en una variable local antes de que el metodo borrador lo borre
            String correoABuscar = vista.textoDelCorreo.getText().trim();
            if (!correoABuscar.isBlank()) {
                if (procesarVerificacionCorreo()) {
                    intentosFallidos = 0; // Si el correo es real y válido se reinicia el contador                 
                    //Cerramos la ventana de restablecer contraseña
                    vista.dispose();

                    //Se instancia la ventana de las nuevas contraseñas
                    Vista_NuevaContraseña vista_Nueva = new Vista_NuevaContraseña();
                    Controlador_nuevaContraseña conNueva = new Controlador_nuevaContraseña(vista_Nueva, correoABuscar);                 
                    vista_Nueva.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
                    vista_Nueva.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
                    //Se pone visible
                    vista_Nueva.setVisible(true);
                } else {
                    intentosFallidos++; //Sumamos un intento si el correo no existe o tiene mal formato
                    if (intentosFallidos >= 5) {
                        tiempoBloqueoHasta = System.currentTimeMillis() + TIEMPO_ESPERA;
                        JOptionPane.showMessageDialog(vista,
                                "Has superado los 5 intentos permitidos.\nEl formulario se ha bloqueado por 1 minuto.",
                                "Límite Superado", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Por favor, ingrese un correo electrónico", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            }
        }
        //Se le da clic al boton de cancelar para volver atrás
        if (e.getSource() == vista.botonCancelar) {
            vista.dispose();
            Vista_Login vistaLogin = new Vista_Login();
            Controlador_Login controlador = new Controlador_Login(vistaLogin);
            vistaLogin.setVisible(true);
        }
    }

    //Valida el formato del correo y verifica si existe en la Base de Datos
    public boolean procesarVerificacionCorreo() {
        String correo = vista.textoDelCorreo.getText().trim();

        //Validar que el correo no tenga espacios internos
        if (correo.contains(" ")) {
            JOptionPane.showMessageDialog(vista, "El correo electrónico no puede contener espacios en blanco", "Formato de Correo", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Se valida que el correo tenga el formato
        if (!validarCorreo(correo)) {
            JOptionPane.showMessageDialog(vista, "El correo debe tener el formato: usuario@gmail.com", "Correo Invalido", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //Llamamos al metodo correcto del DAO para veririficar que exista el correo y sea valido
        boolean existeCorreo = pdao.verificarCorreo(correo);

        if (existeCorreo) {
            JOptionPane.showMessageDialog(vista, "Correo verificado correctamente. Ya puede cambiar su contraseña", "Exito", JOptionPane.INFORMATION_MESSAGE);
            borrador();
            return true;
        } else {
            // Verificar que el correo este registrado en el sistema
            JOptionPane.showMessageDialog(vista, "El correo ingresado no se encuentra registrado en el sistema", "Usuario no encontrado", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Metodo para validar que el correo este con el formato establecido
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
