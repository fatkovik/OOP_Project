package src.encyclopedia.database.controllers;

import src.encyclopedia.Article;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;

public class Repository<T extends ControllerInterface> {

    public T controller;

    private ArrayList<Article> articles = new ArrayList<Article>();

    /**
     * encyclopedia.Repository Construcor
     * 
     * @param param to the txt file containing text to parse (better to use with
     *              path)
     */
    public Repository(T param) {
        this.controller = param;
    }

    /**
     * getter for ArrayList of articles
     * 
     * @return arraylist of article;
     */
    public ArrayList<Article> getArticles() {
        return new ArrayList<>(articles);
    }

    /**
     * getter for path
     * needed in encyclopedia.XML parses class;
     * 
     * @return path
     */

    public Article getArticle(int i) {
        return (Article) articles.get(i);
    }

    public String getPath() {
        return controller.getPath();
    }

    /**
     * parses the String formatting to actual date type
     * 
     * @param _stringDate input string date
     * @return Localdate if could be parsed
     * @return Null if DateTimeParseException
     */
    public static LocalDate dateParse(String _stringDate) {
        // formatter for date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // try catch for wrong date format to not kill the app;
        try {
            return LocalDate.parse(_stringDate, formatter);
        } catch (DateTimeParseException t) {
            System.out.println("Time Formatting is wrong in Input!");
            System.out.println("Make sure to use \"yyyy/MM/dd\" format");
            return LocalDate.now();
        }
    }

    /**
     * Creates and Adds an article to the repository (given all instance variables)
     * 
     * @param _title       Name of the encyclopedia.Article
     * @param _author      Name of the Author
     * @param _publishDate The publication Date
     * @param _content     The contents of the encyclopedia.Article
     */
    public void appendToRepository(String _title, String _author, String _publishDate, String _content) {
        articles.add(new Article(_title, _author, dateParse(_publishDate), _content));
    }

    /**
     * Adds articles from the encyclopedia.XML file to the encyclopedia.Repository
     * 
     * @param args 2D Array created from the encyclopedia.XML file containing
     *             article information
     */

    public void appendToRepository(String[][] args) {
        for (int i = 0; i < args.length; i++) {
            articles.add(new Article(args[i][0], args[i][1], dateParse(args[i][2]), args[i][3]));
        }
    }

    /**
     * Adds a given encyclopedia.Article to the repository
     * 
     * @param article of encyclopedia.Article type
     */

    public void appendToRepository(Article article) {
        articles.add(article);
    }

    /**
     * Removes an article with the given ID
     * 
     * @param index the ID of the article you want remove
     */

    public void removeArticle(int index) throws IndexOutOfBoundsException {
        articles.remove(index - 1);
        controller.deleteElements(String.valueOf(index - 1));
    }

    /**
     * Modifies the encyclopedia.Article with the given ID and parameters
     * 
     * @param index the ID of the article you want modify
     * @TODO: UPDAT METHOD, ADD OVERLOADS, MAKE IT WOKR WITH ID AND TITLE ONLY AND
     *        CHANGE THE GIVEN PARAMETER.
     */
    public void modify(int index, String input) throws IndexOutOfBoundsException {
        String[] arrOfStr = input.split(",");
        articles.get(index - 1).setTitle(arrOfStr[0]);
        articles.get(index - 1).setAuthor(arrOfStr[1]);
        articles.get(index - 1).setPublishDate(dateParse(arrOfStr[2]));
        articles.get(index - 1).setContent(arrOfStr[3]);
    }

    public void print() {

        if (articles.size() == 0) {
            System.out.println("Repository Empty! Input 'c' to create an encyclopedia.Article!");
        } else {
            for (int i = 0; i < articles.size(); i++) {
                System.out.println("");

                System.out.println(
                        "[" + (i + 1) + "] " + articles.get(i).getAuthor() + " - " + articles.get(i).getTitle());
            }
        }

    }

    public void print(int index) throws IndexOutOfBoundsException {
        getArticles().get(index - 1).print();
    }

    /**
     * Searches in the encyclopedia
     * 
     * @param input      Title or Author to be Found
     * @param searchType Title or Author to be Searched in Encyclopedia
     */
    public Article search(String input) {
        for (int i = 0; i < getArticles().size(); i++) {
            if (getArticles().get(i).getTitle().contains(input) || getArticles().get(i).getAuthor().contains(input)) {
                System.out.println("\n" +  " Found!");
                return getArticles().get(i);
            }
        }
        System.out.println("No Article or Author Found by your search");
        return null;
    }

    // #region Sorting

    /**
     * Sorts <code>Title</code> with compareTo()
     */
    public void sortByTitle() {
        articles.sort(new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
    }

    /**
     * Sorts <code>Author</code> with compareTo()
     */
    public void sortByAuthor() {
        articles.sort(new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                return o1.getAuthor().compareTo(o2.getAuthor());
            }
        });
    }

    /**
     * Sorts <code>Publish Date</code> with compareTo()
     * "Doesn't Work Now"
     */
    public void sortByDate() {
        articles.sort(new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                return o1.getPublishDate().compareTo(o2.getPublishDate());
            }
        });
    }
    // #endregion



    ArrayList<Article> favArticles = new ArrayList<>();
    XML favController = new XML("src/encyclopedia/database/FavArticles.xml");

    public void appendToFav(String[][] args) {
        for (int i = 0; i < args.length; i++) {
            favArticles.add(new Article(args[i][0], args[i][1], dateParse(args[i][2]), args[i][3]));
        }
    }

    public void addFavArticles(int id) {
        appendToFav(favController.readArticle());

        favArticles.add(this.getArticle(id - 1));
        favController.writeArticle(favArticles);
    }

    public void rmFavArticles(int id) {
        if(favArticles.isEmpty()){
            favArticles.remove(0);
        }else{
            favArticles.remove(id -1 );
        }
        favController.deleteElements(String.valueOf(id - 1));
    }

    public ArrayList<Article> getFavArticles() {
        return favArticles;
    }
}
