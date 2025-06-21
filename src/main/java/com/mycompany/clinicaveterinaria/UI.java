import javax.swing.*;
import java.awt.*;
import java.util.*;

public class UI extends JFrame{
    Clinica clinica;
    CardLayout cardLayout = new CardLayout();
    JPanel mainPanel = new JPanel(cardLayout);

    final String homeId = "home";

    final String cadastroId = "Cadastro";
    //Para todas as próximas janelas será necessário
    //que se pergunte qual tipo de usuário você deseja
    //inserir, alterar, remover ou consultar. Acredito
    //que a forma que eu vá fazer isso vai ser por 
    //meio dos carlayouts, fazendo com que eu mude
    //o tipo a depender do que ele for.
    final String inserirCadastroId = "Inserir cadastro";
    final String alterarCadastroId = "Alterar cadastro";
    final String removerCadastroId = "Remover cadastro";
    final String consultarCadastroId = "Consultar cadastro";

    final String agendamentoId = "Agendamento";
    final String inserirAgendamentoId = "Inserir agendamento";
    final String cancelarAgendamentoId = "Cancelar agendamento";

    final String consultaId = "Consulta";
    final String inserirConsultaId = "Inserir consulta";
    final String consultarConsultaId = "Consultar consulta";

    final String vacinaId = "Gerenciamento de vacinas";
    final String incluirVacinaId = "Incluir vacina";
    final String consultarVacinaId = "Consultar vacina";

    final String emitirCartaoId = "Emitir cartão de vacina";
    final String emitirCobrancaId = "Emitir cobrança";
    final String emitirProntuarioId = "Emitir prontuario";

    String currentWindow = homeId;
    Stack<String> history = new Stack<String>();

    Color panelBackgroundColor = Color.blue;
    Color upperBar = Color.red;

    JButton createWindowButton(String buttonMessage, String window){
        JButton newButton = new JButton(buttonMessage);
        newButton.addActionListener(e -> {
            if(window != currentWindow){
                cardLayout.show(mainPanel, window);
                history.push(currentWindow);
                currentWindow = window;
            }
        });

        return newButton;
    }

    JButton createWindowButton(String buttonMessage){
        return createWindowButton(buttonMessage, buttonMessage);
    }

    JButton createButton(String buttonMessage){
        JButton newButton = new JButton(buttonMessage);

        return newButton;
    }

    JPanel addAppLayout(Component base, String panelName){
        JPanel windowPanel = new JPanel(new BorderLayout());

        base.setBackground(Color.blue);
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton returnButton = new JButton("<-");
        returnButton.setPreferredSize(new Dimension(100, 20));
        JButton homeButton = new JButton("home");
        homeButton.setPreferredSize(new Dimension(100, 20));
        JLabel title = new JLabel(panelName);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        topPanel.setBackground(Color.red);

        homeButton.addActionListener(e-> {
            cardLayout.show(mainPanel, homeId);
            currentWindow = homeId;
        });
        returnButton.addActionListener(e -> {
            if(history.size() > 0){
                String newCurrentWindow = history.pop();
                cardLayout.show(mainPanel, newCurrentWindow);
                currentWindow = newCurrentWindow;
            }
        });
        topPanel.add(title, BorderLayout.CENTER);
        topPanel.add(returnButton, BorderLayout.WEST);
        topPanel.add(homeButton, BorderLayout.EAST);

        windowPanel.add(topPanel, BorderLayout.NORTH);
        windowPanel.add(base, BorderLayout.CENTER);


        return windowPanel;
    }

    JPanel createBasicPanel(String panelName, String... n){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7, 1, 0, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for(String curString : n){
            buttonPanel.add(createWindowButton(curString));
        }

        return addAppLayout(buttonPanel, panelName);
    }

    JPanel createForm(){
        return new JPanel(new GridLayout(12, 1, 0, 0));
    }

    JSplitPane formatFormBox(Component a, Component b){
        JSplitPane newPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        newPanel.setResizeWeight(0.3); 
        newPanel.setDividerSize(0);    
        newPanel.setEnabled(false);    

        newPanel.add(a);
        newPanel.add(b);

        return newPanel;
    }

    JSplitPane formatTextBox(String label, JTextField field){
        JLabel newLabel = new JLabel(label);

        return formatFormBox(newLabel, field);
    }

