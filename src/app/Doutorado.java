package app;

import java.io.Serializable;

/**
 * Doutorado
 */
public final class Doutorado extends Bolseiro implements Serializable{

    private static final long serialVersionUID = 1L;
    
    /** 
     * @return Custo que um Doutorado tem para o Projeto, 1200€
     */
    @Override
    public float getCusto_projeto() {
        return 1200;
    }

    
    /** 
     * @param nome Nome do novo Doutorado
     * @param email Email do novo Doutorado
     * @param inicio_bolsa Data de Ínicio da Bolsa do Doutorado
     * @param fim_bolsa Data de Fim da Bolsa do Doutorado
     * @return Um objeto Doutorado
     */
    public Doutorado(String nome, String email, String inicio_bolsa, String fim_bolsa) {
        super(nome, email, inicio_bolsa, fim_bolsa);
    }

}