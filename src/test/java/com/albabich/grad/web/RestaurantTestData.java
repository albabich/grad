package com.albabich.grad.web;

import com.albabich.grad.model.Restaurant;

import java.util.List;

import static com.albabich.grad.model.AbstractBaseEntity.START_SEQ;
import static com.albabich.grad.web.MenuItemTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {
    public static final MatcherFactory<Restaurant> REST_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menuItems");
    public static final MatcherFactory<Restaurant> REST_WITH_MENU_ITEMS_MATCHER = MatcherFactory.usingAssertions(Restaurant.class,
//     No need use ignoringAllOverriddenEquals, see https://assertj.github.io/doc/#breaking-changes
            (a, e) -> {
                throw new UnsupportedOperationException();
            },
            (a, e) -> assertThat(a).usingRecursiveComparison()
                    .ignoringFields("menuItems.restaurant").isEqualTo(e));


    public static final int NOT_FOUND = 10;
    public static final int REST1_ID = START_SEQ + 4;
    public static final int REST2_ID = START_SEQ + 5;
    public static final int REST3_ID = START_SEQ + 6;

    public static final Restaurant rest1 = new Restaurant(REST1_ID, "Хачапури и вино");
    public static final Restaurant rest2 = new Restaurant(REST2_ID, "Munhell");
    public static final Restaurant rest3 = new Restaurant(REST3_ID, "Kwakinn");
    public static final List<Restaurant> restaurants = List.of(rest1, rest2, rest3);

    static {
        rest1.setMenuItems(menuItemsRest1Today);
        rest2.setMenuItems(menuItemsRest2Today);
        rest3.setMenuItems(menuItemsRest3Today);
    }

    public static final List<Restaurant> restaurantsWithMenuToday = List.of(rest1, rest2, rest3);

    public static Restaurant getNew() {
        return new Restaurant(null, "New Restaurant");
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(rest3);
        updated.setName("UpdatedName");
        return updated;
    }
}
