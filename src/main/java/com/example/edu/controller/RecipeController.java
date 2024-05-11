package com.example.edu.controller;

import com.example.edu.adapter.SpoonacularAdapter;
import com.example.edu.model.RecipeInfoVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RecipeController {

    private final SpoonacularAdapter spoonacularAdapter;

    public RecipeController(SpoonacularAdapter spoonacularAdapter) {
        this.spoonacularAdapter = spoonacularAdapter;
    }

    @GetMapping("/recipe")
    public String showRecipePage(@RequestParam String recipeId, Model model) {
        RecipeInfoVO recipeInfo = spoonacularAdapter.getRecipeInfo(recipeId);
        model.addAttribute("recipeInfo", recipeInfo);
        return "recipe";
    }

    @GetMapping("/search")
    public String showSearchPage(@RequestParam String query, Model model) {
        List<RecipeInfoVO> recipes = spoonacularAdapter.searchRecipes(query);
        model.addAttribute("recipes", recipes);
        return "search";
    }

    @GetMapping("/Home")
    public String home(Model model){
        List<RecipeInfoVO> recipes = spoonacularAdapter.showRecipes();
        model.addAttribute("recipes", recipes);
        return "home";
    }
}
