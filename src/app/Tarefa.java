package app;

import java.io.Serializable;

/**
 * Tarefa
 */
public abstract class Tarefa implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private Data inicio_tarefa, fim_tarefa;
    private int duracao_estimada;
    private float taxa_execucao;
    private Pessoa responsavel;

    
    /** 
     * @return Data de Início duma Tarefa
     */
    public Data getInicio_tarefa() {
        return this.inicio_tarefa;
    }
    
    /** 
     * @return Data de Fim duma Tarefa
     */
    public Data getFim_tarefa() {
        return this.fim_tarefa;
    }
    
    /** 
     * @return Duração duma Tarefa
     */
    public int getDuracao() {
        return this.duracao_estimada;
    }
    
    /** 
     * @return Taxa de Execução duma Tarefa
     */
    protected float getTaxa_execucao() {
        return this.taxa_execucao;
    }
    
    /** 
     * @return Pessoa Responsável por uma Tarefa
     */
    public Pessoa getResponsavel() {
        return this.responsavel;
    }
    
    /** 
     * @param responsavel Define uma nova Pessoa Responsável por uma Tarefa
     */
    public void setResponsavel(Pessoa responsavel) {
        this.responsavel = responsavel;
    }
    
    /** 
     * @return Taxa de Esforço duma Tarefa (método abstrato), -1
     */
    public float getTaxa_esforco() {
        return -1;
    }

    
    /** 
     * @param inicio_bolsa Data de Início da Tarefa
     * @param duracao_estimada Duração Estimada da Tarefa (em dias)
     * @return Um novo objeto Tarefa
     */
    protected Tarefa(Data inicio_bolsa, int duracao_estimada) {
        this.inicio_tarefa = inicio_bolsa;
        this.duracao_estimada = duracao_estimada;
        this.taxa_execucao = 0;
    }

    
    /** 
     * @param data_fim Data Fim será a Data Atual caso a Taxa de Execução da Tarefa seja de 100%
     */
    protected void atualizaDataFim(String data_fim) {
        if (getTaxa_execucao()==100) this.fim_tarefa= new Data(data_fim);
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Duracao_estimada= " + duracao_estimada + "\nFim_tarefa= " + fim_tarefa + "\nInicio_tarefa= "
                + inicio_tarefa + "\nResponsavel=" + responsavel + "\nTaxa_execucao=" + taxa_execucao;
    }

}