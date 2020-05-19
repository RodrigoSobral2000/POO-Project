package app;

/**
 * Bolseiro
 */
public abstract class Bolseiro extends Pessoa {
    
    private static final long serialVersionUID = 1L;
    
    private Data inicio_bolsa, fim_bolsa;
    
    /** 
     * @return Data de Ínicio da Bolsa do Bolseiro
     */
    public Data getInicio_bolsa() {
        return inicio_bolsa;
    }
    
    /** 
     * @return Data de Fim da Bolsa do Bolseiro
     */
    public Data getFim_bolsa() {
        return fim_bolsa;
    }
    
    
    /** 
     * @param nome Nome do Bolseiro
     * @param email Email do Bolseiro
     * @param inicio_bolsa Data de Ínicio da sua Bolsa
     * @param fim_bolsa Data de Fim da sua Bolsa
     * @return Um objeto Bolseiro
     */
    protected Bolseiro(String nome, String email, String inicio_bolsa, String fim_bolsa) {
        super(nome, email);
        this.inicio_bolsa = new Data(inicio_bolsa);
        this.fim_bolsa = new Data(fim_bolsa);
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return super.toString()+
        "\nData Inicio Bolsa: "+getInicio_bolsa()+
        "\nData Fim Bolsa: "+getFim_bolsa();
    }

}