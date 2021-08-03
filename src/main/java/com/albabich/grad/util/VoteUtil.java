package com.albabich.grad.util;

import com.albabich.grad.model.Vote;
import com.albabich.grad.to.VoteTo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class VoteUtil {

    public static List<VoteTo> getTos(Collection<Vote> votes) {
        return votes.stream()
                .map(VoteUtil::createTo)
                .collect(Collectors.toList());
    }

    public static VoteTo createTo(Vote vote) {
        return new VoteTo(vote.getRestaurant().getName());
    }
}
