package com.example.kintechai;

public class CategoryModel {

    private String CategoryIconLinkink;
    private String categoryName;

    public CategoryModel(String categoryIconLinkink, String categoryName) {
        CategoryIconLinkink = categoryIconLinkink;
        this.categoryName = categoryName;
    }

    public String getCategoryIconLinkink() {
        return CategoryIconLinkink;
    }

    public void setCategoryIconLinkink(String categoryIconLinkink) {
        CategoryIconLinkink = categoryIconLinkink;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
