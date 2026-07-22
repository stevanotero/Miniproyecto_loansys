/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

/**
 *
 * @author juans
 */
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class PDFExporter {

    private static final Font TITULO_FONT = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
    private static final Font SUBTITULO_FONT = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
    private static final Font NORMAL_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

    public static boolean generarPDF(JTable tabla, String tituloReporte) {
        try {
            String fecha = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String nombreArchivo = "Reporte_" + tituloReporte.replace(" ", "_") + "_" + fecha + ".pdf";

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));
            document.open();

            // Título principal
            Paragraph titulo = new Paragraph("SISTEMA DE PRÉSTAMOS SENA", TITULO_FONT);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            Paragraph subtitulo = new Paragraph(tituloReporte, SUBTITULO_FONT);
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            subtitulo.setSpacingAfter(20);
            document.add(subtitulo);

            // Fecha
            Paragraph fechaP = new Paragraph("Fecha: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()), NORMAL_FONT);
            fechaP.setAlignment(Element.ALIGN_RIGHT);
            document.add(fechaP);

            document.add(new Paragraph("\n"));

            // Crear tabla PDF
            int columnas = tabla.getColumnCount();
            PdfPTable pdfTable = new PdfPTable(columnas);
            pdfTable.setWidthPercentage(100);

            // Encabezados
            for (int i = 0; i < columnas; i++) {
                pdfTable.addCell(new com.itextpdf.text.Phrase(tabla.getColumnName(i), SUBTITULO_FONT));
            }

            // Datos
            for (int fila = 0; fila < tabla.getRowCount(); fila++) {
                for (int col = 0; col < columnas; col++) {
                    Object valor = tabla.getValueAt(fila, col);
                    pdfTable.addCell(valor != null ? valor.toString() : "");
                }
            }

            document.add(pdfTable);
            document.close();

            JOptionPane.showMessageDialog(null, "Reporte guardado como:\n" + nombreArchivo, "PDF Generado", JOptionPane.INFORMATION_MESSAGE);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
