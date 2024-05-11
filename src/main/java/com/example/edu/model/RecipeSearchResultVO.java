package com.example.edu.model;

import java.util.List;

public class RecipeSearchResultVO {
    private List<RecipeInfoVO> recipes;

    public RecipeSearchResultVO() {
    }

    public RecipeSearchResultVO(List<RecipeInfoVO> recipes) {
        this.recipes = recipes;
    }

    public List<RecipeInfoVO> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeInfoVO> recipes) {
        this.recipes = recipes;
    }
}
