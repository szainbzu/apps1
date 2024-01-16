package com.example.apps1;

public class book {
    private String title;
    private String category;
    private int pages;

    public book(){}

    public book(String title, String category, int pages) {
        this.title = title;
        this.category = category;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
