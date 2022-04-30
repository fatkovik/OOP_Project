import java.util.InputMismatchException;
import java.util.Scanner;

public class RepoConsole {

    private Repository repo;

    public RepoConsole(){
        this.repo = new Repository<XML>(new XML(".\\ArticleXML1.xml"));
    }

    public RepoConsole(String type, String path) {
        if(type.equals("XML")){
            this.repo = new Repository<XML>(new XML(path));
        }
        else if(type.equals("TXT")){
            this.repo = new Repository<TXT>(new TXT(path));
        }
        else{
            System.out.println("Unsupported type of document;");
            System.exit(0);
        }
    }
        

    public void printInstructions () {
        System.out.println("-----------------------");
        System.out.println("Input >p< to browse the default Articles.");
        System.out.println("Input >c< to create a new Article.");
        System.out.println("Input >o< and the ID to open an Article.");
        System.out.println("Input >e< and the ID to edit an Article.");
        System.out.println("Input >r< and the ID to remove an Article.");
        System.out.println();
        System.out.println("Input >ts< to sort by Title");
        System.out.println("Input >as< to sort by Author");
        System.out.println("Input >ds< to sort by Date");
        System.out.println();
        System.out.println("Input >se< to search");
        System.out.println();
        System.out.println("To save changes into the file, input  >s<.");
        System.out.println("If you want to end the program, input >q<.");
        System.out.println("-----------------------");
    }


    public void run() {
        Scanner sc = new Scanner(System.in);
        String inputLine;
        System.out.println();
        System.out.println("Welcome to the Repository Console!");
        printInstructions();
        inputLine = sc.nextLine();

        // creating a default repository with the articles in ArticleXML1.xml
        //Repository<XML> repo = new Repository<XML>(new XML(".\\ArticleXML1.xml"));
        repo.appendToRepository(repo.controller.readArticle());

        while (!inputLine.equals("q")) {
            clearScreen();
            try {
                if (inputLine.equals("p")) {
                    repo.print();
                }

                else if (inputLine.startsWith("o ")) {
                    repo.print(Integer.parseInt(inputLine.substring(2)));
                }

                else if (inputLine.startsWith("e ")) {
                    repo.modify(Integer.parseInt(inputLine.substring(2)), createArticleInput());
                    System.out.println("Article Successfully Edited!");
                }

                else if (inputLine.startsWith("r ")) {
                    repo.removeArticle(Integer.parseInt(inputLine.substring(2)));
                    System.out.println("Article Successfully Removed and Saved to;");
                }

                else if (inputLine.equals("c")) {
                    repo.appendToRepository(new Article(createArticleInput()));
                    System.out.println();
                    System.out.println("Article Successfully Created!");
                }

                else if (inputLine.equals("s")) {
                    repo.controller.writeArticle(repo.getArticles());
                    System.out.println("File Successfully Saved!");
                }

                else if (inputLine.equals("ts")) {
                    repo.sortByTitle();
                    System.out.println("File Successfully Sorted!");
                }

                else if (inputLine.equals("as")) {
                    repo.sortByAuthor();
                    System.out.println("File Successfully Sorted!");
                }

                else if (inputLine.equals("ds")) {
                    repo.sortByDate();
                    System.out.println("File Successfully Sorted!");

                } else if (inputLine.equals("se")) {
                    System.out.print("Please Input the Parameter to Search With: >Title< or >Author< ");
                    String searchType = sc.nextLine();
                    System.out.print("Search: ");
                    repo.search(sc.nextLine(),searchType);
                }
                else {
                    System.out.println("Wrong Instructions");
                }
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("Wrong Index: Try Again");
            }

            catch (NumberFormatException f) {
                System.out.println("Wrong Input, Input index Please");
            }

            System.out.println();
            printInstructions();
            inputLine = sc.nextLine();
        }
    }

    public String createArticleInput () {
        Scanner sc = new Scanner(System.in);
        String input = "";

        System.out.print("Enter New Title: ");
        input += sc.nextLine();
        System.out.print("Enter New Author: ");
        input += "," + sc.nextLine();
        System.out.print("Enter New Publication Date (yyyy-mm-dd): ");
        input += "," + sc.nextLine();
        System.out.print("Enter New Content: ");
        input += "," + sc.nextLine();

        return input;
    }

    private static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  
}
