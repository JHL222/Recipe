package com.example.edu.adapter;

import com.example.edu.model.RecipeSearchResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

    public List<RecipeInfoVO> searchRecipes(String query) {
        String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/search?query=" + query + "&instructionsRequired=true";

        HttpHeaders headers = createHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("query", query)
                .queryParam("apiKey", apiKey)
                .queryParam("number", 100);

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

    public List<RecipeInfoVO> searchCategory(String query) {
        String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/search?" + query + "&instructionsRequired=true";

        HttpHeaders headers = createHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("query", query)
                .queryParam("apiKey", apiKey)
                .queryParam("number", 100);

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

    public List<RecipeInfoVO> showRecipes() {
        String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/complexSearch";

        HttpHeaders headers = createHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("apiKey", apiKey)
                .queryParam("addRecipeInformation", true)
                .queryParam("number", 100); // 최대 100개의 결과를 가져옵니다.

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

}
