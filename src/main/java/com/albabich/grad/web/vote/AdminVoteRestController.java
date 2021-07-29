package com.albabich.grad.web.vote;

import com.albabich.grad.model.Vote;
import com.albabich.grad.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = AdminVoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminVoteRestController {
     static final String REST_URL =  "/rest/admin/votes";

    private static final Logger log = LoggerFactory.getLogger(AdminVoteRestController.class);
    private VoteRepository voteRepository;

    public AdminVoteRestController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }
@GetMapping("/{id}")
    public Vote getWithUser( @PathVariable int id) {
        return voteRepository.getWithUser(id);
    }
}
