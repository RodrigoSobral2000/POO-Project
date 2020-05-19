package app;

import java.io.Serializable;

/**
 * Licenciado
 */
public final class Licenciado extends Formando implements Serializable{

    private static final long serialVersionUID = 1L;

    
    /** 
     * @return Custo que um Licenciado tem para o Projeto, 800€
     */
    @Override
    public float getCusto_projeto() {
        return 800;
    }

    
    /** 
     * @param nome Nome do novo Licenciado
     * @param email Email do novo Licenciado
     * @param inicio_bolsa Data de Ínicio da Bolsa do Licenciado
     * @param fim_bolsa Data de Fim da Bolsa do Licenciado
     * @return Um objeto Licenciado
     */
    public Licenciado(String nome, String email, String inicio_bolsa, String fim_bolsa) {
        super(nome, email, inicio_bolsa, fim_bolsa);
    }
    
}