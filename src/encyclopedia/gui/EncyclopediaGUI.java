package src.encyclopedia.gui;

import src.encyclopedia.Article;
import src.encyclopedia.database.controllers.Repository;
import src.encyclopedia.database.controllers.TXT;
import src.encyclopedia.database.controllers.XML;
import src.encyclopedia.database.controllers.Repository;

import javax.swing.*;
import javax.swing.border.Border;

import org.w3c.dom.Text;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class EncyclopediaGUI extends JFrame implements ActionListener {

    public static final int WIDTH = 700;
    public static final int HEIGHT = 700;

    private Repository repo;
    private int size;

    JPanel articlesPanel;

    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu editMenu;
    JMenu helpMenu;
    JMenuItem loadItem;
    JMenuItem saveItem;
    JMenuItem exitItem;
    JMenuItem helpItem;
    JMenuItem createItem;

    public EncyclopediaGUI(String path) {

        super("Java Encyclopedia");
        setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout());

        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        helpMenu = new JMenu("Help");

        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");
        createItem = new JMenuItem("Create New Article");
        helpItem = new JMenuItem("Get Instructions");

        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);
        helpItem.addActionListener(this);
        createItem.addActionListener(this);

        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        helpMenu.add(helpItem);
        editMenu.add(createItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        this.setJMenuBar(menuBar);
        // ABOVE // SETTING UP MENU BAR

        if (xmlOrTxt(path))
            this.repo = new Repository<XML>(new XML(path));
        else if (!xmlOrTxt(path))
            this.repo = new Repository<TXT>(new TXT(path));

        this.repo.appendToRepository(repo.controller.readArticle());

        // border

        size = repo.getArticles().size();

        // ABOVE PART
        JPanel abovePart = new JPanel(new GridLayout());

        ImageIcon logo = new ImageIcon("src/encyclopedia/gui/preview.png");
        Image image = logo.getImage(); // transform it
        Image newimg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        logo = new ImageIcon(newimg);

        JLabel label = new JLabel();
        label.setText("Repository" + " \n " + size + " Articles");
        label.setFont(new Font("Segoe", Font.BOLD, 10));
        label.setIcon(logo);
        abovePart.add(label);

        // Articles Panel
        articlesPanel = new JPanel(new GridLayout(20, 1));
        Border grayline = BorderFactory.createLineBorder(Color.gray, 2);

        for (int i = 0; i < repo.getArticles().size(); i++) {
            
            int newI = i;

            JLabel title = new JLabel(repo.getArticle(i).getAuthor() + " - " + repo.getArticle(i).getTitle() + "                         " +
             repo.getArticle(i).getPublishDate());
            
            // title.setFont(new Font("Arial", Font.BOLD, 20));
            // title.setHorizontalAlignment(SwingConstants.LEFT);
            // title.setVerticalAlignment(SwingConstants.CENTER);
            // title.setBorder(grayline);
            // title.setSize(700, 20);
            

            ArticleUI articleButton = new ArticleUI(i);
            ActionListener aListener = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Open Article num " + articleButton.getIndex());
                    ArticleWindow window = new ArticleWindow(repo.getArticle(newI));
                }

            };
            
            articleButton.addActionListener(aListener);
            articleButton.add(title);
            articlesPanel.add(articleButton);
        
        }

        // this.add(greeting, BorderLayout.NORTH);
        this.add(abovePart);
        this.add(articlesPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // if (e.getSource() == loadItem) {
        //     System.out.println("*beep boop* you loaded a file");
        //     repo.controller.
        // }
        if (e.getSource() == saveItem) {
            System.out.println("*beep boop* you saved a file");
            repo.controller.writeArticle(repo.getArticles());
        }

        if (e.getSource() == helpItem) {
            System.out.println("Here are the instructions");    
            String text = "";
            TextAreaDemo demo = new TextAreaDemo();
            demo.setVisible(true);  

            text += "," + demo.getStory();
            text += "," + demo.getStory();
            text += "," + demo.getStory();
            text += "," + demo.getStory();
            text += "," + demo.getStory();
        }

        if (e.getSource() == createItem) {
            
            System.out.println("Creating New Article");
            repo.appendToRepository(new Article(createArticleInput()));
            
            JLabel title = new JLabel(repo.getArticle(size).getAuthor() + " - " + repo.getArticle(size).getTitle() + repo.getArticle(size).getPublishDate());
            ArticleUI articleButton = new ArticleUI(size);
            ActionListener aListener = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Open Article num" + articleButton.getIndex());
                    ArticleWindow window = new ArticleWindow(repo.getArticle(size - 1));
                }

            };
            articleButton.add(title);
            articleButton.addActionListener(aListener);
            articlesPanel.add(articleButton);
            size++;
            
            repaint();
            revalidate();
        }

        if (e.getSource() == exitItem) {
            System.exit(0);
        }

    }

    private String createArticleInput () {
        Scanner sc = new Scanner(System.in);
        String input = "";

        System.out.print("Enter New Title: ");
        input += sc.nextLine();
        System.out.print("Enter New Author: ");
        input += "," + sc.nextLine();
        System.out.print("Enter New Publication Date (yyyy-mm-dd): ");
        input += "," + sc.nextLine();
        System.out.print("Enter New Content: ");
        input += "," + sc.nextLine();

        return input;
    }

    private String createArticleInputUI () {
        String text = "";
        TextAreaDemo demo = new TextAreaDemo();
        demo.setVisible(true);   
        System.out.println(demo.getStory());

        return null;
        
    }

    /**
     * @param filePath
     * @return boolean
     */
    public static boolean xmlOrTxt(String filePath) {
        if (filePath.substring(filePath.length() - 3, filePath.length()).equals("xml"))
            return true;
        else if (filePath.substring(filePath.length() - 3, filePath.length()).equals("txt"))
            return false;
        else {
            System.out.println("Unsupported type of document;");
            System.exit(0);
            return false;
        }
    }

    public class TextAreaDemo extends JFrame
                          implements ActionListener
    {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 200;
    public static final int NUMBER_OF_LINES = 10;
    public static final int NUMBER_OF_CHAR = 30;

    private JTextArea story;


    public TextAreaDemo( )
        {
            setTitle("Text Area Demo");
            setSize(WIDTH, HEIGHT);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(2, 1));
            JPanel storyPanel = new JPanel( );
            storyPanel.setLayout(new BorderLayout( ));
            storyPanel.setBackground(Color.WHITE);
            story = new JTextArea(NUMBER_OF_LINES, NUMBER_OF_CHAR);

            storyPanel.add(story, BorderLayout.CENTER);
            JLabel storyLabel = new JLabel("Enter Author: ");
            storyPanel.add(storyLabel, BorderLayout.NORTH);

            add(storyPanel);

            JPanel buttonPanel = new JPanel( );
            buttonPanel.setLayout(new FlowLayout( ));
            buttonPanel.setBackground(Color.PINK);
            JButton actionButton = new JButton("Confirm");
            actionButton.addActionListener(this);
            buttonPanel.add(actionButton);

            JButton clearButton = new JButton("Clear");
            clearButton.addActionListener(this);
            buttonPanel.add(clearButton);

            add(buttonPanel);
        }

        public void actionPerformed(ActionEvent e)
        {
            String actionCommand = e.getActionCommand( );
            
            if (actionCommand.equals("Confirm")) {
                System.out.println(story.getText());
            } else {
                story.setText("");
            }

            
        }

        public String getStory(){
            return this.story.getText();
        }
    }
}
