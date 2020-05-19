package app;

import java.io.Serializable;
import java.text.*;
import java.util.*;

/**
 * Data
 */

public final class Data implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * String que armazena uma Data
     */
    private String data;

    
    /** 
     * @return Uma Data num Formato String
     */
    public String getData() {
        return data;
    }
    
    /** 
     * @param data Uma nova Data
     */
    public void setData(String data) {
        this.data = data;
    }
    
    
    /** 
     * @param data Data 
     * @return Um objeto Data
     */
    public Data(String data) {
        this.data = data;
    }

    
    /** 
     * @return TRUE se a Data está num formato dd/mm/aaaa, FALSE se não estiver
     */
    public Boolean dataVerifica(){
        try{
            //utilizado para formação de datas
            SimpleDateFormat formato_data = new SimpleDateFormat("dd/MM/yyyy");
            //aqui passa para falso quer dizer que não se aceitam datas que não existem
            formato_data.setLenient(false);
            //converte a string no tipo data com o certo formato, se funcionar a data é invalida
            formato_data.parse(this.data);
            return true;
        } catch(ParseException ex){
            return false;
        }
    }
    
    /** 
     * @param data2 Data à qual vamos fazer a comparação
     * @return 0 se Data=data2, -1 se data2>Data, 1 se Data>data2
     * @throws ParseException
     */
    public int compara_datas(Data data2) throws ParseException {
        SimpleDateFormat dfs = new SimpleDateFormat("dd/MM/yyyy");
        Date dt1= dfs.parse(getData());
        Date dt2= dfs.parse(data2.getData());  
        //são iguais
        if(dt1.compareTo(dt2)==0) return 0;
        //data1 é maior
        else if(dt1.compareTo(dt2) >0) return 1;
        //data2 é maior
        else return -1;
    }
    
    /** 
     * @param incremento Número de Dias que vamos incrementar a uma Data
     * @return String Data com os Dias incrementados
     * @throws ParseException
     */
    public String incrementaDataDia(int incremento) throws ParseException {
        SimpleDateFormat formato_data = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        Date dt2 = formato_data.parse(this.data);
        cal.setTime(dt2);
        cal.add(Calendar.DAY_OF_MONTH, incremento);
        return formato_data.format(cal.getTime());
    }
         
}