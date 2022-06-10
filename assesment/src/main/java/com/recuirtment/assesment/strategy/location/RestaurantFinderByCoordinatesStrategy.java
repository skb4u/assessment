package com.recuirtment.assesment.strategy.location;


import com.recuirtment.assesment.domain.Restaurant;
import com.recuirtment.assesment.strategy.RestaurantFinderStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author Saravanakumar Alavandar
 */
@Component
@Slf4j
public class RestaurantFinderByCoordinatesStrategy implements RestaurantFinderStrategy {


    private CoordinatesOrganiser coordinatesOrganiser;

    public RestaurantFinderByCoordinatesStrategy(CoordinatesOrganiser coordinatesOrganiser) {
        this.coordinatesOrganiser = coordinatesOrganiser;
    }

    @Override
    public List<Restaurant> getList() {

        System.out.println(this.coordinatesOrganiser.getRoot().toString());

        return null;
    }

    @Override
    public void build(List<Restaurant> list) {

        list.forEach(r -> coordinatesOrganiser.addRestaurant(r));

    }

    @Override
    public void add(Restaurant restaurant) {
        coordinatesOrganiser.addRestaurant(restaurant);
    }

    @Override
    public List<Restaurant> findByLatLong(double latitude, double longitude, double distance) {
        return coordinatesOrganiser.findByLatLong(latitude, longitude, distance);
    }

    @Override
    public List<Restaurant> findByLatLong(double latitude, double longitude) {
        return findByLatLong(latitude, longitude, 2);
    }


}
