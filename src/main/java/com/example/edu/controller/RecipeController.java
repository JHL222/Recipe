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

    @GetMapping("/recipesearch")
    public String showRecipeSearchPage() {
        return "recipesearch";
    }

    @GetMapping("/search")
    public String searchRecipes(@RequestParam String query, Model model) {
        // SpoonacularAdapter를 사용하여 Spoonacular API 호출 및 검색 결과 가져오기
        List<RecipeInfoVO> searchResults = spoonacularAdapter.searchRecipes(query);

        // 검색 결과와 검색어를 모델에 추가
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("query", query);

        return "fetchrecipe"; // 검색 결과를 표시하는 JSP 페이지로 이동
    }

    @GetMapping("/fetchrecipe")
    public String fetchRecipes(Model model) {
        // SpoonacularAdapter를 통해 레시피 목록을 가져옴
        List<RecipeInfoVO> recipes = spoonacularAdapter.fetchRecipes();

        // 메뉴 번호순으로 50개씩 잘라서 sublist에 저장
        List<RecipeInfoVO> slicedRecipes = sliceRecipes(recipes, 0, 50);

        // 모델에 잘라낸 레시피 목록을 추가
        model.addAttribute("recipes", slicedRecipes);

        return "fetchrecipe";
    }

    // 레시피 목록을 start부터 end까지 잘라서 반환하는 메서드
    private List<RecipeInfoVO> sliceRecipes(List<RecipeInfoVO> recipes, int start, int end) {
        // 메뉴 번호순으로 50개씩 잘라서 sublist에 저장
        return recipes.subList(start, Math.min(end, recipes.size()));
    }


}
