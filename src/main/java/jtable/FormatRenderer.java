/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtable;

/**
 *
 * @author dm
 */
import java.awt.Color;
import java.text.Format;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableCellRenderer;
 
/**
 * @author  Roberto Silva 
 * @brief   classe é responsável pela formatação de data, hora
 */
public class FormatRenderer extends DefaultTableCellRenderer {
 
    private final Format formatter;
 
    /*
     * No construtor da classe é passado o formato
     */
    public FormatRenderer(Format formatter) {
        this.formatter = formatter;
    }
 
    @Override
    public void setValue(Object value) {
        //  setando o formato do cellrenderer
        try {
            if (value != null) {
                if ( value instanceof Double && ((Double)value) >= 100.0 ) {
                    setBackground(Color.red );
                    setForeground(Color.white);
                } 
                value = formatter.format(value);                  

            }
        } catch (IllegalArgumentException e) {
        }
        super.setValue(value);
    }
     
    /**
     *  método retorna formato para data
     * @return 
     */
    public static FormatRenderer getDateRenderer() {
        return new FormatRenderer(new SimpleDateFormat("dd/MM/yyyy"));
    }
     
    /**
     *  método retorna formato para data e hora
     * @return 
     */
    public static FormatRenderer getDateTimeRenderer() {
        return new FormatRenderer(DateFormat.getDateTimeInstance());
    }
 
    /**
     *  método retorna formato para hora
     * @return 
     */
    public static FormatRenderer getTimeRenderer() {
        //linha comentada é um formato de horario mais completo
        //return new FormatRenderer(DateFormat.getTimeInstance());
        return new FormatRenderer(new SimpleDateFormat("HH:mm"));
    }
}