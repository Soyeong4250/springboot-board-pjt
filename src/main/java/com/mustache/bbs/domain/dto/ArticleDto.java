package com.mustache.bbs.domain.dto;

import com.mustache.bbs.domain.Article;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ArticleDto {

    private String title;
    private String content;

    public ArticleDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article toEntity() {
        return new Article(this.title, this.content);
    }
}
