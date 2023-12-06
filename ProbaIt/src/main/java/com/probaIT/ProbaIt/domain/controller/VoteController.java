package com.probaIT.ProbaIt.domain.controller;

import com.probaIT.ProbaIt.domain.entities.Vote;
import com.probaIT.ProbaIt.domain.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500/")
@RestController
@RequiredArgsConstructor
public class VoteController {

    private final VoteService service;

    @GetMapping(value = "testVote")
    public Vote getVoteById(@RequestParam(name = "id") Long id) {
        return service.findByVoteId(id);
    }

    @GetMapping(value = "countVotes")
    public Long countVotes(@RequestParam(name = "optionId") Long id) {
        return service.countVotesOfAnOption(id);
    }

    @PostMapping(value = "vote")
    public Vote vote(@RequestBody Vote vote) {
        return service.createVote(vote);
    }

    @PostMapping(value = "unvote")
    public void unVote(@RequestBody Vote vote) {
        service.unVote(vote);
    }


}
