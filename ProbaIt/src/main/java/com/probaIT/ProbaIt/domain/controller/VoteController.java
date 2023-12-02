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

    @GetMapping(value = "getVotes")
    public List<Vote> getVotesOfAnOption(@RequestParam(name = "id") Long id) {
        return service.findByOptionId(id);
    }

    @GetMapping(value = "usersVotes")
    public List<Vote> getVotesOfAnUser(@RequestParam(name = "id") Long id) {
        return service.findByUserId(id);
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
