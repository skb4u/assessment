package com.recuirtment.assesment.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

/**
 * @author Saravanakumar Alavandar
 */
@Data
@JsonAutoDetect
public class RestaurantRequest {

    private double latitude;

    private double longitude;

    private String name;

    private String cuisine;

    private String foodItem;

    private double withInDistance;

}
