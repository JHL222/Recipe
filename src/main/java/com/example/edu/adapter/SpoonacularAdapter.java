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

import com.example.edu.model.RecipeInfoVO;

import java.util.ArrayList;
import java.util.List;

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

        return response.getBody();
    }

    public List<RecipeInfoVO> searchRecipes(String query) {
        String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/search?query=" + query;

        HttpHeaders headers = createHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("query", query)
                .queryParam("apiKey", apiKey);

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
