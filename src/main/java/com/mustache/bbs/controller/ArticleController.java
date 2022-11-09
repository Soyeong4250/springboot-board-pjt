package com.mustache.bbs.controller;

import com.mustache.bbs.domain.Article;
import com.mustache.bbs.domain.dto.ArticleDto;
import com.mustache.bbs.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("")
    public String index() {
        return "redirect:/articles/list";
    }

    @GetMapping("/new")
    public String registerArticlePage(){
        return "articles/new";
    }

    @PostMapping("")
    public String registArticles(ArticleDto articleDto) {
        log.info(articleDto.toString());
        Article savedArticle = articleRepository.save(articleDto.toEntity());
        log.info("generatedId: {}", savedArticle.getId());
        return "";
    }

    @GetMapping("/{id}")
    public String selectArticle(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);

        if(!optArticle.isEmpty()) {
            model.addAttribute("article", optArticle.get());
            return "articles/show";
        } else {
            model.addAttribute("message", String.format("%d번 게시글이 존재하지 않습니다.", id));
            return "error";
        }
    }
    
    @GetMapping("/list")
    public String articleList(Model model) {
        log.debug("articleList 호출");
        List<Article> articleList = articleRepository.findAll();
        model.addAttribute("articles", articleList);
        return "articles/list";
    }

    @GetMapping("/{id}/edit")
    public String editArticlePage(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);

        if(!optArticle.isEmpty()) {
            model.addAttribute("article", optArticle.get());
            return "articles/edit";
        } else {
            model.addAttribute("message", String.format("%d번 게시글이 존재하지 않습니다.", id));
            return "error";
        }
    }

    @PostMapping("/{id}/update")
    public String updateArticle(@PathVariable Long id, ArticleDto articleDto, Model model) {
        log.debug("updateArticle 호출");
        log.info("title:{} content{}", articleDto.getTitle(), articleDto.getContent());
        Article article = articleRepository.save(articleDto.toEntity());
        model.addAttribute("artcle", article);
        return String.format("redirect:/articles/%d", article.getId());
    }

}
