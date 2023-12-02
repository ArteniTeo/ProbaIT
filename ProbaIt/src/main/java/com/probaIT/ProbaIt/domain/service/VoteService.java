package com.probaIT.ProbaIt.domain.service;

import com.probaIT.ProbaIt.domain.entities.Vote;
import com.probaIT.ProbaIt.domain.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository repository;

    public Vote createVote(Vote vote) {
        Vote checkIfExist = repository.findByUserIdAndPollIdAndOptionId(vote.getUser().getId(), vote.getPoll().getId(), vote.getOption().getId());
        if (checkIfExist != null) return new Vote(0L);
        else return repository.save(vote);
    }

    public Vote findByVoteId(Long id) {
        return repository.getById(id);
    }

    public List<Vote> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public List<Vote> findByPollId(Long pollId) {
        return repository.findByPollId(pollId);
    }

    public List<Vote> findByOptionId(Long optionId) {
        return repository.findByOptionId(optionId);
    }

    public void unVote(Vote vote) {
        repository.delete(vote);
    }


}
