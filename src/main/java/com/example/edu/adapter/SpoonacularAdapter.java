package com.example.edu.adapter;

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
        String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/search";

        HttpHeaders headers = createHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("apiKey", apiKey)
                .queryParam("query", query)
                .queryParam("number", 50); // 50개씩 가져오기

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<SearchResponse> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                SearchResponse.class);

        List<RecipeInfoVO> searchResults = new ArrayList<>();
        if (response.getBody() != null && response.getBody().getResults() != null) {
            for (SearchResultItem item : response.getBody().getResults()) {
                RecipeInfoVO recipeInfo = new RecipeInfoVO();
                recipeInfo.setTitle(item.getTitle());
                recipeInfo.setImage(item.getImage());
                // 다른 필요한 정보들도 추가할 수 있음
                searchResults.add(recipeInfo);
            }
        }

        return searchResults;
    }
    private static class SearchResponse {
        private List<SearchResultItem> results;

        public List<SearchResultItem> getResults() {
            return results;
        }

        public void setResults(List<SearchResultItem> results) {
            this.results = results;
        }
    }

    private static class SearchResultItem {
        private String title;
        private String image;

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
    }
    public List<RecipeInfoVO> fetchRecipes() {
        String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/complexSearch";

        HttpHeaders headers = createHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("number", 50) // 50개씩 가져오기
                .queryParam("sort", "id"); // ID 순서대로 정렬

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<FetchRecipesResponse> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                FetchRecipesResponse.class);

        List<RecipeInfoVO> recipes = new ArrayList<>();

        // API 응답이 성공하고 결과가 null이 아닌지 확인
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            // API 응답에서 결과 리스트를 가져옴
            List<FetchRecipesResultItem> results = response.getBody().getResults();

            // 가져온 결과가 null이 아닌지 확인
            if (results != null) {
                // 가져온 결과를 RecipeInfoVO로 변환하여 리스트에 추가
                for (FetchRecipesResultItem item : results) {
                    RecipeInfoVO recipe = new RecipeInfoVO();
                    recipe.setId(item.getId());
                    recipe.setTitle(item.getTitle());
                    // 다른 필요한 정보들도 추가할 수 있음
                    recipes.add(recipe);
                }
            }
        }

        return recipes;
    }
    private static class FetchRecipesResponse {
        private List<FetchRecipesResultItem> results;

        public List<FetchRecipesResultItem> getResults() {
            return results;
        }

        public void setResults(List<FetchRecipesResultItem> results) {
            this.results = results;
        }
    }

    private static class FetchRecipesResultItem {
        private int id;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}
