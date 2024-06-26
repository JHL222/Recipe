package com.example.edu.model;

import java.util.List;

public class RecipeInfoVO {
    private String title;
    private String image;
    private String instructions;
    private int id;
    private List<String> ingredients;
    private String calories;
    private String carbs;
    private String fat;
    private String protein;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getCalories() { return calories; }
    public void setCalories(String calories) { this.calories = calories; }
    public String getCarbs() { return carbs; }
    public void setCarbs(String carbs) { this.carbs = carbs; }
    public String getFat() { return fat; }
    public void setFat(String fat) { this.fat = fat; }
    public String getProtein() { return protein; }
    public void setProtein(String protein) { this.protein = protein; }
}
