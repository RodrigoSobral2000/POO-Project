package app;

import java.text.ParseException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App {
    
    public static void main(String[] args) throws ParseException {
        CentroInvestigacao centro_investigacao= new CentroInvestigacao();
        Ficheiros fich = new Ficheiros();
        Interface frame= new Interface(centro_investigacao);
        fich.verificar_objeto(centro_investigacao);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                fich.escrever_obj_pess(centro_investigacao.getLista_membros());
                fich.escrever_obj_proj(centro_investigacao.getLista_projetos());
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }
}
