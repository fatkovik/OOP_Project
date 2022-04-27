import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TXT implements ControllerInterface{

    private String path;
    private File txtFile;
    Scanner sc;

    public TXT(String _path){
        this.path = _path;
        this.txtFile = new File(this.path);
        
        try {
            sc = new Scanner(txtFile);
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Wrong path to file:");
            System.exit(0);
        }
    }

    public String[][] readArticle() {
        sc.useDelimiter("\\Z");
        
        String textItself = sc.next();
        
        String[] articleDiffered = textItself.split("<article>");
        
        String[][] args = new String[articleDiffered.length][5];
        for (int i = 0; i < articleDiffered.length; i++) {
            args[i][0] = articleDiffered[i].split("<nextElem>")[0];
            args[i][1] = articleDiffered[i].split("<nextElem>")[1];
            args[i][2] = articleDiffered[i].split("<nextElem>")[2];
            args[i][3] = articleDiffered[i].split("<nextElem>")[3];
            args[i][4] = articleDiffered[i].split("<nextElem>")[4];
        }

        for (int i = 0; i < args.length; i++) {
            for (int j = 0; j < args.length; j++) {
                System.out.println(args[i][j]);
            }
        }

        return args;
    }

    public void writeArticle(ArrayList<Article> articles) {
        System.out.println("that works");
    }

    public String getPath(){
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void deleteElements(String id) {
        // TODO Auto-generated method stub
        
    }

}
