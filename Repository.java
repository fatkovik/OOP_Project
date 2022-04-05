import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Repository {

    private ArrayList<Article> articles = new ArrayList<Article>();
    
    private String path;
    /**
     * Repository Construcor
     * @param Path to the txt file containing text to parse (better to use with path)
     */
    public Repository(String _path){
        this.path = _path;
    }
    /**
     * no arg Constructor for Repository Class
     */
    public Repository(){
        this.path = "\\.";
    }
    /**
     * Copy Constructor for Repostiry class
     * @param Repository
     */
    public Repository(Repository repo){
        this.path = repo.path;
    }
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
        return path;
    }

    /**
     * parses the String formatting to actual date type
     * @param _stringDate input string date
     * @return Localdate if could be parsed
     * @return Null if DateTimeParseException
     */
    public static LocalDate dateParse(String _stringDate){
        //formatter for date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        //try catch for wrong date format to not kill the app;
        try{
            return LocalDate.parse(_stringDate, formatter);
        }catch(DateTimeParseException t){
            System.out.println("Time formatting wrong in input");
            return null;
        }
    }

    /**
     * just creates the article, formates and everything
     * @param _nameOfArticle
     * @param _author
     * @param _publishDate
     * @param _text
     */
    public void appendToArticleList(String _id, String _nameOfArticle, String _author, String _publishDate, String _text){
        articles.add(new Article(_nameOfArticle, _author, dateParse(_publishDate), _text));
    }

    /**
     * adds article to articles from xml
     * @param String[][] 2d array contaiongn many articles, each with its nodes
     */
    public void appendToArticleList(String[][] args){
        for (int i = 0; i < args.length; i++) {
            articles.add(new Article(args[i][0], args[i][1], dateParse(args[i][2]), args[i][3]));   
        }
    }

    //#region test
    public void testPrint(){
        for (int i = 0; i < articles.size(); i++) {
            System.out.println("");
            System.out.println(articles.get(i).getId());
            System.out.println(articles.get(i).getNameOfArticle());
            System.out.println(articles.get(i).getAuthor());
            System.out.println(articles.get(i).getPublishDate());
            System.out.println(articles.get(i).getText());
            System.out.println("");
        }
        
    }

    //below just for testing purposes;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Repository repo = new Repository(".\\ArticleXML1.xml");
        XMLController x = new XMLController(".\\ArticleXML1.xml");
        repo.appendToArticleList(x.readArticleFromXML());
        repo.testPrint();
        
        //repo.articles.get(0).setText(sc.nextLine());

        x.writeArticleToXML(repo.articles);
    }
    //#endregion
}

