package Tela;
import com.mycompany.clinicaveterinaria.Clinica;
import javax.swing.JFrame;
import java.util.*;
import java.awt.*;
import java.lang.reflect.*;

public class PanelController{
    public Clinica clinica = new Clinica();
    private Stack<Constructor<?>> paneHistory = new Stack<Constructor<?>>();
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
        try{
            nextPanel = nextPanel.getClass().getConstructor(PanelController.class).newInstance(this);
        }catch(Throwable e){
            System.out.println("Problema instanciando classe em panel switch.");
            return;
        }

        try{
            paneHistory.push(currentPanel.getClass().getConstructor(PanelController.class));
        }catch(Throwable e){
            System.out.println("problem");
            return;
        }

        nextPanel.setVisible(true);
        currentPanel.dispose();
    }

    public void panelReturn(JFrame currentPanel){
        Constructor<?> previousPanel;
        try{
            previousPanel = paneHistory.pop();
        }catch(Throwable e){
            System.out.println("Não há itens para dar pop no stack.");
            return;
        }
        lastFrameLocation = currentPanel.getLocation();
        JFrame newPanel;
        try{
            newPanel = (JFrame)previousPanel.newInstance(this);
        }catch(Throwable e){
            System.out.println("Erro tentando instanciar frame.");
            return;
        }

        newPanel.setVisible(true);
        currentPanel.dispose();
    }

    public void goHome(JFrame currentPanel){
        lastFrameLocation = currentPanel.getLocation();
        new Menu(this).setVisible(true);
        currentPanel.dispose();
        paneHistory.clear();
    }
}

