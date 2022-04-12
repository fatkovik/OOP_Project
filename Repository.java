import java.util.ArrayList;
import java.nio.ReadOnlyBufferException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Repository{

    private ArrayList<Article> articles = new ArrayList<Article>();
    
    public XMLController xmlController;
    /**
     * Repository Construcor
     * @param Path to the txt file containing text to parse (better to use with path)
     */
    public Repository(String _path){
        xmlController = new XMLController(_path);
    }
    /**
     * no arg Constructor for Repository Class
     */
    public Repository(){
        xmlController = new XMLController("\\.");
    }
    // /**
    //  * Copy Constructor for Repository class
    //  * @param other Repository containing articles
    //  */
    // public Repository(Repository other){
    // }
    /**
     * getter for ArrayList of articles
     * @return arraylist of article;
     */
    public ArrayList<Article> getArticles() {
        return new ArrayList<>(articles);
    }

    /**
     * getter for path
     * needed in XML parses class;
     * @return path
     */
    public String getPath() {
        return xmlController.getPath();
    }

    /**
     * parses the String formatting to actual date type
     * @param _stringDate input string date
     * @return Localdate if could be parsed
     * @return Null if DateTimeParseException
     */
    public static LocalDate dateParse(String _stringDate){
        //formatter for date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //try catch for wrong date format to not kill the app;
        try{
            return LocalDate.parse(_stringDate, formatter);
        }catch(DateTimeParseException t){
            System.out.println("Time Formatting is wrong in Input!");
            System.out.println("Make sure to use \"yyyy/MM/dd\" format");
            return null;
        }
    }

    /**
    * Creates and Adds an article to the repository (given all instance variables)
    * @param nameOfArticle Name of the Article
    * @param author Name of the Author
    * @param publishDate The publication Date
    * @param content The contents of the Article
    */
    public void appendToRepository(String _title, String _author, String _publishDate, String _content){
        articles.add(new Article(_title, _author, dateParse(_publishDate), _content));
    }

    /**
     * Adds articles from the XML file to the Repository
     * @param args 2D Array created from the XML file containing article information
     */

    public void appendToRepository(String[][] args){
        for (int i = 0; i < args.length; i++) {
            articles.add(new Article(args[i][0], args[i][1], dateParse(args[i][2]), args[i][3]));   
        }
    }

    /**
     * Adds a given Article to the repository
     * @param article of Article type
     */

    public void appendToRepository(Article article){
        articles.add(article);
    }

    /**
     * Removes an article with the given ID
     * @param index the ID of the article you want remove
     */

    public void removeArticle(int index) {
        articles.remove(index - 1);
    }


    //#region test
    /**
     * Modifies the Article with the given ID and parameters
     * @param index the ID of the article you want modify
     * @TODO: UPDAT METHOD, ADD OVERLOADS, MAKE IT WOKR WITH ID AND TITLE ONLY AND CHANGE THE GIVEN PARAMETER.
     */

    public void modify (int index, String title, String author, String date, String content) {
        System.out.println("ID: " + articles.get(index - 1).getId());
        articles.get(index - 1).setTitle(title);
        articles.get(index - 1).setAuthor(author);
        articles.get(index - 1).setPublishDate(dateParse(date));
        articles.get(index - 1).setContent(content);
    }



    public void print(){
        for (int i = 0; i < articles.size(); i++) {
            System.out.println("");
            System.out.println(articles.get(i).getId());
            System.out.println(articles.get(i).getTitle());
            System.out.println(articles.get(i).getAuthor());
            System.out.println(articles.get(i).getPublishDate());
            System.out.println(articles.get(i).getContent());
            System.out.println("");
        }
        
    }
    //#endregion
}

