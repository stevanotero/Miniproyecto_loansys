package proyect_loansys.controller;


import proyect_loansys.view.Vista_Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import proyect_loansys.model.PersonaDao_Registro;
import proyect_loansys.model.Persona_Registro;
import proyect_loansys.view.Vista_Registro;

public class Controlador_Registro implements ActionListener {

    // Variables para el control de intentos y bloqueo temporal
    private int intentosFallidos = 0;
    private long tiempoBloqueoHasta = 0; // Guardara el tiempo en milisegundos
    private final long TIEMPO_ESPERA = 60000; // 60,000 ms = 1 minuto de bloqueo

    public PersonaDao_Registro pdao = new PersonaDao_Registro();
    public Vista_Registro vista = new Vista_Registro();

    public Controlador_Registro(Vista_Registro vista) {
        this.vista = vista;

        //Se activan los botones que estan en la vista del registro
        this.vista.botonRegistrar.addActionListener(this);
        this.vista.botonLogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Aquí es cuando el usuario le da clic en registrarse
        if (e.getSource() == vista.botonRegistrar) {

            // Condicion que verifica si actualmente está bloqueado
            if (System.currentTimeMillis() < tiempoBloqueoHasta) {
                long segundosRestantes = (tiempoBloqueoHasta - System.currentTimeMillis()) / 1000;
                JOptionPane.showMessageDialog(vista,
                        "Acceso temporalmente bloqueado por demasiados intentos.\nIntente de nuevo en " + segundosRestantes +
                                " segundos.",
                        "Registro bloqueado", JOptionPane.ERROR_MESSAGE);
                return;
                //Corta la ejecución en caliente, no procesa nada más
            }
            String passwordConvertido = String.valueOf(vista.textoDeLaContraseña.getPassword());
            if (!vista.textoDelDocumento.getText().isBlank()
                    && !vista.textoDelCorreo.getText().isBlank()
                    && !passwordConvertido.isBlank()) {

                // Se intenta agregar y se evalua el resultado del proceso
                if (setAdd()) {
                    intentosFallidos = 0; //Si el registro fue exitoso en la base de datos se limpia el tiempo
                    vista.dispose(); // Cierra la ventana de registro
                    Vista_Login vistaLogin = new Vista_Login();
                    Controlador_Login controlador = new Controlador_Login(vistaLogin);
                    vistaLogin.setVisible(true); // Abre la ventana de login
                } else {
                    intentosFallidos++; // e suma el contador de intentos si se falla

                    if (intentosFallidos >= 5) {
                        //Se guarda el momento exacto en el futuro en que se desbloqueará
                        tiempoBloqueoHasta = System.currentTimeMillis() + TIEMPO_ESPERA;
                        JOptionPane.showMessageDialog(vista,
                                "Has superado los 5 intentos permitidos.\nEl registro se ha bloqueado por 1 minuto.",
                                "Límite Superado", JOptionPane.getDesktopPaneForComponent(vista).getDebugGraphicsOptions());
                    }
                }

            } else {
                JOptionPane.showMessageDialog(vista, "Faltan datos por llenar", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            }
        }

        //Se le da clic al boton de iniciar sesion
        if (e.getSource() == vista.botonLogin) {
            vista.dispose();
            Vista_Login vistaLogin = new Vista_Login();
            Controlador_Login controlador = new Controlador_Login(vistaLogin);
            vistaLogin.setVisible(true);
        }
    }

    // Se cambia a boolean para avisarle al actionPerformed si se guardó con éxito o no
    public boolean setAdd() {
        try {
            int documento = Integer.parseInt(vista.textoDelDocumento.getText().trim());
            String correo = vista.textoDelCorreo.getText().trim();
            String contraseña = String.valueOf(vista.textoDeLaContraseña.getPassword()).trim();

            //Validar que el correo no tenga espacios en blanco adentro
            if (correo.contains(" ")) {
                JOptionPane.showMessageDialog(vista, "El correo electrónico no puede contener espacios en blanco", "Formato de Correo", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (correo.length() > 100) {
                JOptionPane.showMessageDialog(vista, "El correo electrónico es demasiado largo (Máximo 100 caracteres)", "Límite de Caracteres", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (!validarCorreo(correo)) {
                JOptionPane.showMessageDialog(vista, "El correo debe tener el formato: usuario@gmail.com", "Correo Inválido", JOptionPane.ERROR_MESSAGE);
                return false; // Retorna falso porque el formato de correo fallo
            }

            if (!validarContraseña(contraseña)) {
                JOptionPane.showMessageDialog(vista, "Contraseña débil. Requiere mínimo 8 caracteres, una mayúscula, una minúscula y un símbolo", "Contraseña Inválida", JOptionPane.ERROR_MESSAGE);
                return false; // Retorna falso porque la contraseña fallo
            }

            // Si se envia la informacion correctamente en el dao
            Persona_Registro p = new Persona_Registro(documento, correo, contraseña, 1);
            boolean resultado = pdao.setAgregar(p);

            if (resultado) {
                JOptionPane.showMessageDialog(vista, "Usuario registrado con éxito");
                borrador();
                return true; // retorna a true si los datos entraron a la base de datos con exito
            } else {
                return false; //Fallo la base de datos por un dato ya existente
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "El campo documento debe contener solo números enteros", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return false; // Retorna falso por error de escritura de caracteres en el documento
        }
    }

    // Metodo para validar que el correo este planteada correctamente
    private boolean validarCorreo(String correo) {
        String regex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    // El metodo para validar que los parametros de contraseña, su minimo este planteada para que este seguro
    private boolean validarContraseña(String contraseña) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(contraseña);
        return matcher.matches();
    }

    void borrador() {
        vista.textoDelDocumento.setText("");
        vista.textoDelCorreo.setText("");
        vista.textoDeLaContraseña.setText("");
    }
}
