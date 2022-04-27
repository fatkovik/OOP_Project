import java.time.LocalDate;

class Article{
    private String title;
    private LocalDate publishDate;
    private String author;
    private String content;
    
    /**
     * No arg constructor for Article Class
     */
    public Article(){
        this.title = "No Title";
        this.author = "No Author";
        this.publishDate = LocalDate.of(1000, 1, 1);
        this.content = null;
    }   

    /**
     * Default Constructor for Article class
     * @param title of the Article
     * @param author of the Article
     * @param publishDate of Article
     * @param content of the Article
     */
    public Article(String title, String author ,LocalDate publishDate, String content){
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
        this.content = content;
    }

    /**
     * Constructor using String for Date for Article class
     * @param title of the Article
     * @param author of the Article
     * @param publishDate String date (d/mm/yyyy) of Article
     * @param content of the Article
     */
    public Article(String title, String author ,String publishDate, String content){
        this.title = title;
        this.author = author;
        this.publishDate = Repository.dateParse(publishDate);
        this.content = content;
    }
    /**
     * Creates an Article given a input string separated by comammas
     * @param input
     */
    public Article (String input) {
        String[] arrOfStr = input.split(",");
        this.title = arrOfStr[0];
        this.author = arrOfStr[1];
        this.publishDate = Repository.dateParse(arrOfStr[2]);
        this.content = arrOfStr[3];


    }
    /**
     * Copy Constructor for Article class
     * @param article Article
     */
    public Article(Article article){
        this.title = article.title;
        this.author = article.author;
        this.publishDate = article.publishDate;
        this.content = article.content;
    }

    /**
     * @return name of given article
     */

    public String getTitle(){
        return title;
    }

     /**
     * @return Author of given article
     */

    public String getAuthor() {
        return author;
    }

    /**
     * @return The publication date in LocalDate String format
     */

    public String getPublishDate() {
        return String.valueOf(publishDate);
    }
    /**
     * gets <code>publishDate</code>
     * @return publish date typeOf LocalDate
     */
    public LocalDate getPublishDateTypeLocalDate() {
        return publishDate;
    }

    /**
     * @return The contents of the Article
     */

    public String getContent() {
        return content;
    }


    /**
     * @param author The author of the Article
     */
    public void setAuthor(String author) {
        if (author != null) {
            this.author = author;
        }
    }
    /**
     * @param title The title of the article
     */

    public void setTitle(String title) {
        if (title != null) {
            this.title = title;
        }
    }
    /**
     * @param content The content of the article
     */

    public void setContent(String content) {
        if (content != null) {
            this.content = content;
        }
    }

    /**
     * @param date The date of publication
     */
    public void setPublishDate(LocalDate date) {
        if (date != null) {
            this.publishDate = date;
        }
    }

    public void print(){
        System.out.println("");
        //System.out.println("ID (Somewhat Useless And Fake Right Now): " + this.getId());
        System.out.println("Title: " + this.getTitle());
        System.out.println("Author: " + this.getAuthor());
        System.out.println("Publication Date: " + this.getPublishDate());
        System.out.println("Content: " + this.getContent());
    }

}