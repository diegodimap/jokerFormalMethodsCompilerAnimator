package gui;

import animation.AnimationPanel;
import analysis.TokenClassifier;
import configuration.EditorColors;
import configuration.Fonts;
import configuration.Options;
import base.Token;
import io.SaveFile;
import java.awt.Cursor;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import base.Patterns;
import configuration.LAFOption;
import configuration.SoundPaths;
import io.ReadFile;
import io.WriteFile;
import java.io.IOException;
import javax.swing.JProgressBar;
import tools.EditorTools;
//import tools.PrintCode;
import b.BTransformation;
import z.ZTransformation;
import csp.CSPTransformation;
import tools.Imprimir;

/**
 * This is the main window. It controls all the other ones. It also is responsible
 * for coordinating the load and save of preferences. The variables values are
 * accessible from this class to configure all the other windows and user preferences.
 * @author Diego Henrique Oliveira de Souza
 */
public class JokerWindow extends javax.swing.JFrame {
    //arrays de controle
    private ArrayList<String> undo = new ArrayList<String>();
    private int undoPosition = 0;
    public static ArrayList<Token> tokensLexica = new ArrayList<Token>();
    //trata dos tempos de compilacao
    private long tempoTotal = 0;
    //aparencia
    private UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
    //cores
    private static SimpleAttributeSet PALAVRA_CHAVE = new SimpleAttributeSet();
    private static SimpleAttributeSet COLCHETES = new SimpleAttributeSet();
    private static SimpleAttributeSet SIMBOLOS = new SimpleAttributeSet();
    private static SimpleAttributeSet IDENTIFICADOR = new SimpleAttributeSet();
    private static SimpleAttributeSet COMENTARIO = new SimpleAttributeSet();
    private static SimpleAttributeSet OPERADORES = new SimpleAttributeSet();
    private static SimpleAttributeSet SAIDA = new SimpleAttributeSet();
    private static SimpleAttributeSet NAO_RECONHECIDO = new SimpleAttributeSet();
    //cores do editor
    private static EditorColors cores = new EditorColors();
    //configuracoes do JLotos
    private static Options options = new Options();
    //configuracao da fonte do editor JLotos
    private static Fonts fontes = new Fonts();
    //ferramentas de edicao
    private static EditorTools editorTools = new EditorTools();
    //sons de erro
    private static SoundPaths soundPaths = new SoundPaths();
    //aparencia
    private static LAFOption lafOptions = new LAFOption();
    //private static String cod = "";
    public static File arq = null;
    public static AnimationPanel ap;

    //LOG
    public static void logIt(String msg) {
        append(msg + "\n", IDENTIFICADOR, areaLog);
        //JokerWindow.areaLog.setText(JokerWindow.areaLog.getText() + "\n" + msg);
    }

    //ANIMATION PANEL
    public static AnimationPanel getAp() {
        return ap;
    }

    public static void setAp(AnimationPanel ap) {
        JokerWindow.ap = ap;
    }

    public static void setArquivo(File f) {
        arq = f;
    }

    public File getArquivo() {
        return arq;
    }

    public static JComboBox getSelectedLanguage() {
        return selectedLanguage;
    }

    public static void setSelectedLanguage(JComboBox selectedLanguage) {
        JokerWindow.selectedLanguage = selectedLanguage;
    }

    public JokerWindow(String xmlizes) {
        //inicializa os componentes da janela
        initComponents();

        //CONFIGURA O JOKER
        String cspmOld = options.getCspmFolder();
        String probOld = options.getProbcliFolder();
        String cspmNew = "";
        String probNew = "";

        String sistemaOperacional = System.getProperty("os.name");

        if (sistemaOperacional.contains("Win")) {
            sistemaOperacional = ".exe";
        } else {
            sistemaOperacional = "";
        }

//        System.out.println(cspmTest);
//        System.out.println(probTest);

        File test = new File("");

        if(test.getAbsolutePath().contains(" ")){
            JOptionPane.showMessageDialog(null, "The folder Joker Package is in: \n "
                    + test.getAbsolutePath() + "\n "
                    + "contain blank spaces. \n"
                    + "Please move Joker Package to another folder \n"
                    + "with no blank spaces such as: \n"
                    + "\"c:\\Joker\", for Windows or\n"
                    + "\"/home/user/Joker\", for Linux." , "Bad Folder", JOptionPane.WARNING_MESSAGE);
        }

        cspmNew = test.getAbsolutePath() + test.separator + "lib" + test.separator + "cspm-0.5.2.0" + sistemaOperacional;
        probNew = test.getAbsolutePath() + test.separator + "lib" + test.separator + "ProB" + test.separator + "probcli" + sistemaOperacional;

//            System.out.println(cspmTest);
//            System.out.println(probTest);

        try {

            File testFile = new File("configuration.joker");
            if (!testFile.exists()) {
                testFile.createNewFile();
            }

            String configs = ReadFile.leia("./configuration.joker");
            String partes[] = configs.split(";");

            WriteFile.escreva("./configuration.joker",
                    "cspm=" + cspmNew
                    + ";\nprobcli=" + probNew + ";"
                    + partes[2] + ";"
                    + partes[3] + ";"
                    + partes[4] + ";"
                    + partes[5]);
            this.dispose();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "FILE ERROR", JOptionPane.ERROR_MESSAGE);
        }


        if (xmlizes.length() == 0) {
            //nada
        } else {
            areaCodigo.setText(xmlizes); 
            TokenClassifier al = new TokenClassifier(areaCodigo.getText());
            areaCodigo.setText("");
            for (Token token : al.getTokens()) {
                colorirCodigo(token);
            }
        }

        //icone do programa
        setIconImage(new ImageIcon(getClass().getResource("/img/jokerLogo.png")).getImage());

