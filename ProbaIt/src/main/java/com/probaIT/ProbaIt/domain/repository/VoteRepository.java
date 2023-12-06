package com.probaIT.ProbaIt.domain.repository;

import com.probaIT.ProbaIt.domain.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findByUserId(Long userId);

    List<Vote> findByPollId(Long pollId);

    List<Vote> findByOptionId(Long optionId);

    long countByOption_Id(long id);

    Vote findByUserIdAndPollIdAndOptionId(Long userId, Long pollId, Long optionId);

    @Override
    void deleteById(Long id);
}
