import java.time.LocalDate;

class Article{
    private static int prevId = 0;
    private final int ID;
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

        prevId++;
        this.ID = prevId;
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

        prevId++;
        this.ID = prevId;
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

        prevId++;
        this.ID = prevId;
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
        this.ID = article.ID;
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
     * @return The contents of the Article
     */

    public String getContent() {
        return content;
    }
    /**
     * @return The unique ID of the Article
     */
    public int getId() {
        return ID;
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

}