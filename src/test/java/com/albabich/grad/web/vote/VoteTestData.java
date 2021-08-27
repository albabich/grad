package com.albabich.grad.web.vote;

import com.albabich.grad.model.Vote;
import com.albabich.grad.web.MatcherFactory;

import java.time.Month;
import java.util.List;

import static com.albabich.grad.model.AbstractBaseEntity.START_SEQ;
import static com.albabich.grad.web.restaurant.RestaurantTestData.*;
import static com.albabich.grad.web.user.UserTestData.user1;
import static com.albabich.grad.web.user.UserTestData.user2;
import static java.time.LocalDate.now;
import static java.time.LocalDate.of;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "user", "restaurant");

    public static final int VOTE1_ID = START_SEQ + 22;

    public static final Vote vote1 = new Vote(VOTE1_ID, now(), rest1);
    public static final Vote vote2 = new Vote(VOTE1_ID + 1, of(2021, Month.APRIL, 5), rest2);
    public static final Vote vote3 = new Vote(VOTE1_ID + 2, now(), rest2);
    public static final Vote vote4 = new Vote(VOTE1_ID + 3, now(), rest3);
    public static final Vote vote5 = new Vote(VOTE1_ID + 4, of(2021, Month.APRIL, 6), rest2);
    public static final Vote vote6 = new Vote(VOTE1_ID + 5, of(2021, Month.APRIL, 6), rest3);

    public static Vote getNew() {
        return new Vote(null, now());
    }

    public static final Vote newVote = getNew();
    public static final Vote newVote2 = getNew();

    public static final List<Vote> voteRest1Today = List.of(vote1);
    public static final List<Vote> voteRest2Today = List.of(vote2);
    public static final List<Vote> voteRest3Today = List.of(vote4);

    static {
        newVote.setUser(user1);
        newVote.setRestaurant(rest1);
        newVote2.setUser(user2);
        newVote2.setRestaurant(rest3);
    }
}
