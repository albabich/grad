package com.albabich.grad.util;

import com.albabich.grad.model.Vote;
import com.albabich.grad.to.VoteTo;

import java.util.Collection;
import java.util.List;

public class VoteUtil {

    public static List<VoteTo> getTos(Collection<Vote> votes) {
        return votes.stream()
                .map(VoteUtil::createTo)
                .toList();
    }

    public static VoteTo createTo(Vote vote) {
        return new VoteTo(vote.getId(), vote.getRestaurant().getId());
    }
}
