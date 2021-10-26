/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.sys;

import bd.Firebird;
import util.Config;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dm
 */
public class Millennium {
    
   private static Firebird MW;
   
   public Millennium(){
       String ipMillennium = Config.IP_MILLENNIUM;
       String bancoMillennium = Config.BANCO_MILLENNIUM;
        String userMillennium = Config.USER_MILLENNIUM;
        String passMillennium = Config.PASS_MILLENNIUM;
        MW = new Firebird(ipMillennium,bancoMillennium,userMillennium,passMillennium);
        MW.connect();
   } 
   
   public ResultSet todasB(){
       ResultSet rs;
       String sql;
       sql = "SELECT f.cod_filial, f.nome\n" +
                "FROM filiais f\n" +
                "WHERE cod_filial IS NOT NULL\n" +
                "AND cel IS NOT NULL\n" +
                "ORDER BY cod_filial;";
       rs = MW.executarQuery(sql);
       return rs;
   }
   
   
   
   public Map<String,Float> codB(String dataInicio, String dataFim){
       String sql;
       ResultSet rs;
       Map<String,Float> map = new HashMap<>();
       
       sql = "select venda_filial_filial_Filiais.cod_filial  as B , " +
                "     coalesce ( " +
                "        sum (  " +
                "    case when venda_venda_produtos_eventos.tipo_operacao = 'E' " +
                "         then venda_venda_produtos_eventos.QUANTIDADE * -1  " +
                "         else venda_venda_produtos_eventos.QUANTIDADE end " +
                "        * venda_venda_produtos_eventos.preco * venda_venda_movimento.valor_final /  " +
                "        case when venda_venda_movimento.total = 0  " +
                "             then null  " +
                "             else (  " +
                "                select sum ( pe.QUANTIDADE * pe.preco )  " +
                "                from produtos_eventos pe  " +
                "                where pe.tipo_operacao = venda_venda_movimento.tipo_operacao " +
                "                and pe.cod_operacao = venda_venda_movimento.cod_operacao   ) " +
                "        end ) , 0 )  as COD " +
                "from movimento venda_venda_movimento  " +
                "     inner join eventos venda_venda_eventos on ( ( venda_venda_movimento.EVENTO = venda_venda_eventos.EVENTO ) ) " +
                "     inner join produtos_eventos venda_venda_produtos_eventos on ( ( venda_venda_movimento.tipo_operacao = venda_venda_produtos_eventos.tipo_operacao and          venda_venda_movimento.cod_operacao = venda_venda_produtos_eventos.cod_operacao ) ) " +
                "     inner join Filiais venda_filial_filial_Filiais on ( ( venda_venda_movimento.FILIAL = venda_filial_filial_Filiais.FILIAL ) )  " +
                "WHERE ( ( ( venda_filial_filial_Filiais.cod_filial <> 'FILIAL TESTE' ) " +
                "AND ( venda_venda_movimento.DATA between cast ( '"+ dataInicio +"' as date ) and cast ( '"+ dataFim +"' as date ) ) ) ) " +
                "AND ( ( ( venda_venda_eventos.tipo_saida = 'V' AND venda_venda_eventos.tipo_evento = 'S' ) OR ( venda_venda_eventos.tipo_entrada = 'V' AND      venda_venda_eventos.tipo_evento = 'E' ) ) AND venda_venda_movimento.CANCELADA = 'F' )  " +
                "group by venda_filial_filial_Filiais.cod_filial , venda_venda_movimento.FILIAL";
        rs = MW.executarQuery(sql);
        
        try {   
            while (rs.next()){
                map.put(rs.getString("B"),rs.getFloat("COD"));
            }
        } catch (Exception e) {
            System.err.println("Error :" + e.getMessage());
        }       
        
        return map;
    }
   
