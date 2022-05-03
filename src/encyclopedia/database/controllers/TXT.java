package src.encyclopedia.database.controllers;

import src.encyclopedia.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TXT implements ControllerInterface {

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
        sc.useDelimiter("<>");
        
        String textItself = "";

        while (sc.hasNext()) {
            textItself = textItself + sc.nextLine();

        }

        String[][] args = new String[textItself.split("<article>").length][4];

        String[] diffArticles = textItself.split("<article>");

        for (int i = 0; i < args.length; i++) {
            for (int j = 0; j < 4; j++) {
                args[i][j] = diffArticles[i].split("<nextElem>")[j];
                System.out.println(args[i][j]);
            }
        }
        return args;
    }

    public void writeArticle(ArrayList<Article> articles) {
        
        String toOutput = "";
        for (int i = 0; i < articles.size(); i++) {
            toOutput = "\n" + toOutput + "\n" + articles.get(i).getTitle() + "<nextElem>" + articles.get(i).getAuthor() + "<nextElem>" +
                       articles.get(i).getPublishDate() + "<nextElem>" + articles.get(i).getContent() + "\n" + "<article>" + "\n";
        }
        toOutput = toOutput + "<>";

        try {
            FileWriter fileWrite = new FileWriter(txtFile);
            fileWrite.write(toOutput);
            fileWrite.close();
            System.out.println("wroteSuccesfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPath(){
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void deleteElements(String id) {
    }

}
