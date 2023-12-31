package com.probaIT.ProbaIt.domain.service;

import com.probaIT.ProbaIt.domain.entities.Poll;
import com.probaIT.ProbaIt.domain.repository.PollRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PollService {

    private final PollRepository repository;
    private final OptionService optionService;
    private final VoteService voteService;

    public Poll createPoll(Poll poll) {
        return repository.save(poll);
    }

    public Poll getPollById(Long id) {
        return repository.getById(id);
    }

    public Poll findByQuestion(String question) {
        return repository.findByQuestion(question);
    }

    public List<Poll> getAllPolls() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public List<Poll> getAllPollsOfAUser(Long id) {
        return repository.findByUserId(id);
    }

    public void removePoll(Long userId, Long pollId) {
        Poll pollToBeDeleted = repository.getById(pollId);
        if (pollToBeDeleted.getUser().getId() == userId) {
            voteService.removeAllVotesByPollId(pollId);
            optionService.removeOptionByPollId(pollId);
            repository.deleteById(pollId);
        }
    }

    public Poll updatePoll(Poll poll) {
        return repository.save(poll);
    }

}
