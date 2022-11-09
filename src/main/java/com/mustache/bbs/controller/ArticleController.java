package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.ArticleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/articles")
public class ArticleController {

    @GetMapping("/new")
    public String registerPage(){
        return "articles/new";
    }

    @PostMapping("")
    public String registArticles(ArticleDto articleDto) {
        log.info(articleDto.toString());
        return "";
    }

}
