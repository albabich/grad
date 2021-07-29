package com.albabich.grad.web;

import com.albabich.grad.model.Vote;
import com.albabich.grad.to.VoteTo;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import static com.albabich.grad.model.AbstractBaseEntity.START_SEQ;
import static com.albabich.grad.web.RestaurantTestData.*;
import static java.time.LocalDate.now;
import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;

public class VoteTestData {
    public static MatcherFactory.Matcher<VoteTo> VOTE_TO_MATCHER = MatcherFactory.usingEqualsComparator(VoteTo.class);
    public static MatcherFactory.Matcher<Map> VOTE_RESULTS_MATCHER = MatcherFactory.usingAssertions(Map.class,
            (a, e) -> assertThat(a).isEqualTo(e),
            (a, e) -> assertThat(a).isEqualTo(e));
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "user", "restaurant.menuItems");

    public static final int VOTE1_ID = START_SEQ + 22;
    public static final int NOT_FOUND = 10;

    public static final Vote vote1 = new Vote(VOTE1_ID, of(2021, Month.MARCH, 1), rest1);
    public static final Vote vote2 = new Vote(VOTE1_ID + 1, now(), rest2);
    public static final Vote vote3 = new Vote(VOTE1_ID + 2, now(), rest2);
    public static final Vote vote4 = new Vote(VOTE1_ID + 3, now(), rest3);
    public static final Vote vote5 = new Vote(VOTE1_ID + 4, of(2021, Month.APRIL, 6), rest2);
    public static final Vote vote6 = new Vote(VOTE1_ID + 5, of(2021, Month.APRIL, 6), rest3);

    public static Vote getNew() {
        return new Vote(null, now(), rest1);
    }

    public static final Map<String, Integer> voteResultToday = new HashMap<>();

    static {
        voteResultToday.put("Munhell", 2);
        voteResultToday.put("Kwakinn", 1);
    }
}
