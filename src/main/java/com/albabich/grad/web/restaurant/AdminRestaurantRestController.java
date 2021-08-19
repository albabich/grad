package com.albabich.grad.web.restaurant;

import com.albabich.grad.model.Restaurant;
import com.albabich.grad.repository.RestaurantRepository;
import com.albabich.grad.to.RestaurantTo;
import com.albabich.grad.util.RestaurantUtil;
import com.albabich.grad.util.exception.IllegalRequestDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.albabich.grad.util.ValidationUtil.*;

@RestController
@RequestMapping(value = AdminRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantRestController {
    public static final String REST_URL = "/rest/admin/restaurants";

    private static final Logger log = LoggerFactory.getLogger(AdminRestaurantRestController.class);
    protected static final String EXCEPTION_DUPLICATE_RESTAURANT_MESSAGE = "You already have restaurant with this name";

    private final RestaurantRepository restaurantRepository;

    public AdminRestaurantRestController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public List<RestaurantTo> getAll() {
        log.info("getAll");
        return RestaurantUtil.getTos(restaurantRepository.findAll());
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("get restaurant {}", id);
        return checkNotFoundWithId(restaurantRepository.findById(id).orElse(null), id);
    }

    @CacheEvict(value = "restaurantsAndMenus", allEntries = true)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete restaurant {}", id);
        checkNotFoundWithId(restaurantRepository.delete(id) != 0, id);
    }

    @CacheEvict(value = "restaurantsAndMenus", allEntries = true)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestBody Restaurant restaurant) {
        log.info("create {}", restaurant);
        try {
            checkNew(restaurant);
            Assert.notNull(restaurant, "restaurant must not be null");
            Restaurant created = restaurantRepository.save(restaurant);
            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL + "/{id}")
                    .buildAndExpand(created.getId()).toUri();
            return ResponseEntity.created(uriOfNewResource).body(created);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(EXCEPTION_DUPLICATE_RESTAURANT_MESSAGE);
        }
    }

    @CacheEvict(value = "restaurantsAndMenus", allEntries = true)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id) {
        log.info("update {}", restaurant);
        try {
            assureIdConsistent(restaurant, id);
            Assert.notNull(restaurant, "restaurant must not be null");
            checkNotFoundWithId(restaurantRepository.save(restaurant), restaurant.id());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(EXCEPTION_DUPLICATE_RESTAURANT_MESSAGE);
        }
    }
}
