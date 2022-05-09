package src.encyclopedia.gui;

import src.encyclopedia.Article;
import src.encyclopedia.database.controllers.Repository;
import src.encyclopedia.database.controllers.TXT;
import src.encyclopedia.database.controllers.XML;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncyclopediaGUI extends JFrame implements ActionListener {

    public static final int WIDTH = 700;
    public static final int HEIGHT = 700;

    private Repository repo;

    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu editMenu;
    JMenu helpMenu;
    JMenuItem loadItem;
    JMenuItem saveItem;
    JMenuItem exitItem;
    JMenuItem helpItem;

    public EncyclopediaGUI (String path) {

        super("Java Encyclopedia");
        setSize(WIDTH,HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout());

        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        helpMenu = new JMenu("Help");

        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        helpItem = new JMenuItem("Get Instructions");

        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);
        helpItem.addActionListener(this);

        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        helpMenu.add(helpItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        this.setJMenuBar(menuBar);
        // ABOVE // SETTING UP MENU BAR

        if(xmlOrTxt(path)) this.repo = new Repository<XML>(new XML(path));
        else if(!xmlOrTxt(path)) this.repo = new Repository<TXT>(new TXT(path));

        this.repo.appendToRepository(repo.controller.readArticle());

        // border


        // ABOVE PART
        JPanel abovePart = new JPanel(new GridLayout());

        ImageIcon logo = new ImageIcon("src/encyclopedia/gui/preview.png");
        Image image = logo.getImage(); // transform it
        Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        logo = new ImageIcon(newimg);

        JLabel label = new JLabel();
        label.setText("Repository" + " \n " + repo.getArticles().size() + " Articles");
        label.setFont(new Font("Segoe", Font.BOLD, 40));
        label.setIcon(logo);
        abovePart.add(label);





        // Articles Panel
        JPanel articlesPanel = new JPanel(new GridLayout(20,1));
        Border grayline = BorderFactory.createLineBorder(Color.gray, 2);
        for (int i = 0; i < repo.getArticles().size(); i++) {
            JLabel title = new JLabel(repo.getArticle(i).getAuthor() + " - " + repo.getArticle(i).getTitle());

            title.setFont(new Font("Arial", Font.BOLD, 20));
            title.setHorizontalAlignment(SwingConstants.LEFT);
            title.setVerticalAlignment(SwingConstants.CENTER);
            title.setBorder(grayline);
            title.setSize(700, 20);

            articlesPanel.add(title);
        }

        //this.add(greeting, BorderLayout.NORTH);
        this.add(abovePart);
        this.add(articlesPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==loadItem) {
            System.out.println("*beep boop* you loaded a file");
        }
        if(e.getSource()==saveItem) {
            System.out.println("*beep boop* you saved a file");
        }

        if(e.getSource()==helpItem) {
            System.out.println("Here are the instructions");
        }
        if(e.getSource()==exitItem) {
            System.exit(0);
        }


    }

     /** 
     * @param filePath
     * @return boolean
     */
    public static boolean xmlOrTxt(String filePath){
        if(filePath.substring(filePath.length() - 3, filePath.length()).equals("xml")) return true;
        else if (filePath.substring(filePath.length() - 3, filePath.length()).equals("txt")) return false;
        else{
            System.out.println("Unsupported type of document;");
            System.exit(0);
            return false;
        }
    }
}
