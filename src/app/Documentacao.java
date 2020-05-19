package app;

import java.io.Serializable;

/**
 * Documentacao
 */
public final class Documentacao extends Tarefa implements Serializable{

    private static final long serialVersionUID = 1L;
    
    /** 
     * @return Taxa de Esforço duma Tarefa de Documentação, 0.25
     */
    @Override
    public float getTaxa_esforco() {
        return 0.25f;
    }

    /** 
     * @param data_inicio Data de Ínicio duma Tarefa de Documentação
     * @param duracao Duração duma Tarefa de Documentação
     * @return Um objeto Documentação
     */
    public Documentacao(Data data_inicio, int duracao) {
        super(data_inicio, duracao);
    }

}