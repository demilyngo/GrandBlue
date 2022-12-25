package com.sea.GrandBlue.controllers;

import com.sea.GrandBlue.models.Article;
import com.sea.GrandBlue.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ArticleController  {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/")
    private String home(Model model) {
        ArrayList<Article> randomArticles = new ArrayList<>();
        randomArticles.add(articleRepository.GetRandom("Facts"));
        randomArticles.add(articleRepository.GetRandom("Animals"));
        randomArticles.add(articleRepository.GetRandom("Plants"));
        model.addAttribute("randomArticles", randomArticles);
        return "main";
    }


    @GetMapping("/articles")
    private String aticlesTypes(@RequestParam(value = "type", required = false) ArrayList <String> type,
                               @RequestParam(value = "food", defaultValue = "All") String food,
                               Model model) {
        if(type == null) {
            Iterable<Article> articles = articleRepository.findAll();
            model.addAttribute("articles", articles);
        }
        else {
            ArrayList<Article> result = new ArrayList<>();
            for (String currentType : type) {
                if (currentType.equals("Animals") && !food.equals("All")) {
                    ArrayList<Article> articles = articleRepository.SortByFood(food);
                    result.addAll(articles);
                } else {
                    ArrayList<Article> articles = articleRepository.SortByType(currentType);
                    result.addAll(articles);
                }
                model.addAttribute("articles", result);
            }
        }
        return "articles";
    }

    @GetMapping("/articles/{id}")
    private String aticlesDetails(@PathVariable(value="id") long id, Model model) {
        if(!articleRepository.existsById(id)) {
            return "redirect:/articles";
        }

        Optional<Article> article = articleRepository.findById(id);
        ArrayList<Article> res = new ArrayList<>();
        article.ifPresent(res::add);
        model.addAttribute("article", res);
        return "details";
    }

    @GetMapping("/articles/add")
    private String articleAddPage(Model model) {
        return "add";
    }

    @PostMapping("/articles/add")
    private String articleAdd (@RequestParam String name,
                              @RequestParam String type,
                              @RequestParam String description,
                              @RequestParam(required = false) String place,
                              @RequestParam(required = false) String food,
                              @RequestParam(required = false) String imgsrc, Model model){
        Article article = new Article(name, type, description, place, food, imgsrc);
        articleRepository.save(article);
        return "redirect:/articles";
    }
}
