package com.albabich.grad.web.vote;

import com.albabich.grad.model.Vote;
import com.albabich.grad.repository.UserRepository;
import com.albabich.grad.repository.VoteRepository;
import com.albabich.grad.util.VoteUtil;
import com.albabich.grad.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.Map;

import static com.albabich.grad.util.ValidationUtil.*;

@RestController
@RequestMapping(value = ProfileVoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileVoteRestController {
    static final String REST_URL = "/rest/profile/votes";

    private static final Logger log = LoggerFactory.getLogger(ProfileVoteRestController.class);

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;

    public ProfileVoteRestController(VoteRepository voteRepository, UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/today")
    public Map<String, Long> getResultsToday() {
        log.info("getResultsByDate {}", LocalDate.now());
        return VoteUtil.getResults(VoteUtil.getTos(voteRepository.findAllByDate(LocalDate.now())));
    }

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@Valid @RequestBody Vote vote) {
        checkVoteAbility();
        int userId = SecurityUtil.authUserId();
        log.info("create vote {} for user {}", vote, userId);
        checkNew(vote);
        Assert.notNull(vote, "vote must not be null");
        vote.setDate(LocalDate.now());
        vote.setUser(userRepository.getOne(userId));

        Vote todayVote = voteRepository.getByDateAndUser(LocalDate.now(), userId);
        if (todayVote != null) {
            update(vote, todayVote.id());
            return null;
        }

        Vote created = voteRepository.save(vote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Transactional
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Vote vote, @PathVariable int id) {
        checkVoteAbility();
        int userId = SecurityUtil.authUserId();
        log.info("update vote {} for user {}", vote, userId);
        Assert.notNull(vote, "vote must not be null");
        assureIdConsistent(vote, id);
        if (get(vote.getId(), userId) != null) {
            vote.setDate(LocalDate.now());
            vote.setUser(userRepository.getOne(userId));
            voteRepository.save(vote);
        }
    }

    public Vote get(int id, int userId) {
        return voteRepository.findById(id)
                .filter(vote -> vote.getUser().getId() == userId)
                .orElse(null);
    }
}
