import java.util.ArrayList;
import java.util.Formatter;
import java.io.File;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.Supplier;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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

    public void readFromXML() throws Exception{

        //instantiating the factory
        DocumentBuilderFactory docBuilderfactory = DocumentBuilderFactory.newInstance();

        //parsing the XML file here
        DocumentBuilder docBuilder = docBuilderfactory.newDocumentBuilder();

        Document doc = docBuilder.parse(new File(this.path));

        //normalizing to get proper formatting
        doc.getDocumentElement().normalize();

        //printing the root element
        System.out.println("root element: " + doc.getDocumentElement().getNodeName());

        //getting the elements with tag name "article"
        NodeList list = doc.getElementsByTagName("article");

        //loop throught text and each node;
        for (int i = 0; i < list.getLength(); i++) {

            Node node = list.item(i);

            if(node.getNodeType() == Node.ELEMENT_NODE){

                Element e = (Element) node;

                appendToArticleList(e.getElementsByTagName("nameOfArticle").item(0).getTextContent(),
                                         e.getElementsByTagName("author").item(0).getTextContent(),
                                         e.getElementsByTagName("publishDate").item(0).getTextContent(),
                                         e.getElementsByTagName("text").item(0).getTextContent());
            }
        }
    }    

    public void writeToXML(){
        //yes
    }

    public void appendToArticleList(String _nameOfArticle, String _author, String _publishDate, String _text){
        articles.add(new Article(_nameOfArticle, _author, dateParse(_publishDate), _text));
    }

    //#region test
    public void testPrint(){
        System.out.println(articles.size());
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
        Repository repo = new Repository(".\\ArticleXML1.xml");
        repo.readFromXML();
        // repo.loadFromTxt();
        repo.testPrint();
        //ArrayList<Article> list = repo.getArticles();
        //System.out.println(list.get(0).getId());
    }
    //#endregion
}
