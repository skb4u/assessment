package com.recuirtment.assesment.service;

import com.recuirtment.assesment.domain.Restaurant;

import java.util.Collections;
import java.util.List;

/**
 * @author Saravanakumar Alavandar
 */
public interface RestaurantFinderService {

    void addRestaurant(Restaurant restaurant);

    default List<Restaurant> findByLCoordinates(double latitude, double longitude, double distance) {
        return Collections.EMPTY_LIST;
    }

    default List<Restaurant> findByCoordinatesAndCuisine(double latitude, double longitude, String cuisine) {
        return Collections.emptyList();
    }

    // TODO
    /**
     * add functionality to find restaurants by food items and reviews.
     */

}
