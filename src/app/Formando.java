package app;

import java.io.Serializable;

/**
 * Formando
 */
public abstract class Formando extends Bolseiro implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Docente orientador;

    /** 
     * @return Docente Orientador do Formando
     */
    public Docente getOrientador() {
        return orientador;
    }
    
    /** 
     * @param orientador Novo Docente Orientador do Formando
     */
    protected void setOrientador(Docente orientador) {
        this.orientador = orientador;
    }

    
    /** 
     * @param nome Nome do Formando
     * @param email Email do Formando
     * @param inicio_bolsa Data de Início do Formando
     * @param fim_bolsa Data de Fim do Formando
     * @return Um objeto Formando
     */
    protected Formando(String nome, String email, String inicio_bolsa, String fim_bolsa) {
        super(nome, email, inicio_bolsa, fim_bolsa);
    }
    
}