package src.encyclopedia.database;

import src.encyclopedia.*;
import java.util.ArrayList;

public class FavArticles{
    private ArrayList<Article> favArticles;

    public FavArticles(){
        favArticles = new ArrayList<>();
    }

    public void addFavArticle(Article article){
        favArticles.add(article);
    }

    public ArrayList<Article> getFavArticles() {
        return favArticles;
    }

    public void setFavArticles(ArrayList<Article> favArticles) {
        this.favArticles = favArticles;
    }

}