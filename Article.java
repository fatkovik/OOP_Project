import java.time.LocalDate;

class Article{
    private String nameOfArticle;
    private LocalDate publishDate; //depricated, probably goona change soon
    private String author;
    private String text;

    
    /**
     * No arg constructor for Article Class
     */
    public Article(){
        this.nameOfArticle = "void";
        this.author = "no Author";
        this.publishDate = LocalDate.of(1000, 1, 1);
    }   

    /**
     * Default Constructor for Article class
     * @param Name of Article
     * @param Author
     * @param Date of publishing
     * @param Text of the article itself
     */
    public Article(String nameOfArticle_, String author_ ,LocalDate publishDate_, String text_){
        this.nameOfArticle = nameOfArticle_;
        this.author = author_;
        this.publishDate = publishDate_;
        this.text = text_;
    }

    /**
     * Copy Constructor for Article class
     * @param Article
     */
    public Article(Article article_){
        this.nameOfArticle = article_.nameOfArticle;
        this.author = article_.author;
        this.publishDate = article_.publishDate;
        this.text = article_.text;
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
    public LocalDate getPublishDate() {
        return publishDate;
    }
    /**
     * @return the text of article itself
     */
    public String getText() {
        return text;
    }


    /**
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    /**
     * @param nameOfArticle
     */
    public void setNameOfArticle(String nameOfArticle) {
        this.nameOfArticle = nameOfArticle;
    }
    /**
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    //no setter for date; since cant be changed :)

}