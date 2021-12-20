package com.t3h.news.model;

import lombok.Data;

@Data
public class NewsModel extends BaseModel {

    private String title;

    private String content;

    private String avatar;

    private String author;

    private int categoryId;

    private String originalResource;

    private int numberAccess;

    private int censor;


}