   public ResultSet despesas(String B){
        String sql, sqlIni, sqlCrit, sqlFim;
        ResultSet rs;
        
        sqlIni = "SELECT v.lancamento_filial_filial_nome AS B, " +
                    "       v.F_1907459465 AS DATA, " +
                    "       v.lancamento_lancamento_OBS AS DESCRICAO, " +
                    "                COALESCE (SUM (v.F_2714866558) , 0) AS VALOR " +
                    "FROM " +
                    "  (SELECT DISTINCT F_2463272603.COD_FILIAL AS lancamento_filial_filial_nome, " +
                    "                   F_3915621685.F_1907459465 AS F_1907459465, " +
                    "                   F_3915621685.lancamento_lancamento_OBS AS lancamento_lancamento_OBS, " +
                    "                   F_3915621685.F_2282248934 AS F_2282248934, " +
                    "                   F_3915621685.F_3518719985 AS F_3518719985, " +
                    "                   COALESCE (F_3915621685.sum_lancamento_VALOR_INICIAL_, " +
                    "                             0) AS sum_lancamento_VALOR_INICIAL_, " +
                    "                            CAST (NULL AS double precision) AS F_2714866558 " +
                    "   FROM TMP_DESPESA_3 F_3915621685 " +
                    "   INNER JOIN lancamentos F_3233442989 ON (((F_3915621685.F_3518719985 = F_3233442989.LANCAMENTO " +
                    "                                             AND F_3915621685.lancamento_lancamento_OBS = F_3233442989.OBS))) " +
                    "   INNER JOIN Filiais F_2463272603 ON (((F_3915621685.F_2282248934 = F_2463272603.FILIAL))) " +
                    "   UNION ALL SELECT DISTINCT F_2463272603.COD_FILIAL AS lancamento_filial_filial_nome, " +
                    "                             F_1308918612.F_1907459465 AS F_1907459465, " +
                    "                             F_1308918612.lancamento_lancamento_OBS AS lancamento_lancamento_OBS, " +
                    "                             F_1308918612.F_2282248934 AS F_2282248934, " +
                    "                             F_1308918612.F_3518719985 AS F_3518719985, " +
                    "                             CAST (NULL AS double precision) AS sum_lancamento_VALOR_INICIAL_, " +
                    "                                  COALESCE (F_1308918612.F_2714866558, " +
                    "                                            0) AS F_2714866558 " +
                    "   FROM TMP_DESPESA_2 F_1308918612 " +
                    "   INNER JOIN lancamentos F_3233442989 ON (((F_1308918612.F_3518719985 = F_3233442989.LANCAMENTO " +
                    "                                             AND F_1308918612.lancamento_lancamento_OBS = F_3233442989.OBS))) " +
                    "   INNER JOIN Filiais F_2463272603 ON (((F_1308918612.F_2282248934 = F_2463272603.FILIAL)))) v " +
                    "   WHERE LANCAMENTO_FILIAL_FILIAL_NOME = " + B + " ";

        sqlCrit = despesasCriterios(B);
        sqlFim = " group by v.lancamento_filial_filial_nome , v.F_1907459465 , v.lancamento_lancamento_OBS , v.F_2282248934 , v.F_3518719985;";
        
        sql = sqlIni + sqlCrit + sqlFim;
        
        rs = MW.executarQuery(sql);
        return rs;
   }
  
   public String despesasCriterios(String B){
       String sql;
       sql =  "";

       return  sql;
       
//    wSQLcrit = ""
//    Dim I As Integer
//    With lstCriterios
//        For I = 0 To .ListCount - 1
//        wSQLcrit = wSQLcrit & " AND UPPER(lancamento_lancamento_OBS) NOT LIKE '" & UCase(.list(I)) & "%'"
//        'adoDespesas.Recordset.Filter = adoDespesas.Recordset.Filter & " AND (LANCAMENTO_LANCAMENTO_OBS < '" & .List(i) & "' AND LANCAMENTO_LANCAMENTO_OBS > '" & .List(i) & "')"
//    
//    Next
//    End With
       
   }
   
