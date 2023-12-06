package com.probaIT.ProbaIt.domain.repository;

import com.probaIT.ProbaIt.domain.entities.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PollRepository extends JpaRepository<Poll, Long> {

    Poll findByQuestion(String question);

    List<Poll> findByUserId(Long userId);

    @Override
    void deleteById(Long aLong);
}
