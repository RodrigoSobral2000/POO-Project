package app;

import java.io.Serializable;

/**
 * Pessoa
 */
public abstract class Pessoa implements Serializable{

   private static final long serialVersionUID = 1L;
    
    private String nome, email;
    private float carga;
    
    /** 
     * @return Nome da Pessoa
     */
    public String getNome() {
        return nome;
    }
    
    /** 
     * @return Email da Pessoa
     */
    public String getEmail() {
        return email;
    }
    
    /** 
     * @return Carga da Pessoa
     */
    public float getCarga() {
        return carga;
    }
    
    /** 
     * @param carga Nova carga diária da Pessoa
     */
    public void setCarga(float carga) {
        this.carga = carga;
    }
    
    /** 
     * @return Custo duma Pessoa para um Projeto (método abstrato), -1€
     */
    protected float getCusto_projeto() {
        return -1;
    }

    
    /** 
     * @param nome Nome da nova Pessoa
     * @param email Email da nova Pessoa
     * @return Um novo objeto Pessoa
     */
    // CONSTRUTOR
    protected Pessoa(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.carga=0;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "\nNome= "+ getNome()+ 
        "\nEmail="+ getEmail()+ 
        "\nCusto Projeto= "+ getCusto_projeto();
    }

}