package com.albabich.grad.web.vote;

import com.albabich.grad.AuthorizedUser;
import com.albabich.grad.model.Vote;
import com.albabich.grad.repository.RestaurantRepository;
import com.albabich.grad.repository.UserRepository;
import com.albabich.grad.repository.VoteRepository;
import com.albabich.grad.to.VoteTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;

import static com.albabich.grad.util.ValidationUtil.checkChangeVoteAbility;

@RestController
@RequestMapping(value = ProfileVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileVoteController {
    static final String REST_URL = "/rest/profile/votes";

    private static final Logger log = LoggerFactory.getLogger(ProfileVoteController.class);

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public ProfileVoteController(VoteRepository voteRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    @PostMapping()
    public ResponseEntity<Vote> createWithLocation(@RequestBody @Valid VoteTo voteTo, @AuthenticationPrincipal AuthorizedUser authUser) {

        int userId = authUser.getId();
        int restaurantId = voteTo.getRestaurantId();
        Vote vote = new Vote(null, LocalDate.now(), restaurantRepository.getById(restaurantId));

        log.info("create vote {} for user {} for restaurant {}", vote, userId, restaurantId);

        vote.setUser(userRepository.getById(userId));

        Vote todayVote = voteRepository.getByDateAndUser(LocalDate.now(), userId);
        if (todayVote != null) {
            checkChangeVoteAbility();
            vote.setId(todayVote.id());
        }

        Vote created = voteRepository.save(vote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
