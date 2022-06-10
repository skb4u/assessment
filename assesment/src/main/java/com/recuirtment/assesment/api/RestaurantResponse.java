package com.recuirtment.assesment.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.recuirtment.assesment.domain.Restaurant;
import lombok.Data;

import java.util.List;

@Data
@JsonAutoDetect
public class RestaurantResponse {

    private List<Restaurant> restaurants;
}
