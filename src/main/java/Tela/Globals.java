package Tela;
import com.mycompany.clinicaveterinaria.Clinica;
import javax.swing.JFrame;
import java.util.*;

public class Globals{
    public static Clinica clinica = new Clinica();
    public static Stack<JFrame> paneHistory = new Stack<JFrame>();
    public static JFrame home = new Menu();

    public static void panelSwitch(JFrame currentPanel, JFrame nextPanel){
        nextPanel.setVisible(true);
        paneHistory.push(currentPanel);
        currentPanel.dispose();
    }

    public static void panelReturn(JFrame currentPanel){
        JFrame previousPanel;
        try{
            previousPanel = paneHistory.pop();
        }catch(Throwable e){
            previousPanel = null;
        }

        if(previousPanel != null){
            previousPanel.setVisible(true);
            currentPanel.dispose();
        }
    }

    public static void goHome(JFrame currentPanel){
        home.setVisible(true);
        currentPanel.dispose();
        paneHistory.clear();
    }
}
