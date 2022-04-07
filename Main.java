import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        Repository repo = new Repository(".\\ArticleXML1.xml");

        repo.appendToRepository(repo.xmlController.readArticleFromXML());

        //repo.appendToRepository("Title 3", "Author 3", "1269-12-29", "Content 3");

        repo.removeArticle(1);

        repo.xmlController.writeArticleToXML(repo);

        System.out.println();
        repo.print();
    }
}
