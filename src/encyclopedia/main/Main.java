package src.encyclopedia.main;

import src.encyclopedia.RepoConsole;

public class Main {

    public static void main(String[] args) {

        RepoConsole console = new RepoConsole("./src/encyclopedia/database/ArticleXML.xml");
        console.run();

        //encyclopedia.TXT txt = new encyclopedia.TXT(".\\ArticleTXT.txt");
        //txt.readArticle();

    }
}