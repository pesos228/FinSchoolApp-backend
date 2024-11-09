package com.finchool.server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDto {
    private String title;
    private String content;
    private int moduleId;
}
