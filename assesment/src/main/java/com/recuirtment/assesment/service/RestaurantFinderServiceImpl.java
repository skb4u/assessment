package com.recuirtment.assesment.service;

import com.recuirtment.assesment.domain.Restaurant;
import com.recuirtment.assesment.strategy.RestaurantFinderStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Service layer to strategy
 *
 * @author Saravanakumar Alavandar
 */
@Slf4j
@Component
public class RestaurantFinderServiceImpl implements RestaurantFinderService {

    // This would be list of beans because multiple strategy can be added future. This would be chosen based on the input parameters.
    private RestaurantFinderStrategy restaurantFinderStrategy;

    public RestaurantFinderServiceImpl(RestaurantFinderStrategy restaurantFinderStrategy) {
        this.restaurantFinderStrategy = restaurantFinderStrategy;
    }

    @Override
    public void addRestaurant(Restaurant restaurant) {
        this.restaurantFinderStrategy.add(restaurant);

        if (log.isDebugEnabled()) {
            log.debug("Successfully added {} ", restaurant.toString());
        }

    }

    @Override
    public List<Restaurant> findByLCoordinates(double latitude, double longitude, double distance) {

        if (log.isDebugEnabled()) {
            log.debug("finding for coordinates {} {} and distance {} ", latitude, longitude, distance);
        }

        return this.restaurantFinderStrategy.findByLatLong(latitude, longitude, distance);
    }
}
