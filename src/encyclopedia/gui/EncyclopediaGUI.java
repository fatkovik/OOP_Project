package src.encyclopedia.gui;

import src.encyclopedia.Article;
import src.encyclopedia.RepoConsole;
import src.encyclopedia.database.controllers.Repository;
import src.encyclopedia.database.controllers.TXT;
import src.encyclopedia.database.controllers.XML;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static src.encyclopedia.gui.ArticleWindow.whiteLine;

public class EncyclopediaGUI extends JFrame implements ActionListener {

    public static final int WIDTH = 700;
    public static final int HEIGHT = 700;
    public static final Color background = new Color(32, 32, 32, 255);
    Font font = new Font("Verdana", Font.BOLD, 12);
    public static final Font dateFont = new Font("Verdana", Font.BOLD, 24);

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
        setResizable(false);
        setLocation(450, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
       

        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        helpMenu = new JMenu("Console");

        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");
        createItem = new JMenuItem("Create New Article");
        helpItem = new JMenuItem("Run from Console :)");

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
        JPanel abovePart = new JPanel(new BorderLayout());
        abovePart.setBackground(background);
        ImageIcon logo = new ImageIcon("src/encyclopedia/gui/preview.png");
        Image image = logo.getImage(); // transform it
        Image newimg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        logo = new ImageIcon(newimg);

        JLabel label = new JLabel();
        label.setText("<html> Repository <br/>" + path + " </html>");
        label.setFont(new Font("Segoe", Font.BOLD, 20));
        label.setForeground(Color.white);
        label.setIcon(logo);

        abovePart.add(label, BorderLayout.NORTH);
       


        JTextField searchArea = new JTextField("Search");
        searchArea.setColumns(50);
        abovePart.add(searchArea, BorderLayout.WEST);
        
        JButton searchConfirm = new JButton("Confirm");
        searchConfirm.setBackground(background);
        searchConfirm.setForeground(Color.white);
        searchConfirm.setBorder(whiteLine);
        abovePart.add(searchConfirm);
        ActionListener searchListener = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (repo.search(searchArea.getText()) != null) {
                    ArticleWindow newWindow = new ArticleWindow(repo.search(searchArea.getText()));
                } else {
                    JFrame errorFrame = new JFrame("Article Not Found!");
                    errorFrame.setLocationRelativeTo(searchArea);
                    errorFrame.setSize(320, 100);
                    errorFrame.setResizable(false);
                    errorFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    errorFrame.setBackground(background);
    

                    
                    JLabel errorText = new JLabel("Article Not Found!");
                    errorText.setFont(dateFont);
                    errorText.setBackground(Color.white);
                    errorFrame.add(errorText);
                    errorFrame.setVisible(true);
                }
                
                
            }
        };
        
        searchConfirm.addActionListener(searchListener);




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

    public void createGUI() {
        for (int i = 0; i < repo.getArticles().size(); i++) {

            JLabel title = new JLabel("        " + repo.getArticle(i).getAuthor() + " - " +
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
                    options.setSize(200, 200);
                    options.setLocationRelativeTo(options);
                    options.setResizable(false);
                    options.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    options.setLayout(new GridLayout(3, 1));
                    options.setVisible(true);

                    JButton open = new JButton();
                    JLabel openText = new JLabel("Open");
                    openText.setForeground(Color.white);
                    open.add(openText);
                    open.setBackground(background);
                    ActionListener openListener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(finalI == repo.getArticles().size()){
                                ArticleWindow window = new ArticleWindow(repo.getArticle(finalI - 1));
                            } else{
                                ArticleWindow window = new ArticleWindow(repo.getArticle(finalI));
                            }
                            
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

                            if(finalI <= 1){
                                repo.removeArticle(1);
                            }else{
                                repo.removeArticle(size);
                            }
                            articlesPanel.remove(articleButton);
                            options.dispose();
                            size--;
                            repaint();
                            revalidate();
                        }
                    };
                    remove.addActionListener(removeListener);

                    JButton favorite = new JButton();
                    JLabel favText = new JLabel("Un/Favorite");
                    favText.setForeground(Color.white);
                    favorite.add(favText);
                    favorite.setBackground(background);
                    ActionListener favListener = new ActionListener() {
                       
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            System.out.println("Article Added to Favorites!");
                            if (repo.getFavArticles().contains(repo.getArticle(size - 1))) {
                                repo.rmFavArticles(size -1);
                                title.setForeground(Color.white);
                            } else {
                                repo.addFavArticles(size - 1);
                                title.setForeground(Color.yellow);
                            }
                            options.dispose();

                            repaint();
                            revalidate();
                        }
                    };
                    favorite.addActionListener(favListener);
                    options.add(favorite);
                    options.add(remove);
                    options.add(open);
                }

            };

            articleButton.addActionListener(aListener);
            articleButton.add(title);
            articlesPanel.add(articleButton);

        }
    }

    /**
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loadItem) {
            System.out.println("*beep boop* you loaded a file");
            repo.controller.readArticle();
        }
        if (e.getSource() == saveItem) {
            System.out.println("*beep boop* you saved a file");
            repo.controller.writeArticle(repo.getArticles());
        }

        if (e.getSource() == helpItem) {
            RepoConsole console = new RepoConsole("./src/encyclopedia/database/ArticleXML.xml");
            console.run();
        }

        if (e.getSource() == createItem) {

            System.out.println("Creating New Article");

            JFrame create = new JFrame();
            create.setTitle("Enter The Right Parameters");
            create.setSize(300, 500);
            create.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            create.setLayout(new GridLayout(1, 1));
            create.setVisible(true);
            create.setBackground(background);
            create.setResizable(false);
            create.setLocationRelativeTo(this);

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

            fields.setBackground(background);
            create.add(fields);

            JButton confirm = new JButton("Confirm");
            confirm.setBackground(background);
            confirm.setForeground(Color.white);
            ActionListener confirmListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    repo.appendToRepository(
                            new Article(title.getText(), author.getText(), date.getText(), content.getText()));

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
                            options.setSize(200, 200);
                            options.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            options.setLocationRelativeTo(options);
                            options.setResizable(false);
                            options.setLayout(new GridLayout(3, 1));
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

                                    if(size <= 1){
                                        repo.removeArticle(1);
                                    }else{
                                        repo.removeArticle(size);
                                    }
                                    
                                    articlesPanel.remove(articleButton);
                                    options.dispose();
                                    size--;
                                    repaint();
                                    revalidate();
                                }
                            };
                            remove.addActionListener(removeListener);

                            JButton favorite = new JButton();
                            JLabel favText = new JLabel("Favorite");
                            favText.setForeground(Color.white);
                            favorite.add(favText);
                            favorite.setBackground(background);
                            ActionListener favListener = new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    System.out.println("Article Added to Favorites!");
                                    if (repo.getFavArticles().contains(repo.getArticle(size - 1))) {
                                        repo.rmFavArticles(size -1);
                                        title.setForeground(Color.white);
                                    } else {
                                        repo.addFavArticles(size - 1);
                                        title.setForeground(Color.yellow);
                                    }
                                    options.dispose();

                                    repaint();
                                    revalidate();
                                }
                            };
                            favorite.addActionListener(favListener);

                            options.add(favorite);
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