    JPanel inserirCadastro(){
        JSplitPane mainPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        mainPanel.setResizeWeight(0.3); 
        mainPanel.setDividerSize(0);    
        mainPanel.setEnabled(false);    
        CardLayout cardLayout = new CardLayout();
        JPanel switchPanel = new JPanel(cardLayout);

        String vetId = "veterinario";
        String tutorId = "tutor";
        String funcionarioExtraId = "extra";

        JPanel vetPanel = new JPanel();
        {
            JButton sendButton = new JButton("send");
            JPanel contentPanel = createForm();

            ArrayList<String> stringList = new ArrayList<String>();
            for(Specialty s : clinica.specialties){
                stringList.add(s.nome);
            }
            JComboBox specialties = new JComboBox(stringList.toArray());

            JTextField nome = new JTextField(15);
            JTextField email = new JTextField(15);
            JTextField cpf = new JTextField(15);
            JTextField phone = new JTextField(15);
            JTextField cfmv = new JTextField(15);

            contentPanel.add(formatFormBox(new JLabel("Nome"), nome));
            contentPanel.add(formatFormBox(new JLabel("Email"), email));
            contentPanel.add(formatFormBox(new JLabel("Especialidades"), specialties));
            contentPanel.add(formatFormBox(new JLabel("CPF"), cpf));
            contentPanel.add(formatFormBox(new JLabel("Número de telefone"), phone));
            contentPanel.add(formatFormBox(new JLabel("Cfmv"), cfmv));

            sendButton.addActionListener(e -> {
                Specialty actualSpecialty = null;
                for(Specialty s : clinica.specialties){
                    if(s.nome == specialties.getSelectedItem()){
                        actualSpecialty = s;
                    }
                }

                Veterinario newVet = new Veterinario(nome.getText(), 
                        email.getText(),
                        actualSpecialty,
                        cfmv.getText(),
                        cpf.getText(),
                        phone.getText());

                if(!clinica.signUser(newVet)){
                    JOptionPane.showMessageDialog(
                            null,  // parent component; you can use your window/panel instead of null if you want
                            "Erro ao cadastrar o veterinário. Verifique os dados e tente novamente.",
                            "Erro de Cadastro",
                            JOptionPane.ERROR_MESSAGE
                            );
                }
            });

            vetPanel.add(contentPanel);
            vetPanel.add(sendButton);
        }

        JPanel funcionarioExtraPanel = new JPanel();
        {
            JButton sendButton = new JButton("send");
            JPanel contentPanel = createForm();

            JTextField nome = new JTextField(15);
            JTextField email = new JTextField(15);
            JTextField cpf = new JTextField(15);
            JTextField phone = new JTextField(15);
            JTextField turno = new JTextField(15);

            contentPanel.add(formatFormBox(new JLabel("Nome"), nome));
            contentPanel.add(formatFormBox(new JLabel("Email"), email));
            contentPanel.add(formatFormBox(new JLabel("Turno"), turno));
            contentPanel.add(formatFormBox(new JLabel("CPF"), cpf));
            contentPanel.add(formatFormBox(new JLabel("Número de telefone"), phone));

            funcionarioExtraPanel.add(contentPanel);
            funcionarioExtraPanel.add(sendButton);
        }

        JPanel tutorPanel = new JPanel();
        {
            JPanel contentPanel = createForm();
            JButton sendButton = new JButton();

            JTextField nome = new JTextField(15);
            JTextField email = new JTextField(15);
            JTextField cpf = new JTextField(15);
            JTextField phone = new JTextField(15);
            JTextField address = new JTextField(15);

            contentPanel.add(formatFormBox(new JLabel("Nome"), nome));
            contentPanel.add(formatFormBox(new JLabel("Email"), email));
            contentPanel.add(formatFormBox(new JLabel("Address"), address));
            contentPanel.add(formatFormBox(new JLabel("CPF"), cpf));
            contentPanel.add(formatFormBox(new JLabel("Número de telefone"), phone));

            tutorPanel.add(contentPanel);
            tutorPanel.add(sendButton);
        }

        switchPanel.add(vetPanel, vetId);
        switchPanel.add(funcionarioExtraPanel, funcionarioExtraId);
        switchPanel.add(tutorPanel, tutorId);
        switchPanel.add(new JPanel(), " ");

        JPanel optionPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        {
            JButton vetButton = new JButton(vetId);
            vetButton.addActionListener(e -> cardLayout.show(switchPanel, vetId));
            JButton tutorButton = new JButton(tutorId);
            tutorButton.addActionListener(e -> cardLayout.show(switchPanel, tutorId));
            JButton extraButton = new JButton(funcionarioExtraId);
            extraButton.addActionListener(e -> cardLayout.show(switchPanel, funcionarioExtraId));

            optionPanel.add(vetButton);
            optionPanel.add(tutorButton);
            optionPanel.add(extraButton);
        }

        mainPanel.add(optionPanel);
        mainPanel.add(switchPanel);

        cardLayout.show(switchPanel, " ");

        return addAppLayout(mainPanel, "Inserir cadastro");
    }

    JPanel telaEmitirCartao(){
        return new JPanel();
    }

    JPanel telaEmitirCobrança(){
        return new JPanel();
    }

    JPanel telaEmitirProntuario(){
        return new JPanel();
    }

    public UI(Clinica clinica){
        super("clinica");
        this.clinica = clinica;
        setSize(500, 600);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); // Center window

        mainPanel.add(createBasicPanel("Clinica", cadastroId, agendamentoId, consultaId, vacinaId, emitirCartaoId, emitirCobrancaId, emitirProntuarioId), homeId);
        mainPanel.add(createBasicPanel("Cadastro", inserirCadastroId, alterarCadastroId, removerCadastroId, consultarCadastroId), cadastroId);
        mainPanel.add(createBasicPanel("Agendamento", inserirAgendamentoId, cancelarAgendamentoId), agendamentoId);
        mainPanel.add(createBasicPanel("Consulta", inserirConsultaId, consultarConsultaId), consultaId);
        mainPanel.add(createBasicPanel("Vacina", incluirVacinaId, consultarVacinaId), vacinaId);
        mainPanel.add(telaEmitirCartao(), emitirCartaoId);
        mainPanel.add(telaEmitirCobrança(), emitirCobrancaId);
        mainPanel.add(telaEmitirProntuario(), emitirProntuarioId);
        mainPanel.add(inserirCadastro(), inserirCadastroId);

        cardLayout.show(mainPanel, homeId);

        setContentPane(mainPanel);
        setVisible(true);
    }
}
