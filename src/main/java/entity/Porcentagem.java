
package entity;


/**
 *
 * @author dm
 */
public class Porcentagem {
    
    public String B, quebra, superv;
    public Float anteriorInt, anterior, atual, meta, dia;
    
    public Porcentagem(){
//        this.clear();
    }
    
//    public void clear(){
//        this.set("XX", 0.0, 0.0, 0.0);
//    }
    
    public String getB(){
        return this.B;
    }
    
    public void setB(String loja){
        this.B = loja;
    }
    
    public String getSuperV(){
        return this.superv;
    }
    
    public void setSuperV(String supervisor){
        this.superv = supervisor;
    }
    
    public String getQuebra(){
        return this.quebra;
    }
    
    public void setQuebra(String quebra){
        this.quebra = quebra;
    }    
    
    public Float getAnteriorInt(){
        return this.anteriorInt;
    }
    
    public void setAnteriorInt(Float valor){
        this.anteriorInt = valor;
    }    
    
    public Float getAnterior(){
        return this.anterior;
    }
    
    public void setAnterior(Float valor){
        this.anterior = valor;
    }       
    
    public Float getAtual(){
        return this.atual;
    }
    
    public void setAtual(Float valor){
        this.atual = valor;
    }   
    
    public Float getMeta(){
        return this.meta;
    }
    
    public void setMeta(Float valor){
        this.meta = valor;
    }      
    
    public Float getDia(){
        return this.dia;
    }
    
    public void setDia(Float valor){
        this.dia = valor;
    } 
    
    public void set(String loja, String supervisor, Float anteriorInt, Float anterior, Float atual, Float meta, Float dia){
        this.setB(loja);
        this.setQuebra(supervisor);
        this.setAnteriorInt(anteriorInt);
        this.setAnterior(anterior);
        this.setAtual(atual);
        this.setAtual(meta);
        this.setDia(dia);        
    }
    
    public boolean print(){
        System.out.println(getQuebra() + " " + getB() + ": " + getDia() + " " + getAnteriorInt() + " " + getAnterior() + " " + getAtual() + " " + getMeta());            
        return true;
    }
            
}