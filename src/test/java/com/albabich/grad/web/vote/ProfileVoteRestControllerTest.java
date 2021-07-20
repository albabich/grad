package com.albabich.grad.web.vote;

import com.albabich.grad.web.AbstractControllerTest;
import com.albabich.grad.web.restaurant.AdminRestaurantRestController;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.albabich.grad.util.VoteUtil.createTo;
import static com.albabich.grad.web.MenuItemTestData.*;
import static com.albabich.grad.web.RestaurantTestData.REST1_ID;
import static com.albabich.grad.web.TestUtil.userHttpBasic;
import static com.albabich.grad.web.UserTestData.admin;
import static com.albabich.grad.web.VoteTestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProfileVoteRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = ProfileVoteRestController.REST_URL + '/';

    @Test
    void createWithLocation()throws Exception {
    }

    @Test
    void update()throws Exception {
    }

    @Test
    void getResultsToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "today")
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHER.contentJson(createTo(vote2),createTo(vote3),createTo(vote4)));
    }

    @Test
    void get() throws Exception{
    }
}