   public void setDespesas(String dataInicio, String dataFim){
       String sql;
       
       MW.executarComando("DELETE FROM TMP_DESPESA_1;");
       MW.executarComando("DELETE FROM TMP_DESPESA_2;");       
       MW.executarComando("DELETE FROM TMP_DESPESA_3;");       
       
       sql = "insert into TMP_DESPESA_1 ( F_2282248934 , F_1907459465 , LANCAMENTO_LANCAMENTO_OBS , F_3518719985 , " +
               "SUM_LANCAMENTO_VALOR_INICIAL_ ) select F_3233442989.FILIAL  as F_2282248934 , F_3233442989.DATA_EMISSAO  " +
               "as F_1907459465 , F_3233442989.OBS  as lancamento_lancamento_OBS , F_3233442989.LANCAMENTO  as F_3518719985 ," +
               "coalesce ( sum ( F_3233442989.VALOR_INICIAL ) , 0 )  as sum_lancamento_VALOR_INICIAL_ from lancamentos F_3233442989 "+
               "inner join tipos_pgtos F_3462522015 on ( ( F_3233442989.TIPO_PGTO = F_3462522015.TIPO_PGTO ) ) WHERE (  ( " +
               "F_3233442989.DATA_VENCIMENTO between cast ( '" + dataInicio + "' as date ) and cast ( '" + dataFim + "' as date ) ) AND ( F_3233442989.PREVISAO " +
               "= 'F' AND case when ( F_3233442989.tipo = 'P' ) then case when ( F_3233442989.efetuado = 'T' ) then 'PAGO' else 'PAGAR' " +
               "end else case when ( F_3233442989.efetuado = 'T' ) then 'RECEBIDO' else 'RECEBER' end end = 'PAGO' AND F_3462522015.DESCRICAO " +
               "<> 'DEVOLUCAO' ) ) group by F_3233442989.FILIAL , F_3233442989.DATA_EMISSAO , F_3233442989.OBS , F_3233442989.LANCAMENTO";
       MW.executarComando(sql);
       
       sql = "insert into TMP_DESPESA_2 ( F_2282248934 , F_1907459465 , LANCAMENTO_LANCAMENTO_OBS , F_3518719985 , " +
                " F_2714866558 ) select F_397917763.F_2282248934  as F_2282248934 , F_397917763.F_1907459465  as F_1907459465 ," +
                " F_397917763.lancamento_lancamento_OBS  as lancamento_lancamento_OBS , F_397917763.F_3518719985  as F_3518719985 ," +
                " coalesce ( max ( ( ( F_397917763.sum_lancamento_VALOR_INICIAL_ ) * -1 ) ) , 0 )  as F_2714866558 from TMP_DESPESA_1 " +
                " F_397917763 inner join lancamentos F_3233442989 on ( ( F_397917763.F_3518719985 = F_3233442989.LANCAMENTO and " + 
                " F_397917763.lancamento_lancamento_OBS = F_3233442989.OBS ) ) WHERE ( ( F_3233442989.PREVISAO = 'F' AND case " +
                " when ( F_3233442989.tipo = 'P' ) then case when ( F_3233442989.efetuado = 'T' ) then 'PAGO' else 'PAGAR' end " +
                " else case when ( F_3233442989.efetuado = 'T' ) then 'RECEBIDO' else 'RECEBER' end end = 'PAGO' ) ) " +
                "group by F_397917763.F_2282248934 , F_397917763.F_1907459465 , F_397917763.lancamento_lancamento_OBS , F_397917763.F_3518719985";
       MW.executarComando(sql);
       
       sql = "insert into TMP_DESPESA_3 ( F_2282248934 , F_1907459465 , LANCAMENTO_LANCAMENTO_OBS , F_3518719985 , SUM_LANCAMENTO_VALOR_INICIAL_ ) " +
               "select F_3233442989.FILIAL  as F_2282248934 , F_3233442989.DATA_EMISSAO  as F_1907459465 , F_3233442989.OBS  as lancamento_lancamento_OBS , " +
               "F_3233442989.LANCAMENTO  as F_3518719985 , coalesce ( sum ( F_3233442989.VALOR_INICIAL ) , 0 )  as sum_lancamento_VALOR_INICIAL_ from lancamentos " +
               "F_3233442989 inner join tipos_pgtos F_3462522015 on ( ( F_3233442989.TIPO_PGTO = F_3462522015.TIPO_PGTO ) ) WHERE ( (F_3233442989.DATA_VENCIMENTO " +
               "between cast ( '" + dataInicio + "' as date ) and cast ( '" + dataFim + "' as date ) ) AND ( F_3233442989.PREVISAO = 'F' AND case when ( F_3233442989.tipo = 'P' ) " +
               "then case when ( F_3233442989.efetuado = 'T' ) then 'PAGO' else 'PAGAR' end else case when ( F_3233442989.efetuado = 'T' ) " +
               "then 'RECEBIDO' else 'RECEBER' end end = 'PAGO' AND F_3462522015.DESCRICAO <> 'DEVOLUCAO' ) ) group by F_3233442989.FILIAL , " +
               "F_3233442989.DATA_EMISSAO , F_3233442989.OBS , F_3233442989.LANCAMENTO";
       MW.executarComando(sql);
       
   }
   
}

    

