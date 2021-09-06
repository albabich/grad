package com.albabich.grad.web.vote;

import com.albabich.grad.model.Vote;
import com.albabich.grad.repository.RestaurantRepository;
import com.albabich.grad.repository.VoteRepository;
import com.albabich.grad.to.VoteTo;
import com.albabich.grad.web.AbstractControllerTest;
import com.albabich.grad.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.albabich.grad.web.restaurant.RestaurantTestData.*;
import static com.albabich.grad.web.TestUtil.userHttpBasic;
import static com.albabich.grad.web.user.UserTestData.user1;
import static com.albabich.grad.web.user.UserTestData.user2;
import static com.albabich.grad.web.vote.VoteTestData.VOTE_MATCHER;
import static java.time.LocalDate.now;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProfileVoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL = ProfileVoteController.REST_URL + '/';

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void createWithLocation() throws Exception {
        VoteTo newVoteTo = new VoteTo(REST1_ID);
        Vote newVote = new Vote(null, now(), restaurantRepository.getById(newVoteTo.getRestaurantId()));
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVoteTo))
                .with(userHttpBasic(user1)))
                .andDo(print())
                .andExpect(status().isCreated());

        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        Vote actual = voteRepository.getById(newId);
        VOTE_MATCHER.assertMatch(actual, newVote);
        REST_MATCHER.assertMatch(actual.getRestaurant(), rest1);
    }

    @Test
    void createIfExist() throws Exception {
        VoteTo newVoteTo = new VoteTo(REST3_ID);
        Vote newVote = new Vote(null, now(), restaurantRepository.getById(newVoteTo.getRestaurantId()));
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVoteTo))
                .with(userHttpBasic(user1)))
                .andDo(print())
                .andExpect(status().isCreated());

        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        Vote actual = voteRepository.getById(newId);
        VOTE_MATCHER.assertMatch(actual, newVote);
        REST_MATCHER.assertMatch(actual.getRestaurant(), rest3);
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void createInvalid() throws Exception {
        VoteTo newVoteTo = new VoteTo(NOT_FOUND);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVoteTo))
                .with(userHttpBasic(user2)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}