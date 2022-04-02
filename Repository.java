import java.util.ArrayList;
import java.io.File;
import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

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

    
    public ArrayList<Article> getArticles() {
        return new ArrayList<>(articles);
    }

    
    /**
     * Load from file from the specified path method
     * creates new article object with given arguments
     * argument are default Article constructor args
     *      @Sidenote Article constructor argument are: Name, Author, Date, Text
     * MAKE THIS XXXML
     *               
     * @throws Exception of not finding the txt
     */
    public void loadFromTxt() throws Exception{

        File file = new File(path); //path
        Scanner sc = new Scanner(file);     

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        String[] diffArticles = new String[4];

        while(sc.hasNextLine()){
            diffArticles = sc.nextLine().split("\\$");
            try{
                articles.add(new Article(diffArticles[0], diffArticles[1], LocalDate.parse(diffArticles[2], formatter),diffArticles[3]));
            }catch(ArrayIndexOutOfBoundsException a){
                System.out.println("delete the spaces in txt file");
            }
        }

        sc.close();
    }

    //this test too
    public void testPrint(){
        for (int i = 0; i < articles.size(); i++) {
            System.out.println(articles.get(i).getNameOfArticle());
            System.out.println(articles.get(i).getAuthor());
            System.out.println(articles.get(i).getPublishDate());
            System.out.println(articles.get(i).getText());
        }
        
    }

    //below just for testing purposes;
    public static void main(String[] args) throws Exception {
        Repository repo = new Repository(".\\articles_formatted.txt");
        repo.loadFromTxt();
        repo.testPrint();
        ArrayList<Article> list = repo.getArticles();
        System.out.println(list.size());



        String[] strs = new String[28];
        //.....
        for(String str : strs) {
            
        }
    }
}
