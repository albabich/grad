package com.albabich.grad.web.restaurant;

import com.albabich.grad.model.Restaurant;
import com.albabich.grad.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = ProfileRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestaurantRestController {
    static final String REST_URL = "/rest/profile/restaurants";

    private static final Logger log = LoggerFactory.getLogger(ProfileRestaurantRestController.class);

    private final RestaurantRepository restaurantRepository;

    public ProfileRestaurantRestController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Cacheable("restaurantsAndMenus")
    @GetMapping("/with-menu/today")
    public List<Restaurant> getAllWithMenuItemsToday() {
        log.info("getAll with menuItems today");
        return restaurantRepository.getAllWithMenuItemsByDate(LocalDate.now());
    }
}
