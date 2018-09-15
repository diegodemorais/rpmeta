package date;

/**
 *
 * @author dm
 */
public final class DataPorcentagem {
    public String dtAtualIni, dtAtualFim, dtAnteriorIni, dtAnteriorFim, dtAnteriorIntIni, dtAnteriorIntFim, dtReferencia;
    
    public DataPorcentagem(){
        dtAtualIni = this.dtAtualIni();
        dtAtualFim = this.dtAtualFim();
        dtAnteriorIni = this.dtAnteriorIni();
        dtAnteriorFim = this.dtAnteriorFim();
        dtAnteriorIntIni = this.dtAnteriorIntIni();
        dtAnteriorIntFim = this.dtAnteriorIntFim();
        dtReferencia = dtAtualFim;
    }
    
    public String dtAtualIni(){
        String dt = Data.primeiroDiaMes(Data.referencia())
                .format(Data.formatarMMddyyyy());
        return dt;
    }
    
    public String dtAtualFim(){
        String dt = Data.referencia()
                .format(Data.formatarMMddyyyy());
        return dt;
    }
   
    public String dtAnteriorIni(){
        String dt = Data.primeiroDiaMes(Data.anoAnterior(Data.referencia()))
                .format(Data.formatarMMddyyyy());
        return dt;
    }

    public String dtAnteriorFim(){
        String dt,dtAnt;
        dt = Data.anoAnterior(Data.referencia())
            .format(Data.formatarMMddyyyy());
        
        //Verificando se ano anterior foi bissexto
        dtAnt = this.dtAtualFim();
        if ( dtAnt.substring(0,5).equals("02/28") ) {
            dt = this.dtAnteriorIntFim(); //Se for bissexto, atribui dia 29 para comparar o parcial
        } 
        return dt;
    }    
        
    public String dtAnteriorIntIni(){
        String dt = Data.primeiroDiaMes(Data.anoAnterior(Data.referencia()))
                .format(Data.formatarMMddyyyy());
        return dt;
    }
        
    public String dtAnteriorIntFim(){
        String dt = Data.ultimoDiaMes(Data.anoAnterior(Data.referencia()))
                .format(Data.formatarMMddyyyy());
        return dt;
    }
    
    public String dtReferenciaddMM(){
        String dt = Data.referencia()
            .format(Data.formatarddMM());
        return dt;
    }
    
    public String dtReferenciadd(){
        String dt = Data.referencia()
            .format(Data.formatardd());
        return dt;
    }

    public String dtReferenciaMM(){
        String dt = Data.referencia()
            .format(Data.formatarMM());
        return dt;
    }

    public String dtReferenciayy(){
        String dt = Data.referencia()
            .format(Data.formataryy());
        return dt;
    }    
    
    public String dtReferenciayyyy(){
        String dt = Data.referencia()
            .format(Data.formataryyyy());
        return dt;
    }  
    
    public String dtReferenciaAnterioryy(){
        String dt = Data.ultimoDiaMes(Data.anoAnterior(Data.referencia()))
                .format(Data.formataryy());
        return dt;
    }
    
    public String dtReferenciaUltimoDiaMes(){
        int days = Data.referencia().getMonth().length(true);
        return String.valueOf(days);
    }
    
}
