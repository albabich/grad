package com.albabich.grad.util;

import com.albabich.grad.model.Vote;
import com.albabich.grad.to.VoteTo;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class VoteUtil {

    public static List<VoteTo> getTos(Collection<Vote> votes) {
        return votes.stream()
                .map(VoteUtil::createTo)
                .collect(Collectors.toList());
    }

    public static VoteTo createTo(Vote vote) {
        return new VoteTo( vote.getDate(), vote.getRestaurant().getName());
    }

    public static Map<String, Long> getResults(List<VoteTo> allByDate) {
        return allByDate.stream()
                .collect(groupingBy(VoteTo::getRestaurantName, counting()));
    }
}
