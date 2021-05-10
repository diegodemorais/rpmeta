package date;

/**
 *
 * @author dm
 */
public final class DataPorcentagem {
    public String dtAtualIni, dtAtualFim, dtAnteriorIni, dtAnteriorFim, dtAnteriorIntIni, dtAnteriorIntFim, dtReferencia;
    
    public DataPorcentagem(int anoAnterior){
        dtAtualIni = this.dtAtualIni();
        dtAtualFim = this.dtAtualFim();
        dtAnteriorIni = this.dtAnteriorIni(anoAnterior);
        dtAnteriorFim = this.dtAnteriorFim(anoAnterior);
        dtAnteriorIntIni = this.dtAnteriorIntIni(anoAnterior);
        dtAnteriorIntFim = this.dtAnteriorIntFim(anoAnterior);
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
   
    public String dtAnteriorIni(int anoAnterior){
        String dt = Data.primeiroDiaMes(Data.anoAnterior(Data.referencia(), anoAnterior))
                .format(Data.formatarMMddyyyy());
        return dt;
    }

    public String dtAnteriorFim(int anoAnterior){
        String dt,dtAnt;
        dt = Data.anoAnterior(Data.referencia(), anoAnterior)
            .format(Data.formatarMMddyyyy());
        
        //Verificando se ano anterior foi bissexto
        dtAnt = this.dtAtualFim();
        if ( dtAnt.substring(0,5).equals("02/28") ) {
            dt = this.dtAnteriorIntFim(anoAnterior); //Se for bissexto, atribui dia 29 para comparar o parcial
        } 
        return dt;
    }    
        
    public String dtAnteriorIntIni(int anoAnterior){
        String dt = Data.primeiroDiaMes(Data.anoAnterior(Data.referencia(), anoAnterior))
                .format(Data.formatarMMddyyyy());
        return dt;
    }
        
    public String dtAnteriorIntFim(int anoAnterior){
        String dt = Data.ultimoDiaMes(Data.anoAnterior(Data.referencia(), anoAnterior))
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
    
    public String dtReferenciaAnterioryy(int anoAnterior){
        String dt = Data.ultimoDiaMes(Data.anoAnterior(Data.referencia(),anoAnterior))
                .format(Data.formataryy());
        return dt;
    }
    
    public String dtReferenciaUltimoDiaMes(){
        int days = Data.referencia().getMonth().length(true);
        return String.valueOf(days);
    }
    
}
