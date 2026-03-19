package com.aguiro.foodflow.restaurant;

import com.aguiro.foodflow.api.model.RestaurantRequest;
import com.aguiro.foodflow.api.model.RestaurantResponse;
import com.aguiro.foodflow.common.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public List<RestaurantResponse> findAll() {
        return restaurantRepository.findByActiveTrue()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public RestaurantResponse findById(Long id) {
        return restaurantRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found: " + id));
    }

    @Transactional
    public RestaurantResponse create(RestaurantRequest req) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(req.getName());
        restaurant.setAddress(req.getAddress());
        restaurant.setPhone(req.getPhone());
        return toDto(restaurantRepository.save(restaurant));
    }

    @Transactional
    public RestaurantResponse update(Long id, RestaurantRequest req) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found: " + id));
        restaurant.setName(req.getName());
        restaurant.setAddress(req.getAddress());
        restaurant.setPhone(req.getPhone());
        return toDto(restaurantRepository.save(restaurant));
    }

    @Transactional
    public void delete(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found: " + id));
        restaurant.setActive(false);
        restaurantRepository.save(restaurant);
    }

    private RestaurantResponse toDto(Restaurant restaurant) {
        RestaurantResponse response = new RestaurantResponse();
        response.setId(restaurant.getId());
        response.setName(restaurant.getName());
        response.setAddress(restaurant.getAddress());
        response.setPhone(restaurant.getPhone());
        response.setActive(restaurant.isActive());
        return response;
    }
}
