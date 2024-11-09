package com.finchool.server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDtoList {
    private int id;
    private String title;
    private String content;
    private int moduleId;
}
