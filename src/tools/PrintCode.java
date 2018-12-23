package tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.OrientationRequested;

/**
 * This class prints the code in the selected printer.
 * Some options are allowed, like paper size, orientation and margins.
 * It can be printed on black and white or in colors.
 * @author Diego Henrique Oliveira de Souza
 */
public class PrintCode implements Printable {
    private String text = "";

    /**
     * This method receives the text that will be printed as a parameter
     * and calls the configuration windows to the user do the wished
     * choices.
     * @param texto
     */
    public PrintCode(String texto) {
        text += texto;
        //texto.replaceAll("\n", )
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(OrientationRequested.PORTRAIT);
        aset.add(new Copies(1));
        aset.add(new JobName("LOTOS Specification", null));
        /*
         * Cria um "Printer job"
         */
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(this);

        /*
         * Localiza um serviço de impressão
         * que possa tratar esta requisção.
         */
        PrintService[] services = PrinterJob.lookupPrintServices();

        if (services.length > 0) {
            //System.out.println("Impressora seleionada" + services[0].getName());
            try {
                pj.setPrintService(services[0]);
                pj.pageDialog(aset);
                if (pj.printDialog(aset)) {
                    pj.print(aset);
                }
            } catch (PrinterException pe) {
                //System.err.println(pe);
            }
        }
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) {

        if (pageIndex == 0) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pf.getImageableX(), pf.getImageableY());
            g2d.setColor(Color.black);

            int y = 1;
            int x = 1;
            for (int i=0; i<text.length()-2; i++) {
                if(text.substring(i, i+1).equals("\n")){
                    y += 1;
                    x = 1;
                }
                g2d.drawString(text.substring(i, i+1), 7*x++, 12*y);
            }

            return Printable.PAGE_EXISTS;
        } else {
            return Printable.NO_SUCH_PAGE;
        }
    }
}