        //trata da aparencia do programa
        for (final UIManager.LookAndFeelInfo lookAndFeelInfo : looks) {
            JMenuItem t = new JMenuItem(lookAndFeelInfo.getName());
            t.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/camera.png")));
            t.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    mudarLAF(lookAndFeelInfo);
                }
            });
            laf.add(t);

            if (lookAndFeelInfo.getClassName().equalsIgnoreCase(UIManager.getSystemLookAndFeelClassName())) {
                this.mudarLAF(lookAndFeelInfo);
            }

            //SELECIONA SPECIFICATONS AS INITIAL TAB
            abas.setSelectedIndex(1);
        }

        //inicia as cores do editor com as configuracoes padrao
        configurarCoresEditor();
        //fechamento padrao do programa
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //tamanho inicial
        this.setSize(800, 600);
        //configura o titulo do programa
        this.setTitle("JOKER 1.0");
        //configura o estado da janela como maximizado
        this.setExtendedState(JokerWindow.MAXIMIZED_BOTH);
        //mostra a janela principal
        this.setVisible(true);
    }

    public static void configurarCoresEditor() {
        //trata das cores do editor
        StyleConstants.setForeground(PALAVRA_CHAVE, cores.getPalavra_chave());
        StyleConstants.setBold(PALAVRA_CHAVE, cores.isIsBold_palavra_chave());
        StyleConstants.setItalic(PALAVRA_CHAVE, cores.isIsItalic_palavra_chave());

        StyleConstants.setForeground(IDENTIFICADOR, cores.getIdentificador());
        StyleConstants.setBold(IDENTIFICADOR, cores.isIsBold_identificador());
        StyleConstants.setItalic(IDENTIFICADOR, cores.isIsItalic_identificador());

        StyleConstants.setForeground(COMENTARIO, cores.getComentario());
        StyleConstants.setBold(COMENTARIO, cores.isIsBold_comentario());
        StyleConstants.setItalic(COMENTARIO, cores.isIsItalic_comentario());

        StyleConstants.setForeground(OPERADORES, cores.getOperadores());
        StyleConstants.setBold(OPERADORES, cores.isIsBold_operadores());
        StyleConstants.setItalic(OPERADORES, cores.isIsItalic_operadores());

        StyleConstants.setForeground(SAIDA, cores.getSaida());
        StyleConstants.setBold(SAIDA, cores.isIsBold_saida());
        StyleConstants.setItalic(SAIDA, cores.isIsItalic_saida());

        StyleConstants.setForeground(SIMBOLOS, cores.getProcesso());
        StyleConstants.setBold(SIMBOLOS, cores.isIsBold_processo());
        StyleConstants.setItalic(SIMBOLOS, cores.isIsItalic_processo());

        StyleConstants.setForeground(COLCHETES, cores.getGate());
        StyleConstants.setBold(COLCHETES, cores.isIsBold_gate());
        StyleConstants.setItalic(COLCHETES, cores.isIsItalic_gate());

        StyleConstants.setForeground(NAO_RECONHECIDO, cores.getNao_reconhecidos());
        StyleConstants.setBold(NAO_RECONHECIDO, false);
        StyleConstants.setItalic(NAO_RECONHECIDO, false);
    }

    //trata da mudanca de aparencia do Joker
    private void mudarLAF(UIManager.LookAndFeelInfo lookAndFeelInfo) {
        try {
            UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            JokerWindow.logIt(e.getMessage());
        }
    }

    public void colorirAoAbrir() {
        //pegando a saida da lexica
        TokenClassifier al = new TokenClassifier(areaCodigo.getText());
        this.tokensLexica = al.getTokens();

        //adicionando a saida da analise lexica e na area de codigo colorido
        areaCodigo.setText("");
        for (Token t : tokensLexica) {
            colorirCodigo(t);
        }
    }

    public static void colorirCodigo(Token t) {
        if (t.getTipo().equals(Patterns.palavraChave)) {
            append(t.getConteudo(), PALAVRA_CHAVE, areaCodigo);
        } else {
            if (t.getTipo().equals(Patterns.simbolo) || (t.getTipo().equals(Patterns.atribuicao))) {
                append(t.getConteudo(), COLCHETES, areaCodigo);
            } else {
                if ((t.getConteudo().startsWith("(*")) || (t.getConteudo().startsWith("''"))) {
                    append(t.getConteudo(), COMENTARIO, areaCodigo);
                } else {
                    if (t.getTipo().equals(Patterns.operador)) {
                        append(t.getConteudo(), OPERADORES, areaCodigo);
                    } else {
                        if (t.getTipo().equals(Patterns.saida)) {
                            append(t.getConteudo(), SAIDA, areaCodigo);
                        } else {
                            if (t.getTipo().equals(Patterns.identificador)) {
                                append(t.getConteudo(), IDENTIFICADOR, areaCodigo);
                            } else {
                                if ((t.getTipo().equals(Patterns.doisPontos))
                                        || (t.getTipo().equals(Patterns.simbolo)
                                        || (t.getTipo().equals(Patterns.pontoEvirgula))
                                        || (t.getTipo().equals(Patterns.virgula)))) {
                                    append(t.getConteudo(), SIMBOLOS, areaCodigo);
                                } else {
                                    append(t.getConteudo(), NAO_RECONHECIDO, areaCodigo);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    protected static void append(String s, AttributeSet attributes, JTextPane tp) {
        Document d = tp.getDocument();
        try {
            d.insertString(d.getLength(), s, (javax.swing.text.AttributeSet) attributes);
        } catch (BadLocationException ble) {
            JOptionPane.showMessageDialog(null, ble.getMessage());
        }
    }

    public static EditorColors getCores() {
        return cores;
    }

    public static Fonts getFonts() {
        return fontes;
    }

    public static Options getOptions() {
        return options;
    }

    public static SoundPaths getSoundPaths() {
        return soundPaths;
    }

    public static LAFOption getLafOption() {
        return lafOptions;
    }

    public static EditorTools getEditorTools() {
        return editorTools;
    }

    public static void setFontAreaCodigo(Font f) {
        areaCodigo.setFont(f);
        areaLts.setFont(f);
    }

    public static void setCodigoAreaCodig(String cod) {
        areaCodigo.setText(cod);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        mainProcess = new javax.swing.JComboBox();
        abas = new javax.swing.JTabbedPane();
        panelAnimation = new javax.swing.JPanel();
        animationSplit = new javax.swing.JSplitPane();
        interactionViewer = new javax.swing.JPanel();
        processesScroll = new javax.swing.JScrollPane();
        interactionPanel = new javax.swing.JPanel();
        variablesScroll = new javax.swing.JScrollPane();
        outputScroll = new javax.swing.JScrollPane();
        outputPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaOut = new javax.swing.JTextPane();
        traceViewer = new javax.swing.JPanel();
        tracesScroll = new javax.swing.JScrollPane();
        tracePanel = new javax.swing.JPanel();
        panelSpec = new javax.swing.JPanel();
        rodapePanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        animationTime = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        escolha = new javax.swing.JLabel();
        waitLTS = new javax.swing.JTextField();
        selectedLanguage = new javax.swing.JComboBox();
        escolha1 = new javax.swing.JLabel();
        waitLTSEvery = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaCodigo = new javax.swing.JTextPane();
        panelLTS = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaLts = new javax.swing.JTextPane();
        panelXML = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        areaXml = new javax.swing.JTextPane();
        panelLOG = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        areaLog = new javax.swing.JTextPane();
        jToolBarPrincipal = new javax.swing.JToolBar();
        Arquivo = new javax.swing.JToolBar();
        abrirTool = new javax.swing.JButton();
        reopenTool = new javax.swing.JButton();
        salvarTool1 = new javax.swing.JButton();
        salvarTool = new javax.swing.JButton();
        novoTool = new javax.swing.JButton();
        fecharTool = new javax.swing.JButton();
        imprimirTool = new javax.swing.JButton();
        Editar = new javax.swing.JToolBar();
        copiarTool = new javax.swing.JButton();
        recortarTool = new javax.swing.JButton();
        colarTool = new javax.swing.JButton();
        pesquisarTool = new javax.swing.JButton();
        atualizarTool = new javax.swing.JButton();
        redoTool = new javax.swing.JButton();
        undoTool = new javax.swing.JButton();
        Editor = new javax.swing.JToolBar();
        fontesEditorTool = new javax.swing.JButton();
        coresEditorTool = new javax.swing.JButton();
        Graficos = new javax.swing.JToolBar();
        graphicsOptions = new javax.swing.JButton();
        Animar = new javax.swing.JToolBar();
        compilarTool = new javax.swing.JButton();
        compilarTool1 = new javax.swing.JButton();
        Looks = new javax.swing.JToolBar();
        looksTool = new javax.swing.JButton();
        Config = new javax.swing.JToolBar();
        configuracoesTool = new javax.swing.JButton();
        alarmesTool = new javax.swing.JButton();
        atalhosTool = new javax.swing.JButton();
        Ajuda = new javax.swing.JToolBar();
        ajudaJlotos = new javax.swing.JButton();
        ajudaManual = new javax.swing.JButton();
        contatoTool = new javax.swing.JButton();
        barra = new javax.swing.JProgressBar();
        barraMenu = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        abrirArquivoMenu = new javax.swing.JMenuItem();
        reopen = new javax.swing.JMenuItem();
        salvarArquivoMenu1 = new javax.swing.JMenuItem();
        salvarArquivoMenu = new javax.swing.JMenuItem();
        novoArquivoMenu = new javax.swing.JMenuItem();
        fecharArquivoMenu = new javax.swing.JMenuItem();
        imprimirArquivoMenu = new javax.swing.JMenuItem();
        sairMenu = new javax.swing.JMenuItem();
        edit = new javax.swing.JMenu();
        copyMenu = new javax.swing.JMenuItem();
        cutMenu = new javax.swing.JMenuItem();
        pasteMenu = new javax.swing.JMenuItem();
        findMenu = new javax.swing.JMenuItem();
        refreshMenu = new javax.swing.JMenuItem();
        redoMenu = new javax.swing.JMenuItem();
        undoMenu = new javax.swing.JMenuItem();
        editor = new javax.swing.JMenu();
        fontesMenu = new javax.swing.JMenuItem();
        editorColorsMenu = new javax.swing.JMenuItem();
        animation = new javax.swing.JMenu();
        animate = new javax.swing.JMenuItem();
        graphics = new javax.swing.JMenu();
        metricasMenu = new javax.swing.JMenuItem();
        laf = new javax.swing.JMenu();
        config = new javax.swing.JMenu();
        opcoesMenu = new javax.swing.JMenuItem();
        sonsMenu = new javax.swing.JMenuItem();
        atalhosMenu = new javax.swing.JMenuItem();
        help = new javax.swing.JMenu();
        sobreJLotosMenu = new javax.swing.JMenuItem();
        manualJLotosMenu = new javax.swing.JMenuItem();
        contactUsMenu = new javax.swing.JMenuItem();

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("MAIN PROCESS:");

        mainProcess.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NONE" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Joker");
        setMinimumSize(new java.awt.Dimension(800, 600));

        panelAnimation.setBorder(javax.swing.BorderFactory.createTitledBorder("ANIMATION"));

        processesScroll.setBorder(javax.swing.BorderFactory.createTitledBorder("Interaction Viewer"));

        interactionPanel.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                interactionPanelComponentAdded(evt);
            }
        });

        javax.swing.GroupLayout interactionPanelLayout = new javax.swing.GroupLayout(interactionPanel);
        interactionPanel.setLayout(interactionPanelLayout);
        interactionPanelLayout.setHorizontalGroup(
            interactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 302, Short.MAX_VALUE)
        );
        interactionPanelLayout.setVerticalGroup(
            interactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
        );

        processesScroll.setViewportView(interactionPanel);

        outputPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Output"));

        areaOut.setEditable(false);
        jScrollPane3.setViewportView(areaOut);

        javax.swing.GroupLayout outputPanelLayout = new javax.swing.GroupLayout(outputPanel);
        outputPanel.setLayout(outputPanelLayout);
        outputPanelLayout.setHorizontalGroup(
            outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outputPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        outputPanelLayout.setVerticalGroup(
            outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
        );

        outputScroll.setViewportView(outputPanel);

        variablesScroll.setViewportView(outputScroll);

        javax.swing.GroupLayout interactionViewerLayout = new javax.swing.GroupLayout(interactionViewer);
        interactionViewer.setLayout(interactionViewerLayout);
        interactionViewerLayout.setHorizontalGroup(
            interactionViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(processesScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
            .addComponent(variablesScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
        );
        interactionViewerLayout.setVerticalGroup(
            interactionViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(interactionViewerLayout.createSequentialGroup()
                .addComponent(processesScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(variablesScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
        );

        animationSplit.setLeftComponent(interactionViewer);

        tracesScroll.setBorder(javax.swing.BorderFactory.createTitledBorder("Trace Viewer"));
        tracesScroll.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tracesScrollPropertyChange(evt);
            }
        });
        tracesScroll.setViewportView(tracePanel);

        javax.swing.GroupLayout traceViewerLayout = new javax.swing.GroupLayout(traceViewer);
        traceViewer.setLayout(traceViewerLayout);
        traceViewerLayout.setHorizontalGroup(
            traceViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tracesScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
        );
        traceViewerLayout.setVerticalGroup(
            traceViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tracesScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
        );

        animationSplit.setRightComponent(traceViewer);

        javax.swing.GroupLayout panelAnimationLayout = new javax.swing.GroupLayout(panelAnimation);
        panelAnimation.setLayout(panelAnimationLayout);
        panelAnimationLayout.setHorizontalGroup(
            panelAnimationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(animationSplit, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
        );
        panelAnimationLayout.setVerticalGroup(
            panelAnimationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(animationSplit, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
        );

        abas.addTab("Animation", panelAnimation);

        panelSpec.setBorder(javax.swing.BorderFactory.createTitledBorder("SPECIFICATION"));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Animated in:");

        animationTime.setEditable(false);
        animationTime.setForeground(new java.awt.Color(0, 153, 0));
        animationTime.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        animationTime.setText("0");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Language:");

        escolha.setText("Wait the compiler for:");

        waitLTS.setForeground(new java.awt.Color(204, 0, 0));
        waitLTS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        waitLTS.setText("3");

        selectedLanguage.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CSP", "Z", "B", "XML", "Other" }));

        escolha1.setText("seconds and check the LTS every ");

        waitLTSEvery.setForeground(new java.awt.Color(204, 0, 0));
        waitLTSEvery.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        waitLTSEvery.setText("850");

        jLabel1.setText("ms");

        javax.swing.GroupLayout rodapePanelLayout = new javax.swing.GroupLayout(rodapePanel);
        rodapePanel.setLayout(rodapePanelLayout);
        rodapePanelLayout.setHorizontalGroup(
            rodapePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rodapePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectedLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(escolha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(waitLTS, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(escolha1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(waitLTSEvery, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(animationTime, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        rodapePanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {waitLTS, waitLTSEvery});

        rodapePanelLayout.setVerticalGroup(
            rodapePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rodapePanelLayout.createSequentialGroup()
                .addGroup(rodapePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(selectedLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(animationTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(escolha)
                    .addComponent(waitLTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(escolha1)
                    .addComponent(waitLTSEvery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        rodapePanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel3, jLabel4});

        areaCodigo.setFont(new java.awt.Font("Monospaced", 0, 16));
        areaCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                areaCodigoKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(areaCodigo);

        javax.swing.GroupLayout panelSpecLayout = new javax.swing.GroupLayout(panelSpec);
        panelSpec.setLayout(panelSpecLayout);
        panelSpecLayout.setHorizontalGroup(
            panelSpecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rodapePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
        );
        panelSpecLayout.setVerticalGroup(
            panelSpecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSpecLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rodapePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        abas.addTab("Specification", panelSpec);

        panelLTS.setBorder(javax.swing.BorderFactory.createTitledBorder("LTS"));

        areaLts.setFont(new java.awt.Font("Monospaced", 0, 16));
        jScrollPane1.setViewportView(areaLts);

        javax.swing.GroupLayout panelLTSLayout = new javax.swing.GroupLayout(panelLTS);
        panelLTS.setLayout(panelLTSLayout);
        panelLTSLayout.setHorizontalGroup(
            panelLTSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLTSLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelLTSLayout.setVerticalGroup(
            panelLTSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLTSLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addContainerGap())
        );

        abas.addTab("LTS", panelLTS);

        panelXML.setBorder(javax.swing.BorderFactory.createTitledBorder("XML"));

        areaXml.setFont(new java.awt.Font("Monospaced", 0, 16));
        jScrollPane8.setViewportView(areaXml);

        javax.swing.GroupLayout panelXMLLayout = new javax.swing.GroupLayout(panelXML);
        panelXML.setLayout(panelXMLLayout);
        panelXMLLayout.setHorizontalGroup(
            panelXMLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
        );
        panelXMLLayout.setVerticalGroup(
            panelXMLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
        );

        abas.addTab("XML", panelXML);

        panelLOG.setBorder(javax.swing.BorderFactory.createTitledBorder("LOG"));

        areaLog.setFont(new java.awt.Font("Monospaced", 0, 16));
        jScrollPane7.setViewportView(areaLog);

        javax.swing.GroupLayout panelLOGLayout = new javax.swing.GroupLayout(panelLOG);
        panelLOG.setLayout(panelLOGLayout);
        panelLOGLayout.setHorizontalGroup(
            panelLOGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
        );
        panelLOGLayout.setVerticalGroup(
            panelLOGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
        );

        abas.addTab("LOG", panelLOG);

        jToolBarPrincipal.setFloatable(false);
        jToolBarPrincipal.setRollover(true);

        Arquivo.setRollover(true);
        Arquivo.setToolTipText("File");
        Arquivo.setName("File"); // NOI18N

        abrirTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/script_edit.png"))); // NOI18N
        abrirTool.setToolTipText("Open (Ctrl+O)");
        abrirTool.setFocusable(false);
        abrirTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        abrirTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        abrirTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirToolActionPerformed(evt);
            }
        });
        Arquivo.add(abrirTool);

        reopenTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/script_go.png"))); // NOI18N
        reopenTool.setToolTipText("Reopen (Ctrl+R)");
        reopenTool.setFocusable(false);
        reopenTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        reopenTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        reopenTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reopenToolActionPerformed(evt);
            }
        });
        Arquivo.add(reopenTool);

        salvarTool1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/script_lightning.png"))); // NOI18N
        salvarTool1.setToolTipText("Save (Ctrl+S)");
        salvarTool1.setFocusable(false);
        salvarTool1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        salvarTool1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        salvarTool1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarTool1ActionPerformed(evt);
            }
        });
        Arquivo.add(salvarTool1);

        salvarTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/script_save.png"))); // NOI18N
        salvarTool.setToolTipText("Save As (Ctrl+Alt+S)");
        salvarTool.setFocusable(false);
        salvarTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        salvarTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        salvarTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarToolActionPerformed(evt);
            }
        });
        Arquivo.add(salvarTool);

        novoTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/script_add.png"))); // NOI18N
        novoTool.setToolTipText("New (Ctrl+N)");
        novoTool.setFocusable(false);
        novoTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        novoTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        novoTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novoToolActionPerformed(evt);
            }
        });
        Arquivo.add(novoTool);

        fecharTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/script_delete.png"))); // NOI18N
        fecharTool.setToolTipText(" Close (Ctrl+Shift+C)");
        fecharTool.setFocusable(false);
        fecharTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fecharTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        fecharTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecharToolActionPerformed(evt);
            }
        });
        Arquivo.add(fecharTool);

        imprimirTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/printer.png"))); // NOI18N
        imprimirTool.setToolTipText("Print (Ctrl+P)");
        imprimirTool.setFocusable(false);
        imprimirTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imprimirTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        imprimirTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirToolActionPerformed(evt);
            }
        });
        Arquivo.add(imprimirTool);

        jToolBarPrincipal.add(Arquivo);

        Editar.setRollover(true);
        Editar.setToolTipText("Edit");
        Editar.setName("Edit"); // NOI18N

        copiarTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/copy.png"))); // NOI18N
        copiarTool.setToolTipText("Copy (Ctr+C)");
        copiarTool.setFocusable(false);
        copiarTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        copiarTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        copiarTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copiarToolActionPerformed(evt);
            }
        });
        Editar.add(copiarTool);

        recortarTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cut.png"))); // NOI18N
        recortarTool.setToolTipText("Cut (Crt+X)");
        recortarTool.setFocusable(false);
        recortarTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        recortarTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        recortarTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recortarToolActionPerformed(evt);
            }
        });
        Editar.add(recortarTool);

        colarTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/briefcase.png"))); // NOI18N
        colarTool.setToolTipText("Paste (Ctr+V)");
        colarTool.setFocusable(false);
        colarTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        colarTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        colarTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colarToolActionPerformed(evt);
            }
        });
        Editar.add(colarTool);

        pesquisarTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/find.png"))); // NOI18N
        pesquisarTool.setToolTipText("Find/Replace (Ctrl+H)");
        pesquisarTool.setFocusable(false);
        pesquisarTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pesquisarTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pesquisarTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisarToolActionPerformed(evt);
            }
        });
        Editar.add(pesquisarTool);

        atualizarTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/arrow_refresh.png"))); // NOI18N
        atualizarTool.setToolTipText("Refresh Text (Ctrl+F5)");
        atualizarTool.setFocusable(false);
        atualizarTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        atualizarTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        atualizarTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarToolActionPerformed(evt);
            }
        });
        Editar.add(atualizarTool);

        redoTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/arrow_redo.png"))); // NOI18N
        redoTool.setToolTipText("Redo (Ctrl+Y)");
        redoTool.setFocusable(false);
        redoTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        redoTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        redoTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoToolActionPerformed(evt);
            }
        });
        Editar.add(redoTool);

        undoTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/arrow_undo.png"))); // NOI18N
        undoTool.setToolTipText("Undo (Ctrl+Z)");
        undoTool.setFocusable(false);
        undoTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        undoTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        undoTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoToolActionPerformed(evt);
            }
        });
        Editar.add(undoTool);

        jToolBarPrincipal.add(Editar);

        Editor.setRollover(true);
        Editor.setToolTipText("Configurations");
        Editor.setName("Configurations"); // NOI18N

        fontesEditorTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/style.png"))); // NOI18N
        fontesEditorTool.setToolTipText("Configure Font Size and Type (F4)");
        fontesEditorTool.setFocusable(false);
        fontesEditorTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fontesEditorTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        fontesEditorTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fontesEditorToolActionPerformed(evt);
            }
        });
        Editor.add(fontesEditorTool);

        coresEditorTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/script_palette.png"))); // NOI18N
        coresEditorTool.setToolTipText("Configure Editor Colors (F5)");
        coresEditorTool.setFocusable(false);
        coresEditorTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        coresEditorTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        coresEditorTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coresEditorToolActionPerformed(evt);
            }
        });
        Editor.add(coresEditorTool);

        jToolBarPrincipal.add(Editor);

        Graficos.setRollover(true);
        Graficos.setToolTipText("Graphics");
        Graficos.setName("Graphics"); // NOI18N

        graphicsOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/chart_organisation.png"))); // NOI18N
        graphicsOptions.setToolTipText("Graphics Options (F11)");
        graphicsOptions.setFocusable(false);
        graphicsOptions.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        graphicsOptions.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        graphicsOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graphicsOptionsActionPerformed(evt);
            }
        });
        Graficos.add(graphicsOptions);

        jToolBarPrincipal.add(Graficos);

        Animar.setRollover(true);
        Animar.setToolTipText("Run");
        Animar.setName("Run"); // NOI18N

        compilarTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/accept.png"))); // NOI18N
        compilarTool.setToolTipText("Animate (F6)");
        compilarTool.setFocusable(false);
        compilarTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        compilarTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        compilarTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compilarToolActionPerformed(evt);
            }
        });
        Animar.add(compilarTool);

        compilarTool1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tick.png"))); // NOI18N
        compilarTool1.setToolTipText("Reset Animation");
        compilarTool1.setFocusable(false);
        compilarTool1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        compilarTool1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        compilarTool1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compilarTool1ActionPerformed(evt);
            }
        });
        Animar.add(compilarTool1);

        jToolBarPrincipal.add(Animar);

        Looks.setRollover(true);
        Looks.setToolTipText("Appearence");
        Looks.setName("Appearence"); // NOI18N

        looksTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/camera.png"))); // NOI18N
        looksTool.setToolTipText("Change Java Look And Feel");
        looksTool.setFocusable(false);
        looksTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        looksTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        looksTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                looksToolActionPerformed(evt);
            }
        });
        Looks.add(looksTool);

        jToolBarPrincipal.add(Looks);

        Config.setRollover(true);
        Config.setToolTipText("Configuration");
        Config.setName("Configuration"); // NOI18N

        configuracoesTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cog.png"))); // NOI18N
        configuracoesTool.setToolTipText("Options (F12)");
        configuracoesTool.setFocusable(false);
        configuracoesTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        configuracoesTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        configuracoesTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configuracoesToolActionPerformed(evt);
            }
        });
        Config.add(configuracoesTool);

        alarmesTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/music.png"))); // NOI18N
        alarmesTool.setToolTipText("Sounds (Alt+S)");
        alarmesTool.setFocusable(false);
        alarmesTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        alarmesTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        alarmesTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alarmesToolActionPerformed(evt);
            }
        });
        Config.add(alarmesTool);

        atalhosTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/controller.png"))); // NOI18N
        atalhosTool.setToolTipText("Shortcuts (Ctrl+Alt+S)");
        atalhosTool.setFocusable(false);
        atalhosTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        atalhosTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        atalhosTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atalhosToolActionPerformed(evt);
            }
        });
        Config.add(atalhosTool);

        jToolBarPrincipal.add(Config);

        Ajuda.setRollover(true);
        Ajuda.setToolTipText("Help");
        Ajuda.setName("Help"); // NOI18N

        ajudaJlotos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/help.png"))); // NOI18N
        ajudaJlotos.setToolTipText("About JOKER (F1)");
        ajudaJlotos.setFocusable(false);
        ajudaJlotos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ajudaJlotos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ajudaJlotos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajudaJlotosActionPerformed(evt);
            }
        });
        Ajuda.add(ajudaJlotos);

        ajudaManual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/help.png"))); // NOI18N
        ajudaManual.setToolTipText("JOKER Manual (F2)");
        ajudaManual.setFocusable(false);
        ajudaManual.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ajudaManual.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ajudaManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajudaManualActionPerformed(evt);
            }
        });
        Ajuda.add(ajudaManual);

        contatoTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/email.png"))); // NOI18N
        contatoTool.setToolTipText("Contact Us");
        contatoTool.setFocusable(false);
        contatoTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        contatoTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        contatoTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contatoToolActionPerformed(evt);
            }
        });
        Ajuda.add(contatoTool);

        jToolBarPrincipal.add(Ajuda);

        barra.setBackground(new java.awt.Color(255, 255, 255));
        barra.setFont(Looks.getFont());
        barra.setToolTipText("Main Progress Bar");
        barra.setValue(100);
        barra.setString("MAIN PROGRESS BAR");
        barra.setStringPainted(true);

        file.setText("File");

        abrirArquivoMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        abrirArquivoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/script_edit.png"))); // NOI18N
        abrirArquivoMenu.setText("Open");
        abrirArquivoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirArquivoMenuActionPerformed(evt);
            }
        });
        file.add(abrirArquivoMenu);

        reopen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        reopen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/script_go.png"))); // NOI18N
        reopen.setText("Reopen");
        reopen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reopenActionPerformed(evt);
            }
        });
        file.add(reopen);

        salvarArquivoMenu1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        salvarArquivoMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/script_lightning.png"))); // NOI18N
        salvarArquivoMenu1.setText("Save");
        salvarArquivoMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarArquivoMenu1ActionPerformed(evt);
            }
        });
        file.add(salvarArquivoMenu1);

        salvarArquivoMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        salvarArquivoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/script_save.png"))); // NOI18N
        salvarArquivoMenu.setText("Save As");
        salvarArquivoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarArquivoMenuActionPerformed(evt);
            }
        });
        file.add(salvarArquivoMenu);

        novoArquivoMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        novoArquivoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/script_add.png"))); // NOI18N
        novoArquivoMenu.setText("New");
        novoArquivoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novoArquivoMenuActionPerformed(evt);
            }
        });
        file.add(novoArquivoMenu);

        fecharArquivoMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        fecharArquivoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/script_delete.png"))); // NOI18N
        fecharArquivoMenu.setText("Close");
        fecharArquivoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecharArquivoMenuActionPerformed(evt);
            }
        });
        file.add(fecharArquivoMenu);

        imprimirArquivoMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        imprimirArquivoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/printer.png"))); // NOI18N
        imprimirArquivoMenu.setText("Print");
        imprimirArquivoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirArquivoMenuActionPerformed(evt);
            }
        });
        file.add(imprimirArquivoMenu);

        sairMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        sairMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exit.png"))); // NOI18N
        sairMenu.setText("Exit");
        sairMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairMenuActionPerformed(evt);
            }
        });
        file.add(sairMenu);

        barraMenu.add(file);

        edit.setText("Edit");

        copyMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        copyMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/copy.png"))); // NOI18N
        copyMenu.setText("Copy");
        copyMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyMenuActionPerformed(evt);
            }
        });
        edit.add(copyMenu);

        cutMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        cutMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cut.png"))); // NOI18N
        cutMenu.setText("Cut");
        cutMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutMenuActionPerformed(evt);
            }
        });
        edit.add(cutMenu);

        pasteMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        pasteMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/briefcase.png"))); // NOI18N
        pasteMenu.setText("Paste");
        pasteMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteMenuActionPerformed(evt);
            }
        });
        edit.add(pasteMenu);

        findMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        findMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/find.png"))); // NOI18N
        findMenu.setText("Find/Replace");
        findMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findMenuActionPerformed(evt);
            }
        });
        edit.add(findMenu);

        refreshMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.CTRL_MASK));
        refreshMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/arrow_refresh.png"))); // NOI18N
        refreshMenu.setText("Refresh Text");
        refreshMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshMenuActionPerformed(evt);
            }
        });
        edit.add(refreshMenu);

        redoMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        redoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/arrow_redo.png"))); // NOI18N
        redoMenu.setText("Redo");
        redoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoMenuActionPerformed(evt);
            }
        });
        edit.add(redoMenu);

        undoMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        undoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/arrow_undo.png"))); // NOI18N
        undoMenu.setText("Undo");
        undoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoMenuActionPerformed(evt);
            }
        });
        edit.add(undoMenu);

        barraMenu.add(edit);

        editor.setText("Editor");

        fontesMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        fontesMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/style.png"))); // NOI18N
        fontesMenu.setText("Fonts");
        fontesMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fontesMenuActionPerformed(evt);
            }
        });
        editor.add(fontesMenu);

        editorColorsMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        editorColorsMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/script_palette.png"))); // NOI18N
        editorColorsMenu.setText("Editor Colors");
        editorColorsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editorColorsMenuActionPerformed(evt);
            }
        });
        editor.add(editorColorsMenu);

        barraMenu.add(editor);

        animation.setText("Animation");

        animate.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        animate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/accept.png"))); // NOI18N
        animate.setText("Animate");
        animate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                animateActionPerformed(evt);
            }
        });
        animation.add(animate);

        barraMenu.add(animation);

        graphics.setText("Graphics");

        metricasMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, 0));
        metricasMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/chart_organisation.png"))); // NOI18N
        metricasMenu.setText("Graphics Options");
        metricasMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                metricasMenuActionPerformed(evt);
            }
        });
        graphics.add(metricasMenu);

        barraMenu.add(graphics);

        laf.setText("Appearence");
        barraMenu.add(laf);

        config.setText("Configuration");

        opcoesMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, 0));
        opcoesMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cog.png"))); // NOI18N
        opcoesMenu.setText("Options");
        opcoesMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcoesMenuActionPerformed(evt);
            }
        });
        config.add(opcoesMenu);

        sonsMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        sonsMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bell.png"))); // NOI18N
        sonsMenu.setText("Sounds");
        sonsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sonsMenuActionPerformed(evt);
            }
        });
        config.add(sonsMenu);

        atalhosMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        atalhosMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/controller.png"))); // NOI18N
        atalhosMenu.setText("Shortcuts");
        atalhosMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atalhosMenuActionPerformed(evt);
            }
        });
        config.add(atalhosMenu);

        barraMenu.add(config);

        help.setText("Help");

        sobreJLotosMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        sobreJLotosMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/help.png"))); // NOI18N
        sobreJLotosMenu.setText("About JOKER");
        sobreJLotosMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sobreJLotosMenuActionPerformed(evt);
            }
        });
        help.add(sobreJLotosMenu);

        manualJLotosMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        manualJLotosMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/help.png"))); // NOI18N
        manualJLotosMenu.setText("JOKER Manual");
        manualJLotosMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manualJLotosMenuActionPerformed(evt);
            }
        });
        help.add(manualJLotosMenu);

        contactUsMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/email.png"))); // NOI18N
        contactUsMenu.setText("Contact us");
        contactUsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactUsMenuActionPerformed(evt);
            }
        });
        help.add(contactUsMenu);

        barraMenu.add(help);

        setJMenuBar(barraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBarPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(barra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
            .addComponent(abas, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBarPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(abas, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barra, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sobreJLotosMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sobreJLotosMenuActionPerformed
        JOptionPane.showMessageDialog(this, "UNIVERSIDADE FEDERAL DO RIO GRANDE DO NORTE \n\n"
                + "Master Thesis Work by \nDiego Henrique Oliveira de Souza\n\n"
                + "Advisor:\n-Marcel Oliveira", "About JOKER", JOptionPane.INFORMATION_MESSAGE);
}//GEN-LAST:event_sobreJLotosMenuActionPerformed

    private void manualJLotosMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manualJLotosMenuActionPerformed
        JOptionPane.showMessageDialog(this, "The JOKER manual is not disponible yet", "JOKER Manual", JOptionPane.ERROR_MESSAGE);
}//GEN-LAST:event_manualJLotosMenuActionPerformed

    private void selecionaProcessos() {
        mainProcess.removeAllItems();
        for (int i = 0; i < JokerWindow.areaCodigo.getText().length(); i++) {
            if (JokerWindow.areaCodigo.getText().charAt(i) == '=') {
                boolean test = true;
                int cont = i - 1;
                String process = "";
                while (test) {
                    if (JokerWindow.areaCodigo.getText().charAt(cont) == '\n') {
                        test = false;
                    } else {
                        process += JokerWindow.areaCodigo.getText().charAt(cont);
                        cont--;
                    }
                }
                String processoFinal = "";
                for (int k = process.length() - 1; k >= 0; k--) {
                    processoFinal += process.charAt(k);
                }
                mainProcess.addItem(processoFinal);
            }
        }
    }

    private void selecionaLinguagem() {
        //difinindo a linguagem
        String extension = SaveFile.getNomeArquivo().substring(SaveFile.getNomeArquivo().length() - 4);

        if (extension.equalsIgnoreCase(".csp")) {
            selectedLanguage.setSelectedIndex(0);
            //selecionaProcessos();
        } else if (extension.equalsIgnoreCase("fdr2")) {
            selectedLanguage.setSelectedIndex(0);
           // selecionaProcessos();
        } else if (extension.equalsIgnoreCase(".mch")) {
            selectedLanguage.setSelectedIndex(2);
        } else if (extension.equalsIgnoreCase(".tex")) {
            selectedLanguage.setSelectedIndex(1);
        }
        else if (extension.equalsIgnoreCase("otos")) {
            JOptionPane.showMessageDialog(null, "LOTOS language is not supported anymore.", "LOTOS File", JOptionPane.WARNING_MESSAGE);
        }
        else if (extension.equalsIgnoreCase(".xml")) {
            selectedLanguage.setSelectedIndex(3);
        } else {
            selectedLanguage.setSelectedIndex(4);
        }
    }

    private void abrirArquivoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirArquivoMenuActionPerformed
        this.fecharToolActionPerformed(evt);

        //configura o cursor como de espera
        abas.setSelectedIndex(1);

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        //coloca o conteudo de codigo no textArea areaCodigo
        areaCodigo.setText(SaveFile.lerArquivo(".")); //cod.replace("\t", "    ");
        areaCodigo.setCaretPosition(0);

        //coloca o nome do arquivo a aba codigo
        abas.setTitleAt(1, SaveFile.getNomeArquivo());

        selecionaLinguagem();

        //habilita o botao pra compilar
        animate.setEnabled(true);
        compilarTool.setEnabled(true);

        //coloca o cursor padrao do sistema
        this.setCursor(Cursor.getDefaultCursor());

        //colore o codigo ao abrir
        this.colorirAoAbrir();

        //coloca o nome do arquivo atual na barra de titulos
        this.setTitle("JOKER 1.0 - " + SaveFile.getNomeArquivo());

        //coloca o cursor no inicio da area de codigo
        areaCodigo.setCaretPosition(0);
}//GEN-LAST:event_abrirArquivoMenuActionPerformed

    private void salvarArquivoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarArquivoMenuActionPerformed
        SaveFile.salvarArquivo(areaCodigo.getText());
        abas.setTitleAt(1, arq.getName());
        selecionaLinguagem();
        if (selectedLanguage.getSelectedIndex() == 0) {
            selecionaProcessos();
        }
}//GEN-LAST:event_salvarArquivoMenuActionPerformed

    private void opcoesMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcoesMenuActionPerformed
        new OptionsWindow().setVisible(true);
}//GEN-LAST:event_opcoesMenuActionPerformed

    private void salvarToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarToolActionPerformed
        this.salvarArquivoMenuActionPerformed(evt);
}//GEN-LAST:event_salvarToolActionPerformed

    private void compilarToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compilarToolActionPerformed
        this.animateActionPerformed(evt);
}//GEN-LAST:event_compilarToolActionPerformed

    private void abrirToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirToolActionPerformed
        this.abrirArquivoMenuActionPerformed(evt);
}//GEN-LAST:event_abrirToolActionPerformed

    private void configuracoesToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configuracoesToolActionPerformed
        new OptionsWindow().setVisible(true);
    }//GEN-LAST:event_configuracoesToolActionPerformed
