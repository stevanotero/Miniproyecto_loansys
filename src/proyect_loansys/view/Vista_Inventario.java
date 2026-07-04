package proyect_loansys.view;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.SwingUtilities;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * @author Alexis
 */
public class Vista_Inventario extends Vista_Principal {

    public JTextField textoParaBuscar;
    public JButton btnBuscar;
    public JButton btnLimpiar; // ✨ NUEVO: Declaración del botón limpiar
    public JComboBox<String> cbxCategoria;
    public JComboBox<String> cbxEstado;
    public JTable tablaDeElementos;
    public DefaultTableModel modeloTabla;

    public Vista_Inventario() {
        super();
        setTextoBienvenida("");
        setTextoModulo("Panel de Inventario"); 
    }

    @Override
    protected JPanel crearPanelCentro() {
        JPanel panelPrincipalCentrado = new JPanel(new BorderLayout());
        panelPrincipalCentrado.setBackground(Color.WHITE);
        panelPrincipalCentrado.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JPanel barraSuperior = new JPanel(new BorderLayout());
        barraSuperior.setOpaque(false);

        // Panel de búsqueda (Aumentamos el ancho a 12 para que quepan holgadamente los 3 componentes)
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 15));
        panelBusqueda.setOpaque(false);

        textoParaBuscar = new JTextField();
        textoParaBuscar.setPreferredSize(new Dimension(200, 35)); // Un poquito más corto para dar espacio
        textoParaBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textoParaBuscar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        textoParaBuscar.setText(""); 

        // Boton Buscar 
        btnBuscar = new JButton("Buscar");
        btnBuscar.setPreferredSize(new Dimension(90, 35)); 
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnBuscar.setBackground(new Color(228, 230, 233)); 
        btnBuscar.setForeground(Color.BLACK); 
        btnBuscar.setFocusPainted(false);
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBuscar.setBorder(BorderFactory.createLineBorder(new Color(190, 190, 190), 1, true));

        // ✨ NUEVO: Creación del Botón Limpiar
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setPreferredSize(new Dimension(90, 35)); 
        btnLimpiar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLimpiar.setBackground(new Color(240, 240, 240)); // Un tono sutilmente diferente
        btnLimpiar.setForeground(new Color(80, 80, 80)); 
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLimpiar.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true));

        // Añadimos los tres elementos al panel de búsqueda
        panelBusqueda.add(textoParaBuscar);
        panelBusqueda.add(btnBuscar);
        panelBusqueda.add(btnLimpiar); // ✨ NUEVO: Agregado al flujo

        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        panelFiltros.setOpaque(false);

        // Filtro Categoria
        JPanel grupoCategoria = new JPanel();
        grupoCategoria.setLayout(new BoxLayout(grupoCategoria, BoxLayout.Y_AXIS));
        grupoCategoria.setOpaque(false);
        JLabel textoCategoria = new JLabel("Categoría");
        textoCategoria.setFont(new Font("Segoe UI", Font.BOLD, 12));
        textoCategoria.setForeground(Color.GRAY);
        textoCategoria.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        cbxCategoria = new JComboBox<>(new String[]{
            "Todas", "Herramientas Manuales", "Equipos Electrónicos", 
            "Instrumentos de Medición", "Equipos de Cómputo", "Material de Laboratorio"
        });
        cbxCategoria.setPreferredSize(new Dimension(180, 30)); 
        cbxCategoria.setMaximumSize(new Dimension(180, 30));
        cbxCategoria.setBackground(Color.WHITE);
        cbxCategoria.setAlignmentX(Component.LEFT_ALIGNMENT);
        grupoCategoria.add(textoCategoria);
        grupoCategoria.add(Box.createVerticalStrut(5));
        grupoCategoria.add(cbxCategoria);

        // Filtro Estado
        JPanel grupoEstado = new JPanel();
        grupoEstado.setLayout(new BoxLayout(grupoEstado, BoxLayout.Y_AXIS));
        grupoEstado.setOpaque(false);
        JLabel textoEstado = new JLabel("Estado");
        textoEstado.setFont(new Font("Segoe UI", Font.BOLD, 12));
        textoEstado.setForeground(Color.GRAY);
        textoEstado.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        cbxEstado = new JComboBox<>(new String[]{
            "Todos", "Disponible", "Prestado", "En Mantenimiento", "Dañado", "Dado de Baja"
        });
        cbxEstado.setPreferredSize(new Dimension(140, 30));
        cbxEstado.setMaximumSize(new Dimension(140, 30));
        cbxEstado.setBackground(Color.WHITE);
        cbxEstado.setAlignmentX(Component.LEFT_ALIGNMENT);
        grupoEstado.add(textoEstado);
        grupoEstado.add(Box.createVerticalStrut(5));
        grupoEstado.add(cbxEstado);
        
        panelFiltros.add(grupoCategoria);
        panelFiltros.add(grupoEstado);

        barraSuperior.add(panelBusqueda, BorderLayout.WEST);
        barraSuperior.add(panelFiltros, BorderLayout.EAST);
        
        JPanel panelParaTabla = new JPanel(new BorderLayout());
        panelParaTabla.setOpaque(false);
        
        JLabel lbTituloListado = new JLabel("Listado de elementos");
        lbTituloListado.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbTituloListado.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        panelParaTabla.add(lbTituloListado, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Elemento");
        modeloTabla.addColumn("Categoría");
        modeloTabla.addColumn("Estado");
        modeloTabla.addColumn("Descripción");
        
        tablaDeElementos = new JTable(modeloTabla);
        tablaDeElementos.setRowHeight(40);
        tablaDeElementos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaDeElementos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaDeElementos.getTableHeader().setReorderingAllowed(false);
        tablaDeElementos.setGridColor(new Color(230, 230, 233));
        
        JScrollPane scrollTabla = new JScrollPane(tablaDeElementos);
        scrollTabla.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true));
        scrollTabla.getViewport().setBackground(Color.WHITE);
        
        panelParaTabla.add(scrollTabla, BorderLayout.CENTER);
        
        JLabel textoDeConsejo = new JLabel("Consejo: Haz doble clic sobre cualquier fila para editar el nombre, descripción o estado del elemento.");
        textoDeConsejo.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        textoDeConsejo.setForeground(Color.GRAY);
        textoDeConsejo.setBorder(BorderFactory.createEmptyBorder(8, 5, 0, 0));
        panelParaTabla.add(textoDeConsejo, BorderLayout.SOUTH);
        
        panelPrincipalCentrado.add(barraSuperior, BorderLayout.NORTH);
        panelPrincipalCentrado.add(panelParaTabla, BorderLayout.CENTER);
        
        return panelPrincipalCentrado;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Vista_Inventario().setVisible(true));
    }
}