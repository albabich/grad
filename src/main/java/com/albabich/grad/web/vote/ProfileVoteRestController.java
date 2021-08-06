package com.albabich.grad.web.vote;

import com.albabich.grad.model.Vote;
import com.albabich.grad.repository.RestaurantRepository;
import com.albabich.grad.repository.UserRepository;
import com.albabich.grad.repository.VoteRepository;
import com.albabich.grad.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

import static com.albabich.grad.util.ValidationUtil.*;

@RestController
@RequestMapping(value = ProfileVoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileVoteRestController {
    static final String REST_URL = "/rest/profile/votes";

    private static final Logger log = LoggerFactory.getLogger(ProfileVoteRestController.class);

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public ProfileVoteRestController(VoteRepository voteRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    @PostMapping(value = "/for")
    public ResponseEntity<Vote> createWithLocation(@RequestParam int restaurantId) {
        checkVoteAbility();
        int userId = SecurityUtil.authUserId();
        Vote vote = new Vote(null, LocalDate.now());

        log.info("create vote {} for user {} for restaurant {}", vote, userId, restaurantId);
        vote.setUser(userRepository.getOne(userId));
        vote.setRestaurant(checkNotFoundWithId(restaurantRepository.findById(restaurantId).orElse(null),restaurantId));

        Vote todayVote = voteRepository.getByDateAndUser(LocalDate.now(), userId);
        if (todayVote != null) {
            vote.setId(todayVote.id());
        }

        Vote created = voteRepository.save(vote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
