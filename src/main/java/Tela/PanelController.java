package Tela;
import com.mycompany.clinicaveterinaria.*;
import javax.swing.JFrame;
import java.util.*;
import java.awt.*;
import java.lang.reflect.*;

public class PanelController{
    public Clinica clinica = new Clinica();
    private Stack<Constructor<?>> paneHistory = new Stack<Constructor<?>>();
    public JFrame curPanel = null;
    
    private void startPanel(JFrame panel){
        System.out.println("Starting the panel");
        curPanel = panel;
        panel.setSize(500, 600);        
        panel.setResizable(false);       
        panel.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        panel.setLocationRelativeTo(null);
        panel.setAlwaysOnTop(true);
    }

    public void setPanel(JFrame panel){
        System.out.println("Passed through the setpanel function");
        if(curPanel == null){
            startPanel(panel);
            return;
        }
        panel.setSize(500, 600);        
        panel.setResizable(false);       
        panel.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        Point location = curPanel.getLocation();
        panel.setLocationRelativeTo(null);
        if(false)
            panel.setLocation(location.x, location.y);
        panel.setAlwaysOnTop(true);
    }

    public void setPanel(){
        setPanel(curPanel);
    }

    public void panelSwitch(JFrame nextPanel){
        if(curPanel == null){
            System.out.println("Não há painel atual.");
            return;
        }
        try{
            paneHistory.push(curPanel.getClass().getConstructor(PanelController.class));
        }catch(Throwable e){
            System.out.println("problem");
            return;
        }

        curPanel.dispose();
        curPanel = nextPanel;
        setPanel(nextPanel);
        nextPanel.setVisible(true);
    }

    public void panelReturn(){
        if(curPanel == null){
            System.out.println("Não há painel atual.");
            return;
        }

        Constructor<?> previousPanel;
        try{
            previousPanel = paneHistory.pop();
        }catch(Throwable e){
            System.out.println("Não há itens para dar pop no stack.");
            return;
        }
        JFrame newPanel;
        try{
            newPanel = (JFrame)previousPanel.newInstance(this);
        }catch(Throwable e){
            System.out.println("Erro tentando instanciar frame.");
            return;
        }

        curPanel.dispose();
        curPanel = newPanel;
        setPanel();
        newPanel.setVisible(true);
    }

    public void goHome(){
        if(curPanel == null){
            System.out.println("Não há painel atual.");
            return;
        }
        JFrame newMenu = new Menu(this);
        curPanel.dispose();
        paneHistory.clear();
        curPanel = newMenu;
        setPanel();
        newMenu.setVisible(true);
    }
}

