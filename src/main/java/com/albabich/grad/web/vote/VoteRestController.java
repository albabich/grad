package com.albabich.grad.web.vote;

import com.albabich.grad.repository.VoteRepository;
import com.albabich.grad.to.VoteTo;
import com.albabich.grad.util.VoteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    static final String REST_URL = "/rest/votes";

    private static final Logger log = LoggerFactory.getLogger(VoteRestController.class);

    private final VoteRepository voteRepository;

    public VoteRestController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @GetMapping("/today")
    public List<VoteTo> getToday() {
        log.info("getByDate {}", LocalDate.now());
        return VoteUtil.getTos(voteRepository.findAllByDate(LocalDate.now()));
    }
}
