package app;

import java.io.Serializable;

/**
 * Docente
 */

public final class Docente extends Pessoa implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private int num_mecanografico;
    private String area_investigacao;
    
    /** 
     * @return Número Mecanográfico dum Docente
     */
    public int getNum_mecanografico() {
        return num_mecanografico;
    }
    
    /** 
     * @return Área de Investigação dum Docente
     */
    public String getArea_investigacao() {
        return area_investigacao;
    }
    
    /** 
     * @return Custo que um Docente tem para o Projeto, 0€
     */
    @Override
    public float getCusto_projeto() {
        return 0;
    }

    
    /** 
     * @param nome Nome do novo Docente
     * @param email Email do novo Docente
     * @param num_mecanografico Número Mecanográfico do novo Docente
     * @param area_investigacao Área de Investigação do novo Docente
     * @return Um objeto Docente
     */
    public Docente(String nome, String email, int num_mecanografico, String area_investigacao) {
        super(nome, email);
        this.num_mecanografico = num_mecanografico;
        this.area_investigacao = area_investigacao;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return super.toString()+ 
        "\nArea_investigacao=" + area_investigacao + 
        "\nNum_mecanografico=" + num_mecanografico+"\n";
    }

}