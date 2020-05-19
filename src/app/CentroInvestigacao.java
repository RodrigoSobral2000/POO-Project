package app;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * CentroInvestigacao
 */
public class CentroInvestigacao implements Serializable{

    private static final long serialVersionUID = 1L;

    private ArrayList<Projeto> lista_projetos;
    private ArrayList<Pessoa> lista_membros;
    
    /** 
     * @return Lista de Projetos dum Centro de Investigação
     */
    public ArrayList<Projeto> getLista_projetos() {
        return lista_projetos;
    }
    
    /** 
     * @return Lista de Membros dum Centro de Investigação
     */
    public ArrayList<Pessoa> getLista_membros() {
        return lista_membros;
    }

    /** 
     * @param c_or_nc TRUE para uma lista de Projetos Concluídos, FALSE para uma lista de Projetos Não Concluídos numa Data Estimada
     * @param dia Dia Atual
     * @param mes Mes Atual
     * @param ano Ano Atual
     * @return Uma lista de todos os Projetos que estão (ou não) concluídos
     * @throws ParseException
     */
    public ArrayList<Projeto> listarProjetosCOrNC(boolean c_or_nc, byte dia, byte mes, int ano) throws ParseException {
        ArrayList<Projeto> projetos_conc_or_nconc= new ArrayList<>();
        Projeto proj_aux;
        Tarefa tar_aux;
        byte cont_tarefas_conc;
        Data data_estimada_fim_projeto, data_atual= new Data(Byte.toString(dia)+"/"+Byte.toString(mes)+"/"+Integer.toString(ano));

        for (int i=0; i<getLista_projetos().size(); i++) {
            proj_aux=getLista_projetos().get(i);
            data_estimada_fim_projeto= new Data(proj_aux.getData_inicio_projeto().incrementaDataDia((int)proj_aux.getDuracao_estimada()*30));
            cont_tarefas_conc=0;
            for (int j=0; j<proj_aux.getLista_tarefas().size(); j++) {
                tar_aux=proj_aux.getLista_tarefas().get(j);
                if (tar_aux.getTaxa_execucao()==100) cont_tarefas_conc++;
                else continue;
            }

            if (c_or_nc==true && cont_tarefas_conc==proj_aux.getLista_tarefas().size()) projetos_conc_or_nconc.add(proj_aux);
            else if (c_or_nc==false && cont_tarefas_conc<proj_aux.getLista_tarefas().size() && (data_atual.compara_datas(data_estimada_fim_projeto)<0)) 
                projetos_conc_or_nconc.add(proj_aux);
            else continue;
        }
        return projetos_conc_or_nconc;
    }
   
    /** 
     * @param bolseiro Bolseiro a ser vericado
     * @return boolean TRUE se o Bolseiro não está associado a qualquer Projeto, FALSE se o Bolseiro já foi associado a um Projeto
     */
    public boolean verificaUnicidadeBolseiro(Bolseiro bolseiro) {
        for (int i=0; i<getLista_projetos().size(); i++) {
            if (getLista_projetos().get(i).getLista_bolseiros().indexOf(bolseiro)!=-1) return false;
            else continue;
        }
        return true;
    }
    
    /** 
     * @param ind_proj_a_associar Projeto ao qual a Pessoa (Docente, Bolseiro) vai ser associada
     * @param ind_pessoa_associada Pessoa que vai ser associada ao Projeto
     */
    public void associarPessoas(int ind_proj_a_associar, int ind_pessoa_associada) {
        
        if ((getLista_projetos().get(ind_proj_a_associar).getLista_bolseiros().isEmpty()==true || getLista_projetos().get(ind_proj_a_associar).getLista_bolseiros()==null) && getLista_membros().get(ind_pessoa_associada).getCusto_projeto()!=0) {
            getLista_projetos().get(ind_proj_a_associar).getLista_bolseiros().add((Bolseiro) getLista_membros().get(ind_pessoa_associada));
        }
        else if ((getLista_projetos().get(ind_proj_a_associar).getLista_docentes().isEmpty()==true || getLista_projetos().get(ind_proj_a_associar).getLista_docentes()==null) && getLista_membros().get(ind_pessoa_associada).getCusto_projeto()==0) {
            getLista_projetos().get(ind_proj_a_associar).getLista_docentes().add((Docente) getLista_membros().get(ind_pessoa_associada));
        }
        else if (getLista_projetos().get(ind_proj_a_associar).getLista_bolseiros().indexOf(getLista_membros().get(ind_pessoa_associada))!=-1 || getLista_projetos().get(ind_proj_a_associar).getLista_docentes().indexOf(getLista_membros().get(ind_pessoa_associada))!=-1 || getLista_projetos().get(ind_proj_a_associar).getInvestigador_principal().equals(getLista_membros().get(ind_pessoa_associada))==true) { 
            JOptionPane.showMessageDialog(null, "Esse membro já se encontra associado a esse Projeto.", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
        else {
            if (getLista_membros().get(ind_pessoa_associada).getCusto_projeto()!=0) {
                if (verificaUnicidadeBolseiro((Bolseiro)getLista_membros().get(ind_pessoa_associada))==true)
                    getLista_projetos().get(ind_proj_a_associar).getLista_bolseiros().add((Bolseiro) getLista_membros().get(ind_pessoa_associada));
                else JOptionPane.showMessageDialog(null, "O Bolseiro em causa já se encontra associado a um Projeto.", "ERRO!", JOptionPane.ERROR_MESSAGE);
            }
            else getLista_projetos().get(ind_proj_a_associar).getLista_docentes().add((Docente) getLista_membros().get(ind_pessoa_associada));
        }            
    }
    
    /** 
     * @return Um objeto Centro De Investigação
     */
    public CentroInvestigacao() {
        this.lista_projetos = new ArrayList<>();
        this.lista_membros = new ArrayList<>();
    }
}