package com.aguiro.foodflow.restaurant;

import com.aguiro.foodflow.api.RestaurantsApi;
import com.aguiro.foodflow.api.model.RestaurantRequest;
import com.aguiro.foodflow.api.model.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController implements RestaurantsApi {

    private final RestaurantService restaurantService;

    @Override
    public ResponseEntity<List<RestaurantResponse>> findAllRestaurants() {
        return ResponseEntity.ok(restaurantService.findAll());
    }

    @Override
    public ResponseEntity<RestaurantResponse> findRestaurantById(Long id) {
        return ResponseEntity.ok(restaurantService.findById(id));
    }

    @Override
    public ResponseEntity<RestaurantResponse> createRestaurant(RestaurantRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.create(request));
    }

    @Override
    public ResponseEntity<RestaurantResponse> updateRestaurant(Long id, RestaurantRequest request) {
        return ResponseEntity.ok(restaurantService.update(id, request));
    }

    @Override
    public ResponseEntity<Void> deleteRestaurant(Long id) {
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
