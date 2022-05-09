package encyclopedia.database.controllers;

import encyclopedia.Article;

import java.util.ArrayList;

public interface ControllerInterface {
    String[][] readArticle();

    void writeArticle(ArrayList<Article> articles);

    String getPath();

    void deleteElements(String id);
}
