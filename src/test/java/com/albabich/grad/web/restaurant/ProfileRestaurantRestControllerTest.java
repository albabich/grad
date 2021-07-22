package com.albabich.grad.web.restaurant;

import com.albabich.grad.web.AbstractControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.albabich.grad.web.RestaurantTestData.REST_MATCHER;
import static com.albabich.grad.web.RestaurantTestData.restaurants;
import static com.albabich.grad.web.TestUtil.userHttpBasic;
import static com.albabich.grad.web.UserTestData.user1;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProfileRestaurantRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = ProfileRestaurantRestController.REST_URL + '/';

//    @Autowired
//    private CacheManager cacheManager;
//
//    @BeforeEach
//    public void setup() {
//        cacheManager.getCache("restaurantsAndMenus").clear();
//    }

    @Test
    void getAllWithMenuItemsToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL+"with-menu/today")
                .with(userHttpBasic(user1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(REST_MATCHER.contentJson(restaurants));
    }
}