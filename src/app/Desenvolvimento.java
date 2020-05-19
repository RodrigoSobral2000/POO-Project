package app;

/**
 * Desenvolvimento
 */
public final class Desenvolvimento extends Tarefa {

    private static final long serialVersionUID = 1L;
    
    /** 
     * @return Taxa de Esforço duma Tarefa de Desenvolvimento, 1
     */
    @Override
    public float getTaxa_esforco() {
        return 1;
    }
    
    /** 
     * @param data_inicio Data de Ínicio duma Tarefa de Desenvolvimento
     * @param duracao Duração duma Tarefa de Desenvolvimento
     * @return Um objeto Desenvolvimento
     */
    public Desenvolvimento(Data data_inicio, int duracao) {
        super(data_inicio, duracao);
    }
    
}