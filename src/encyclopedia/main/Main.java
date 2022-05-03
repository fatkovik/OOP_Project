package encyclopedia.main;

import encyclopedia.RepoConsole;

public class Main {

    public static void main(String[] args) {

        RepoConsole console = new RepoConsole("XML", "./src/encyclopedia/database/ArticleXML1.xml");
        console.run();

        //encyclopedia.TXT txt = new encyclopedia.TXT(".\\ArticleTXT.txt");
        //txt.readArticle();

    }
}