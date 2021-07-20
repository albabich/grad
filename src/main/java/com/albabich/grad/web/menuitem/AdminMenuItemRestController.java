package com.albabich.grad.web.menuitem;

import com.albabich.grad.model.MenuItem;
import com.albabich.grad.repository.MenuItemRepository;
import com.albabich.grad.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;

import static com.albabich.grad.util.ValidationUtil.*;

@RestController
@RequestMapping(value = AdminMenuItemRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMenuItemRestController {
    static final String REST_URL = "/rest/admin/restaurants";

    private static final Logger log = LoggerFactory.getLogger(AdminMenuItemRestController.class);

    private MenuItemRepository menuItemRepository;
    private RestaurantRepository restaurantRepository;

    public AdminMenuItemRestController(MenuItemRepository menuItemRepository, RestaurantRepository restaurantRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/{restaurantId}/menu-items/{id}")
    public MenuItem get(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("get menuItem {} for restaurant {}", id, restaurantId);
        MenuItem menuItem = menuItemRepository.findById(id)
                .filter(mItem -> mItem.getRestaurant().getId() == restaurantId)
                .orElse(null);
        return checkNotFoundWithId(menuItem, id);
    }

    @CacheEvict(value = "restaurantsAndMenus", allEntries = true)
    @Transactional
    @PostMapping(value = "/{restaurantId}/menu-items", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuItem> createWithLocation(@Valid @RequestBody MenuItem menuItem, @PathVariable int restaurantId) {
        log.info("create menuItem {} for restaurant {}", menuItem, restaurantId);
        Assert.notNull(menuItem, "menuItem must not be null");
        checkNew(menuItem);
        menuItem.setRestaurant(restaurantRepository.getOne(restaurantId));
        menuItem.setDate(LocalDate.now());
        MenuItem created = menuItemRepository.save(menuItem);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/" + restaurantId + "/menu-items" + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @CacheEvict(value = "restaurantsAndMenus", allEntries = true)
    @Transactional
    @PutMapping(value = "/{restaurantId}/menu-items/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody MenuItem menuItem, @PathVariable int id, @PathVariable int restaurantId) {
        log.info("update menuItem {} for restaurant {}", menuItem, restaurantId);
        Assert.notNull(menuItem, "menuItem must not be null");
        assureIdConsistent(menuItem, id);
        menuItem.setRestaurant(restaurantRepository.getOne(restaurantId));
        menuItemRepository.save(menuItem);
    }

    @CacheEvict(value = "restaurantsAndMenus", allEntries = true)
    @DeleteMapping("/{restaurantId}/menu-items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable int id, @PathVariable int restaurantId) {
        log.info("delete menuItem {} for restaurant {}", id, restaurantId);
        checkNotFoundWithId(menuItemRepository.delete(id, restaurantId)!= 0, id);
    }
}
