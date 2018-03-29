
package report;

/**
 *
 * @author dm
 */
import entity.Porcentagem;
import java.util.ArrayList;
import java.util.Iterator;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class PorcentagemJRDataSource implements JRDataSource {

	private final Iterator<Porcentagem> iterator;
	private Porcentagem cursor;

	public PorcentagemJRDataSource(ArrayList<Porcentagem> perc) {
            super();
            iterator = perc.iterator();
	}

        @Override
	public boolean next() throws JRException {
            boolean retorno = iterator.hasNext();
            if(retorno){
                    cursor=iterator.next();
            }
            return retorno;
	}

//        @Override
//	public Object getFieldValue(JRField nome) throws JRException {
//            Porcentagem perc = cursor;
//            if (nome.getName().equals("B")) {
//                    return perc.getB();
//            }
//            if (nome.getName().equals("Quebra")) {
//                    return perc.getQuebra();
//            }
//            if (nome.getName().equals("SuperV")) {
//                    return perc.getSuperV();
//            }              
//            try {
//                if (nome.getName().equals("AnteriorInt")) {
//                        return (perc.getAnteriorInt()/1000);
//                }
//            } catch (Exception e) {
//                System.err.println(nome.getName() + "(" + perc.getB() + "):" + e.getMessage());
//            }
//            try {
//                if (nome.getName().equals("Anterior")) {
//                        return (perc.getAnterior()/1000);
//                }
//            } catch (Exception e) {
//                System.err.println(nome.getName() + "(" + perc.getB() + "):" + e.getMessage());
//            }
//            try {
//                if (nome.getName().equals("Atual")) {
//                        return (perc.getAtual()/1000);
//                }
//            } catch (Exception e) {
//                System.err.println(nome.getName() + "(" + perc.getB() + "):" + e.getMessage());
//            }
//            try {
//                if (nome.getName().equals("Meta")) {
//                        return perc.getMeta();
//                }
//            } catch (Exception e) {
//                System.err.println(nome.getName() + "(" + perc.getB() + "):" + e.getMessage());
//            }
//            try {
//                if (nome.getName().equals("Dia")) {
//                        return (perc.getDia()/1000);
//                }
//            } catch (Exception e) {
//                System.err.println(nome.getName() + "(" + perc.getB() + "):" + e.getMessage());
//            }                
//            return null;
//	}
        
        @Override
	public Object getFieldValue(JRField nome) throws JRException {
            Porcentagem perc = cursor;
            if (nome.getName().equals("B")) {
                    return perc.getB();
            }
            if (nome.getName().equals("Quebra")) {
                    return perc.getQuebra();
            }
            if (nome.getName().equals("SuperV")) {
                    return perc.getSuperV();
            }   
            if (nome.getName().equals("Extra")) {
                return perc.getExtra();
            } 
            try {
                if (nome.getName().equals("AnteriorInt")) {
                        return (perc.getAnteriorInt()/1000);
                }
                if (nome.getName().equals("Anterior")) {
                        return (perc.getAnterior()/1000);
                }       
                if (nome.getName().equals("Atual")) {
                        return (perc.getAtual()/1000);
                }    
                if (nome.getName().equals("Meta")) {
                        return perc.getMeta();
                }  
                if (nome.getName().equals("Dia")) {
                        return (perc.getDia()/1000);
                }                
            } catch (Exception e) {
                System.err.println(nome.getName() + "(" + perc.getB() + "):" + e.getMessage());
            }
            return null;
	}        

}
