package app;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Projeto
 */
public class Projeto implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private String nome, acronimo;
    private Data data_inicio_projeto, data_fim_projeto;
    private float duracao_estimada;
    private Docente investigador_principal;
    private ArrayList <Docente> lista_docentes;
    private ArrayList <Bolseiro> lista_bolseiros;
    private ArrayList <Tarefa> lista_tarefas;
    private boolean encerrado;

    
    /** 
     * @return Nome dum Projeto
     */
    public String getNome() {
        return nome;
    }
    
    /** 
     * @return Acrónimo dum Projeto
     */
    public String getAcronimo() {
        return this.acronimo;
    }
    
    /** 
     * @return Data de Início dum Projeto
     */
    public Data getData_inicio_projeto() {
        return this.data_inicio_projeto;
    }
    
    /** 
     * @return Data de Fim dum Projeto, definida quando todas as Tarefas tiverem Taxa de Execução a 100%
     */
    public Data getData_fim_projeto() {
        return this.data_fim_projeto;
    }
    
    /** 
     * @return Duração estimada dum Projeto
     */
    public float getDuracao_estimada() {
        return duracao_estimada;
    }
    
    /** 
     * @return Investigador Principal dum Projeto
     */
    public Docente getInvestigador_principal() {
        return investigador_principal;
    }
    
    /** 
     * @param investigador_principal Define um novo Docente Investigador Principal dum Projeto
     */
    public void setInvestigador_principal(Docente investigador_principal) {
        this.investigador_principal = investigador_principal;
    }
    
    /** 
     * @return Lista de Docentes dum Projeto
     */
    public ArrayList<Docente> getLista_docentes() {
        return lista_docentes;
    }
    
    /** 
     * @return Lista de Bolseiros dum Projeto
     */
    public ArrayList<Bolseiro> getLista_bolseiros() {
        return lista_bolseiros;
    }
    
    /** 
     * @return Lista de Tarefas dum Projeto
     */
    public ArrayList<Tarefa> getLista_tarefas() {
        return lista_tarefas;
    }
    
    /** 
     * @return TRUE se o Projeto está encerrado, FALSE se não
     */
    public boolean isEncerrado() {
        return encerrado;
    }
    
    
    /** 
     * @param nome_projeto Nome do Projeto
     * @return Acrónimo com as iniciais do Projeto
     */    
    private String nomeAcronimo(String nome_projeto){
        String lista[] = nome_projeto.split(" ");
        String acronimo="";
        for(int i=0;i<lista.length;i++) acronimo+=lista[i].charAt(0);
        return acronimo;
    }
    
    /** 
     * @param c_or_ni Se TRUE, são as Tarefas Concluídas, se FALSE, são as Tarefas Não Inicializadas
     * @return Lista de Tarefas Concluídas ou Não Inicializadas
     */
    public ArrayList<Tarefa> listarTarefasCOrNI(boolean c_or_ni) {
        ArrayList<Tarefa> tarefas_c_ni= new ArrayList<>();
        for (int i=0; i<getLista_tarefas().size(); i++) {
            if (c_or_ni==true && getLista_tarefas().get(i).getTaxa_execucao()==100) tarefas_c_ni.add(getLista_tarefas().get(i));
            else if (c_or_ni==false && getLista_tarefas().get(i).getTaxa_execucao()==0) tarefas_c_ni.add(getLista_tarefas().get(i));
            else continue;
        }
        return tarefas_c_ni;
    }
    
    /** 
     * @param dia_atual Dia atual
     * @param mes_atual Mes atual
     * @param ano_atual Ano atual
     * @return Lista de Tarefas Não Concluídas dentro da data estimada
     * @throws ParseException
     */
    public ArrayList<Tarefa> listarTarefasNC(byte dia_atual, byte mes_atual, int ano_atual) throws ParseException {
        Data data_atual= new Data(Integer.toString(dia_atual)+"/"+Integer.toString(mes_atual)+"/"+Integer.toString(ano_atual));
        Data data_estimada_fim;
        ArrayList<Tarefa> lista_tarefas_nconc= new ArrayList<>();
        for (int i=0; i<getLista_tarefas().size(); i++) {
            data_estimada_fim= new Data(getLista_tarefas().get(i).getInicio_tarefa().incrementaDataDia(getLista_tarefas().get(i).getDuracao()));
            if (getLista_tarefas().get(i).getTaxa_execucao()<100 && getLista_tarefas().get(i).getTaxa_execucao()>0 && data_atual.compara_datas(data_estimada_fim)>0) {
                lista_tarefas_nconc.add(getLista_tarefas().get(i));
            }
        }
        return lista_tarefas_nconc;
    }
    
    /** 
     * @param responsavel Pessoa que será Responsável por uma Tarefa
     * @param tarefa Tarefa que será responsabilizada
     * @param dia Dia 
     * @param mes
     * @param ano
     * @throws ParseException
     */
    public void atribuirTarefa(Pessoa responsavel, Tarefa tarefa, byte dia, byte mes, int ano) throws ParseException {
        Data data_atual= new Data(Byte.toString(dia)+"/"+Byte.toString(mes)+"/"+Integer.toString(ano));
        String mensagem_erro;
        if (tarefa.getResponsavel()!=null) {
            mensagem_erro="A Tarefa selecionada já tem um responsável associado.";
            JOptionPane.showMessageDialog(null, mensagem_erro, "ERRO!", JOptionPane.ERROR_MESSAGE);
        } else if (responsavel.getCarga()+tarefa.getTaxa_esforco()>1) {
            mensagem_erro= responsavel.getNome()+"não pode aceitar essa Tarefa.";
            JOptionPane.showMessageDialog(null, mensagem_erro, "ERRO!", JOptionPane.ERROR_MESSAGE);
        } else if (responsavel.getCusto_projeto()!=0) {
            Bolseiro aluno= (Bolseiro) responsavel;
            if (aluno.getFim_bolsa().compara_datas(data_atual)<0) {
                mensagem_erro= "A bolsa de "+responsavel.getNome()+"expirou.";
                JOptionPane.showMessageDialog(null, mensagem_erro, "ERRO!", JOptionPane.ERROR_MESSAGE);
            } else {
                responsavel.setCarga(responsavel.getCarga()+tarefa.getTaxa_esforco());
                tarefa.setResponsavel(responsavel);
            }
        } else {
            responsavel.setCarga(responsavel.getCarga()+tarefa.getTaxa_esforco());
            tarefa.setResponsavel(responsavel);
        }
    }
    
    /**
     * 
     * @param ind_tarefa_a_remover Index da Tarefa que será removida.
     */
    public void eliminarTarefa(int ind_tarefa_a_remover) {
        Tarefa tarefa_a_remover=getLista_tarefas().get(ind_tarefa_a_remover);
        int index_doc_bols;
        if (tarefa_a_remover.getResponsavel()==null) getLista_tarefas().remove(ind_tarefa_a_remover);
        else if (tarefa_a_remover.getResponsavel().equals(getInvestigador_principal())==true) {
            if (getInvestigador_principal().getCarga()-tarefa_a_remover.getTaxa_esforco()<0) getInvestigador_principal().setCarga(0);
            else getInvestigador_principal().setCarga(getInvestigador_principal().getCarga()-tarefa_a_remover.getTaxa_esforco());
            getLista_tarefas().remove(ind_tarefa_a_remover);
        } else if (tarefa_a_remover.getResponsavel().getCusto_projeto()==0) {
            if ((index_doc_bols=getLista_docentes().indexOf(tarefa_a_remover.getResponsavel()))!=-1) {
                if (getLista_docentes().get(index_doc_bols).getCarga()-tarefa_a_remover.getTaxa_esforco()<0) getLista_docentes().get(index_doc_bols).setCarga(0);
                else getLista_docentes().get(index_doc_bols).setCarga(getLista_docentes().get(index_doc_bols).getCarga()-tarefa_a_remover.getTaxa_esforco());
            }
            getLista_tarefas().remove(ind_tarefa_a_remover);
        } else {
            if ((index_doc_bols=getLista_bolseiros().indexOf(tarefa_a_remover.getResponsavel()))!=-1) {
                if (getLista_bolseiros().get(index_doc_bols).getCarga()-tarefa_a_remover.getTaxa_esforco()<0) getLista_bolseiros().get(index_doc_bols).setCarga(0);
                else getLista_bolseiros().get(index_doc_bols).setCarga(getLista_bolseiros().get(index_doc_bols).getCarga()-tarefa_a_remover.getTaxa_esforco());
            }
            getLista_tarefas().remove(ind_tarefa_a_remover);
        }
    }

    /** 
     * @return Custo dum Projeto. Será a soma do custo de todas as tarefas em que os membros estiveram envolvidos
     */
    public float custoProjeto() {
        float custo_total=0;
        for (int i=0; i<getLista_tarefas().size(); i++) {
            if (getLista_tarefas().get(i).getResponsavel()!=null) {
                custo_total+=getLista_tarefas().get(i).getResponsavel().getCusto_projeto()*getLista_tarefas().get(i).getDuracao();
            } else continue;
        }
        return custo_total;
    }
    public void encerrarProjeto() {
        this.encerrado=true;
    }
    
    
    /** 
     * @param nome Nome do Projeto
     * @param inicio_bolsa Data de Início de um Projeto
     * @param duracao_estimada Duração estimada dum Projeto
     * @return Um novo objeto Projeto
     */
    public Projeto(String nome, String inicio_bolsa, float duracao_estimada) {
        this.nome = nome;
        this.acronimo=nomeAcronimo(nome);
        this.data_inicio_projeto = new Data(inicio_bolsa);
        this.duracao_estimada = duracao_estimada;
        this.encerrado=false;
        this.lista_tarefas= new ArrayList<>();
        this.lista_bolseiros= new ArrayList<>();
        this.lista_docentes= new ArrayList<>();
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Projeto [acronimo=" + acronimo+ ", data_inicio_projeto="
                + data_inicio_projeto.getData() + ", duracao_estimada=" + duracao_estimada + ", encerrado=" + encerrado
                + ", lista_tarefas=" + lista_tarefas + ", nome=" + nome + "]\n\n\n";
    }

}