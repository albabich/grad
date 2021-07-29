package com.albabich.grad.web.vote;

import com.albabich.grad.model.Vote;
import com.albabich.grad.repository.VoteRepository;
import com.albabich.grad.web.AbstractControllerTest;
import com.albabich.grad.web.VoteTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.albabich.grad.web.RestaurantTestData.REST1_ID;
import static com.albabich.grad.web.RestaurantTestData.REST3_ID;
import static com.albabich.grad.web.VoteTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProfileVoteRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = ProfileVoteRestController.REST_URL + '/';

    @Autowired
    private VoteRepository voteRepository;

    @Test
    void createWithLocation() throws Exception {
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + "for?restaurantId=" + REST1_ID)
                .contentType(MediaType.APPLICATION_JSON)
//                .with(userHttpBasic(user1))
        )
                .andDo(print());
        Vote newVote = VoteTestData.newVote;

        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteRepository.getOne(newId), newVote);
    }

    @Test
    void createWithLocationIfExist() throws Exception {
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + "for?restaurantId=" + REST3_ID)
                        .contentType(MediaType.APPLICATION_JSON)
//                .with(userHttpBasic(user2))
        )
                .andDo(print());
        Vote newVote = VoteTestData.newVote2;

        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteRepository.getOne(newId), newVote);
    }

    @Test
    void update() throws Exception {
    }

    @Test
    void getResultsToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "today")
//                .with(userHttpBasic(user1))
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_RESULTS_MATCHER.contentJson(voteResultToday));
    }
}