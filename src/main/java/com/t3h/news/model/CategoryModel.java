package com.t3h.news.model;

public class CategoryModel extends BaseModel{

    private String name;

    private int parentsCategoryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentsCategoryId() {
        return parentsCategoryId;
    }

    public void setParentsCategoryId(int parentsCategoryId) {
        this.parentsCategoryId = parentsCategoryId;
    }
}
