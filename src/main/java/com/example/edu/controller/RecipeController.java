package com.example.edu.controller;

import com.example.edu.adapter.SpoonacularAdapter;
import com.example.edu.model.RecipeInfoVO;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public String showSearchPage(@RequestParam @Nullable Map<String, Object> query, Model model) {
        if (query.containsKey("query")) {
            List<RecipeInfoVO> recipes = spoonacularAdapter.searchRecipes((String) query.get("query"));
            model.addAttribute("recipes", recipes);
            return "search";
        } else if (query.containsKey("cuisine")) {
            String cuisine = (String) query.get("cuisine");
            List<RecipeInfoVO> recipes = spoonacularAdapter.searchCategory(cuisine);
            model.addAttribute("recipes", recipes);
            return "search";
        } else if(query.containsKey("diet")) {
            String diet = (String) query.get("diet");
            List<RecipeInfoVO> recipes = spoonacularAdapter.searchCategory(diet);
            model.addAttribute("recipes", recipes);
            return "search";
        }else if(query.containsKey("intolerances")) {
            String intolerances = (String) query.get("intolerances");
            List<RecipeInfoVO> recipes = spoonacularAdapter.searchCategory(intolerances);
            model.addAttribute("recipes", recipes);
            return "search";

        } else {
            return "search";
        }
    }


    @GetMapping("/Home")
    public String home(Model model){
        List<RecipeInfoVO> recipes = spoonacularAdapter.showRecipes();
        model.addAttribute("recipes", recipes);
        return "home";
    }
}
