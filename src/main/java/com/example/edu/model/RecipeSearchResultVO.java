package com.example.edu.model;

import java.util.List;

public class RecipeSearchResultVO {
    private List<RecipeInfoVO> results;
    private int totalResults;
    private int offset;
    private int number;

    // Getters and Setters

    public List<RecipeInfoVO> getResults() {
        return results;
    }

    public void setResults(List<RecipeInfoVO> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}