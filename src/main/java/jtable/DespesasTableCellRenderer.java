/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtable;

import java.awt.Color;
import java.awt.Component;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author dm
 */
public class DespesasTableCellRenderer extends DefaultTableCellRenderer {
    private final TableCellRenderer delegate;
    
    public DespesasTableCellRenderer(TableCellRenderer defaultRenderer) {
        this.delegate = defaultRenderer;
    }
    

    /**
     * seta formato e tamanho de colunas
     * @param columnModel 
     */
    public void setTableColumnModel(TableColumnModel columnModel){        
        columnModel.getColumn(1).setCellRenderer(FormatRenderer.getDateRenderer());
        columnModel.getColumn(3).setCellRenderer(NumberRenderer.getCurrencyRenderer());
            
        //caso queiram alterar o tamanho das colunas, setar o parâmetro setAutoResizeMode(JTable.AUTO_RESIZE_OFF); da JTable
        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(450);
        columnModel.getColumn(3).setPreferredWidth(100);
}    
    
   
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        //obtendo ColumnModel da JTable e setando formato
        TableColumnModel columnModel = table.getColumnModel();
        setTableColumnModel(columnModel);

        return c;
    }
    
}
