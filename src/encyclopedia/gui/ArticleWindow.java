package src.encyclopedia.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.*;

import src.encyclopedia.Article;

public class ArticleWindow extends JFrame {
    public ArticleWindow(Article article){
        super();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(690, 420);
        this.setVisible(true);
        this.setTitle("Article " + article.getTitle());

        

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel author = new JLabel(article.getAuthor());
        panel.add( author, BorderLayout.NORTH);
        
        JLabel content = new JLabel(article.getContent());
        panel.add(content, BorderLayout.WEST);

        add(panel);
    }
}
