package com.example.edu.controller;

import com.example.edu.adapter.SpoonacularAdapter;
import com.example.edu.model.RecipeInfoVO;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String showSearchPage(@RequestParam @Nullable Map<String, Object> query,
                                 @RequestParam(defaultValue = "1") int page,
                                 Model model) {
        int pageSize = 60; // 페이지당 결과 수
        int maxPages = 15; // 최대 페이지 수
        int offset = (page - 1) * pageSize; // 페이지 오프셋 계산

        if (query.containsKey("query")) {
            String searchQuery = (String) query.get("query");
            List<RecipeInfoVO> recipes = spoonacularAdapter.searchRecipes(searchQuery, pageSize, offset);
            model.addAttribute("recipes", recipes);
            // 다음에 추가할 코드를 위해 검색어를 모델에 추가
            model.addAttribute("query", searchQuery);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", maxPages);
        } else if (query.containsKey("cuisine")) {
            String cuisine = (String) query.get("cuisine");
            List<RecipeInfoVO> recipes = spoonacularAdapter.searchCategory(cuisine, pageSize, offset);
            model.addAttribute("recipes", recipes);
            // 다음에 추가할 코드를 위해 요리 유형을 모델에 추가
            model.addAttribute("cuisine", cuisine);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", maxPages);
        } else if(query.containsKey("diet")) {
            String diet = (String) query.get("diet");
            List<RecipeInfoVO> recipes = spoonacularAdapter.searchCategory(diet, pageSize, offset);
            model.addAttribute("recipes", recipes);
            // 다음에 추가할 코드를 위해 다이어트 정보를 모델에 추가
            model.addAttribute("diet", diet);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", maxPages);
        } else if(query.containsKey("intolerances")) {
            String intolerances = (String) query.get("intolerances");
            List<RecipeInfoVO> recipes = spoonacularAdapter.searchCategory(intolerances, pageSize, offset);
            model.addAttribute("recipes", recipes);
            // 다음에 추가할 코드를 위해 불편한 식품 정보를 모델에 추가
            model.addAttribute("intolerances", intolerances);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", maxPages);
        }

        return "search";
    }

    @GetMapping("/Home")
    public String home(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 60; // 페이지당 결과 수
        int maxPages = 15; // 최대 페이지 수
        int offset = (page - 1) * pageSize; // 페이지 오프셋 계산

        List<RecipeInfoVO> recipes = spoonacularAdapter.showRecipes(pageSize, offset);
        model.addAttribute("recipes", recipes);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", maxPages);

        return "home";
    }

    @GetMapping("/mealplan")
    public String getMealPlan(Model model) {
        RecipeInfoVO recipes = spoonacularAdapter.createMealPlan();
        model.addAttribute("recipes", recipes);

        return "mealplan";
    }
}
