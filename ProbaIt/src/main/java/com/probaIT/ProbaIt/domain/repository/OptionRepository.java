package com.probaIT.ProbaIt.domain.repository;

import com.probaIT.ProbaIt.domain.entities.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {

    List<Option> findByPollId(Long pollId);


    @Override
    void deleteById(Long aLong);
}
