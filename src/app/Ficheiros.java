/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author user
 */
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 * 
 * @author Filipa Capela
 * @author Rodrigo Sobral
 */
public class Ficheiros implements Serializable{

    
    private static final long serialVersionUID = 1L;
        
    /**
     * @param centro_investigacao Centro de Investigação para preencher a sua Lista de Membros com a informação dum Ficheiro TXT
    */
    private void lerFicheiro_pessoas(CentroInvestigacao centro_investigacao){
        //se for a primeira utilização, le o ficheiro que contem as pessoas e coloca as no array pessoas que exsite no centro de investigação
        File f= new File("pessoas.txt");
        if(f.exists() && f.isFile()){
            try{
                FileReader fr = new FileReader(f);
                BufferedReader br= new BufferedReader(fr);
                String line;
                while((line=br.readLine()) !=null){
                    String lista[] = line.split(",");
                    if(lista[0].compareTo("0")==0){
                        centro_investigacao.getLista_membros().add(new Docente(lista[1], lista[2], Integer.parseInt(lista[3]), lista[4]) );
                    } else {
                        if(lista[0].compareTo("1200")==0) centro_investigacao.getLista_membros().add(new Doutorado(lista[1], lista[2], lista[3], lista[4]));
                        else {
                            if(lista[0].compareTo("800")==0) centro_investigacao.getLista_membros().add(new Licenciado(lista[1], lista[2],lista[3],lista[4]));
                            else if(lista[0].compareTo("1000")==0) centro_investigacao.getLista_membros().add(new Mestre(lista[1], lista[2], lista[3],lista[4]));
                        }
                    }
                }
                br.close();
            }catch(FileNotFoundException ex){
                JOptionPane.showMessageDialog(null, "[pessoas.txt]: Erro ao abrir o ficheiro de texto", "ERRO!", JOptionPane.ERROR_MESSAGE);
            }catch(IOException ex){
                JOptionPane.showMessageDialog(null, " [pessoas.txt]: Erro ao ler o ficheiro de texto.", "ERRO!", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "[pessoas.txt]: Erro, o ficheiro não existe", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    /**
     * @param centro_investigacao Centro de Investigação para preencher a sua Lista de Projetos com a informação dum Ficheiro TXT
    */
    private void lerFicheiro_projeto_t(CentroInvestigacao centro_investigacao){
        //se for a primeira utilização lê o ficheiro que apresenta os dados dos projetos e tarafas e coloca as no array list projetos e na respetiva array list de tarefas do prpejto
        File f= new File("informacao_projeto.txt");
        if(f.exists() && f.isFile()){
            try{
                FileReader fr = new FileReader(f);
                BufferedReader br= new BufferedReader(fr);
                String line;
                int indice, j=0;
                
                while((line=br.readLine()) !=null){
                    String lista[] = line.split(",");
                    centro_investigacao.getLista_projetos().add(new Projeto(lista[0], lista[1], Float.parseFloat(lista[2])));
                    indice= lista.length-1;
                    for(int i=0;i<Integer.parseInt(lista[indice]);i++){
                        line=br.readLine();
                        String lista_[] = line.split(",");
                        if(lista_[0].compareTo("0.25")==0){
                            centro_investigacao.getLista_projetos().get(j).getLista_tarefas().add(new Documentacao(new Data(lista_[1]), Integer.parseInt(lista_[2])));
                        }
                        else if (lista_[0].compareTo("0.5")==0){
                            centro_investigacao.getLista_projetos().get(j).getLista_tarefas().add(new Design(new Data(lista_[1]), Integer.parseInt(lista_[2])));
                        }
                        else if(lista_[0].compareTo("1")==0) centro_investigacao.getLista_projetos().get(j).getLista_tarefas().add(new Desenvolvimento(new Data(lista_[1]), Integer.parseInt(lista_[2])));
                    }
                    j++;   
                }
                br.close();
    
            }catch(FileNotFoundException ex){
                JOptionPane.showMessageDialog(null, "[informacao_projeto.txt]: Erro ao abrir ficheiro de texto.", "ERRO!", JOptionPane.ERROR_MESSAGE);
            }catch(IOException ex){
               JOptionPane.showMessageDialog(null, "[informacao_projeto.txt]: Erro, ao ler o ficheiro de texto", "ERRO!", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "[informacao_projeto.txt]: Erro, o ficheiro não existe", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /** 
     * @param pessoas  Lista de Membros dum Centro de Investigação 
     */
    public void escrever_obj_pess(ArrayList<Pessoa> pessoas){
        //pessoas-->escrever
        File f = new File("pessoas_obj");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(pessoas);
            oos.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "[pessoas.obj]: Erro, ao criar o ficheiro", "ERRO!", JOptionPane.ERROR_MESSAGE);
        } catch(IOException e){
            JOptionPane.showMessageDialog(null, "[pessoas.obj]: Erro, ao escrever no ficheiro", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /** 
     * @param proj Lista de Projetos dum Centro de Investigação 
     */
    public void escrever_obj_proj(ArrayList<Projeto> proj){
        //projetos-->escrever
        File f = new File("projetos_obj");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(proj);
            oos.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "[projetos.obj]: Erro, o ficheiro não existe", "ERRO!", JOptionPane.ERROR_MESSAGE);
        } catch(IOException e){
            JOptionPane.showMessageDialog(null, "[projetos.obj]: Erro,ao esvrever no ficheiro", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    /** 
     * @return Lista de Pessoas Lida no Ficheiro Objeto
     */
    private ArrayList<Pessoa> lerfichPessObj(){
        //pessoas-->ler
        File f = new File("pessoas_obj");
        if(f.exists() && f.isFile()){
            try {
                FileInputStream is =new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(is);
                ArrayList<Pessoa> pessoa = (ArrayList<Pessoa>)ois.readObject();
                ois.close();
                
               
                return pessoa;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "[pessoas.obj]: Erro, ao ler o ficheiro", "ERRO!", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, "[pessoas.obj]: Erro,ao converter algum objeto", "ERRO!", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "[pessoas.obj]: Erro, o ficheiro não existe", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    /** 
     * @return Lista de Projetos Lida no Ficheiro Objeto
     */
    private ArrayList<Projeto> lerfichProjObj(){
        File f = new File("projetos_obj");
        if(f.exists() && f.isFile()){
            try {
                FileInputStream is = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(is);
                ArrayList<Projeto> projeto = (ArrayList<Projeto>)ois.readObject();
                
                ois.close();
                
                return projeto;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "[projetos.obj]: Erro, ao ler o ficheiro", "ERRO!", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, "[projetos.obj]: Erro, ao converter algum objeto", "ERRO!", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "[projetos.obj]: Erro,ficheiro não existe", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    /** 
     * @param cent Centro de Investigação no qual vai ser guardada toda a informação.
     * É feita uma verificação para saber se devemos ler os ficheiros TXT ou os OBJ, caso existam
     */
    public void verificar_objeto(CentroInvestigacao cent){
        File ficheiro = new File("pessoas_obj");
        File ficheiro1 = new File("projetos_obj");
        if(ficheiro.exists() && ficheiro1.exists()){
            ArrayList<Projeto> proj = lerfichProjObj();
            ArrayList<Pessoa> pess = lerfichPessObj();
            for(int i=0;i<proj.size();i++){
                cent.getLista_projetos().add(proj.get(i));
            }
            for(int i=0;i<pess.size();i++){
                cent.getLista_membros().add(pess.get(i));
            }
        }
        else if((ficheiro.exists() && !ficheiro1.exists()) || (ficheiro1.exists() && !ficheiro.exists())){
            JOptionPane.showMessageDialog(null, "Só um dos ficheiros é que existe", "Aviso!", JOptionPane.WARNING_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "Os ficheiros objetos não existem", "Aviso!", JOptionPane.WARNING_MESSAGE);
            lerFicheiro_pessoas(cent);
            lerFicheiro_projeto_t(cent);
        }
    }
        
}

