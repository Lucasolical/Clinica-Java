package Tela;
import com.mycompany.clinicaveterinaria.Clinica;
import javax.swing.JFrame;
import java.util.*;
import java.lang.Class.*;
import java.lang.reflect.Constructor;

public class Globals{
    public static Clinica clinica = new Clinica();
    public static Stack<JFrame> paneHistory = new Stack<JFrame>();

    public static void panelSwitch(JFrame currentPanel, JFrame nextPanel){
        nextPanel.setVisible(true);
        try{
            paneHistory.push(currentPanel.getClass().getConstructor().newInstance());
        }catch(Throwable e){
        }
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
