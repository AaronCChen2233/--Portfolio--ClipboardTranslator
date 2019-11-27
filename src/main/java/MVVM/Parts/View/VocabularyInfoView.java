package MVVM.Parts.View;

import Bootstrap.Tools.GetConfigProperty;
import Bootstrap.Tools.ImageTools;
import Bootstrap.Tools.OpenBrowse;
import MVVM.Parts.ViewModel.VocabularyInfoViewModel;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class VocabularyInfoView extends JFrame {
    private VocabularyInfoViewModel vocabularyInfoViewModel;
    private JTextArea definitionInEnglishLabel;
    private JTextArea definitionInChineseLabel;
    private JTextArea exampleLabel;
    private JPanel mainPanel;
    private JPanel infoPanel;
    private JPanel imgPanel;
    private JPanel buttonsPanel;
    private JScrollPane definitionInEnglishScrollPane;
    private JScrollPane definitionInChineseScrollPane;
    private JScrollPane exampleScrollPane;
    private JScrollPane imgScrollPane;
    private JButton saveButton;
    private JButton speechButton;
    private JButton showOnBrowserButton;

    public VocabularyInfoView() {
        super();
        JFXPanel fxPanel = new JFXPanel();
        setSize(450, 600);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        /*Set location at corner*/
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        setLocation(scrSize.width - getWidth(), scrSize.height - toolHeight.bottom - getHeight());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.setMinimumSize(new Dimension(450, 300));
        infoPanel.setMaximumSize(new Dimension(450, 300));

        imgPanel = new JPanel(new GridLayout(10, 3));
        imgScrollPane = new JScrollPane(imgPanel);
        setJScrollPaneStyle(imgScrollPane);
        imgScrollPane.setMinimumSize(new Dimension(450, 200));
        imgScrollPane.setMaximumSize(new Dimension(450, 200));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));

        definitionInEnglishLabel = new JTextArea();
        setTextAreaStyle(definitionInEnglishLabel);
        definitionInEnglishScrollPane = new JScrollPane(definitionInEnglishLabel);
        setJScrollPaneStyle(definitionInEnglishScrollPane);

        definitionInChineseLabel = new JTextArea();
        setTextAreaStyle(definitionInChineseLabel);
        definitionInChineseScrollPane = new JScrollPane(definitionInChineseLabel);
        setJScrollPaneStyle(definitionInChineseScrollPane);

        exampleLabel = new JTextArea();
        setTextAreaStyle(exampleLabel);
        exampleScrollPane = new JScrollPane(exampleLabel);
        setJScrollPaneStyle(exampleScrollPane);

        saveButton = new JButton("Save");
        speechButton = new JButton("Speech");
        showOnBrowserButton = new JButton("Show on Browser");
        buttonsPanel.add(saveButton);
        buttonsPanel.add(speechButton);
        buttonsPanel.add(showOnBrowserButton);

        mainPanel.add(infoPanel);
        infoPanel.add(definitionInEnglishScrollPane);
        infoPanel.add(definitionInChineseScrollPane);
        infoPanel.add(exampleScrollPane);
        mainPanel.add(imgScrollPane);
        mainPanel.add(buttonsPanel);

        add(mainPanel);

        addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                setState(Frame.ICONIFIED);
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {

            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( vocabularyInfoViewModel.save()){
                    /*If save success disable saveButton*/
                    saveButton.setEnabled(false);
                }
            }
        });

        speechButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Speech();
            }
        });

        showOnBrowserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = GetConfigProperty.vURL + vocabularyInfoViewModel.getVocabulary();
                try {
                    OpenBrowse.open(url);
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    private static void setJScrollPaneStyle(JScrollPane scrollPane) {
        JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL) {
            @Override
            public boolean isVisible() {
                return true;
            }
        };
        scrollBar.setUnitIncrement(3);
        scrollPane.setVerticalScrollBar(scrollBar);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 2));
    }

    private static void setTextAreaStyle(JTextArea label) {
        Font infoFont = new Font("Microsoft JhengHei", Font.LAYOUT_NO_LIMIT_CONTEXT, 16);
        label.setFont(infoFont);
        label.setLineWrap(true);
        label.setWrapStyleWord(true);
        label.setEditable(false);
    }

    public void windowPopUp(VocabularyInfoViewModel vocabularyInfoViewModel) {
        this.vocabularyInfoViewModel = vocabularyInfoViewModel;
        setVisible(true);
        allScrollPanelScrollToTop();

        if (vocabularyInfoViewModel.isNotFound()) {
            setTitle(vocabularyInfoViewModel.getVocabulary());
            imgPanel.removeAll();
            imgPanel.updateUI();
            definitionInEnglishLabel.setText("");
            definitionInChineseLabel.setText("");
            exampleLabel.setText("");
            saveButton.setEnabled(false);
            showOnBrowserButton.setEnabled(false);
            speechButton.setEnabled(false);
            setState(Frame.NORMAL);
        } else {
            if(vocabularyInfoViewModel.isVocabularySaved()){
                saveButton.setEnabled(false);
            }else {
                saveButton.setEnabled(true);
            }
            showOnBrowserButton.setEnabled(true);
            speechButton.setEnabled(true);
            setState(Frame.NORMAL);

            /*Set information*/
            setTitle(vocabularyInfoViewModel.getVocabulary());
            definitionInEnglishLabel.setText(String.join("\n", vocabularyInfoViewModel.getDefinitionInEnglish()));
            definitionInChineseLabel.setText(String.join("\n", vocabularyInfoViewModel.getDefinitionInChinese()));
            exampleLabel.setText(String.join("\n", vocabularyInfoViewModel.getExample()));

            /*Set images*/
            imgPanel.removeAll();
            for (String imgs : vocabularyInfoViewModel.getImgSrcList()) {
                BufferedImage suiteImage = null;
                try {
                    suiteImage = ImageIO.read(new URL(imgs));
                    suiteImage = ImageTools.resize(suiteImage, 140, 140);

                    JLabel pic = new JLabel();
                    pic.setIcon(new ImageIcon(suiteImage));
                    imgPanel.add(pic);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            imgPanel.updateUI();
            mainPanel.updateUI();
        }
    }

    private void allScrollPanelScrollToTop() {
        definitionInEnglishScrollPane.getVerticalScrollBar().setValue(0);
        definitionInChineseScrollPane.getVerticalScrollBar().setValue(0);
        exampleScrollPane.getVerticalScrollBar().setValue(0);
        imgScrollPane.getVerticalScrollBar().setValue(0);
    }

    private void Speech(){
        String bip = vocabularyInfoViewModel.getSpeechMP3URL();
        Media hit = new Media(bip);
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
    }
}
