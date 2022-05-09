package src.encyclopedia.main;

import src.encyclopedia.RepoConsole;
import src.encyclopedia.gui.EncyclopediaGUI;

public class Main {

    public static void main(String[] args) {

//        RepoConsole console = new RepoConsole("./src/encyclopedia/database/ArticleXML.xml");
//        console.run();
        EncyclopediaGUI test = new EncyclopediaGUI("src/encyclopedia/database/ArticleXML.xml");

    }
}