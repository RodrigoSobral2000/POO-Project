package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Interface
 */
public final class Interface extends JFrame implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private JLabel lblBar, lblBar2, lbltituloMenu, lblDataAtual;
    private JLabel lblPedirNome, lblPedirDataInicio, lblPedirDuracao, lblInseriuCorreto, lblProjetoEncerrado;
    private JPanel menuPrincipal;
    private JTextField txfInsereNome, txfInsereDataInicio, txfInsereDuracao, txfInsereTaxaExec;
    private JSpinner spnAno, spnDia, spnMes;
    private JButton btnAdicionarProjetos, btnListarProjetos, btnListarMembros, btnHome, btnCriarProjeto, btnConfirmAtualizar, btnAtribuir, btnAtribuirTar, btnAtribuirIP, btnAtribuirOrientadores, btnListarProjetosConcluidos, btnListarProjetosNConcluidos, btnAssociarPessoas, btnAtribuirOrientador;
    private JButton btnAdicionarTarefa, btnCriarTarefa, btnEliminarTarefa, btnApagarTarefa, btnAtribuirTarefa, btnCustoProjeto, btnEncerrarProjeto, btnListarTarefas, btnListarTarefasConc, btnListarTarefasNConc, btnListarTarefasNI, btnAtualizarExecucao, btnAssociar;
    private JList<String> lsListaProjetos, lsListaTarefas, lsListaMembros, lsListaBolseiros;
    private ArrayList<Pessoa> aux;
    private JScrollPane scrollListaProjetos, scrollListaProjetos2, scrollListaTarefas, scrollListaTarefas2, scrollListaMembros, scrollListaMembros2, scrollListaBolseiros;
    private JTextArea txaListaMembros, txaListaTarefas, txaListaProjetos;
    private JCheckBox cbDocumentacao, cbDesign, cbDesenvolvimento;
    
    private int ind_proj_selecionado=-1, ind_tar_selecionada=-1, ind_membro_selecionado=-1, ind_bolseiro_selecionado=-1;
    
    /** 
     * Interface Gráfica da Interface
     * @param centro_investigacao Centro de Investigação no qual vão ser aplicadas as alterações
     * @return Um objeto Interface
     */
    public Interface(CentroInvestigacao centro_investigacao) {
        super();   

        // ======================================================== LABELS ========================================================
        this.lbltituloMenu = new JLabel("<html><b>Gerenciamento de Projetos</b></html>");
        this.lblBar= new JLabel("/");
        this.lblBar2= new JLabel("/");
        this.lblDataAtual= new JLabel("Data Atual:");
        this.lblPedirNome= new JLabel();
        this.lblPedirDataInicio= new JLabel();
        this.lblPedirDuracao= new JLabel();
        this.lblInseriuCorreto= new JLabel();
        this.lblProjetoEncerrado= new JLabel("<html><b>Projeto Encerrado</b></html>");  

        this.lbltituloMenu.setVisible(true);
        this.lblDataAtual.setVisible(true);
        this.lblBar.setVisible(true);
        this.lblBar2.setVisible(true);
        this.lblPedirDataInicio.setVisible(false);
        this.lblPedirDuracao.setVisible(false);
        this.lblPedirNome.setVisible(false);
        this.lblInseriuCorreto.setVisible(false);
        this.lblProjetoEncerrado.setVisible(false);
        
        this.lbltituloMenu.setFont(new Font("Consolas", 1, 36)); 
        this.lbltituloMenu.setForeground(new Color(255, 255, 255));
        this.lbltituloMenu.setBounds(400, 20, 600, 50);
        this.lbltituloMenu.setVerticalAlignment(SwingConstants.TOP);

        this.lblDataAtual.setFont(new Font("Consolas", 1, 20));
        this.lblDataAtual.setForeground(new Color(255, 255, 255));
        this.lblDataAtual.setBounds(840, 600, 150, 20);
        
        this.lblBar.setFont(new Font("Tahoma", 0, 20));
        this.lblBar.setForeground(new Color(240, 240, 240));
        this.lblBar.setBounds(1050, 600, 20, 25);

        this.lblBar2.setFont(new Font("Tahoma", 0, 20)); 
        this.lblBar2.setForeground(new Color(240, 240, 240));
        this.lblBar2.setBounds(1130, 600, 20, 25);

        this.lblPedirNome.setFont(new Font("Consolas", 0, 20)); 
        this.lblPedirNome.setForeground(new Color(240, 240, 240));
        
        this.lblPedirDataInicio.setFont(new Font("Consolas", 0, 20)); 
        this.lblPedirDataInicio.setForeground(new Color(240, 240, 240));

        this.lblPedirDuracao.setFont(new Font("Consolas", 0, 20)); 
        this.lblPedirDuracao.setForeground(new Color(240, 240, 240));

        this.lblInseriuCorreto.setFont(new Font("Consolas", 1, 20));
        this.lblInseriuCorreto.setForeground(new Color(0, 153, 51));

        this.lblProjetoEncerrado.setFont(new Font("Consolas", 0, 20));
        this.lblProjetoEncerrado.setForeground(new Color(204, 0, 0));
        this.lblProjetoEncerrado.setBounds(1000, 550, 200, 30);

        // ======================================================== CAMPOS DE TEXTO ========================================================
        this.txfInsereDataInicio= new JTextField();
        this.txfInsereNome= new JTextField();
        this.txfInsereDuracao= new JTextField();
        this.txfInsereTaxaExec= new JTextField();

        this.txfInsereNome.setVisible(false);
        this.txfInsereDataInicio.setVisible(false);
        this.txfInsereDuracao.setVisible(false);
        this.txfInsereTaxaExec.setVisible(false);

        this.txfInsereNome.setFont(new Font("Consolas", 0, 20));
        this.txfInsereDataInicio.setFont(new Font("Consolas", 0, 20));
        this.txfInsereDuracao.setFont(new Font("Consolas", 0, 20));
        this.txfInsereTaxaExec.setFont(new Font("Consolas", 0, 20));

        // ======================================================== LISTA DE PROJETOS ========================================================
        DefaultListModel<String> modelo_lista_projetos = new DefaultListModel<>();
        DefaultListModel<String> modelo_lista_projetos_tarefas = new DefaultListModel<>();
        DefaultListModel<String> modelo_lista_projetos_membros = new DefaultListModel<>();
        DefaultListModel<String> modelo_lista_projetos_bolseiros = new DefaultListModel<>();

        this.scrollListaProjetos = new JScrollPane();
        this.scrollListaProjetos2= new JScrollPane();
        this.scrollListaTarefas= new JScrollPane();
        this.scrollListaTarefas2= new JScrollPane();
        this.scrollListaMembros= new JScrollPane();
        this.scrollListaMembros2= new JScrollPane();
        this.scrollListaBolseiros= new JScrollPane();

        this.lsListaProjetos = new JList<>(modelo_lista_projetos);
        this.lsListaTarefas= new JList<>(modelo_lista_projetos_tarefas);
        this.lsListaMembros= new JList<>(modelo_lista_projetos_membros);
        this.lsListaBolseiros= new JList<>(modelo_lista_projetos_bolseiros);
        this.txaListaMembros= new JTextArea();
        this.txaListaTarefas= new JTextArea();
        this.txaListaProjetos= new JTextArea();

        this.scrollListaProjetos.setVisible(false);
        this.scrollListaProjetos2.setVisible(false);
        this.scrollListaTarefas.setVisible(false);
        this.scrollListaMembros.setVisible(false);
        this.scrollListaMembros2.setVisible(false);
        this.scrollListaTarefas2.setVisible(false);
        this.scrollListaBolseiros.setVisible(false);
        this.lsListaProjetos.setVisible(false);
        this.lsListaTarefas.setVisible(false);
        this.lsListaMembros.setVisible(false);
        this.lsListaBolseiros.setVisible(false);
        this.lsListaBolseiros.setVisible(false);
        this.txaListaMembros.setVisible(false);
        this.txaListaTarefas.setVisible(false);
        this.txaListaProjetos.setVisible(false);

        this.scrollListaProjetos.setBounds(770, 140, 430, 400);
        this.lsListaProjetos.setFont(new Font("Consolas", 1, 20));
        this.lsListaProjetos.setBackground(new Color(255, 255, 255));
        this.lsListaProjetos.setForeground(new Color(51, 204, 255));
        this.lsListaProjetos.setBounds(770, 140, 430, 400);

        this.scrollListaProjetos2.setBounds(40, 140, 1200, 370);
        this.txaListaProjetos.setFont(new Font("Consolas", 1, 20));
        this.txaListaProjetos.setBackground(new Color(255, 255, 255));
        this.txaListaProjetos.setForeground(new Color(51, 204, 255));
        this.txaListaProjetos.setBounds(40, 140, 1200, 370);

        this.scrollListaMembros.setBounds(40, 140, 1200, 370);
        this.txaListaMembros.setBackground(new Color(255, 255, 255));
        this.txaListaMembros.setFont(new Font("Consolas", 0, 20));
        this.txaListaMembros.setForeground(new Color(51, 204, 255));
        this.txaListaMembros.setBounds(40, 140, 1200, 370);

        this.scrollListaMembros2.setBounds(70, 140, 430, 370);
        this.lsListaMembros.setFont(new Font("Consolas", 1, 20));
        this.lsListaMembros.setBackground(new Color(255, 255, 255));
        this.lsListaMembros.setForeground(new Color(51, 204, 255));
        this.lsListaMembros.setBounds(70, 140, 430, 370);

        this.scrollListaBolseiros.setBounds(770, 140, 430, 400);
        this.lsListaBolseiros.setFont(new Font("Consolas", 1, 20));
        this.lsListaBolseiros.setBackground(new Color(255, 255, 255));
        this.lsListaBolseiros.setForeground(new Color(51, 204, 255));
        this.lsListaBolseiros.setBounds(770, 140, 430, 400);

        this.scrollListaTarefas.setBounds(40, 140, 1200, 370);
        this.txaListaTarefas.setBackground(new Color(255, 255, 255));
        this.txaListaTarefas.setFont(new Font("Consolas", 0, 20));
        this.txaListaTarefas.setForeground(new Color(51, 204, 255));
        this.txaListaTarefas.setBounds(40, 140, 1200, 370);

        this.scrollListaTarefas2.setBounds(770, 140, 430, 370);
        this.txaListaTarefas.setBackground(new Color(255, 255, 255));
        this.lsListaTarefas.setFont(new Font("Consolas", 1, 20));
        this.lsListaTarefas.setForeground(new Color(51, 204, 255));
        this.lsListaTarefas.setBounds(770, 140, 430, 370);

        
        this.scrollListaProjetos.setViewportView(this.lsListaProjetos);
        this.scrollListaMembros.setViewportView(this.txaListaMembros);
        this.scrollListaMembros2.setViewportView(this.lsListaMembros);
        this.scrollListaTarefas.setViewportView(this.txaListaTarefas);
        this.scrollListaTarefas2.setViewportView(this.lsListaTarefas);

        // ======================================================== AÇÕES LISTAS ========================================================

        this.lsListaProjetos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                btnAdicionarProjetos.setVisible(false);
                btnListarMembros.setVisible(false);
                btnListarProjetos.setVisible(false);
                btnCustoProjeto.setText("<html><b>Custo Projeto</b></html>");

                ind_proj_selecionado = lsListaProjetos.getSelectedIndex();
                if (ind_proj_selecionado==-1) JOptionPane.showMessageDialog(null, "Não selecionou um projeto válido.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                else if (centro_investigacao.getLista_projetos().get(ind_proj_selecionado).isEncerrado()==true) {
                    lblProjetoEncerrado.setVisible(true);
                    btnAdicionarTarefa.setVisible(false);
                    btnEliminarTarefa.setVisible(false);
                    btnAtribuirTarefa.setVisible(false);
                    btnCustoProjeto.setVisible(false);
                    btnEncerrarProjeto.setVisible(false);
                    btnAtualizarExecucao.setVisible(false);
                    btnAtribuirOrientadores.setVisible(false);
                    btnAtribuirIP.setVisible(false);
                    btnAssociarPessoas.setVisible(false);
                } else {
                    btnAdicionarTarefa.setVisible(true);
                    btnEliminarTarefa.setVisible(true);
                    btnAtribuirTarefa.setVisible(true);
                    btnCustoProjeto.setVisible(true);
                    btnEncerrarProjeto.setVisible(true);
                    btnListarTarefas.setVisible(true);
                    btnListarTarefasConc.setVisible(true);
                    btnListarTarefasNConc.setVisible(true);
                    btnListarTarefasNI.setVisible(true);
                    btnAtualizarExecucao.setVisible(true);
                    btnAtribuirOrientadores.setVisible(true);
                    btnAtribuirIP.setVisible(true);
                    btnAssociarPessoas.setVisible(true);
                    lblProjetoEncerrado.setVisible(false);
                }
            }
        });
        this.lsListaTarefas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {

                ind_tar_selecionada = lsListaTarefas.getSelectedIndex();
                if (ind_tar_selecionada==-1) JOptionPane.showMessageDialog(null, "Não selecionou uma tarefa válida.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                else { 
                }
            }
        });
        this.lsListaMembros.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {

                ind_membro_selecionado = lsListaMembros.getSelectedIndex();
                if (ind_membro_selecionado==-1) JOptionPane.showMessageDialog(null, "Não selecionou uma tarefa válida.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                else { 
                    if (ind_bolseiro_selecionado!=-1) btnAtribuirOrientador.setVisible(true);
                }
            }
        });
        this.lsListaBolseiros.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {

                ind_bolseiro_selecionado = lsListaBolseiros.getSelectedIndex();
                if (ind_bolseiro_selecionado==-1) JOptionPane.showMessageDialog(null, "Não selecionou uma Bolseiro válido.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                else { 
                    if (ind_membro_selecionado!=-1) btnAtribuirOrientador.setVisible(true);
                }
            }
        });
        
        // ======================================================== CHECK BOXES ========================================================
        this.cbDesenvolvimento= new JCheckBox("<html><b>Desenvolvimento</b></html>");
        this.cbDesign= new JCheckBox("<html><b>Design</b></html>");
        this.cbDocumentacao= new JCheckBox("<html><b>Documentação</b></html>");

        this.cbDesenvolvimento.setVisible(false);
        this.cbDocumentacao.setVisible(false);
        this.cbDesign.setVisible(false);

        this.cbDesign.setBackground(new Color(255, 255, 255));
        this.cbDesign.setFont(new Font("Consolas", 1, 20));
        this.cbDesign.setBounds(70, 140, 110, 30);

        this.cbDocumentacao.setBackground(new Color(255, 255, 255));
        this.cbDocumentacao.setFont(new Font("Consolas", 1, 20));
        this.cbDocumentacao.setBounds(200, 140, 170, 30);

        this.cbDesenvolvimento.setBackground(new Color(255, 255, 255));
        this.cbDesenvolvimento.setFont(new Font("Consolas", 1, 20));
        this.cbDesenvolvimento.setBounds(390, 140, 200, 30);


        // ======================================================== BUTÕES ========================================================
        this.btnAdicionarProjetos = new JButton("<html><b>Adicionar Projeto</b></html>");
        this.btnListarProjetos = new JButton("<html><b>Listar Projetos</b></html>");
        this.btnListarMembros = new JButton("<html><b>Listar Membros do Centro</b></html>");
        this.btnHome= new JButton("<html><b><u>HOME</u></b></html>");
        this.btnCriarProjeto= new JButton("<html><b>Criar Projeto</b></html>");
        this.btnAdicionarTarefa= new JButton("<html><b>Criar Tarefa</b></html>");
        this.btnEliminarTarefa= new JButton("<html><b>Eliminar Tarefa</b></html>");
        this.btnAtribuirTarefa= new JButton("<html><b>Atribuir Tarefas</b></html>");
        this.btnCustoProjeto= new JButton("<html><b>Custo Projeto</b></html>");
        this.btnEncerrarProjeto= new JButton("<html><b>Encerrar Projeto</b></html>");
        this.btnListarTarefas= new JButton("<html><b>Listar Tarefas</b></html>");
        this.btnListarTarefasConc= new JButton("<html><b>Listar Tarefas Concluídas</b></html>");
        this.btnListarTarefasNConc= new JButton("<html><b>Listar Tarefas Não Concluídas</b></html>");
        this.btnListarTarefasNI= new JButton("<html><b>Listar Tarefas Não Iniciadas</b></html>");
        this.btnAtualizarExecucao= new JButton("<html><b>Atualizar Taxa de Execução</b></html>");
        this.btnCriarTarefa= new JButton("<html><b>Adicionar Tarefa</b></html>");
        this.btnApagarTarefa= new JButton("<html><b>Eliminar Tarefa</b></html>");
        this.btnConfirmAtualizar= new JButton("<html><b>Atualizar</b></html>");
        this.btnAtribuir= new JButton();
        this.btnListarProjetosConcluidos= new JButton("<html><b>Listar Projetos Concluídos</b></html>");
        this.btnListarProjetosNConcluidos= new JButton("<html><b>Listar Projetos Não Concluídos</b></html>");
        this.btnAtribuirIP= new JButton("<html><b>Atribuir IP</b></html>");
        this.btnAtribuirOrientadores= new JButton("<html><b>Atribuir Orientadores</b></html>");
        this.btnAssociarPessoas= new JButton("<html><b>Associar Membros</b></html>");
        this.btnAtribuirOrientador= new JButton("<html><b>Atribuir Orientador</b></html>");
        this.btnAssociar= new JButton("<html><b>Associar</b></html>");
        this.btnAtribuirTar= new JButton("<html><b>Atribuir Tarefa</b></html>");

        this.btnAdicionarProjetos.setVisible(true);
        this.btnListarProjetos.setVisible(true);
        this.btnListarMembros.setVisible(true);
        this.btnListarProjetosConcluidos.setVisible(true);
        this.btnListarProjetosNConcluidos.setVisible(true);
        this.btnHome.setVisible(true);
        this.btnCriarProjeto.setVisible(false);
        this.btnAdicionarTarefa.setVisible(false);
        this.btnEliminarTarefa.setVisible(false);
        this.btnAtribuirTarefa.setVisible(false);
        this.btnCustoProjeto.setVisible(false);
        this.btnEncerrarProjeto.setVisible(false);
        this.btnListarTarefas.setVisible(false);
        this.btnListarTarefasConc.setVisible(false);
        this.btnListarTarefasNConc.setVisible(false);
        this.btnListarTarefasNI.setVisible(false);
        this.btnAtualizarExecucao.setVisible(false);
        this.btnCriarTarefa.setVisible(false);
        this.btnApagarTarefa.setVisible(false);
        this.btnConfirmAtualizar.setVisible(false);
        this.btnAtribuir.setVisible(false);
        this.btnAtribuirIP.setVisible(false);
        this.btnAtribuirOrientadores.setVisible(false);
        this.btnAssociarPessoas.setVisible(false);
        this.btnAtribuirOrientador.setVisible(false);
        this.btnAssociar.setVisible(false);
        this.btnAtribuirTar.setVisible(false);

        this.btnAdicionarProjetos.setHorizontalAlignment(SwingConstants.LEFT);
        this.btnAdicionarProjetos.setBackground(new Color(255, 255, 255));
        this.btnAdicionarProjetos.setFont(new Font("Consolas", 1, 20));
        this.btnAdicionarProjetos.setBounds(70, 160, 400, 50);

        this.btnListarProjetos.setHorizontalAlignment(SwingConstants.LEFT);
        this.btnListarProjetos.setBackground(new Color(255, 255, 255));
        this.btnListarProjetos.setFont(new Font("Consolas", 1, 20));
        this.btnListarProjetos.setBounds(70, 280, 400, 50);

        this.btnListarProjetosConcluidos.setHorizontalAlignment(SwingConstants.RIGHT);
        this.btnListarProjetosConcluidos.setBackground(new Color(255, 255, 255));
        this.btnListarProjetosConcluidos.setFont(new Font("Consolas", 1, 20));
        this.btnListarProjetosConcluidos.setBounds(680, 220, 410, 50);
        
        this.btnListarProjetosNConcluidos.setHorizontalAlignment(SwingConstants.RIGHT);
        this.btnListarProjetosNConcluidos.setBackground(new Color(255, 255, 255));
        this.btnListarProjetosNConcluidos.setFont(new Font("Consolas", 1, 20));
        this.btnListarProjetosNConcluidos.setBounds(680, 360, 410, 50);

        this.btnListarMembros.setHorizontalAlignment(SwingConstants.LEFT);
        this.btnListarMembros.setBackground(new Color(255, 255, 255));
        this.btnListarMembros.setFont(new Font("Consolas", 1, 20));
        this.btnListarMembros.setBounds(70, 400, 400, 50);
        
        this.btnCriarProjeto.setForeground(new Color(51, 204, 255));
        this.btnCriarProjeto.setBackground(new Color(255, 255, 255));
        this.btnCriarProjeto.setFont(new Font("Consolas", 1, 20));
        this.btnCriarProjeto.setBounds(510, 350, 220, 50);

        this.btnCriarTarefa.setForeground(new Color(51, 204, 255));
        this.btnCriarTarefa.setBackground(new Color(255, 255, 255));
        this.btnCriarTarefa.setFont(new Font("Consolas", 1, 20));
        this.btnCriarTarefa.setBounds(480, 320, 210, 30);

        this.btnHome.setBounds(30, 600, 70, 35);
        this.btnHome.setFont(new Font("Consolas", 1, 20));
        this.btnHome.setBackground(new Color(255, 255, 255));
        this.btnHome.setHorizontalAlignment(SwingConstants.LEFT);

        this.btnAdicionarTarefa.setBounds(70, 140, 200, 40);
        this.btnAdicionarTarefa.setBackground(new Color(255, 255, 255));
        this.btnAdicionarTarefa.setFont(new Font("Consolas", 1, 18));

        this.btnEliminarTarefa.setBounds(70, 200, 200, 40);
        this.btnEliminarTarefa.setBackground(new Color(255, 255, 255));
        this.btnEliminarTarefa.setFont(new Font("Consolas", 1, 18));

        this.btnApagarTarefa.setBackground(new Color(255, 255, 255));
        this.btnApagarTarefa.setBounds(1000, 540, 200, 40);
        this.btnApagarTarefa.setForeground(new Color(204, 0, 0));
        this.btnApagarTarefa.setFont(new Font("Consolas", 1, 20));

        this.btnAtribuirTarefa.setBounds(70, 260, 200, 40);
        this.btnAtribuirTarefa.setBackground(new Color(255, 255, 255));
        this.btnAtribuirTarefa.setFont(new Font("Consolas", 1, 18));

        this.btnAtribuir.setBounds(560, 280, 140, 90);
        this.btnAtribuir.setBackground(new Color(255, 255, 255));
        this.btnAtribuir.setFont(new Font("Consolas", 1, 20));
        this.btnAtribuir.setForeground(new Color(0, 204, 255));
        
        this.btnAtribuirTar.setBounds(560, 280, 140, 90);
        this.btnAtribuirTar.setBackground(new Color(255, 255, 255));
        this.btnAtribuirTar.setFont(new Font("Consolas", 1, 20));
        this.btnAtribuirTar.setForeground(new Color(0, 204, 255));

        this.btnAssociar.setBounds(560, 280, 140, 90);
        this.btnAssociar.setBackground(new Color(255, 255, 255));
        this.btnAssociar.setFont(new Font("Consolas", 1, 20));
        this.btnAssociar.setForeground(new Color(0, 204, 255));

        this.btnCustoProjeto.setBounds(70, 320, 200, 40);
        this.btnCustoProjeto.setBackground(new Color(255, 255, 255));
        this.btnCustoProjeto.setFont(new Font("Consolas", 1, 18));

        this.btnEncerrarProjeto.setBounds(70, 380, 200, 40);
        this.btnEncerrarProjeto.setBackground(new Color(255, 255, 255));
        this.btnEncerrarProjeto.setFont(new Font("Consolas", 1, 18));

        this.btnAtribuirIP.setBounds(70, 440, 200, 40);
        this.btnAtribuirIP.setBackground(new Color(255, 255, 255));
        this.btnAtribuirIP.setFont(new Font("Consolas", 1, 18));

        this.btnAtribuirOrientadores.setBounds(320, 440, 370, 40);
        this.btnAtribuirOrientadores.setBackground(new Color(255, 255, 255));
        this.btnAtribuirOrientadores.setFont(new Font("Consolas", 1, 18));

        this.btnAtribuirOrientador.setBounds(560, 280, 140, 90);
        this.btnAtribuirOrientador.setBackground(new Color(255, 255, 255));
        this.btnAtribuirOrientador.setFont(new Font("Consolas", 1, 20));
        this.btnAtribuirOrientador.setForeground(new Color(0, 204, 255));

        this.btnAssociarPessoas.setBounds(70, 500, 620, 40);
        this.btnAssociarPessoas.setBackground(new Color(255, 255, 255));
        this.btnAssociarPessoas.setFont(new Font("Consolas", 1, 18));

        this.btnConfirmAtualizar.setBounds(770, 540, 140, 40);
        this.btnConfirmAtualizar.setForeground(new Color(0, 102, 51));
        this.btnConfirmAtualizar.setBackground(new Color(255, 255, 255));
        this.btnConfirmAtualizar.setFont(new Font("Consolas", 1, 18));

        this.btnAtualizarExecucao.setBounds(320, 380, 370, 40);
        this.btnAtualizarExecucao.setBackground(new Color(255, 255, 255));
        this.btnAtualizarExecucao.setFont(new Font("Consolas", 1, 18));

        this.btnListarTarefas.setBounds(320, 140, 370, 40);
        this.btnListarTarefas.setBackground(new Color(255, 255, 255));
        this.btnListarTarefas.setFont(new Font("Consolas", 1, 18));

        this.btnListarTarefasNConc.setBounds(320, 320, 370, 40);
        this.btnListarTarefasNConc.setBackground(new Color(255, 255, 255));
        this.btnListarTarefasNConc.setFont(new Font("Consolas", 1, 18));

        this.btnListarTarefasNI.setBounds(320, 260, 370, 40);
        this.btnListarTarefasNI.setBackground(new Color(255, 255, 255));
        this.btnListarTarefasNI.setFont(new Font("Consolas", 1, 18));
        
        this.btnListarTarefasConc.setBounds(320, 200, 370, 40);
        this.btnListarTarefasConc.setBackground(new Color(255, 255, 255));
        this.btnListarTarefasConc.setFont(new Font("Consolas", 1, 18));
        

        // ======================================================== AÇÕES BUTÕES ========================================================
        
        // DONE
        this.btnAdicionarProjetos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                lblPedirNome.setText("Insira o Nome do seu Projeto:");
                lblPedirDataInicio.setText("Insira a Data de Ínicio do Projeto:");
                lblPedirDuracao.setText("Insira a Duração do Projeto (em meses):");
                lblInseriuCorreto.setText("Projeto Adicionado com Sucesso!");
                lblPedirNome.setBounds(80, 160, 330, 50);
                lblPedirDataInicio.setBounds(80, 220, 390, 50);
                lblPedirDuracao.setBounds(80, 280, 440, 50);
                lblInseriuCorreto.setBounds(150, 350, 350, 40);
                txfInsereNome.setBounds(410, 170, 320, 30);
                txfInsereDataInicio.setBounds(480, 230, 250, 30);
                txfInsereDuracao.setBounds(520, 290, 210, 30);
                txfInsereDataInicio.setText("");
                txfInsereDuracao.setText("");
                txfInsereNome.setText("");
                btnListarMembros.setVisible(false);
                btnAdicionarProjetos.setVisible(false);
                btnListarProjetos.setVisible(false);
                btnListarProjetosConcluidos.setVisible(false);
                btnListarProjetosNConcluidos.setVisible(false);
                lblPedirDataInicio.setVisible(true);
                lblPedirDuracao.setVisible(true);
                lblPedirNome.setVisible(true);
                txfInsereDataInicio.setVisible(true);
                txfInsereDuracao.setVisible(true);
                txfInsereNome.setVisible(true);
                btnCriarProjeto.setVisible(true);
                scrollListaProjetos.setVisible(false);
                lsListaProjetos.setVisible(false);
            }
        });
        
        // DONE
        this.btnCriarProjeto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Data data_inicio= new Data(txfInsereDataInicio.getText());
                if (data_inicio.dataVerifica()==false) JOptionPane.showMessageDialog(null, "Por favor, insira os dados corretamente.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                else {
                    try {
                        centro_investigacao.getLista_projetos().add(new Projeto(txfInsereNome.getText(), txfInsereDataInicio.getText(), Float.parseFloat(txfInsereDuracao.getText())));
                        lblInseriuCorreto.setVisible(true);
                        btnCriarProjeto.setVisible(false);
                    } catch (NumberFormatException erro) {
                        JOptionPane.showMessageDialog(null, "Por favor, insira os dados corretamente.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        // DONE
        this.btnListarProjetos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Data data_atual= new Data(Byte.toString((byte)spnDia.getValue())+"/"+Byte.toString((byte)spnMes.getValue())+"/"+Integer.toString((int)spnAno.getValue()));
                Data fim_estimado;
                btnAdicionarProjetos.setVisible(false);
                btnListarProjetos.setVisible(false);
                btnListarMembros.setVisible(false);
                btnListarProjetosConcluidos.setVisible(false);
                btnListarProjetosNConcluidos.setVisible(false);
                modelo_lista_projetos.removeAllElements();
                for (int i=0; i<centro_investigacao.getLista_projetos().size(); i++) {
                    try {
                        fim_estimado= new Data((centro_investigacao.getLista_projetos().get(i).getData_inicio_projeto().incrementaDataDia((int)(centro_investigacao.getLista_projetos().get(i).getDuracao_estimada()*30))));
                        if (data_atual.compara_datas(fim_estimado)>0)
                        modelo_lista_projetos.addElement(centro_investigacao.getLista_projetos().get(i).getNome());
                    } catch (ParseException erro) {
                        modelo_lista_projetos.addElement(centro_investigacao.getLista_projetos().get(i).getNome());
                    }
                }
                scrollListaProjetos.setVisible(true);
                lsListaProjetos.setVisible(true);
            }
        });
        
        // DONE
        this.btnListarProjetosNConcluidos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ArrayList<Projeto> lista_projetos_n_concluidos= new ArrayList<>();
                Projeto proj_aux;
                try {
                    lista_projetos_n_concluidos=centro_investigacao.listarProjetosCOrNC(false, (byte)spnDia.getValue(), (byte)spnMes.getValue(), (int) spnAno.getValue());
                } catch (ParseException erro) { }
                if (lista_projetos_n_concluidos.isEmpty()==true) JOptionPane.showMessageDialog(null, "Todos os Projetos estão concluídos ou não inicializados.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                else {
                    btnAdicionarProjetos.setVisible(false);
                    btnListarMembros.setVisible(false);
                    btnListarProjetos.setVisible(false);
                    btnListarProjetosConcluidos.setVisible(false);
                    btnListarProjetosNConcluidos.setVisible(false);
                    scrollListaProjetos2.setVisible(true);
                    txaListaProjetos.setVisible(true);

                    txaListaProjetos.setText(null);
                    txaListaProjetos.append("NOME (ACRÓNIMO): EMAIL DO IP | DATA INÍCIO | DURAÇÃO ESTIMADA | DATA FIM | Nº DOCENTES | Nº BOLSEIROS\n\n");                
                    for (int i=0; i<lista_projetos_n_concluidos.size(); i++) {
                        proj_aux=lista_projetos_n_concluidos.get(i);
                        if (proj_aux.getInvestigador_principal()==null) {
                            txaListaProjetos.append(proj_aux.getNome()+"("+proj_aux.getAcronimo()+"): - | "+proj_aux.getData_inicio_projeto().getData()+" | "+proj_aux.getDuracao_estimada()+" | - | 0 | 0\n");
                        }
                        else if (proj_aux.getInvestigador_principal()!=null) {
                            txaListaProjetos.append(proj_aux.getNome()+"("+proj_aux.getAcronimo()+"): "+proj_aux.getInvestigador_principal().getEmail()+" | "+proj_aux.getData_inicio_projeto().getData()+" | "+proj_aux.getDuracao_estimada()+" | - | "+ proj_aux.getLista_docentes().size()+" | "+proj_aux.getLista_bolseiros().size()+"\n");
                        }
                        else if (proj_aux.getInvestigador_principal()!=null && proj_aux.getLista_bolseiros()!=null && proj_aux.getLista_docentes()==null) {
                            txaListaProjetos.append(proj_aux.getNome()+"("+proj_aux.getAcronimo()+"): "+proj_aux.getInvestigador_principal().getEmail()+" | "+proj_aux.getData_inicio_projeto().getData()+" | "+proj_aux.getDuracao_estimada()+" | - | 0 | "+proj_aux.getLista_bolseiros().size()+"\n");
                        }
                        else if (proj_aux.getInvestigador_principal()!=null && proj_aux.getLista_bolseiros()==null && proj_aux.getLista_docentes()!=null) {
                            txaListaProjetos.append(proj_aux.getNome()+"("+proj_aux.getAcronimo()+"): "+proj_aux.getInvestigador_principal().getEmail()+" | "+proj_aux.getData_inicio_projeto().getData()+" | "+proj_aux.getDuracao_estimada()+" | - | "+ proj_aux.getLista_docentes().size()+" | 0\n");
                        }
                    }
                }
            }
        });

        // DONE
        this.btnListarProjetosConcluidos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ArrayList<Projeto> lista_projetos_concluidos= new ArrayList<>();
                Projeto proj_aux;
                try {
                    lista_projetos_concluidos=centro_investigacao.listarProjetosCOrNC(true, (byte)spnDia.getValue(), (byte)spnMes.getValue(), (int) spnAno.getValue());
                } catch (ParseException erro) { }
                if (lista_projetos_concluidos.isEmpty()==true) JOptionPane.showMessageDialog(null, "Não há Projetos concluídos.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                else {
                    btnAdicionarProjetos.setVisible(false);
                    btnListarMembros.setVisible(false);
                    btnListarProjetos.setVisible(false);
                    btnListarProjetosConcluidos.setVisible(false);
                    btnListarProjetosNConcluidos.setVisible(false);
                    scrollListaProjetos2.setVisible(true);
                    txaListaProjetos.setVisible(true);
                    txaListaProjetos.setText(null);
                    txaListaProjetos.append("NOME (ACRÓNIMO): EMAIL DO IP | DATA INÍCIO | DURAÇÃO ESTIMADA | DATA FIM | Nº DOCENTES | Nº BOLSEIROS\n\n");                
                    for (int i=0; i<lista_projetos_concluidos.size(); i++) {
                        proj_aux=lista_projetos_concluidos.get(i);
                        txaListaProjetos.append(proj_aux.getNome()+"("+proj_aux.getAcronimo()+"): "+proj_aux.getInvestigador_principal().getEmail()+" | "+proj_aux.getData_inicio_projeto().getData()+" | "+proj_aux.getDuracao_estimada()+" | "+proj_aux.getData_fim_projeto().getData()+" | "+ proj_aux.getLista_docentes().size()+" | "+proj_aux.getLista_bolseiros().size()+"\n");
                    }
                }
            }
        });

        // DONE
        this.btnAdicionarTarefa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cbDesenvolvimento.setVisible(true);
                cbDocumentacao.setVisible(true);
                cbDesign.setVisible(true);
                scrollListaProjetos.setVisible(false);
                lsListaProjetos.setVisible(false);
                
                btnAdicionarTarefa.setVisible(false);
                btnEliminarTarefa.setVisible(false);
                btnAtribuirTarefa.setVisible(false);
                btnCustoProjeto.setVisible(false);
                btnEncerrarProjeto.setVisible(false);
                btnListarTarefas.setVisible(false);
                btnListarTarefasConc.setVisible(false);
                btnListarTarefasNConc.setVisible(false);
                btnListarTarefasNI.setVisible(false);
                btnAtualizarExecucao.setVisible(false);
                btnAtribuirIP.setVisible(false);
                btnAtribuirOrientadores.setVisible(false);
                btnAssociarPessoas.setVisible(false);
                
                lblProjetoEncerrado.setVisible(false);
                lblPedirDataInicio.setVisible(true);
                lblPedirDuracao.setVisible(true);
                txfInsereNome.setVisible(false);
                txfInsereDataInicio.setVisible(true);
                txfInsereDuracao.setVisible(true);
                btnCriarTarefa.setVisible(true);

                lblPedirDataInicio.setText("Insira a Data de Ínicio da Tarefa:");
                lblPedirDuracao.setText("Insira a Duração do Tarefa (em dias):");
                lblInseriuCorreto.setText("Tarefa Adicionada com Sucesso!");
                txfInsereDataInicio.setText("");
                txfInsereDuracao.setText("");
                cbDesenvolvimento.setSelected(false);
                cbDesign.setSelected(false);
                cbDocumentacao.setSelected(false);
                lblPedirDataInicio.setBounds(70, 210, 380, 50);
                lblPedirDuracao.setBounds(70, 260, 410, 50);
                txfInsereDataInicio.setBounds(470, 210, 220, 30);
                txfInsereDuracao.setBounds(490, 260, 200, 30);
                lblInseriuCorreto.setBounds(110, 320, 330, 30);
            }
        });
        
        // DONE
        this.btnCriarTarefa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Data data_inicio= new Data(txfInsereDataInicio.getText());
                if (data_inicio.dataVerifica()==false) JOptionPane.showMessageDialog(null, "Por favor, insira os dados corretamente.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                else {
                    try {
                        if ((cbDocumentacao.isSelected() && cbDesenvolvimento.isSelected()) || (cbDocumentacao.isSelected() && cbDesign.isSelected()) || (cbDesenvolvimento.isSelected() && cbDesign.isSelected()) || (!cbDesenvolvimento.isSelected() && !cbDesign.isSelected() && !cbDocumentacao.isSelected())) {
                            JOptionPane.showMessageDialog(null, "Deve selecionar UM tipo de Tarefa.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            if (cbDocumentacao.isSelected()) centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().add(new Documentacao(data_inicio, Integer.parseInt(txfInsereDuracao.getText())));
                            else if (cbDesign.isSelected()) centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().add(new Design(data_inicio, Integer.parseInt(txfInsereDuracao.getText())));
                            else if (cbDesenvolvimento.isSelected()) centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().add(new Desenvolvimento(data_inicio, Integer.parseInt(txfInsereDuracao.getText())));
                            lblInseriuCorreto.setVisible(true);
                            btnCriarTarefa.setVisible(false);
                        }
                    } catch (NumberFormatException erro) {
                        JOptionPane.showMessageDialog(null, "Por favor, insira os dados corretamente.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        // DONE
        this.btnEliminarTarefa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Tarefa tar_aux;

                scrollListaTarefas2.setVisible(true);
                lsListaTarefas.setVisible(true);
                btnApagarTarefa.setVisible(true);
                scrollListaProjetos.setVisible(false);
                lsListaProjetos.setVisible(false);
                btnEliminarTarefa.setVisible(false);
                btnAdicionarTarefa.setVisible(false);
                btnAtribuirTarefa.setVisible(false);
                btnCustoProjeto.setVisible(false);
                btnEncerrarProjeto.setVisible(false);
                btnListarTarefas.setVisible(false);
                btnListarTarefasConc.setVisible(false);
                btnListarTarefasNConc.setVisible(false);
                btnListarTarefasNI.setVisible(false);
                btnAtualizarExecucao.setVisible(false);
                btnApagarTarefa.setVisible(true);
                btnAtribuirIP.setVisible(false);
                btnAtribuirOrientadores.setVisible(false);
                btnAssociarPessoas.setVisible(false);
                
                modelo_lista_projetos_tarefas.removeAllElements();
                for (int i=0; i<centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().size(); i++) {
                    tar_aux=centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().get(i);
                    if (tar_aux.getTaxa_execucao()<100) {
                        if (tar_aux.getTaxa_esforco()==0.25f) modelo_lista_projetos_tarefas.addElement("Documentação - "+tar_aux.getTaxa_execucao()+" - "+tar_aux.getDuracao());
                        else if (tar_aux.getTaxa_esforco()==0.5f) modelo_lista_projetos_tarefas.addElement("Design - "+tar_aux.getTaxa_execucao()+" - "+tar_aux.getDuracao());
                        else if (tar_aux.getTaxa_esforco()==1) modelo_lista_projetos_tarefas.addElement("Desenvolvimento - "+tar_aux.getTaxa_execucao()+" - "+tar_aux.getDuracao());
                    }
                    else continue;
                }
            }
        });
        
        // DONE
        this.btnApagarTarefa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                centro_investigacao.getLista_projetos().get(ind_proj_selecionado).eliminarTarefa(ind_tar_selecionada);
                lblInseriuCorreto.setText("Tarefa Eliminada!");
                lblInseriuCorreto.setBounds(770, 540, 190, 30);
                lblInseriuCorreto.setVisible(true);
                btnApagarTarefa.setVisible(false);
            }
        });
        
        // DONE
        this.btnAtualizarExecucao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Tarefa tar_aux;
                if (modelo_lista_projetos_tarefas.isEmpty()==true) 
                JOptionPane.showMessageDialog(null, "Não há tarefas com responsáveis.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                else {
                    scrollListaTarefas2.setVisible(true);
                    lsListaTarefas.setVisible(true);
                    scrollListaProjetos.setVisible(false);
                    lsListaProjetos.setVisible(false);
                    btnEliminarTarefa.setVisible(false);
                    btnAdicionarTarefa.setVisible(false);
                    btnAtribuirTarefa.setVisible(false);
                    btnCustoProjeto.setVisible(false);
                    btnEncerrarProjeto.setVisible(false);
                    btnListarTarefas.setVisible(false);
                    btnListarTarefasConc.setVisible(false);
                    btnListarTarefasNConc.setVisible(false);
                    btnListarTarefasNI.setVisible(false);
                    btnAtualizarExecucao.setVisible(false);
                    btnAtribuirIP.setVisible(false);
                    btnAtribuirOrientadores.setVisible(false);
                    btnAssociarPessoas.setVisible(false);

                    btnConfirmAtualizar.setVisible(true);
                    txfInsereTaxaExec.setText(null);
                    txfInsereTaxaExec.setBounds(930, 540, 270, 40);
                    txfInsereTaxaExec.setVisible(true);
                    
                    modelo_lista_projetos_tarefas.removeAllElements();
                    for (int i=0; i<centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().size(); i++) {
                        tar_aux=centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().get(i);
                        if (tar_aux.getTaxa_execucao()<100 && tar_aux.getResponsavel()!=null) {
                            if (tar_aux.getTaxa_esforco()==0.25f) modelo_lista_projetos_tarefas.addElement("Documentação - "+tar_aux.getTaxa_execucao()+" - "+tar_aux.getDuracao());
                            else if (tar_aux.getTaxa_esforco()==0.5f) modelo_lista_projetos_tarefas.addElement("Design - "+tar_aux.getTaxa_execucao()+" - "+tar_aux.getDuracao());
                            else if (tar_aux.getTaxa_esforco()==1) modelo_lista_projetos_tarefas.addElement("Desenvolvimento - "+tar_aux.getTaxa_execucao()+" - "+tar_aux.getDuracao());
                        }
                    }
                }
            }
        });

        // DONE
        this.btnConfirmAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Docente doc_aux;
                Bolseiro bols_aux;
                int index;
                double nova_taxa=-1;
                try {
                    nova_taxa= Double.parseDouble(txfInsereTaxaExec.getText());
                    if (nova_taxa>=0 && nova_taxa<=100) {
                        if (nova_taxa==100) {
                            if (centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().get(ind_tar_selecionada).getResponsavel().getCusto_projeto()==0) {
                                doc_aux=(Docente) centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().get(ind_tar_selecionada).getResponsavel();
                                index=centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_docentes().indexOf(doc_aux);
                                centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_docentes().get(index).setCarga(doc_aux.getCarga()-centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().get(ind_tar_selecionada).getTaxa_esforco());
                                centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().get(ind_tar_selecionada).atualizaDataFim(Byte.toString((byte)spnDia.getValue())+"/"+Byte.toString((byte)spnMes.getValue())+"/"+Integer.toString((int)spnAno.getValue()));
                            }
                            else if (centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().get(ind_tar_selecionada).getResponsavel().getCusto_projeto()==0) {
                                bols_aux=(Bolseiro) centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().get(ind_tar_selecionada).getResponsavel();
                                index=centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_bolseiros().indexOf(bols_aux);
                                centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_bolseiros().get(index).setCarga(bols_aux.getCarga()-centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().get(ind_tar_selecionada).getTaxa_esforco());
                                centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().get(ind_tar_selecionada).atualizaDataFim(Byte.toString((byte)spnDia.getValue())+"/"+Byte.toString((byte)spnMes.getValue())+"/"+Integer.toString((int)spnAno.getValue()));
                            }
                            
                        }
                        lblInseriuCorreto.setText("Tarefa Atualizada!");
                        lblInseriuCorreto.setBounds(770, 540, 140, 40);
                        lblInseriuCorreto.setVisible(true);
                        btnApagarTarefa.setVisible(false);
                    }
                    else JOptionPane.showMessageDialog(null, "Por favor, insira os dados corretamente.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException erro) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira os dados corretamente.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // DONE
        this.btnAtribuirTarefa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Tarefa tar_aux;
                Projeto proj_selecionado= centro_investigacao.getLista_projetos().get(ind_proj_selecionado);
                aux= new ArrayList<>();
                btnAtribuir.setText("<html><b>Atribuir</b></html>");

                if (proj_selecionado.getInvestigador_principal()==null) 
                    JOptionPane.showMessageDialog(null, "O Projeto ainda não tem Investigador Principal definido", "ERRO!", JOptionPane.ERROR_MESSAGE);
                else if (proj_selecionado.getLista_docentes()==null || proj_selecionado.getLista_docentes().isEmpty()==true)
                    JOptionPane.showMessageDialog(null, "O Projeto ainda não tem Docentes associados", "ERRO!", JOptionPane.ERROR_MESSAGE);
                else if (proj_selecionado.getLista_bolseiros()==null || proj_selecionado.getLista_bolseiros().isEmpty()==true) 
                    JOptionPane.showMessageDialog(null, "O Projeto ainda não tem Bolseiros associados", "ERRO!", JOptionPane.ERROR_MESSAGE);
                else {
                    ind_membro_selecionado=-1;
                    ind_tar_selecionada=-1;
                    btnAtribuirTar.setVisible(true);
                    scrollListaTarefas2.setVisible(true);
                    scrollListaMembros2.setVisible(true);
                    lsListaTarefas.setVisible(true);
                    lsListaMembros.setVisible(true);

                    scrollListaProjetos.setVisible(false);
                    lsListaProjetos.setVisible(false);
                    btnEliminarTarefa.setVisible(false);
                    btnAdicionarTarefa.setVisible(false);
                    btnAtribuirTarefa.setVisible(false);
                    btnCustoProjeto.setVisible(false);
                    btnEncerrarProjeto.setVisible(false);
                    btnListarTarefas.setVisible(false);
                    btnListarTarefasConc.setVisible(false);
                    btnListarTarefasNConc.setVisible(false);
                    btnListarTarefasNI.setVisible(false);
                    btnAtualizarExecucao.setVisible(false);
                    btnAtribuirIP.setVisible(false);
                    btnAtribuirOrientadores.setVisible(false);
                    btnAssociarPessoas.setVisible(false);
                    
                    modelo_lista_projetos_tarefas.removeAllElements();
                    modelo_lista_projetos_membros.removeAllElements();
                    for (int i=0; i<proj_selecionado.getLista_tarefas().size(); i++) {
                        tar_aux=proj_selecionado.getLista_tarefas().get(i);
                        if (tar_aux.getTaxa_execucao()<100) {
                            if (tar_aux.getTaxa_esforco()==0.25f) modelo_lista_projetos_tarefas.addElement("Documentação - "+tar_aux.getTaxa_execucao()+" - "+tar_aux.getDuracao());
                            else if (tar_aux.getTaxa_esforco()==0.5f) modelo_lista_projetos_tarefas.addElement("Design - "+tar_aux.getTaxa_execucao()+" - "+tar_aux.getDuracao());
                            else if (tar_aux.getTaxa_esforco()==1) modelo_lista_projetos_tarefas.addElement("Desenvolvimento - "+tar_aux.getTaxa_execucao()+" - "+tar_aux.getDuracao());
                        }
                    }
                    if (proj_selecionado.getInvestigador_principal().getCarga()<1) {
                        modelo_lista_projetos_membros.addElement("IP: "+proj_selecionado.getInvestigador_principal().getEmail()+" - "+proj_selecionado.getInvestigador_principal().getCarga());
                        aux.add(proj_selecionado.getInvestigador_principal());
                    }
                    if (proj_selecionado.getLista_docentes()!= null) {
                        for (int i=0; i<proj_selecionado.getLista_docentes().size(); i++) {
                            if (proj_selecionado.getLista_docentes().get(i).getCarga()<1) {
                                modelo_lista_projetos_membros.addElement("Docente: "+proj_selecionado.getLista_docentes().get(i).getEmail()+" - "+proj_selecionado.getLista_docentes().get(i).getCarga());
                                aux.add(proj_selecionado.getLista_bolseiros().get(i));
                            }
                        }
                    }
                    if (proj_selecionado.getLista_bolseiros()!= null) {
                        for (int i=0; i<proj_selecionado.getLista_bolseiros().size(); i++) {
                            if (proj_selecionado.getLista_bolseiros().get(i).getCarga()<1) {
                                modelo_lista_projetos_membros.addElement("Bolseiro: "+proj_selecionado.getLista_bolseiros().get(i).getEmail()+" - "+proj_selecionado.getLista_bolseiros().get(i).getCarga());
                                aux.add(proj_selecionado.getLista_bolseiros().get(i));
                            }
                        }
                    }
                }
            }
        });

        this.btnAtribuirTar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int index=0;
                if (ind_membro_selecionado==-1 || ind_tar_selecionada==-1) JOptionPane.showMessageDialog(null, "Ainda não selecionou um membro.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                else {
                    try {
                        if (centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getInvestigador_principal().getEmail().compareTo(aux.get(ind_membro_selecionado).getEmail())==0) {
                            centro_investigacao.getLista_projetos().get(ind_proj_selecionado).atribuirTarefa(centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getInvestigador_principal(), centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().get(ind_tar_selecionada), (byte)spnDia.getValue(), (byte)spnMes.getValue(), (int)spnAno.getValue());
                        }
                        else if ((index=centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_bolseiros().indexOf((Bolseiro)aux.get(ind_membro_selecionado)))!=-1) {
                            centro_investigacao.getLista_projetos().get(ind_proj_selecionado).atribuirTarefa(centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_bolseiros().get(index), centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().get(ind_tar_selecionada), (byte)spnDia.getValue(), (byte)spnMes.getValue(), (int)spnAno.getValue());
                        }
                        else if ((index=centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_docentes().indexOf((Docente)aux.get(ind_membro_selecionado)))!=-1) {
                            centro_investigacao.getLista_projetos().get(ind_proj_selecionado).atribuirTarefa(centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_docentes().get(index), centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().get(ind_tar_selecionada), (byte)spnDia.getValue(), (byte)spnMes.getValue(), (int)spnAno.getValue());
                        }
                    } catch (ParseException erro) { }
                }
            }
        });

        // DONE
        this.btnAtribuirIP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Docente doc_aux;
                if (centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_docentes()==null || centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_docentes().isEmpty()==true)
                JOptionPane.showMessageDialog(null, "Ainda não foram associados docentes.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                else if (centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getInvestigador_principal()!=null)
                JOptionPane.showMessageDialog(null, "Já foi atribuído um Investigador Principal a este Projeto.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                else {
                    scrollListaMembros2.setVisible(true);
                    lsListaMembros.setVisible(true);
                    btnAtribuir.setText("<html><b>Atribuir</b></html>");
                    btnAtribuir.setVisible(true);

                    scrollListaProjetos.setVisible(false);
                    lsListaProjetos.setVisible(false);
                    btnAdicionarTarefa.setVisible(false);
                    btnEliminarTarefa.setVisible(false);
                    btnAtribuirTarefa.setVisible(false);
                    btnCustoProjeto.setVisible(false);
                    btnEncerrarProjeto.setVisible(false);
                    btnListarTarefas.setVisible(false);
                    btnListarTarefasConc.setVisible(false);
                    btnListarTarefasNConc.setVisible(false);
                    btnListarTarefasNI.setVisible(false);
                    btnAtualizarExecucao.setVisible(false);
                    btnAtribuirIP.setVisible(false);
                    btnAtribuirOrientadores.setVisible(false);
                    btnAssociarPessoas.setVisible(false);

                    modelo_lista_projetos_membros.removeAllElements();
                    for (int i=0; i<centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_docentes().size(); i++) {
                        doc_aux=centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_docentes().get(i);
                        modelo_lista_projetos_membros.addElement("Docente: "+doc_aux.getEmail()+" - "+doc_aux.getCarga());
                    }
                }
            }
        });

        //DONE
        this.btnAtribuir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (ind_membro_selecionado==-1) JOptionPane.showMessageDialog(null, "Ainda não selecionou um membro.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                else {
                    if (centro_investigacao.getLista_membros().get(ind_membro_selecionado).getCusto_projeto()!=0) {
                        JOptionPane.showMessageDialog(null, "Selecione um DOCENTE.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                    }
                    lblInseriuCorreto.setBounds(560, 280, 140, 90);
                    lblInseriuCorreto.setText("<html><b>Investiador Principal atribuído com sucesso!</b></html>");
                    btnAtribuir.setVisible(false);
                    lblInseriuCorreto.setVisible(true);
                    centro_investigacao.getLista_projetos().get(ind_proj_selecionado).setInvestigador_principal((Docente)centro_investigacao.getLista_membros().get(ind_membro_selecionado));
                    centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_docentes().remove((Docente)centro_investigacao.getLista_membros().get(ind_membro_selecionado));
                }
            }
        });

        // DONE
        this.btnAtribuirOrientadores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Docente doc_aux;
                Bolseiro bols_aux;
                Formando form_aux;
                if (centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_docentes()==null || centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_docentes().isEmpty()==true)
                JOptionPane.showMessageDialog(null, "Ainda não foram associados docentes.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                else {
                    scrollListaMembros2.setVisible(true);
                    lsListaMembros.setVisible(true);
                    scrollListaBolseiros.setVisible(true);
                    lsListaBolseiros.setVisible(true);

                    scrollListaProjetos.setVisible(false);
                    lsListaProjetos.setVisible(false);
                    btnAdicionarTarefa.setVisible(false);
                    btnEliminarTarefa.setVisible(false);
                    btnAtribuirTarefa.setVisible(false);
                    btnCustoProjeto.setVisible(false);
                    btnEncerrarProjeto.setVisible(false);
                    btnListarTarefas.setVisible(false);
                    btnListarTarefasConc.setVisible(false);
                    btnListarTarefasNConc.setVisible(false);
                    btnListarTarefasNI.setVisible(false);
                    btnAtualizarExecucao.setVisible(false);
                    btnAtribuirIP.setVisible(false);
                    btnAtribuirOrientadores.setVisible(false);
                    btnAssociarPessoas.setVisible(false);

                    modelo_lista_projetos_membros.removeAllElements();
                    modelo_lista_projetos_bolseiros.removeAllElements();
                    for (int i=0; i<centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_docentes().size(); i++) {
                        doc_aux=centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_docentes().get(i);
                        modelo_lista_projetos_membros.addElement("Docente: "+doc_aux.getEmail()+" - "+doc_aux.getCarga());
                    }
                    for (int i=0; i<centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_bolseiros().size(); i++) {
                        bols_aux=centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_bolseiros().get(i);
                        if (bols_aux.getCusto_projeto()!=1200) {
                            form_aux= (Formando) bols_aux;
                            if (form_aux.getOrientador()==null) modelo_lista_projetos_bolseiros.addElement("Formando: "+form_aux.getEmail()+" - "+form_aux.getCarga());
                        }
                    }
                }
            }
        });

        //DONE
        this.btnAtribuirOrientador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (ind_membro_selecionado==-1) JOptionPane.showMessageDialog(null, "Ainda não selecionou um Docente.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                else if (ind_bolseiro_selecionado==-1) JOptionPane.showMessageDialog(null, "Ainda não selecionou um Bolseiro.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                else {
                    Formando form_aux= (Formando)centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_bolseiros().get(ind_bolseiro_selecionado);
                    if (centro_investigacao.getLista_membros().get(ind_membro_selecionado).getCusto_projeto()!=0) {
                        JOptionPane.showMessageDialog(null, "Selecione um DOCENTE.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (form_aux.getOrientador()==null) JOptionPane.showMessageDialog(null, "O Bolseiro já tem um Orientador atribuído.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                    else {
                        lblInseriuCorreto.setBounds(560, 280, 140, 90);
                        lblInseriuCorreto.setText("<html><b>Investiador Principal atribuído com sucesso!</b></html>");
                        btnAtribuir.setVisible(false);
                        lblInseriuCorreto.setVisible(true);
                        centro_investigacao.getLista_projetos().get(ind_proj_selecionado).setInvestigador_principal((Docente)centro_investigacao.getLista_membros().get(ind_membro_selecionado));
                    }
                }
            }
        });

        // DONE
        this.btnAssociarPessoas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Licenciado lic_aux;
                Mestre mes_aux;
                Doutorado dout_aux;
                Docente doc_aux;
                if (centro_investigacao.getLista_membros()==null)
                JOptionPane.showMessageDialog(null, "O Centro não tem membros.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                else if (centro_investigacao.getLista_projetos()==null)
                JOptionPane.showMessageDialog(null, "O Centro não tem projetos.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                else {
                    scrollListaMembros2.setVisible(true);
                    lsListaMembros.setVisible(true);
                    btnAssociar.setVisible(true);

                    scrollListaProjetos.setVisible(false);
                    lsListaProjetos.setVisible(false);
                    btnAdicionarTarefa.setVisible(false);
                    btnEliminarTarefa.setVisible(false);
                    btnAtribuirTarefa.setVisible(false);
                    btnCustoProjeto.setVisible(false);
                    btnEncerrarProjeto.setVisible(false);
                    btnListarTarefas.setVisible(false);
                    btnListarTarefasConc.setVisible(false);
                    btnListarTarefasNConc.setVisible(false);
                    btnListarTarefasNI.setVisible(false);
                    btnAtualizarExecucao.setVisible(false);
                    btnAtribuirIP.setVisible(false);
                    btnAtribuirOrientadores.setVisible(false);
                    btnAssociarPessoas.setVisible(false);

                    modelo_lista_projetos_membros.removeAllElements();
                    for (int i=0; i<centro_investigacao.getLista_membros().size(); i++) {
                        if (centro_investigacao.getLista_membros().get(i).getCusto_projeto()==800) {
                            lic_aux= (Licenciado) centro_investigacao.getLista_membros().get(i);
                            modelo_lista_projetos_membros.addElement("Licenciado: "+lic_aux.getEmail()+" - "+lic_aux.getInicio_bolsa().getData()+" - "+lic_aux.getFim_bolsa().getData()+"\n");
                        } 
                        else if (centro_investigacao.getLista_membros().get(i).getCusto_projeto()==1000) {
                            mes_aux=(Mestre) centro_investigacao.getLista_membros().get(i);
                            modelo_lista_projetos_membros.addElement("Mestre: "+mes_aux.getEmail()+" - "+mes_aux.getInicio_bolsa().getData()+" - "+mes_aux.getFim_bolsa().getData()+"\n");
                        } 
                        else if (centro_investigacao.getLista_membros().get(i).getCusto_projeto()==1200) {
                            dout_aux=(Doutorado) centro_investigacao.getLista_membros().get(i);
                            modelo_lista_projetos_membros.addElement("Doutorado: "+dout_aux.getEmail()+" - "+dout_aux.getInicio_bolsa().getData()+" - "+dout_aux.getFim_bolsa().getData()+"\n");
                        } 
                        else if (centro_investigacao.getLista_membros().get(i).getCusto_projeto()==0) {
                            doc_aux=(Docente) centro_investigacao.getLista_membros().get(i);
                            modelo_lista_projetos_membros.addElement("Docente: "+doc_aux.getEmail()+" - "+doc_aux.getArea_investigacao()+"\n");
                        }
                    }
                }
            }
        });

        // DONE
        this.btnAssociar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (ind_membro_selecionado==-1) JOptionPane.showMessageDialog(null, "Ainda não selecionou um membro.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                else {
                    lblInseriuCorreto.setBounds(560, 280, 140, 90);
                    lblInseriuCorreto.setText("<html><b>Membro associado com sucesso!</b></html>");
                    btnAssociar.setVisible(false);
                    lblInseriuCorreto.setVisible(true);
                    centro_investigacao.associarPessoas(ind_proj_selecionado, ind_membro_selecionado);
                }
            }
        });

        // DONE
        this.btnListarTarefas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Tarefa tar_aux;
                scrollListaProjetos.setVisible(false);
                lsListaProjetos.setVisible(false);
                
                btnAdicionarTarefa.setVisible(false);
                btnEliminarTarefa.setVisible(false);
                btnAtribuirTarefa.setVisible(false);
                btnCustoProjeto.setVisible(false);
                btnEncerrarProjeto.setVisible(false);
                btnListarTarefas.setVisible(false);
                btnListarTarefasConc.setVisible(false);
                btnListarTarefasNConc.setVisible(false);
                btnListarTarefasNI.setVisible(false);
                btnAtualizarExecucao.setVisible(false);
                btnAtribuirIP.setVisible(false);
                btnAtribuirOrientadores.setVisible(false);
                btnAssociarPessoas.setVisible(false);

                
                scrollListaTarefas.setVisible(true);
                txaListaTarefas.setVisible(true);
                txaListaTarefas.setText(null);
                txaListaTarefas.append("TIPO: TAXA ESFORÇO | TAXA EXECUÇÃO | DATA INÍCIO | DURAÇÃO | DATA FIM | RESPONSÁVEL\n\n");
                for (int i=0; i<centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().size(); i++) {
                    tar_aux=centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().get(i);
                    if (tar_aux.getTaxa_esforco()==0.25f) {
                        if (tar_aux.getResponsavel()==null) {
                            txaListaTarefas.append("Documentação: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | - \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()==null) {
                            txaListaTarefas.append("Documentação: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | "+tar_aux.getResponsavel().getNome()+" \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()!=null) {
                            txaListaTarefas.append("Documentação: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | "+tar_aux.getFim_tarefa().getData()+" | "+tar_aux.getResponsavel().getNome()+" \n");
                        } 
                    } else if (tar_aux.getTaxa_esforco()==0.5f) {
                        if (tar_aux.getResponsavel()==null) {
                            txaListaTarefas.append("Design: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | - \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()==null) {
                            txaListaTarefas.append("Design: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | "+tar_aux.getResponsavel().getNome()+" \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()!=null) {
                            txaListaTarefas.append("Design: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | "+tar_aux.getFim_tarefa().getData()+" | "+tar_aux.getResponsavel().getNome()+" \n");
                        } 
                    } else if (tar_aux.getTaxa_esforco()==1) {
                        if (tar_aux.getResponsavel()==null) {
                            txaListaTarefas.append("Desenvolvimento: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | - \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()==null) {
                            txaListaTarefas.append("Desenvolvimento: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | "+tar_aux.getResponsavel().getNome()+" \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()!=null) {
                            txaListaTarefas.append("Desenvolvimento: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | "+tar_aux.getFim_tarefa().getData()+" | "+tar_aux.getResponsavel().getNome()+" \n");
                        } 
                    }
                }
            }
        });
        
        // DONE
        this.btnListarTarefasNConc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Tarefa tar_aux;
                ArrayList<Tarefa> tarefas_n_conc= new ArrayList<>();
                try {
                    tarefas_n_conc = centro_investigacao.getLista_projetos().get(ind_proj_selecionado).listarTarefasNC((byte) spnDia.getValue(), (byte) spnMes.getValue(), (int) spnAno.getValue());
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao Gerar Tarefas Não Concluídas.", "ERRO!", JOptionPane.ERROR_MESSAGE);
                }
                scrollListaProjetos.setVisible(false);
                lsListaProjetos.setVisible(false);
                
                btnAdicionarTarefa.setVisible(false);
                btnEliminarTarefa.setVisible(false);
                btnAtribuirTarefa.setVisible(false);
                btnCustoProjeto.setVisible(false);
                btnEncerrarProjeto.setVisible(false);
                btnListarTarefas.setVisible(false);
                btnListarTarefasConc.setVisible(false);
                btnListarTarefasNConc.setVisible(false);
                btnListarTarefasNI.setVisible(false);
                btnAtualizarExecucao.setVisible(false);
                btnAtribuirIP.setVisible(false);
                btnAtribuirOrientadores.setVisible(false);
                btnAssociarPessoas.setVisible(false);

                scrollListaTarefas.setViewportView(txaListaTarefas);
                scrollListaTarefas.setBounds(40, 140, 1200, 370);
                scrollListaTarefas.setVisible(true);
                txaListaTarefas.setVisible(true);
                txaListaTarefas.setText(null);

                txaListaTarefas.append("TIPO: TAXA ESFORÇO | TAXA EXECUÇÃO | DATA INÍCIO | DURAÇÃO | DATA FIM | RESPONSÁVEL\n\n");
                for (int i=0; i<tarefas_n_conc.size(); i++) {
                    tar_aux=centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().get(i);
                    if (tar_aux.getTaxa_esforco()==0.25f) {
                        if (tar_aux.getResponsavel()==null) {
                            txaListaTarefas.append("Documentação: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | - \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()==null) {
                            txaListaTarefas.append("Documentação: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | "+tar_aux.getResponsavel().getNome()+" \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()!=null) {
                            txaListaTarefas.append("Documentação: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | "+tar_aux.getFim_tarefa().getData()+" | "+tar_aux.getResponsavel().getNome()+" \n");
                        } 
                    } else if (tar_aux.getTaxa_esforco()==0.5f) {
                        if (tar_aux.getResponsavel()==null) {
                            txaListaTarefas.append("Design: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | - \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()==null) {
                            txaListaTarefas.append("Design: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | "+tar_aux.getResponsavel().getNome()+" \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()!=null) {
                            txaListaTarefas.append("Design: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | "+tar_aux.getFim_tarefa().getData()+" | "+tar_aux.getResponsavel().getNome()+" \n");
                        } 
                    } else if (tar_aux.getTaxa_esforco()==1) {
                        if (tar_aux.getResponsavel()==null) {
                            txaListaTarefas.append("Desenvolvimento: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | - \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()==null) {
                            txaListaTarefas.append("Desenvolvimento: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | "+tar_aux.getResponsavel().getNome()+" \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()!=null) {
                            txaListaTarefas.append("Desenvolvimento: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | "+tar_aux.getFim_tarefa().getData()+" | "+tar_aux.getResponsavel().getNome()+" \n");
                        } 
                    }
                }
            }
        });

        // DONE
        this.btnListarTarefasConc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Tarefa tar_aux;
                ArrayList<Tarefa> tarefas_conc= centro_investigacao.getLista_projetos().get(ind_proj_selecionado).listarTarefasCOrNI(true);

                scrollListaProjetos.setVisible(false);
                lsListaProjetos.setVisible(false);
                
                btnAdicionarTarefa.setVisible(false);
                btnEliminarTarefa.setVisible(false);
                btnAtribuirTarefa.setVisible(false);
                btnCustoProjeto.setVisible(false);
                btnEncerrarProjeto.setVisible(false);
                btnListarTarefas.setVisible(false);
                btnListarTarefasConc.setVisible(false);
                btnListarTarefasNConc.setVisible(false);
                btnListarTarefasNI.setVisible(false);
                btnAtualizarExecucao.setVisible(false);
                btnAtribuirIP.setVisible(false);
                btnAtribuirOrientadores.setVisible(false);
                btnAssociarPessoas.setVisible(false);

                scrollListaTarefas.setViewportView(txaListaTarefas);
                scrollListaTarefas.setBounds(40, 140, 1200, 370);
                scrollListaTarefas.setVisible(true);
                txaListaTarefas.setVisible(true);
                txaListaTarefas.setText(null);

                txaListaTarefas.append("TIPO: TAXA ESFORÇO | TAXA EXECUÇÃO | DATA INÍCIO | DURAÇÃO | DATA FIM | RESPONSÁVEL\n\n");
                for (int i=0; i<tarefas_conc.size(); i++) {
                    tar_aux=centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().get(i);
                    if (tar_aux.getTaxa_esforco()==0.25f) {
                        if (tar_aux.getResponsavel()==null) {
                            txaListaTarefas.append("Documentação: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | - \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()==null) {
                            txaListaTarefas.append("Documentação: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | "+tar_aux.getResponsavel().getNome()+" \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()!=null) {
                            txaListaTarefas.append("Documentação: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | "+tar_aux.getFim_tarefa().getData()+" | "+tar_aux.getResponsavel().getNome()+" \n");
                        } 
                    } else if (tar_aux.getTaxa_esforco()==0.5f) {
                        if (tar_aux.getResponsavel()==null) {
                            txaListaTarefas.append("Design: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | - \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()==null) {
                            txaListaTarefas.append("Design: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | "+tar_aux.getResponsavel().getNome()+" \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()!=null) {
                            txaListaTarefas.append("Design: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | "+tar_aux.getFim_tarefa().getData()+" | "+tar_aux.getResponsavel().getNome()+" \n");
                        } 
                    } else if (tar_aux.getTaxa_esforco()==1) {
                        if (tar_aux.getResponsavel()==null) {
                            txaListaTarefas.append("Desenvolvimento: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | - \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()==null) {
                            txaListaTarefas.append("Desenvolvimento: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | "+tar_aux.getResponsavel().getNome()+" \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()!=null) {
                            txaListaTarefas.append("Desenvolvimento: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | "+tar_aux.getFim_tarefa().getData()+" | "+tar_aux.getResponsavel().getNome()+" \n");
                        } 
                    }
                }
            }
        });

        // DONE
        this.btnListarTarefasNI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Tarefa tar_aux;
                ArrayList<Tarefa> tarefas_ni= centro_investigacao.getLista_projetos().get(ind_proj_selecionado).listarTarefasCOrNI(false);

                scrollListaProjetos.setVisible(false);
                lsListaProjetos.setVisible(false);
                
                btnAdicionarTarefa.setVisible(false);
                btnEliminarTarefa.setVisible(false);
                btnAtribuirTarefa.setVisible(false);
                btnCustoProjeto.setVisible(false);
                btnEncerrarProjeto.setVisible(false);
                btnListarTarefas.setVisible(false);
                btnListarTarefasConc.setVisible(false);
                btnListarTarefasNConc.setVisible(false);
                btnListarTarefasNI.setVisible(false);
                btnAtualizarExecucao.setVisible(false);
                btnAtribuirIP.setVisible(false);
                btnAtribuirOrientadores.setVisible(false);
                btnAssociarPessoas.setVisible(false);

                scrollListaTarefas.setViewportView(txaListaTarefas);
                scrollListaTarefas.setBounds(40, 140, 1200, 370);
                scrollListaTarefas.setVisible(true);
                txaListaTarefas.setVisible(true);
                txaListaTarefas.setText(null);

                txaListaTarefas.append("TIPO: TAXA ESFORÇO | TAXA EXECUÇÃO | DATA INÍCIO | DURAÇÃO | DATA FIM | RESPONSÁVEL\n\n");
                for (int i=0; i<tarefas_ni.size(); i++) {
                    tar_aux=centro_investigacao.getLista_projetos().get(ind_proj_selecionado).getLista_tarefas().get(i);
                    if (tar_aux.getTaxa_esforco()==0.25f) {
                        if (tar_aux.getResponsavel()==null) {
                            txaListaTarefas.append("Documentação: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | - \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()==null) {
                            txaListaTarefas.append("Documentação: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | "+tar_aux.getResponsavel().getNome()+" \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()!=null) {
                            txaListaTarefas.append("Documentação: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | "+tar_aux.getFim_tarefa().getData()+" | "+tar_aux.getResponsavel().getNome()+" \n");
                        } 
                    } else if (tar_aux.getTaxa_esforco()==0.5f) {
                        if (tar_aux.getResponsavel()==null) {
                            txaListaTarefas.append("Design: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | - \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()==null) {
                            txaListaTarefas.append("Design: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | "+tar_aux.getResponsavel().getNome()+" \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()!=null) {
                            txaListaTarefas.append("Design: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | "+tar_aux.getFim_tarefa().getData()+" | "+tar_aux.getResponsavel().getNome()+" \n");
                        } 
                    } else if (tar_aux.getTaxa_esforco()==1) {
                        if (tar_aux.getResponsavel()==null) {
                            txaListaTarefas.append("Desenvolvimento: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | - \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()==null) {
                            txaListaTarefas.append("Desenvolvimento: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | - | "+tar_aux.getResponsavel().getNome()+" \n");
                        } else if (tar_aux.getResponsavel()!=null && tar_aux.getFim_tarefa()!=null) {
                            txaListaTarefas.append("Desenvolvimento: "+tar_aux.getTaxa_esforco()+" | "+tar_aux.getTaxa_execucao()+" | "+tar_aux.getInicio_tarefa().getData()+ " | "+tar_aux.getDuracao()+" | "+tar_aux.getFim_tarefa().getData()+" | "+tar_aux.getResponsavel().getNome()+" \n");
                        } 
                    }
                }
            }
        });

        // DONE
        this.btnEncerrarProjeto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                centro_investigacao.getLista_projetos().get(ind_proj_selecionado).encerrarProjeto();
                lblProjetoEncerrado.setVisible(true);
                btnAdicionarTarefa.setVisible(false);
                btnEliminarTarefa.setVisible(false);
                btnAtribuirTarefa.setVisible(false);
                btnCustoProjeto.setVisible(false);
                btnEncerrarProjeto.setVisible(false);
                btnAtribuirIP.setVisible(false);
                btnAtribuirOrientadores.setVisible(false);
                btnAssociarPessoas.setVisible(false);
            }
        });
        
        // DONE
        this.btnCustoProjeto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCustoProjeto.setText(Float.toString(centro_investigacao.getLista_projetos().get(ind_proj_selecionado).custoProjeto())+"€");
            }
        });
        
        // DONE
        this.btnListarMembros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Licenciado lic_aux;
                Mestre mes_aux;
                Doutorado dout_aux;
                Docente doc_aux;
                btnAdicionarProjetos.setVisible(false);
                btnListarMembros.setVisible(false);
                btnListarProjetos.setVisible(false);
                btnListarProjetosConcluidos.setVisible(false);
                btnListarProjetosNConcluidos.setVisible(false);
                scrollListaMembros.setVisible(true);
                txaListaMembros.setVisible(true);
                txaListaMembros.setText(null);
                txaListaMembros.append("CARGO: NOME | EMAIL | Área Investigação | Número Mecanográfico | CARGA | Custo Projeto | Bolsa | Orientador\n\n");
                for (int i=0; i<centro_investigacao.getLista_membros().size(); i++) {
                    if (centro_investigacao.getLista_membros().get(i).getCusto_projeto()==800) {
                        lic_aux= (Licenciado) centro_investigacao.getLista_membros().get(i);
                        if (lic_aux.getOrientador()!=null) txaListaMembros.append("Licenciado:"+lic_aux.getNome()+" | "+lic_aux.getEmail()+" | - | - | "+Float.toString(lic_aux.getCarga())+" | "+Float.toString(lic_aux.getCusto_projeto())+" | "+lic_aux.getInicio_bolsa().getData()+" - "+lic_aux.getFim_bolsa().getData()+" | "+lic_aux.getOrientador().getNome()+"\n");
                        else txaListaMembros.append("Licenciado: "+lic_aux.getNome()+" | "+lic_aux.getEmail()+" | - | - | "+Float.toString(lic_aux.getCarga())+" | "+Float.toString(lic_aux.getCusto_projeto())+" | "+lic_aux.getInicio_bolsa().getData()+" - "+lic_aux.getFim_bolsa().getData()+" | - \n");
                    } 
                    else if (centro_investigacao.getLista_membros().get(i).getCusto_projeto()==1000) {
                        mes_aux=(Mestre) centro_investigacao.getLista_membros().get(i);
                        if (mes_aux.getOrientador()!=null) txaListaMembros.append("Mestre:"+mes_aux.getNome()+" | "+mes_aux.getEmail()+" | - | - | "+mes_aux.getCarga()+" | "+mes_aux.getCusto_projeto()+" | "+mes_aux.getInicio_bolsa().getData()+" - "+mes_aux.getFim_bolsa().getData()+" | "+mes_aux.getOrientador().getNome());
                        else txaListaMembros.append("Mestre: "+mes_aux.getNome()+" | "+mes_aux.getEmail()+" | - | - | "+mes_aux.getCarga()+" | "+mes_aux.getCusto_projeto()+" | "+mes_aux.getInicio_bolsa().getData()+" - "+mes_aux.getFim_bolsa().getData()+" | - \n");
                    } 
                    else if (centro_investigacao.getLista_membros().get(i).getCusto_projeto()==1200) {
                        dout_aux=(Doutorado) centro_investigacao.getLista_membros().get(i);
                        txaListaMembros.append("Doutorado: "+dout_aux.getNome()+" | "+dout_aux.getEmail()+" | - | - | "+dout_aux.getCarga()+" | "+dout_aux.getCusto_projeto()+" | "+dout_aux.getInicio_bolsa().getData()+" - "+dout_aux.getFim_bolsa().getData()+" | - \n");
                    } 
                    else if (centro_investigacao.getLista_membros().get(i).getCusto_projeto()==0) {
                        doc_aux=(Docente) centro_investigacao.getLista_membros().get(i);
                        txaListaMembros.append("Docente: "+doc_aux.getNome()+" | "+doc_aux.getEmail()+" | "+doc_aux.getArea_investigacao()+" | "+doc_aux.getNum_mecanografico()+" | "+doc_aux.getCarga()+" | "+doc_aux.getCusto_projeto()+" | - \n");
                    }
                }
            }
        });
        
        // DONE
        this.btnHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnListarMembros.setVisible(true);
                btnAdicionarProjetos.setVisible(true);
                btnListarProjetos.setVisible(true);
                btnListarProjetosConcluidos.setVisible(true);
                btnListarProjetosNConcluidos.setVisible(true);

                lblPedirDataInicio.setVisible(false);
                lblPedirDuracao.setVisible(false);
                lblPedirNome.setVisible(false);
                lblInseriuCorreto.setVisible(false);
                lblProjetoEncerrado.setVisible(false);

                txfInsereDataInicio.setVisible(false);
                txfInsereDuracao.setVisible(false);
                txfInsereTaxaExec.setVisible(false);
                txfInsereNome.setVisible(false);

                scrollListaProjetos.setVisible(false);
                scrollListaProjetos2.setVisible(false);
                scrollListaMembros.setVisible(false);
                scrollListaTarefas.setVisible(false);
                scrollListaMembros2.setVisible(false);
                scrollListaTarefas2.setVisible(false);
                scrollListaBolseiros.setVisible(false);
                lsListaBolseiros.setVisible(false);
                lsListaProjetos.setVisible(false);
                lsListaTarefas.setVisible(false);
                lsListaMembros.setVisible(false);
                txaListaMembros.setVisible(false);
                txaListaTarefas.setVisible(false);
                txaListaProjetos.setVisible(false);

                btnAdicionarTarefa.setVisible(false);
                btnEliminarTarefa.setVisible(false);
                btnAtribuirTarefa.setVisible(false);
                btnCustoProjeto.setVisible(false);
                btnEncerrarProjeto.setVisible(false);
                btnListarTarefas.setVisible(false);
                btnListarTarefasConc.setVisible(false);
                btnListarTarefasNConc.setVisible(false);
                btnListarTarefasNI.setVisible(false);
                btnAtualizarExecucao.setVisible(false);
                btnCriarTarefa.setVisible(false);
                btnCriarProjeto.setVisible(false);
                btnConfirmAtualizar.setVisible(false);
                btnAtribuirIP.setVisible(false);
                btnAtribuirOrientadores.setVisible(false);
                btnAssociarPessoas.setVisible(false);
                btnAssociar.setVisible(false);
                btnAtribuirOrientador.setVisible(false);
                btnAtribuir.setVisible(false);
                btnAtribuirTar.setVisible(false);
                

                cbDesenvolvimento.setVisible(false);
                cbDesign.setVisible(false);
                cbDocumentacao.setVisible(false);
            }
        });


        // ======================================================== SPINNERS ========================================================
        this.spnDia = new JSpinner(new SpinnerNumberModel(Byte.valueOf((byte)13), Byte.valueOf((byte)1), Byte.valueOf((byte)31), Byte.valueOf((byte)1)));
        this.spnMes = new JSpinner(new SpinnerNumberModel(Byte.valueOf((byte)12), Byte.valueOf((byte)1), Byte.valueOf((byte)12), Byte.valueOf((byte)1)));
        this.spnAno = new JSpinner(new SpinnerNumberModel(2019, 2000, 2030, 1));
        this.spnDia.setVisible(true);
        this.spnMes.setVisible(true);
        this.spnAno.setVisible(true);
        this.spnDia.setFont(new Font("Consolas", 0, 16));
        this.spnMes.setFont(new Font("Consolas", 0, 16));
        this.spnAno.setFont(new Font("Consolas", 0, 16));
        this.spnDia.setSize(new Dimension(50, 25));
        this.spnMes.setSize(new Dimension(50, 25));
        this.spnDia.setBounds(980, 600, 50, 30);
        this.spnMes.setBounds(1070, 600, 50, 30);
        this.spnAno.setBounds(1150, 600, 100, 30);
        
        // ======================================================== PAINEIS ========================================================
        this.menuPrincipal= new JPanel();
        this.menuPrincipal.setVisible(true);
        this.menuPrincipal.setLayout(null);
        this.menuPrincipal.setBackground(new Color(51, 204, 255));


        this.menuPrincipal.add(this.lblPedirNome);
        this.menuPrincipal.add(this.lblPedirDataInicio);
        this.menuPrincipal.add(this.lblPedirDuracao);
        this.menuPrincipal.add(this.lblInseriuCorreto);
        this.menuPrincipal.add(this.lblProjetoEncerrado);
        
        this.menuPrincipal.add(this.txfInsereDataInicio);
        this.menuPrincipal.add(this.txfInsereNome);
        this.menuPrincipal.add(this.txfInsereDuracao);
        this.menuPrincipal.add(this.txfInsereTaxaExec);
        
        this.menuPrincipal.add(this.lsListaProjetos);
        this.menuPrincipal.add(this.lsListaTarefas);
        this.menuPrincipal.add(this.lsListaMembros);
        this.menuPrincipal.add(this.lsListaBolseiros);
        this.menuPrincipal.add(this.txaListaMembros);
        this.menuPrincipal.add(this.txaListaTarefas);
        this.menuPrincipal.add(this.txaListaProjetos);
        this.menuPrincipal.add(this.scrollListaBolseiros);
        this.menuPrincipal.add(this.scrollListaProjetos);
        this.menuPrincipal.add(this.scrollListaProjetos2);
        this.menuPrincipal.add(this.scrollListaTarefas);
        this.menuPrincipal.add(this.scrollListaTarefas2);
        this.menuPrincipal.add(this.scrollListaMembros);
        this.menuPrincipal.add(this.scrollListaMembros2);

        this.menuPrincipal.add(this.btnAdicionarProjetos);
        this.menuPrincipal.add(this.btnListarProjetos);
        this.menuPrincipal.add(this.btnListarProjetosConcluidos);
        this.menuPrincipal.add(this.btnListarProjetosNConcluidos);
        this.menuPrincipal.add(this.btnListarMembros);
        this.menuPrincipal.add(this.btnCriarProjeto);
        this.menuPrincipal.add(this.btnAdicionarTarefa);
        this.menuPrincipal.add(this.btnEliminarTarefa);
        this.menuPrincipal.add(this.btnAtribuirTarefa);
        this.menuPrincipal.add(this.btnCustoProjeto);
        this.menuPrincipal.add(this.btnEncerrarProjeto);
        this.menuPrincipal.add(this.btnListarTarefas);
        this.menuPrincipal.add(this.btnListarTarefasConc);
        this.menuPrincipal.add(this.btnListarTarefasNConc);
        this.menuPrincipal.add(this.btnListarTarefasNI);
        this.menuPrincipal.add(this.btnAtualizarExecucao);
        this.menuPrincipal.add(this.btnApagarTarefa);
        this.menuPrincipal.add(this.btnConfirmAtualizar);
        this.menuPrincipal.add(this.btnAtribuirIP);
        this.menuPrincipal.add(this.btnAtribuirOrientadores);
        this.menuPrincipal.add(this.btnAssociarPessoas);
        this.menuPrincipal.add(this.btnAssociar);
        this.menuPrincipal.add(this.btnAtribuir);
        this.menuPrincipal.add(this.btnAtribuirOrientador);
        this.menuPrincipal.add(this.btnAtribuirTar);

        this.menuPrincipal.add(this.cbDesenvolvimento);
        this.menuPrincipal.add(this.cbDesign);
        this.menuPrincipal.add(this.cbDocumentacao);
        this.menuPrincipal.add(this.btnCriarTarefa);


        // ======================================================== FRAME ========================================================
        this.add(this.lbltituloMenu);
        this.add(this.btnHome);
        this.add(this.spnDia);
        this.add(this.spnMes);
        this.add(this.spnAno);
        this.add(this.lblDataAtual);
        this.add(this.lblBar);
        this.add(this.lblBar2);
        this.add(this.menuPrincipal);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Gerenciador de Projetos");
        this.setSize(1300,720);
        
    }

}