package Tela;
import com.mycompany.clinicaveterinaria.Clinica;
import javax.swing.JFrame;
import java.util.*;
import java.awt.*;
import java.lang.reflect.*;

public class PanelController{
    public Clinica clinica = new Clinica();
    private Stack<Constructor<?>> paneHistory = new Stack<>();
    private Point lastFrameLocation = null;
    
    public void setPanel(JFrame panel){
        System.out.println("something");
        panel.setSize(500, 600);        
        panel.setResizable(false);       
        panel.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        if(lastFrameLocation != null)
            panel.setLocation(lastFrameLocation.x, lastFrameLocation.y);
        else
            panel.setLocationRelativeTo(null);
        panel.setAlwaysOnTop(true);
    }

    public void panelSwitch(JFrame currentPanel, JFrame nextPanel){
        lastFrameLocation = currentPanel.getLocation();
        nextPanel.setVisible(true);
        try{
            paneHistory.push(currentPanel.getClass().getConstructor(PanelController.class));
        }catch(Throwable e){
            System.out.println("problem");
        }
        currentPanel.dispose();
    }

    public void panelReturn(JFrame currentPanel){
        lastFrameLocation = currentPanel.getLocation();
        Constructor<?> previousPanel;
        try{
            previousPanel = paneHistory.pop();
        }catch(Throwable e){
            System.out.println("Não há itens para dar pop no stack.");
            previousPanel = null;
        }

        try{
            previousPanel.newInstance(this);
        }catch(Throwable e){
            System.out.println("Erro tentando instanciar frame.");
        }

        if(previousPanel != null){
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

