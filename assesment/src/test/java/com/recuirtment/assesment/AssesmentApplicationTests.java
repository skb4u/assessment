package com.recuirtment.assesment;

import com.recuirtment.assesment.domain.Restaurant;
import com.recuirtment.assesment.strategy.RestaurantFinderStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootTest
class AssesmentApplicationTests {

    @Autowired
    RestaurantFinderStrategy restaurantFinderStrategy;

    @Test
    void contextLoads() {

        restaurantFinderStrategy.build(getTestList());
        List<Restaurant> foundList = restaurantFinderStrategy.findByLatLong(Double.valueOf("37.79612338025215"), Double.valueOf("-122.39727320433803"), Double.valueOf(5));

        foundList.forEach(r -> {
            System.out.println(r.getName() + " " + r.getFoodItems() + " lat: " + r.getLatitude() + " long: " + r.getLongitude());
        });
    }


    public List<Restaurant> getTestList() {

        List<Restaurant> list = new ArrayList<>();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("Mobile_Food_Facility_Permit.csv");


        Scanner sc = new Scanner(is);
        sc.nextLine();
        while (sc.hasNext()) {
            String line = sc.nextLine();

            String[] elements = line.split(",");
            try {
                list.add(new Restaurant(Double.valueOf(elements[14]), Double.valueOf(elements[15]), elements[1], elements[2], elements[11]));
            } catch (NumberFormatException nfe) {
                System.out.println(nfe.getMessage() + " line");
            }
        }


        sc.close();  //closes the scanner


        return list;


    }

}
