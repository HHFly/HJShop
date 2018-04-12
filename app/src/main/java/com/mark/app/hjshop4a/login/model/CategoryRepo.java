package com.mark.app.hjshop4a.login.model;

/**
 * Created by zhuwh on 2017/10/10.
 */

public class CategoryRepo {

    private long categoryId;
    private String categoryName;

    public long getCategoryId() {
        return categoryId;
    }

    public CategoryRepo setCategoryId(long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public CategoryRepo setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    @Override
    public String toString() {
        return categoryName;
    }
}
