package src.encyclopedia.gui;

import src.encyclopedia.Article;
import src.encyclopedia.database.controllers.Repository;
import src.encyclopedia.database.controllers.TXT;
import src.encyclopedia.database.controllers.XML;


import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static src.encyclopedia.gui.ArticleWindow.whiteLine;


public class EncyclopediaGUI extends JFrame implements ActionListener {

    public static final int WIDTH = 700;
    public static final int HEIGHT = 700;
    public static final Color background = new Color(32, 32,32, 255);
    Font font = new Font("Verdana", Font.BOLD, 12);

    private Repository repo;
    private static int size = 0;

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
        this.setLayout(new BorderLayout());

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


        // ABOVE PART
        JPanel abovePart = new JPanel(new FlowLayout());
        abovePart.setBackground(background);
        ImageIcon logo = new ImageIcon("src/encyclopedia/gui/preview.png");
        Image image = logo.getImage(); // transform it
        Image newimg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        logo = new ImageIcon(newimg);

        JLabel label = new JLabel();
        label.setText("<html> Repository <br/> </html>");
        label.setFont(new Font("Segoe", Font.BOLD, 20));
        label.setForeground(Color.white);
        label.setIcon(logo);
        JLabel info = new JLabel( "<html> Path: " + path + "<br/> </html>");
        info.setForeground(Color.white);
        info.setSize(20, 20);
        abovePart.add(label);
        abovePart.add(info);

        JPanel sortPanel = new JPanel(new GridLayout(1, 2));


        // Articles Panel
        articlesPanel = new JPanel(new GridLayout(20, 5));
        articlesPanel.setBackground(background);


        createGUI();
        size = repo.getArticles().size();

        JButton sortByAuthor = new JButton("Sort by Author");
        sortByAuthor.setForeground(Color.WHITE);
        ActionListener sortAuthListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repo.sortByAuthor();
                articlesPanel.removeAll();
                createGUI();
                repaint();
                revalidate();
            }
        };
        sortByAuthor.setBackground(background);
        sortByAuthor.addActionListener(sortAuthListener);
        sortPanel.add(sortByAuthor);

        JButton sortByTitle = new JButton("Sort by Title");
        ActionListener sortTitleListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repo.sortByTitle();
                articlesPanel.removeAll();
                createGUI();
                repaint();
                revalidate();
            }
        };
        sortByTitle.setBackground(background);
        sortByTitle.setForeground(Color.WHITE);
        sortByTitle.addActionListener(sortTitleListener);
        sortPanel.add(sortByTitle);

        JButton sortByDate = new JButton("Sort by Date");
        ActionListener sortDateListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repo.sortByTitle();
                articlesPanel.removeAll();
                createGUI();
                repaint();
                revalidate();
            }
        };
        sortByDate.setBackground(background);
        sortByDate.addActionListener(sortDateListener);
        sortByDate.setForeground(Color.WHITE);
        sortPanel.add(sortByDate);

        repaint();
        revalidate();

        this.add(sortPanel, BorderLayout.SOUTH);
        this.add(abovePart, BorderLayout.NORTH);
        this.add(articlesPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }



    public void createGUI () {
        for (int i = 0; i < repo.getArticles().size(); i++) {


            JLabel title = new JLabel( "        " + repo.getArticle(i).getAuthor() + " - " +
                    repo.getArticle(i).getTitle());
            title.setForeground(Color.white);
            title.setFont(font);

            ArticleUI articleButton = new ArticleUI(i);
            articleButton.setBorder(whiteLine);

            int finalI = i;
            ActionListener aListener = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    JFrame options = new JFrame();
                    options.setLocation(50, 50);
                    options.setTitle("Choose What You Want to do");
                    options.setSize(200, 100);
                    options.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    options.setLayout(new GridLayout(2, 1));
                    options.setVisible(true);


                    JButton open = new JButton();
                    JLabel openText = new JLabel("Open");
                    openText.setForeground(Color.white);
                    open.add(openText);
                    open.setBackground(background);
                    ActionListener openListener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ArticleWindow window = new ArticleWindow(repo.getArticle(finalI));
                            options.dispose();
                        }
                    };
                    open.addActionListener(openListener);

                    JButton remove = new JButton();
                    JLabel removeText = new JLabel("Delete");
                    removeText.setForeground(Color.white);
                    remove.add(removeText);
                    remove.setBackground(background);
                    ActionListener removeListener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            repo.removeArticle(size - 1);
                            articlesPanel.remove(articleButton);
                            options.dispose();
                            size--;
                            repaint();
                            revalidate();
                        }
                    };
                    remove.addActionListener(removeListener);


                    options.add(remove);
                    options.add(open);
                }

            };

            articleButton.addActionListener(aListener);
            articleButton.add(title);
            articlesPanel.add(articleButton);

        }
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

        }

        if (e.getSource() == createItem) {
            
            System.out.println("Creating New Article");

            JFrame create = new JFrame();
            create.setTitle("Enter The Right Parameters");
            create.setSize(300, 300);
            create.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            create.setLayout(new GridLayout(2, 1));
            create.setVisible(true);

            JPanel fields = new JPanel(new GridLayout(5, 1));
            fields.setBackground(background);


            JTextField title = new JTextField("Enter Title");
            JTextField author = new JTextField("Enter Author");
            JTextField date = new JTextField("Enter Publish Date or Leave Empty for Current Date");
            JTextField content = new JTextField("Enter the text");


            fields.add(title);
            fields.add(author);
            fields.add(date);
            fields.add(content);

            create.add(fields);


            JButton confirm = new JButton("Confirm");
            ActionListener confirmListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    repo.appendToRepository(new Article(title.getText(), author.getText(), date.getText(), content.getText()));

                    JLabel titleT = new JLabel("        " + repo.getArticle(size).getAuthor() + " - " +
                            repo.getArticle(size).getTitle());
                    titleT.setForeground(Color.white);
                    titleT.setFont(font);


                    ArticleUI articleButton = new ArticleUI(size);
                    articleButton.setBorder(whiteLine);

                    ActionListener aListener = new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JFrame options = new JFrame();
                            options.setTitle("Choose What You Want to do");
                            options.setSize(200, 100);
                            options.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            options.setLayout(new GridLayout(2, 1));
                            options.setVisible(true);


                            JButton open = new JButton();
                            JLabel openText = new JLabel("Open");
                            openText.setForeground(Color.white);
                            open.add(openText);
                            open.setBackground(background);
                            ActionListener openListener = new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    ArticleWindow window = new ArticleWindow(repo.getArticle(size - 1));
                                    options.dispose();
                                }
                            };
                            open.addActionListener(openListener);

                            JButton remove = new JButton();
                            JLabel removeText = new JLabel("Delete");
                            removeText.setForeground(Color.white);
                            remove.add(removeText);
                            remove.setBackground(background);
                            ActionListener removeListener = new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    repo.removeArticle(size - 1);
                                    articlesPanel.remove(articleButton);
                                    options.dispose();
                                    size--;
                                    repaint();
                                    revalidate();
                                }
                            };
                            remove.addActionListener(removeListener);


                            options.add(remove);
                            options.add(open);
                        }

                    };
                    articleButton.add(titleT);
                    articleButton.addActionListener(aListener);
                    articlesPanel.add(articleButton);
                    size++;
                    create.dispose();
                    repaint();
                    revalidate();
                }
            };
            confirm.addActionListener(confirmListener);
            fields.add(confirm);
            repaint();
            revalidate();
        }

        if (e.getSource() == exitItem) {
            System.exit(0);
        }

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

}