//GEN-FIRST:event_areaCodigo1KeyTyped
//GEN-LAST:event_areaCodigo1KeyTyped
    private void coresEditorToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coresEditorToolActionPerformed
        new EditorColorsWindow().setVisible(true);
    }//GEN-LAST:event_coresEditorToolActionPerformed

    private void fontesEditorToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fontesEditorToolActionPerformed
        new FontWindow().setVisible(true);
    }//GEN-LAST:event_fontesEditorToolActionPerformed

    private void fontesMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fontesMenuActionPerformed
        this.fontesEditorToolActionPerformed(evt);
}//GEN-LAST:event_fontesMenuActionPerformed

    private void novoToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novoToolActionPerformed
        this.fecharArquivoMenuActionPerformed(evt);
}//GEN-LAST:event_novoToolActionPerformed

    private void fecharToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecharToolActionPerformed
        this.setTitle("JOKER 1.0");

        abas.setTitleAt(1, "Specification");
        abas.setTitleAt(2, "LTS");
        abas.setTitleAt(3, "XML");

        areaCodigo.setText("");
        areaLts.setText("");
        areaXml.setText("");
        areaLog.setText("");

        interactionPanel.removeAll();
        tracePanel.removeAll();
}//GEN-LAST:event_fecharToolActionPerformed

    private void fecharArquivoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecharArquivoMenuActionPerformed
        this.fecharToolActionPerformed(evt);
}//GEN-LAST:event_fecharArquivoMenuActionPerformed

    private void novoArquivoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novoArquivoMenuActionPerformed
        this.novoToolActionPerformed(evt);
}//GEN-LAST:event_novoArquivoMenuActionPerformed

    private void editorColorsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editorColorsMenuActionPerformed
        this.coresEditorToolActionPerformed(evt);
}//GEN-LAST:event_editorColorsMenuActionPerformed

    private void alarmesToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alarmesToolActionPerformed
        this.sonsMenuActionPerformed(evt);
    }//GEN-LAST:event_alarmesToolActionPerformed

    private void ajudaJlotosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajudaJlotosActionPerformed
        this.sobreJLotosMenuActionPerformed(evt);
    }//GEN-LAST:event_ajudaJlotosActionPerformed

    private void ajudaManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajudaManualActionPerformed
        this.manualJLotosMenuActionPerformed(evt);
    }//GEN-LAST:event_ajudaManualActionPerformed

    private void copiarToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copiarToolActionPerformed
        copyMenuActionPerformed(evt);
    }//GEN-LAST:event_copiarToolActionPerformed

    private void recortarToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recortarToolActionPerformed
        cutMenuActionPerformed(evt);
    }//GEN-LAST:event_recortarToolActionPerformed

    private void pesquisarToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisarToolActionPerformed
        findMenuActionPerformed(evt);
}//GEN-LAST:event_pesquisarToolActionPerformed

    private void atualizarToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarToolActionPerformed
        refreshMenuActionPerformed(evt);
}//GEN-LAST:event_atualizarToolActionPerformed

    private void copyMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyMenuActionPerformed
        editorTools.setCopiado(areaCodigo.getSelectedText());
    }//GEN-LAST:event_copyMenuActionPerformed

    private void cutMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutMenuActionPerformed
        editorTools.setCopiado(areaCodigo.getSelectedText());
        areaCodigo.replaceSelection("");
    }//GEN-LAST:event_cutMenuActionPerformed

    private void pasteMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteMenuActionPerformed
        areaCodigo.replaceSelection(editorTools.getCopiado());
    }//GEN-LAST:event_pasteMenuActionPerformed

    private void findMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findMenuActionPerformed
        String palavra = JOptionPane.showInputDialog(null, "What word do you want to replace?", "Word to be replaced", JOptionPane.QUESTION_MESSAGE);
        String palavraNova = JOptionPane.showInputDialog(null, "What is the new word for '" + palavra + "'?", "New word", JOptionPane.QUESTION_MESSAGE);
        areaCodigo.setText("");
        for (Token t : tokensLexica) {
            if (t.getConteudo().equalsIgnoreCase(palavra)) {
                Token tNovo = tokensLexica.get(tokensLexica.indexOf(t));
                tNovo.setConteudo(palavraNova);
                colorirCodigo(tNovo);
            } else {
                colorirCodigo(t);
            }
        }
    }//GEN-LAST:event_findMenuActionPerformed

    private void refreshMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshMenuActionPerformed
        TokenClassifier al = new TokenClassifier(areaCodigo.getText());
        areaCodigo.setText("");
        for (Token token : al.getTokens()) {
            colorirCodigo(token);
        }
}//GEN-LAST:event_refreshMenuActionPerformed

    private void imprimirArquivoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirArquivoMenuActionPerformed
        //new PrintCode(areaCodigo.getText());
