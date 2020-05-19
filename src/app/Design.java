package app;

import java.io.Serializable;

/**
 * Design
 */
public final class Design extends Tarefa implements Serializable{

    private static final long serialVersionUID = 1L;

    /** 
     * @return Taxa de Esforço duma Tarefa de Design, 0.5
     */
    @Override
    public float getTaxa_esforco() {
        return 0.5f;
    }

    
    /** 
     * @param data_inicio Data de Ínicio duma Tarefa de Design
     * @param duracao Duração duma Tarefa de Design
     * @return Um objeto Design
     */
    public Design(Data data_inicio, int duracao) {
        super(data_inicio, duracao);
    }
    
}