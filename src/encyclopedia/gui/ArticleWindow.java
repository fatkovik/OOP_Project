package src.encyclopedia.gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.encyclopedia.Article;

public class ArticleWindow extends JFrame {

    public static final Font titleFont = new Font("Verdana", Font.BOLD, 36);
    public static Border whiteLine = new LineBorder(Color.white);

    public ArticleWindow(Article article){

        super();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(690, 420);
        setResizable(false);
        setLocationRelativeTo(this);

        this.setTitle("Article " + article.getTitle() + " by " + article.getAuthor() + "  (" + article.getPublishDate() + ")");
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());

        JLabel title = new JLabel(article.getTitle() + "  (" + article.getPublishDate() + ")");
        title.setFont(titleFont);
        title.setForeground(Color.white);


        JTextArea contentField = new JTextArea(article.getContent(), 10, 40);
        contentField.setForeground(Color.white);
        contentField.setLineWrap(true);
        contentField.setWrapStyleWord(true);
        contentField.setOpaque(false);

        JScrollPane scrollableTextArea = new JScrollPane(contentField);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollableTextArea.getViewport().setBackground(EncyclopediaGUI.background);


        panel.add(title, BorderLayout.NORTH);

        panel.add(scrollableTextArea, BorderLayout.CENTER);
        panel.setBackground(EncyclopediaGUI.background);


        JButton saveChanges = new JButton();
        JLabel saveText = new JLabel("Save Changes");
        saveText.setFont(new Font("Verdana", Font.BOLD, 12));
        saveText.setForeground(Color.white);
        saveChanges.add(saveText);
        saveChanges.setBackground(EncyclopediaGUI.background);

        ActionListener saveListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                article.setContent(contentField.getText());
            }
        };

        JButton clearButton = new JButton();
        JLabel clearText = new JLabel("Clear");
        clearText.setFont(new Font("Verdana", Font.BOLD, 12));
        clearText.setForeground(Color.white);
        clearButton.add(clearText);
        clearButton.setBackground(EncyclopediaGUI.background);
        saveChanges.addActionListener(saveListener);

        ActionListener clearListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentField.setText("");
            }
        };

        clearButton.addActionListener(clearListener);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(saveChanges);
        buttonPanel.add(clearButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
        this.setVisible(true);

    }
}
