package Tela;
import com.mycompany.clinicaveterinaria.Clinica;
import javax.swing.JFrame;
import java.util.*;
import java.lang.Class.*;

public class Globals{
    public static Clinica clinica = new Clinica();
    public static Stack<JFrame> paneHistory = new Stack<JFrame>();

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
            previousPanel.invalidate();
            previousPanel.revalidate();
            previousPanel.repaint();
            previousPanel.setVisible(true);
            currentPanel.dispose();
        }
    }

    public static void goHome(JFrame currentPanel){
        new Menu().setVisible(true);
        currentPanel.dispose();
        paneHistory.clear();
    }
}
