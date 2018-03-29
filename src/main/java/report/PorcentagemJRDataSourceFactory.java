/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import entity.Porcentagem;
import java.util.ArrayList;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;

/**
 *
 * @author dm
 */
public class PorcentagemJRDataSourceFactory {
    private JRDataSource data;

    public JRDataSource createDatasource(Map<String,String> quebra,Map<String,Float> anteriorInt, Map<String,Float> anterior, Map<String,Float> atual, Map<String,Float> meta, Map<String,Float> dia,  Map<String,String> superv) {
        if (data == null) {
            ArrayList<Porcentagem> lista =new ArrayList<>();
            for (Map.Entry<String,String> entry : quebra.entrySet()) {
                Porcentagem perc = new Porcentagem();        
                perc.setB(entry.getKey());
                perc.setQuebra(entry.getValue());
                perc.setSuperV(superv.get(perc.getB()));                                
                perc.setAnteriorInt(anteriorInt.get(perc.getB()));                
                perc.setAnterior(anterior.get(perc.getB()));
                perc.setAtual(atual.get(perc.getB()));
                perc.setMeta(meta.get(perc.getB()));
                perc.setDia(dia.get(perc.getB()));                
//                perc.print();
                lista.add(perc);
            }
            data = new PorcentagemJRDataSource(lista);
        }
        return data;
    }
    
    public JRDataSource createDatasource(Map<String,String> quebra,Map<String,Float> anteriorInt, Map<String,Float> anterior, Map<String,Float> atual, Map<String,Float> meta, Map<String,Float> dia,  Map<String,String> superv, Map<String,String> extra) {
        if (data == null) {
            ArrayList<Porcentagem> lista =new ArrayList<>();
            for (Map.Entry<String,String> entry : quebra.entrySet()) {
                Porcentagem perc = new Porcentagem();        
                perc.setB(entry.getKey());
                perc.setQuebra(entry.getValue());
                perc.setSuperV(superv.get(perc.getB()));                                
                perc.setAnteriorInt(anteriorInt.get(perc.getB()));                
                perc.setAnterior(anterior.get(perc.getB()));
                perc.setAtual(atual.get(perc.getB()));
                perc.setMeta(meta.get(perc.getB()));
                perc.setDia(dia.get(perc.getB()));
                perc.setExtra(extra.get(perc.getB()));
//                perc.print();
                lista.add(perc);
            }
            data = new PorcentagemJRDataSource(lista);
        }
        return data;
    }

}




