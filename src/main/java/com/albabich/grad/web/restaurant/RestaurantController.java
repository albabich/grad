package com.albabich.grad.web.restaurant;

import com.albabich.grad.model.Restaurant;
import com.albabich.grad.repository.RestaurantRepository;
import com.albabich.grad.to.RestaurantTo;
import com.albabich.grad.util.RestaurantUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    static final String REST_URL = "/rest/restaurants";

    private static final Logger log = LoggerFactory.getLogger(RestaurantController.class);

    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/with-menu/today")
    public List<Restaurant> getAllWithMenuItemsToday() {
        log.info("getAll with menuItems today");
        return restaurantRepository.getAllWithMenuItemsByDate(LocalDate.now());
    }

    @GetMapping("/with-votes/today")
    public List<RestaurantTo> getAllWithVotesToday() {
        log.info("getAll with votes today");
        return RestaurantUtil.getTos(restaurantRepository.findAllByVoteDate(LocalDate.now()));
    }
}
