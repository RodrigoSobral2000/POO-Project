package app;

import java.io.Serializable;

/**
 * Mestre
 */
public final class Mestre extends Formando implements Serializable{

    private static final long serialVersionUID = 1L;

    /** 
     * @return Custo que um Mestre tem para o Projeto, 1000€
     */
    @Override
    public float getCusto_projeto() {
        return 1000;
    }

    /** 
     * @param nome Nome do novo Mestre
     * @param email Email do novo Mestre
     * @param inicio_bolsa Data de Ínicio da Bolsa do Mestre
     * @param fim_bolsa Data de Fim da Bolsa do Mestre
     * @return Um objeto Mestre
     */
    public Mestre(String nome, String email, String inicio_bolsa, String fim_bolsa) {
        super(nome, email, inicio_bolsa, fim_bolsa);
    }

}