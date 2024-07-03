package com.example.webcrawler.data;

import java.util.List;

public class CategoryManualsUrls {
    private String category;
    private List<ManualUrls> manualUrlsList;

    public CategoryManualsUrls() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ManualUrls> getManualUrlsList() {
        return manualUrlsList;
    }

    public void setManualUrlsList(List<ManualUrls> manualUrlsList) {
        this.manualUrlsList = manualUrlsList;
    }

    public CategoryManualsUrls(String category, List<ManualUrls> manualUrlsList) {
        this.category = category;
        this.manualUrlsList = manualUrlsList;
    }

    @Override
    public String toString() {
        return "CategoryManualsUrls{" +
                "category='" + category + '\'' +
                ", manualUrlsList=" + manualUrlsList +
                '}';
    }
}