//        Imprimir im = new Imprimir();
//        im.imprime(areaCodigo.getText());
    }//GEN-LAST:event_imprimirArquivoMenuActionPerformed

    private void colarToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colarToolActionPerformed
        pasteMenuActionPerformed(evt);
}//GEN-LAST:event_colarToolActionPerformed

    private void redoToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoToolActionPerformed
        redoMenuActionPerformed(evt);
}//GEN-LAST:event_redoToolActionPerformed

    private void undoToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoToolActionPerformed
        undoMenuActionPerformed(evt);
}//GEN-LAST:event_undoToolActionPerformed

    private void redoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoMenuActionPerformed
        undoPosition++;
        if(undoPosition < undo.size())
            areaCodigo.setText(undo.get(undoPosition));
}//GEN-LAST:event_redoMenuActionPerformed

    private void undoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoMenuActionPerformed
        undoPosition--;
        if(undoPosition >= 0)
            areaCodigo.setText(undo.get(undoPosition));
        //this.refreshMenuActionPerformed(evt);
}//GEN-LAST:event_undoMenuActionPerformed

    private void atalhosToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atalhosToolActionPerformed
        this.atalhosMenuActionPerformed(evt);
    }//GEN-LAST:event_atalhosToolActionPerformed

    private void looksToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_looksToolActionPerformed
        new AppearanceWindow().setVisible(true);
        mudarLAF(lafOptions.getLaf());
    }//GEN-LAST:event_looksToolActionPerformed

    private void contactUsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactUsMenuActionPerformed
        String message = "";
        message += "Developer: Diego Henrique \n";
        message += "e-mail: diegodimap@gmail.com \n";

        message += "Advisor: Marcel Oliveira \n";
        message += "e-mail: marcel139@gmail.com \n";

        JOptionPane.showMessageDialog(null, message, "Contact Us", JOptionPane.INFORMATION_MESSAGE);
}//GEN-LAST:event_contactUsMenuActionPerformed

    private void contatoToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contatoToolActionPerformed
        this.contactUsMenuActionPerformed(evt);
    }//GEN-LAST:event_contatoToolActionPerformed

    private void sairMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairMenuActionPerformed
        dispose();
    }//GEN-LAST:event_sairMenuActionPerformed

    private void animateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_animateActionPerformed
        barra.setForeground(new JProgressBar().getForeground());
        barra.setValue(0);

        barra.setString("CLEANING JOKER'S GUI...");
        interactionPanel.removeAll();
        tracePanel.removeAll();

        areaLts.setText("");
        areaXml.setText("");
        areaLog.setText("");
        areaOut.setText("");

        String sistemaOperacional = System.getProperty("os.name");
        JokerWindow.logIt("SO = " + sistemaOperacional + "\n");

        long inicio = System.currentTimeMillis();
        //chama a analise lexica------------------------------------------------
        barra.setString("LEXICAL ANALYSIS...");
        TokenClassifier al = new TokenClassifier(areaCodigo.getText());
        this.tokensLexica = al.getTokens();

        areaCodigo.setText("");

        //classificando os caracteres de tabulacao e imprimindo na area lexica
        for (Token t : tokensLexica) {
            String conteudo = t.getConteudo();
            if (conteudo.equalsIgnoreCase("\n")) {
                conteudo = "(ENTER)";
            } else if (conteudo.equalsIgnoreCase(" ")) {
                conteudo = "(SPACE)";
            } else if (conteudo.equalsIgnoreCase("\t")) {
                conteudo = "(TAB)";
            } else if (conteudo.equalsIgnoreCase("")) {
                conteudo = "(NULL)";
            }

            //CODIGO COLORIDO
            colorirCodigo(t);

            barra.setString("LEXICAL ANALYSIS DONE!");
            barra.setValue(25);
        }

          //CSP ******************************************************************
        if (selectedLanguage.getSelectedIndex() == 0) {
            CSPTransformation cspt = new CSPTransformation();
        } //Z ********************************************************************
        else if (selectedLanguage.getSelectedIndex() == 1) {
            ZTransformation zt = new ZTransformation();
        } //B***************************************************
        else if (selectedLanguage.getSelectedIndex() == 2) {
            BTransformation ot = new BTransformation();
        } //LOTOS
        else if (selectedLanguage.getSelectedIndex() == 5) {
             //LOTOSTransformation lt = new LOTOSTransformation();
        } //XML*******************************************************************
        else if (selectedLanguage.getSelectedIndex() == 3) {
            barra.setString("LTS IN XML FORMAT...");
            JokerWindow.logIt("XML choosen!");

            JokerWindow.areaXml.setText(JokerWindow.areaCodigo.getText());

            barra.setValue(100);
            barra.setString("ANIMATION FOR XML AVAILABLE!");
            JokerWindow.abas.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(null, "Unsupported language!!!", "Unsupported Language", JOptionPane.ERROR_MESSAGE);
            JokerWindow.logIt("Other Language!");
        }


        interactionPanel.setSize(200, interactionPanel.getHeight());
        ap = new AnimationPanel();
        ap.setSize(interactionPanel.getSize());
        ap.removeAll();
        this.areaOut.setText("");
        ap.animateXML();
        ap.setVisible(true);
        interactionPanel.removeAll();
        interactionPanel.add(ap);

        //exibe a animação
        //abas.setSelectedIndex(0);

        //tempo final
        long fim = System.currentTimeMillis();
        tempoTotal = fim - inicio;
        animationTime.setText((tempoTotal) + " ms");
    }//GEN-LAST:event_animateActionPerformed

    private void sonsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sonsMenuActionPerformed
        new SoundsWindow().setVisible(true);
    }//GEN-LAST:event_sonsMenuActionPerformed
    private void atalhosMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atalhosMenuActionPerformed
        new ShortcutsWindow().setVisible(true);
    }//GEN-LAST:event_atalhosMenuActionPerformed
    private void imprimirToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirToolActionPerformed
        this.imprimirArquivoMenuActionPerformed(evt);
    }//GEN-LAST:event_imprimirToolActionPerformed
    private void metricasMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_metricasMenuActionPerformed
        new GraphicsWindow().setVisible(true);
    }//GEN-LAST:event_metricasMenuActionPerformed
    private void graphicsOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graphicsOptionsActionPerformed
        this.metricasMenuActionPerformed(evt);
    }//GEN-LAST:event_graphicsOptionsActionPerformed

    private void interactionPanelComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_interactionPanelComponentAdded
        this.update(this.getGraphics());
    }//GEN-LAST:event_interactionPanelComponentAdded

    private void tracesScrollPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tracesScrollPropertyChange
    }//GEN-LAST:event_tracesScrollPropertyChange

    private void salvarTool1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarTool1ActionPerformed
        salvarArquivoMenu1ActionPerformed(evt);
    }//GEN-LAST:event_salvarTool1ActionPerformed

    private void salvarArquivoMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarArquivoMenu1ActionPerformed
        SaveFile.saveSameFile(areaCodigo.getText());
        if (selectedLanguage.getSelectedIndex() == 0) {
            selecionaProcessos();
        }
    }//GEN-LAST:event_salvarArquivoMenu1ActionPerformed

    private void reopenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reopenActionPerformed
        areaCodigo.setText(SaveFile.relerArquivo(arq));
        if (selectedLanguage.getSelectedIndex() == 0) {
            selecionaProcessos();
        }
        this.colorirAoAbrir();
        areaCodigo.setCaretPosition(0);
    }//GEN-LAST:event_reopenActionPerformed

    private void reopenToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reopenToolActionPerformed
        reopenActionPerformed(evt);
    }//GEN-LAST:event_reopenToolActionPerformed

    private void compilarTool1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compilarTool1ActionPerformed
        ap = new AnimationPanel();
        ap.removeAll();
        this.areaOut.setText("");
        ap.animateXML();
        ap.setVisible(true);
        interactionPanel.removeAll();
        interactionPanel.add(ap);
        tracePanel.removeAll();
    }//GEN-LAST:event_compilarTool1ActionPerformed

    private void areaCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_areaCodigoKeyTyped
        if(undo.size() < 100){
            undo.add(areaCodigo.getText());
            undoPosition++;
        }else{
            undo.remove(0);
            undo.add(areaCodigo.getText());
            undoPosition++;
        }
}//GEN-LAST:event_areaCodigoKeyTyped

    public static void setEscolha(String s) {
        escolha.setText(s);
    }

    public static String getEscolha() {
        return escolha.getText();
    }

    public static JScrollPane getProcessesScroll() {
        return processesScroll;
    }
