package com.recuirtment.assesment.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author Saravanakumar Alavandar
 * Entity to hold Restaurant Data
 */
@Data
public class Restaurant {

    double latitude;

    double longitude;

    String name;

    String cuisine;

    private String foodItems;

    @JsonIgnore
    Restaurant left;

    @JsonIgnore
    Restaurant right;

    @JsonIgnore
    private double distanceToParent;

    @JsonIgnore
    private boolean visited;

    public Restaurant(double latitude, double longitude, String name, String cuisine, String foodItems) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.cuisine = cuisine;
        this.foodItems = foodItems;
    }


    /**
     * Sets the distance from current to parent. This would useful while searching and dont need to calculate again.
     *
     * @param parent
     */
    public void distance(Restaurant parent) {

        this.distanceToParent = distance(parent.getLatitude(), parent.getLongitude());
    }


    public double distance(double latitude, double longitude) {
        double dlon = Math.toRadians(this.longitude) - Math.toRadians(longitude);
        double dlat = Math.toRadians(this.latitude) - Math.toRadians(latitude);
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(this.latitude) * Math.cos(latitude)
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 3956;

        // calculate the result
        return (c * r);
    }


    @Override
    public String toString() {
        return "Restaurant{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", foodItems='" + foodItems + '\'' +
                ", distanceToParent=" + distanceToParent +
                ", \n left=" + left +
                ", \n right=" + right +
                '}';
    }
}
