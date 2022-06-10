package com.recuirtment.assesment.api;

import com.recuirtment.assesment.domain.Restaurant;
import com.recuirtment.assesment.service.RestaurantFinderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/find")
public class RestaurantController {


    private RestaurantFinderService restaurantFinderService;


    public RestaurantController(RestaurantFinderService restaurantFinderService) {
        this.restaurantFinderService = restaurantFinderService;
    }

    @GetMapping
    public ResponseEntity<RestaurantResponse> get(@RequestBody RestaurantRequest restaurantRequest) {

        RestaurantResponse response = new RestaurantResponse();
        response.setRestaurants(this.restaurantFinderService.findByLCoordinates(restaurantRequest.getLatitude(), restaurantRequest.getLongitude(), restaurantRequest.getWithInDistance()));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<RestaurantResponse> add(@RequestBody RestaurantRequest restaurantRequest) {

        Restaurant restaurant = new Restaurant(restaurantRequest.getLatitude(),
                restaurantRequest.getLongitude(),
                restaurantRequest.getName(),
                restaurantRequest.getCuisine(),
                restaurantRequest.getFoodItem());

        this.restaurantFinderService.addRestaurant(restaurant);

        return ResponseEntity.accepted().build();
    }

}
