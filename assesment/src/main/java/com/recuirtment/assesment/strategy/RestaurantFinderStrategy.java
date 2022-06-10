package com.recuirtment.assesment.strategy;

import com.recuirtment.assesment.domain.Restaurant;

import java.util.List;

/**
 * @author Saravanakumar Alavandar
 */
public interface RestaurantFinderStrategy {

    List<Restaurant> getList();

    void build(List<Restaurant> list);

    void add(Restaurant restaurant);

    /**
     * Find restaurants with in the distance of provided latitude and longitude
     *
     * @param latitude
     * @param longitude
     * @param distance
     * @return
     */
    List<Restaurant> findByLatLong(double latitude, double longitude, double distance);


    /**
     * Find the restaurants with 2 miles distance from provided latitude and longitude
     *
     * @param latitude
     * @param longitude
     * @return
     */
    List<Restaurant> findByLatLong(double latitude, double longitude);

}
