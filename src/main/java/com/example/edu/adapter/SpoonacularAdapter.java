package com.example.edu.adapter;

import com.example.edu.model.RecipeSearchResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import com.example.edu.model.RecipeInfoVO;


@Component
public class SpoonacularAdapter {

    @Value("${spoonacular.api.key}")
    private String apiKey;

    @Value("${spoonacular.api.host}")
    private String apiHost;

    @Autowired
    private RestTemplate restTemplate;

    public SpoonacularAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", apiHost);
        return headers;
    }

    public RecipeInfoVO getRecipeInfo(String recipeId) {
        String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/" + recipeId + "/information";

        HttpHeaders headers = createHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("apiKey", apiKey);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<RecipeInfoVO> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                RecipeInfoVO.class);

        RecipeInfoVO recipeInfo = response.getBody();

        if (recipeInfo != null && recipeInfo.getId() != 0) {
            String ingredientsUrl = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/"
                    + recipeInfo.getId() + "/ingredientWidget.json";

            HttpHeaders ingredientsHeaders = createHeaders();
            HttpEntity<?> ingredientsEntity = new HttpEntity<>(ingredientsHeaders);

            ResponseEntity<String> ingredientsResponse = restTemplate.exchange(
                    ingredientsUrl,
                    HttpMethod.GET,
                    ingredientsEntity,
                    String.class);

            List<String> ingredientsList = parseIngredientsFromResponse(ingredientsResponse.getBody());
            recipeInfo.setIngredients(ingredientsList);
        }

        RecipeInfoVO NutritionInfoVO = getRecipeNutritions(recipeId);
        if (NutritionInfoVO != null) {
            recipeInfo.setCalories(NutritionInfoVO.getCalories());
            recipeInfo.setCarbs(NutritionInfoVO.getCarbs());
            recipeInfo.setFat(NutritionInfoVO.getFat());
            recipeInfo.setProtein(NutritionInfoVO.getProtein());
        }

        return recipeInfo;
    }

    private List<String> parseIngredientsFromResponse(String responseBody) {
        List<String> ingredientsList = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBody);

            if (root.has("ingredients")) {
                JsonNode ingredientsNode = root.get("ingredients");
                Iterator<JsonNode> iterator = ingredientsNode.iterator();
                while (iterator.hasNext()) {
                    JsonNode ingredientNode = iterator.next();
                    String ingredient = ingredientNode.get("name").asText();
                    ingredientsList.add(ingredient);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ingredientsList;
    }

    public List<RecipeInfoVO> searchRecipes(String query, int number, int offset) {
        String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/search?instructionsRequired=true";

        HttpHeaders headers = createHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("query", query)
                .queryParam("apiKey", apiKey)
                .queryParam("number", number)
                .queryParam("offset", offset);


        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<RecipeSearchResultVO> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                RecipeSearchResultVO.class);

        RecipeSearchResultVO result = response.getBody();
        if (result != null && result.getResults() != null) {
            return result.getResults();
        }

        return new ArrayList<>();
    }

    public List<RecipeInfoVO> searchCategory(String query, int number, int offset) {
        String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/search?instructionsRequired=true";

        HttpHeaders headers = createHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("query", query)
                .queryParam("apiKey", apiKey)
                .queryParam("number", number)  // 한 페이지에 출력될 결과 수
                .queryParam("offset", offset);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<RecipeSearchResultVO> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                RecipeSearchResultVO.class);

        RecipeSearchResultVO result = response.getBody();
        if (result != null && result.getResults() != null) {
            return result.getResults();
        }

        return new ArrayList<>();
    }

    public List<RecipeInfoVO> showRecipes(int number, int offset){
        String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/complexSearch";

        HttpHeaders headers = createHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("apiKey", apiKey)
                .queryParam("addRecipeInformation", true)
                .queryParam("number", number)// 한 페이지에 출력될 결과 수
                .queryParam("offset", offset);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<RecipeSearchResultVO> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                RecipeSearchResultVO.class);

        RecipeSearchResultVO result = response.getBody();
        if (result != null && result.getResults() != null) {
            return result.getResults();
        }

        return new ArrayList<>();
    }

    public RecipeInfoVO getRecipeNutritions(String recipeId) {
        String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/" + recipeId + "/nutritionWidget.json";

        HttpHeaders headers = createHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("apiKey", apiKey);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<NutritionInfoVO> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                NutritionInfoVO.class);

        NutritionInfoVO nutritionInfo = response.getBody();
        RecipeInfoVO recipeInfo = new RecipeInfoVO();

        if (nutritionInfo != null) {
            recipeInfo.setCalories(nutritionInfo.getCalories());
            recipeInfo.setCarbs(nutritionInfo.getCarbs());
            recipeInfo.setFat(nutritionInfo.getFat());
            recipeInfo.setProtein(nutritionInfo.getProtein());
        }

        return recipeInfo;
    }

    static class NutritionInfoVO {
        private String calories;
        private String carbs;
        private String fat;
        private String protein;

        public String getCalories() { return calories; }
        public void setCalories(String calories) { this.calories = calories; }
        public String getCarbs() { return carbs; }
        public void setCarbs(String carbohydrates) { this.carbs = carbohydrates; }
        public String getFat() { return fat; }
        public void setFat(String fats) { this.fat = fats; }
        public String getProtein() { return protein; }
        public void setProtein(String proteins) { this.protein = proteins; }
    }

}
