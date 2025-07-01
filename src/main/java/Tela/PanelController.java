package Tela;
import com.mycompany.clinicaveterinaria.Clinica;
import javax.swing.JFrame;
import java.util.*;
import java.awt.*;

public class PanelController{
    public Clinica clinica = new Clinica();
    public Stack<JFrame> paneHistory = new Stack<JFrame>();
    Point lastFrameLocation;
    
    public void setPanel(JFrame panel){
        panel.setSize(700, 800);        
        panel.setResizable(false);       
        panel.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        panel.setVisible(true);
        panel.setLocation(lastFrameLocation.x, lastFrameLocation.y);
        panel.setAlwaysOnTop(true);
    }

    public void panelSwitch(JFrame currentPanel, JFrame nextPanel){
        lastFrameLocation = currentPanel.getLocation();
        nextPanel.setVisible(true);
        try{
            paneHistory.push(currentPanel.getClass().getConstructor().newInstance(this));
        }catch(Throwable e){
        }
        currentPanel.dispose();
    }

    public void panelReturn(JFrame currentPanel){
        lastFrameLocation = currentPanel.getLocation();
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

    public void goHome(JFrame currentPanel){
        lastFrameLocation = currentPanel.getLocation();
        new Menu().setVisible(true);
        currentPanel.dispose();
        paneHistory.clear();
    }
}

