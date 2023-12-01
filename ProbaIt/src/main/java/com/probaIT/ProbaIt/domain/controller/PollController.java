package com.probaIT.ProbaIt.domain.controller;

import com.probaIT.ProbaIt.domain.entities.Poll;
import com.probaIT.ProbaIt.domain.service.PollService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500/")
@RestController
@RequiredArgsConstructor
public class PollController {

    private final PollService service;

    @GetMapping(value = "/polls")
    public List<Poll> getAllPolls() {
        return service.getAllPolls();
    }

    @GetMapping(value = "/userPolls")
    public List<Poll> getAllPollsOfAUser(@RequestParam(value = "userId") Long userId) {
        return service.getAllPollsOfAUser(userId);
    }

    @GetMapping(value = "pollById")
    public Poll getPollById(@RequestParam(value = "id") Long id) {
        return service.getPollById(id);
    }

    @GetMapping(value = "pollByQuestion")
    public Poll getPollByQuestion(@RequestParam(value = "question") String question) {
        return service.findByQuestion(question);
    }

    @PostMapping(value = "poll")
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll) {
        Poll createdPoll = service.createPoll(poll);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPoll);
    }

    @PutMapping(value = "poll")
    public Poll updatePoll(@RequestBody Poll poll) {
        return service.updatePoll(poll);
    }

    @DeleteMapping(value = "poll")
    public void deletePoll(@RequestParam(value = "pollId") Long pollId, @RequestParam(value = "userId") Long userId) {
        service.removePoll(pollId, userId);
    }

}
