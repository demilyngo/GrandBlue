package com.sea.GrandBlue.repo;

import com.sea.GrandBlue.models.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface ArticleRepository extends CrudRepository <Article, Long> {

    default ArrayList<Article> SortByType(String type) {
        ArrayList<Article> articles = new ArrayList<>();

        for (Article currentArticle : this.findAll())
            if (type.equals(currentArticle.getType()))
                articles.add(currentArticle);

        return articles;
    }

    default ArrayList<Article> SortByFood(String food) {
        ArrayList<Article> articles = new ArrayList<>();

        for (Article currentArticle : this.findAll())
            if (food.equals(currentArticle.getFood()))
                articles.add(currentArticle);

        return articles;
    }

    default Article GetRandom(String type) {
        ArrayList<Article> articles = this.SortByType(type);
        Article randomArticle = articles.get((int) (Math.random() * articles.size()));
        return randomArticle;
    }
}