//    public static void main(String args[]) {
//
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new JLotos().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar Ajuda;
    private javax.swing.JToolBar Animar;
    private javax.swing.JToolBar Arquivo;
    private javax.swing.JToolBar Config;
    private javax.swing.JToolBar Editar;
    private javax.swing.JToolBar Editor;
    private javax.swing.JToolBar Graficos;
    private javax.swing.JToolBar Looks;
    public static javax.swing.JTabbedPane abas;
    private javax.swing.JMenuItem abrirArquivoMenu;
    private javax.swing.JButton abrirTool;
    private javax.swing.JButton ajudaJlotos;
    private javax.swing.JButton ajudaManual;
    private javax.swing.JButton alarmesTool;
    private javax.swing.JMenuItem animate;
    private javax.swing.JMenu animation;
    private javax.swing.JSplitPane animationSplit;
    private javax.swing.JTextField animationTime;
    public static javax.swing.JTextPane areaCodigo;
    public static javax.swing.JTextPane areaLog;
    public static javax.swing.JTextPane areaLts;
    public static javax.swing.JTextPane areaOut;
    public static javax.swing.JTextPane areaXml;
    private javax.swing.JMenuItem atalhosMenu;
    private javax.swing.JButton atalhosTool;
    private javax.swing.JButton atualizarTool;
    public static javax.swing.JProgressBar barra;
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JButton colarTool;
    private javax.swing.JButton compilarTool;
    private javax.swing.JButton compilarTool1;
    private javax.swing.JMenu config;
    private javax.swing.JButton configuracoesTool;
    private javax.swing.JMenuItem contactUsMenu;
    private javax.swing.JButton contatoTool;
    private javax.swing.JButton copiarTool;
    private javax.swing.JMenuItem copyMenu;
    private javax.swing.JButton coresEditorTool;
    private javax.swing.JMenuItem cutMenu;
    private javax.swing.JMenu edit;
    private javax.swing.JMenu editor;
    private javax.swing.JMenuItem editorColorsMenu;
    public static javax.swing.JLabel escolha;
    public static javax.swing.JLabel escolha1;
    private javax.swing.JMenuItem fecharArquivoMenu;
    private javax.swing.JButton fecharTool;
    private javax.swing.JMenu file;
    private javax.swing.JMenuItem findMenu;
    private javax.swing.JButton fontesEditorTool;
    private javax.swing.JMenuItem fontesMenu;
    private javax.swing.JMenu graphics;
    private javax.swing.JButton graphicsOptions;
    private javax.swing.JMenu help;
    private javax.swing.JMenuItem imprimirArquivoMenu;
    private javax.swing.JButton imprimirTool;
    public static javax.swing.JPanel interactionPanel;
    private javax.swing.JPanel interactionViewer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JToolBar jToolBarPrincipal;
    private javax.swing.JMenu laf;
    private javax.swing.JButton looksTool;
    public static javax.swing.JComboBox mainProcess;
    private javax.swing.JMenuItem manualJLotosMenu;
    private javax.swing.JMenuItem metricasMenu;
    private javax.swing.JMenuItem novoArquivoMenu;
    private javax.swing.JButton novoTool;
    private javax.swing.JMenuItem opcoesMenu;
    public static javax.swing.JPanel outputPanel;
    private javax.swing.JScrollPane outputScroll;
    private javax.swing.JPanel panelAnimation;
    private javax.swing.JPanel panelLOG;
    private javax.swing.JPanel panelLTS;
    private javax.swing.JPanel panelSpec;
    private javax.swing.JPanel panelXML;
    private javax.swing.JMenuItem pasteMenu;
    private javax.swing.JButton pesquisarTool;
    public static javax.swing.JScrollPane processesScroll;
    private javax.swing.JButton recortarTool;
    private javax.swing.JMenuItem redoMenu;
    private javax.swing.JButton redoTool;
    private javax.swing.JMenuItem refreshMenu;
    private javax.swing.JMenuItem reopen;
    private javax.swing.JButton reopenTool;
    public static javax.swing.JPanel rodapePanel;
    private javax.swing.JMenuItem sairMenu;
    private javax.swing.JMenuItem salvarArquivoMenu;
    private javax.swing.JMenuItem salvarArquivoMenu1;
    private javax.swing.JButton salvarTool;
    private javax.swing.JButton salvarTool1;
    private static javax.swing.JComboBox selectedLanguage;
    private javax.swing.JMenuItem sobreJLotosMenu;
    private javax.swing.JMenuItem sonsMenu;
    public static javax.swing.JPanel tracePanel;
    private javax.swing.JPanel traceViewer;
    public static javax.swing.JScrollPane tracesScroll;
    private javax.swing.JMenuItem undoMenu;
    private javax.swing.JButton undoTool;
    private javax.swing.JScrollPane variablesScroll;
    public static javax.swing.JTextField waitLTS;
    public static javax.swing.JTextField waitLTSEvery;
    // End of variables declaration//GEN-END:variables
}
