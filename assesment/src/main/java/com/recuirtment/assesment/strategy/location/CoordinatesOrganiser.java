package com.recuirtment.assesment.strategy.location;

import com.recuirtment.assesment.domain.Axis;
import com.recuirtment.assesment.domain.Restaurant;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Saravanakumar Alavandar
 */
@Component
@Slf4j
public class CoordinatesOrganiser {

    private Restaurant rootRestaurant;

    private Axis axis;

    public Restaurant getRoot() {
        return this.rootRestaurant;
    }


    /**
     * To add restaurant
     *
     * @param restaurant
     */
    public void addRestaurant(Restaurant restaurant) {

        if (this.rootRestaurant == null) {
            this.rootRestaurant = restaurant;
            this.rootRestaurant.setDistanceToParent(0);
            axis = Axis.X;
            return;
        }
        addChild(rootRestaurant, restaurant, Axis.X);
    }


    private void addChild(Restaurant parent, Restaurant newRestaurant, Axis axis) {

        Restaurant child;

        if (axis == Axis.X) {

            if (newRestaurant.getLatitude() < parent.getLatitude())
                child = parent.getLeft();
            else
                child = parent.getRight();

            if (child != null) {
                axis = (axis == Axis.X) ? Axis.Y : Axis.X;


                addChild(child, newRestaurant, axis);
                newRestaurant.distance(child);
                return;
            }

            if (log.isDebugEnabled()) {
                log.debug("adding child - parent lat {}, current lat {} ", parent.getLatitude(), newRestaurant.getLatitude());
            }

            if (newRestaurant.getLatitude() < parent.getLatitude())
                parent.setLeft(newRestaurant);

            else
                parent.setRight(newRestaurant);

        }

        if (axis == Axis.Y) {

            if (newRestaurant.getLongitude() < parent.getLongitude())
                child = parent.getLeft();
            else
                child = parent.getRight();

            if (child != null) {
                axis = (axis == Axis.X) ? Axis.Y : Axis.X;
                addChild(child, newRestaurant, axis);
                newRestaurant.distance(child);
                return;
            }

            if (log.isDebugEnabled()) {
                log.debug("adding child - parent long {}, current long {} ", parent.getLongitude(), newRestaurant.getLongitude());
            }

            if (newRestaurant.getLongitude() < parent.getLongitude())
                parent.setLeft(newRestaurant);
            else
                parent.setRight(newRestaurant);

        }


    }


    /**
     * Implemented in Pre-Order search
     *
     * @param latitude
     * @param longitude
     * @return
     */
    public List<Restaurant> findByLatLong(double latitude, double longitude, double distance) {

        if (this.rootRestaurant == null)
            return Collections.emptyList();

        List<Restaurant> foundRestaurants = new ArrayList<>();

        find(latitude, longitude, distance, rootRestaurant, foundRestaurants);

        return foundRestaurants;
    }

    private void find(double latitude, double longitude, double distance, Restaurant restaurant, List<Restaurant> foundList) {


        if (Double.compare(restaurant.distance(latitude, longitude), distance) <= 0) {
            foundList.add(restaurant);
        }

        if (restaurant.getLeft() != null) {
            restaurant = restaurant.getLeft();
            find(latitude, longitude, distance, restaurant, foundList);
        }

        if (restaurant.getRight() != null) {
            restaurant = restaurant.getRight();
            find(latitude, longitude, distance, restaurant, foundList);
        }

    }


}
