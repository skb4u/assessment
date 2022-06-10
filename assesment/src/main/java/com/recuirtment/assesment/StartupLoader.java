package com.recuirtment.assesment;

import com.recuirtment.assesment.domain.Restaurant;
import com.recuirtment.assesment.service.RestaurantFinderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
@Profile("load")
@Slf4j
public class StartupLoader {

    private RestaurantFinderService restaurantFinderService;

    public StartupLoader(RestaurantFinderService restaurantFinderService) {
        this.restaurantFinderService = restaurantFinderService;
    }

    public List<Restaurant> getTestList() {

        List<Restaurant> list = new ArrayList<>();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("startupdata/Mobile_Food_Facility_Permit.csv");


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

    @PostConstruct
    public void load() {
        getTestList().forEach(r -> this.restaurantFinderService.addRestaurant(r));

        log.info("All data loaded successfully");

    }
}
