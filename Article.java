import java.time.LocalDate;

class Article{

    private static int prevId = 0;
    private final int ID;
    private String nameOfArticle;
    private LocalDate publishDate;
    private String author;
    private String text;
    
    /**
     * No arg constructor for Article Class
     */
    public Article(){
        this.nameOfArticle = "void";
        this.author = "no Author";
        this.publishDate = LocalDate.of(1000, 1, 1);
        this.text = null;
        prevId++;
        this.ID = prevId;
    }   

    /**
     * Default Constructor for Article class
     * @param Name of Article
     * @param Author
     * @param Date of publishing
     * @param Text of the article itself
     */
    public Article(String _nameOfArticle, String _author ,LocalDate _publishDate, String _text){
        this.nameOfArticle = _nameOfArticle;
        this.author = _author;
        this.publishDate = _publishDate;
        this.text = _text;
        prevId++;
        this.ID = prevId;
    }

    /**
     * Copy Constructor for Article class
     * @param Article
     */
    public Article(Article _article){
        this.nameOfArticle = _article.nameOfArticle;
        this.author = _article.author;
        this.publishDate = _article.publishDate;
        this.text = _article.text;
        this.ID = _article.ID;
    }

    /**
     * @return name of given article
     */
    public String getNameOfArticle(){
        return nameOfArticle;
    }
     /**
     * @return Author of given article
     */
    public String getAuthor() {
        return author;
    }
    /**
     * @return Publish date of given article LOCALDATE format
     */
    public String getPublishDate() {
        return String.valueOf(publishDate);
    }
    /**
     * @return the text of article itself
     */
    public String getText() {
        return text;
    }
    /**
     * @return ID number of the element specified.
     */
    public int getId() {
        return ID;
    }


    /**
     * @param _author
     */
    public void setAuthor(String _author) {
        this.author = _author;
    }
    /**
     * @param _nameOfArticle
     */
    public void setNameOfArticle(String _nameOfArticle) {
        this.nameOfArticle = _nameOfArticle;
    }
    /**
     * @param _text
     */
    public void setText(String _text) {
        this.text = _text;
    }

    //no setter for date; since cant be changed :)

}